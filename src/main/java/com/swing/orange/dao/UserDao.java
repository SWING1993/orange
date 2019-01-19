package com.swing.orange.dao;

import com.swing.orange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByPhone(String phone);
    User findById(long id);
}
