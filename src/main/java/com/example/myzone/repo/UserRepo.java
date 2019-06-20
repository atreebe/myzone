package com.example.myzone.repo;

import com.example.myzone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {
    public List<User> findByUserName(String userName);
    public User findByUserId(int id);
}
