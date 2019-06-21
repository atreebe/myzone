package com.example.myzone.controller;

import com.example.myzone.entity.Comment;
import com.example.myzone.entity.News;
import com.example.myzone.entity.User;
import com.example.myzone.repo.CommentRepo;
import com.example.myzone.repo.NewsRepo;
import com.example.myzone.repo.UserRepo;
import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class CommentController {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private UserRepo userRepo;
    @RequestMapping(value = "/admin/comment",method = RequestMethod.GET)
    public String findAdminComment(Model model,HttpSession session) {
        List<Comment> comment = commentRepo.findDescComment();

        int flag = 0;
        String commentcontent = "给个评论";
        model.addAttribute("flag", flag);
        model.addAttribute("commentcontent", commentcontent);
        model.addAttribute("comment", comment);
        model.addAttribute("username",session.getAttribute("username").toString());
        return "admin/commentinfo";
    }

    //查询评论
    @RequestMapping(value = "/admin/comment",method = RequestMethod.PATCH)
    public String queryComment(String commentContent, Model model, HttpSession session){
        List<Comment> comment = commentRepo.findDescComment();
        List<Comment> Comment = new ArrayList<Comment>();
        if(commentContent.isEmpty())
        {
            Comment = commentRepo.findDescComment();
        }
        else {
            String querytext = '%' + commentContent + '%';
            Comment = commentRepo.queryComment(querytext);
        }
        int flag = 1;
        model.addAttribute("flag",flag);
        model.addAttribute("commentcontent",commentContent);
        model.addAttribute("comment",Comment);
        User nowuser = userRepo.findByUserName(session.getAttribute("username").toString());
        model.addAttribute("nowuser",nowuser);
        return "/admin/commentinfo";
    }

    //删除操作
    @RequestMapping(value = "/admin/comment/{id}",method = RequestMethod.DELETE)
    public String deletenews(@PathVariable("id") Integer id, Model model){
        String result = "评论删除成功";
        String href = "/admin/comment";
        Comment comment =commentRepo.findByCommentId(id);
        News news = newsRepo.findByNewsId(comment.getCommentNewsId());
        news.setNewsComment(news.getNewsComment()-1);
        newsRepo.save(news);
        try{
            commentRepo.delete(comment);
            model.addAttribute("result",result);
            model.addAttribute("hre",href);
            return "/success";
        }catch(Exception e){
            e.printStackTrace();
            result = "评论删除失败！";
            href = "/admin/news";
            model.addAttribute("result",result);
            model.addAttribute("hre",href);
            return "/success";
        }
    }
}

