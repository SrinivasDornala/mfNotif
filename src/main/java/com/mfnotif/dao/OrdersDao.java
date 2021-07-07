package com.mfnotif.dao;

import com.mfnotif.model.Order;
import com.mfnotif.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(long id);
}