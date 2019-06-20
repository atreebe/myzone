package com.example.myzone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.myzone.dao.Userdao;
import com.example.myzone.entity.User;

@Service
public class LoginService {
    @Autowired
    private Userdao userdao;
    public User getUser(String username,String password){
        return userdao.findByUserNameAndUserPassword(username,password);
    }
//    public boolean verifyLogin(User user){
//        List<User> userList = userdao.findByUserNameAndUserPassword(user.getUserName(),user.getUserPassword());
//        return userList.size()>0;
//    }
}
