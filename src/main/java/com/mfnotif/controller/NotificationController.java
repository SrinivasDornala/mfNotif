package com.mfnotif.controller;

import com.mfnotif.cache.LoadCache;
import com.mfnotif.cache.MFCache;
import com.mfnotif.dao.NotificationDao;
import com.mfnotif.dao.OrdersDao;
import com.mfnotif.dao.UserDao;
import com.mfnotif.model.Notification;
import com.mfnotif.model.Order;
import com.mfnotif.model.User;
import com.mfnotif.util.MFutil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
public class NotificationController {
	
	@Autowired
    UserDao userDao;

    @Autowired
    NotificationDao notificationDao;

    @Autowired
    OrdersDao ordersDao;

    @PostMapping("/post")
    public @ResponseBody ResponseEntity<String> post() {
        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }
    @PostMapping("/index")
    public String index(){
     return "index";
   }
   
   @PostMapping("/notification/{id}")
   public ResponseEntity<Notification> createNotification(@PathVariable String id, @RequestBody Notification model){
       MFCache.getCache().put(id,model);
       notificationDao.save(model);
       LoadCache.addtoQueue(MFutil.toMessage(model));
	   return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(model);
   }

    @PostMapping("/notifies/{id}")
    public ResponseEntity<Notification> updateNotifiaction(@PathVariable String id, @RequestBody Notification model){
        MFCache.getCache().put(id,model);
        notificationDao.save(model);
        LoadCache.addtoQueue(MFutil.toMessage(model));
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(model);
    }

    @PostMapping("/notice/{id}/{type}")
    public ResponseEntity<Notification> notify(@PathVariable Long id, @PathVariable String type){
        Optional<Notification> op=notificationDao.findById(id);
        log.info(">>>.Optional {}",op);
        Notification notification= null;
        if(op.isPresent()) {
            notification= op.get();
            notification.setMethod(type);
            notification.setNotify("Y");
            Notification n= notificationDao.save(MFutil.toNotification(notification));
            MFCache.getCache().put(""+id, n);
            LoadCache.addtoQueue(MFutil.toMessage(n));
            Optional<Order> order=ordersDao.findById(id);
            if(order.isPresent()) {
                order.get().setNotify("Y");
                ordersDao.save(order.get());
            }
            notification= n;
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(notification);
    }

   @GetMapping("/notifications")
   public ResponseEntity<List<Notification>> notifications(){
	   return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(notificationDao.findAll());
   }
    @GetMapping("/notify/{id}")
    public ResponseEntity<List<Notification>> notitifaction(@PathVariable long id){
        log.info("ID >>{}",id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(notificationDao.findAllByOrderId(id));
    }

   @GetMapping("/user/{id}")
   public ResponseEntity<User> getUser(@PathVariable long id){
        log.info("ID {}",id);
	   return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body((User)userDao.findById(id).get());
   }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable long id){
        log.info("ID >>{}",id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ordersDao.findAllByUserId(id));
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> users(){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(userDao.findAll());
    }
    @PostMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable User user){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(userDao.saveAndFlush(user));
    }
}
