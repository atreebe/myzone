package com.example.myzone.controller;

import com.example.myzone.entity.Comment;
import com.example.myzone.entity.News;
import com.example.myzone.entity.User;
import com.example.myzone.repo.CommentRepo;
import com.example.myzone.repo.NewsRepo;
import com.example.myzone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;
    @RequestMapping(value = {"/"})
    public String findindex(Model model) {
        return  "front/login";
    }

    @RequestMapping(value = "/news_detail")
    public String findDetailNews(Model model) {
        List<News> news = newsRepo.findAll();
        List<News> news1 = new ArrayList<News>();
        //对新闻表中的新闻信息进行过滤
        for (int i = 0; i < news.size(); i++) {
                news1.add(news.get(i));
        }
        model.addAttribute("news", news1);
        return "front/news_detail";
    }
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpSession session){
        session.removeAttribute("username");
        return "front/login";
    }
    @RequestMapping("/dologin")
    public String doLogin(HttpServletRequest request, HttpSession session,Model model) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            password = new BigInteger(1, md.digest()).toString(16);
            System.out.println("输入密码加密后:"+password);
        }
        catch (Exception e) {
            throw new Exception("MD5加密出现错误，"+e.toString());
        }
        List<User> alluser = userRepo.findAll();
        String str = "redirect:/";
        for(int i = 0 ; i < alluser.size() ; i++) {
//            System.out.println("*************");
//            System.out.println(user.get(i).getUserName() + " " + user.get(i).getUserPassword());
            if(alluser.get(i).getUserName().equals(username) && alluser.get(i).getUserPassword().equals(password)){
                str="redirect:/index.html";
                session.setAttribute("username",username);
            }
        }
        return  str;
    }
}
