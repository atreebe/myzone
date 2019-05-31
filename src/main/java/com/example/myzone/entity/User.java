package com.example.myzone.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id//主码
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id自动增长
    @Column(name = "user_id",length = 8)
    private int userId;//用户编号
    @Column(name = "user_name",length = 256)
    private String userName;//用户名
    @Column(name = "user_password",length =256)
    private String userPassword;//密码
    @Column(name = "user_pic",length = 256)
    private String userPic;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}

