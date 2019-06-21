package com.example.myzone.repo;

import com.example.myzone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {

    @Query(value="Select * from user order by user_id desc;",nativeQuery = true)
    public List<User> findDescUser();

    public User findByUserName(String userName);
    public List<User> findByUserNickname(String userName);
    public User findByUserId(int id);
}
