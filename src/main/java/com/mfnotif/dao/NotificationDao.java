package com.mfnotif.dao;

import com.mfnotif.model.Notification;
import com.mfnotif.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Long> {
    List<Notification> findAllByOrderId(long id);
}
