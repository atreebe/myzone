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
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @RequestMapping(value = {"/regist"})
    public String GoRegist(Model model) {
        return  "front/regist";
    }

    @RequestMapping(value = "/userregist" ,method = RequestMethod.POST)
    public String UserRegist(Model model, User user, MultipartFile picUp) throws Exception {
        String resultStr = "注册成功,点击登录";
        String hrefStr = "/";
        String password = "";
        if(user.getUserName().equals("")){
            resultStr = "用户名称没有填写";
            hrefStr = "/regist";
            model.addAttribute("result", resultStr);
            model.addAttribute("hre", hrefStr);
            return "/success";
        }
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(user.getUserPassword().getBytes());
            System.out.println("加密前的密码为:"+user.getUserPassword());
            password = new BigInteger(1, md.digest()).toString(16);
            System.out.println("加密后的密码为:"+password);
            user.setUserPassword(password);
        }
        catch (Exception e) {
            throw new Exception("MD5加密出现错误，"+e.toString());
        }
        List<User> userslist = userRepo.findByUserNickname(user.getUserNickname());
        if(!userslist.isEmpty()){
            resultStr = "用户昵称称重复";
            hrefStr = "/regist";
            model.addAttribute("result", resultStr);
            model.addAttribute("hre", hrefStr);
            return "/success";
        }
        if (!picUp.isEmpty()) {
            //获取当前时间
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String tablename = dateFormat.format(now);
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
            //文件存入位置
            srcpath = srcpath + "/src/main/resources/static/file/image/user/";
            //获取随机图片名字
            String picfileName = picUp.getOriginalFilename();
            //获取图片后缀名
            String picsuffixName = picfileName.substring(picfileName.lastIndexOf("."));

            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = picUp.getContentType();
            try {
                //将图片文件存入项目文件中
                picUp.transferTo(new File(srcpath + "user" + tablename + picsuffixName));
                String dbpicname = "file/image/user/user" + tablename + picsuffixName;
                user.setUserPic(dbpicname);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        User userinfo = userRepo.save(user);
        if(userinfo == null)
            resultStr = "用户信息添加出错";
        model.addAttribute("result", resultStr);
        model.addAttribute("hre", hrefStr);
        return "/success";
    }

    @RequestMapping(value = "/admin/user",method = RequestMethod.GET)
    public String findAdminUser(Model model) {
        List<User> user = userRepo.findDescUser();
        model.addAttribute("user", user);
        return "admin/userinfo";
    }
    //删除操作
    @RequestMapping(value = "/admin/user/{id}",method = RequestMethod.DELETE)
    public String deletenews(@PathVariable("id") Integer id, Model model){
        String result = "用户删除成功";
        String href = "/admin/user";
        User user = userRepo.findByUserId(id);
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        //提取项目文件路径
        String srcpath = "";
        for (int i = 0; i < path.length(); i++) { //去掉target文件目录
            if (path.charAt(i) == '/' && path.charAt(i + 1) == 't' && path.charAt(i + 2) == 'a' && path.charAt(i + 3) == 'r' && path.charAt(i + 4) == 'g') {
                break;
            } else {
                srcpath += path.charAt(i);
            }
        }
        //文件存入位置
        srcpath = srcpath + "/src/main/resources/static/" + user.getUserPic();
//        if(user.getUserPic().length()==0)
//        {
//            result = "无用户图片";
//        }
//        else
        {
            File file = new File(srcpath);
            if (file.exists()) {
                if (file.delete()) {
                    result=  "图片删除成功";
                } else {
                    result =  "图片删除失败";
                }
            } else {
                result = "图片不存在！";
            }
        }
        try{
            userRepo.delete(user);
            model.addAttribute("result",result);
            model.addAttribute("hre",href);
            return "/success";
        }catch(Exception e){
            e.printStackTrace();
            result = "用户删除失败！";
            href = "/admin/news";
            model.addAttribute("result",result);
            model.addAttribute("hre",href);
            return "/success";
        }
    }

}
