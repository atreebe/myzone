package com.example.myzone.controller;

import com.example.myzone.entity.News;
import com.example.myzone.repo.NewsRepo;
import com.example.myzone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private UserRepo userRepo;
    @RequestMapping(value = {"/", "login"})
    public String findindex(Model model) {
        return  "front/login";
    }

    @RequestMapping(value = {"/dologin"})
    public String GoIndex(Model model) {
        List<News> news = newsRepo.findAll();
        model.addAttribute("news",news);
        return  "front/index";
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
}
