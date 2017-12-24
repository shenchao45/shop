package com.wzh.shop.repository;

import com.wzh.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("from User u where u.username = ?1 and u.password = ?2")
    User findByUsernameAndPassword(String username, String password);
}
