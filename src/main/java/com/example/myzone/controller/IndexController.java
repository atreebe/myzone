package com.example.myzone.controller;

import com.example.myzone.entity.Comment;
import com.example.myzone.entity.News;
import com.example.myzone.repo.CommentRepo;
import com.example.myzone.repo.NewsRepo;
import com.example.myzone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String GoIndex(Model model) {
        List<News> news = newsRepo.findAll();
        model.addAttribute("news",news);
        List<Comment> comment = commentRepo.findAll();
        model.addAttribute("comment",comment);
        return  "front/index";
    }

    @RequestMapping(value = {"/addcomment"},method = RequestMethod.POST)
    public String addcomment(Model model, Comment comment) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String tabletime = dateFormat.format(now);

        comment.setCommentTime(now);
        comment.setCommentGood(0);
        Comment commentinfo = commentRepo.save(comment);
        if(comment == null)
            System.out.println("添加评论出错");
        return  "redirect:/index.html";
    }
}
