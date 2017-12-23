package com.wzh.shop.repository;


import com.wzh.shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderItem,Integer> {
}
