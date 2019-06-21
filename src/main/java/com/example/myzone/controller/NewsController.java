package com.example.myzone.controller;

import com.example.myzone.entity.News;
import com.example.myzone.entity.User;
import com.example.myzone.repo.CommentRepo;
import com.example.myzone.repo.NewsRepo;
import com.example.myzone.repo.NewsRepo;
import com.example.myzone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping
public class NewsController {
    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;

    //来到添加页面
    @RequestMapping(value = "/addnews",method = RequestMethod.GET)
    public String toAddPage(Model model, HttpSession session){
        User nowuser = userRepo.findByUserName(session.getAttribute("username").toString());
        model.addAttribute("nowuser",nowuser);
        return "front/newsadd";
    }

    //对消息进行添加
    @RequestMapping(value = "/addnews",method = RequestMethod.POST)
    public String addnews(News news, Model model,MultipartFile[] picUp) throws FileNotFoundException {
        String resultStr = "动态添加成功！";
        String hrefStr = "/index.html";
        Date now = new Date();

        news.setNewsGood(0);
        news.setNewsTime(now);
        News newsinfo = newsRepo.save(news);
        if(newsinfo == null)
            resultStr = "动态添加出错";
        model.addAttribute("result", resultStr);
        model.addAttribute("hre", hrefStr);
        return "/success";
    }

    @RequestMapping(value = "/admin/addnews/fileup")
    @ResponseBody
    public String upfile(MultipartFile imgFile) throws FileNotFoundException{
        //对文件图片进行上传
        if (!imgFile.isEmpty()) {
            //获取当前时间
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String tablename = dateFormat.format(now);

            //获取原文件名
//            MultipartHttpServletRequest request;
//            CommonsMultipartFile multipartFile = null;
//            multipartFile = (CommonsMultipartFile)request.getfile(picUp);

            //获取项目的绝对路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            //提取项目文件路径
            String srcpath = "";
            for (int i = 0; i < path.length(); i++) {
                if (path.charAt(i) == '/' && path.charAt(i + 1) == 't' && path.charAt(i + 2) == 'a' && path.charAt(i + 3) == 'r' && path.charAt(i + 4) == 'g') {
                    break;
                } else {
                    srcpath += path.charAt(i);
                }
            }
            //获取某个文件路径
            String serverpath = ResourceUtils.getURL("classpath:static").getPath();
            //获取target路径
            String basePath = new ApplicationHome(this.getClass()).getSource().getParentFile().getPath();

            //target里面的path
            path = path + "static/file/image/news/";
            srcpath = srcpath + "/src/main/resources/static/file/image/news/";
            //获取随机图片名字
            String picfileName = imgFile.getOriginalFilename();
            //获取图片后缀名
            String picsuffixName = picfileName.substring(picfileName.lastIndexOf("."));

            //另一种随机图片名字的方法
            picfileName = UUID.randomUUID() + picsuffixName;
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = imgFile.getContentType();
            //获得文件后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            try {
                //将图片文件存入项目文件中
                imgFile.transferTo(new File(srcpath + "news" + tablename + picsuffixName));
                String dbpicname = "file/image/news/news" + tablename + picsuffixName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "/success";
    }

    @ResponseBody
    @RequestMapping("/img")
    public Map<String,Object> uploadImgQiniu(@RequestParam("files") MultipartFile
                                                     file) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String picpath ="";
        if (!file.isEmpty()) {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String tablename = dateFormat.format(now);
//            System.out.println(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
//            // 获取后缀
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            //提取项目文件路径
            String srcpath = "";
            for (int i = 0; i < path.length(); i++) {
                if (path.charAt(i) == '/' && path.charAt(i + 1) == 't' && path.charAt(i + 2) == 'a' && path.charAt(i + 3) == 'r' && path.charAt(i + 4) == 'g') {
                    break;
                } else {
                    srcpath += path.charAt(i);
                }
            }
            //获取某个文件路径
            String serverpath = ResourceUtils.getURL("classpath:static").getPath();
            //获取target路径
            String basePath = new ApplicationHome(this.getClass()).getSource().getParentFile().getPath();
            srcpath = srcpath + "/src/main/resources/static/file/image/news/contentpic/";
            // 文件上传的路径
//            String filePath = "C:/Users/ASUS/Desktop/file/";
            // fileName处理
            picpath += "news" + tablename +suffixName;
            fileName = srcpath + "news" + tablename +suffixName;
            // 文件对象
            File dest = new File(fileName);
            // 创建路径
            System.out.println("----");
            System.out.println(dest);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
                System.out.println("no"+dest);
            }
            try {
                //保存文件
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        map.put("filename","../../file/image/news/" + "news20190510134605.jpg");
//        map.put("filename","../../file/image/news/contentpic/" + picpath);
        map.put("filename","../../file/image/news5.jpeg"); //默认图片
        return map;
    }

    @RequestMapping(value = "/admin/news",method = RequestMethod.GET)
    public String findAdminNews(Model model) {
        List<News> news = newsRepo.findDescNews();

        int flag = 0;
        String newscontent = "心情美丽";
        model.addAttribute("flag", flag);
        model.addAttribute("newscontent", newscontent);
        model.addAttribute("news", news);
        return "admin/newsinfo";
    }
    //查询动态内容题目
    @RequestMapping(value = "/admin/news",method = RequestMethod.PATCH)
    public String queryNews(String newsContent,Model model){
        List<News> news = newsRepo.findDescNews();
        List<News> News = new ArrayList<News>();
        if(newsContent.isEmpty())
        {
            News = newsRepo.findDescNews();
        }
        else {
            String querytext = '%' + newsContent + '%';
            News = newsRepo.queryNews(querytext);
        }
        int flag = 1;
        model.addAttribute("flag",flag);
        model.addAttribute("newscontent",newsContent);
        model.addAttribute("news",News);
        return "/admin/newsinfo";
    }

    //删除操作
    @RequestMapping(value = "/admin/news/{id}",method = RequestMethod.DELETE)
    public String deletenews(@PathVariable("id") Integer id, int[] newsId, Model model){
        String result = "动态消息删除成功";
        String href = "/admin/news";
        News news = newsRepo.findByNewsId(id);
        try{
            newsRepo.delete(news);
            model.addAttribute("result",result);
            model.addAttribute("hre",href);
            return "/success";
        }catch(Exception e){
            e.printStackTrace();
            result = "动态消息删除失败！";
            href = "/admin/news";
            model.addAttribute("result",result);
            model.addAttribute("hre",href);
            return "/success";
        }
    }

}
