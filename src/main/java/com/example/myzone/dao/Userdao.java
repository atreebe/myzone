package com.example.myzone.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.myzone.entity.User;

@Repository
public interface Userdao extends CrudRepository<User,Long> {
    public User findByUserNameAndUserPassword(String name, String password);
}
