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
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class IndexController {
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;

    @RequestMapping(value = {"/index.html"})
    public String GoIndex(Model model,HttpSession session) {
        List<News> news = newsRepo.findAll();
        model.addAttribute("news",news);
        List<Comment> comment = commentRepo.findAll();
        model.addAttribute("comment",comment);
        List<User> user = userRepo.findAll();
        model.addAttribute("user",user);

        User nowuser = userRepo.findByUserName(session.getAttribute("username").toString());
        model.addAttribute("nowuser",nowuser);
        return  "front/index";
    }

    @RequestMapping(value = {"/addcomment"},method = RequestMethod.POST)
    public String addcomment(Model model, Comment comment) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String tabletime = dateFormat.format(now);
//        System.out.println(tabletime+":" +comment.getCommentContent());
        comment.setCommentTime(now);
        comment.setCommentGood(0);
        Comment commentinfo = commentRepo.save(comment);
        News news = newsRepo.findByNewsId(comment.getCommentNewsId());
        news.setNewsComment(news.getNewsComment()+1);
        newsRepo.save(news);
        if(commentinfo == null)
            System.out.println("添加评论出错");
        return  "redirect:/index.html";
    }
    @RequestMapping(value = {"/givelike"})
    public String GiveLike(Model model, HttpSession session,int newsId) {
        News news = newsRepo.findByNewsId(newsId);
        news.setNewsGood(news.getNewsGood()+1);
        newsRepo.save(news);
        return  "redirect:/index.html";
    }

}
