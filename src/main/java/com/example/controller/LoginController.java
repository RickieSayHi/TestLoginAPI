package com.example.controller;

import com.example.entity.User;
import com.example.entity.vo.LoginResultVo;
import com.example.mapper.UserMapper;
import com.example.util.MD5Util;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class LoginController {
    @GetMapping("/showDemo")
    public String showDemo() {
        System.out.println("打印日志===========");
        return "这是一个demo";
    }

    String md5String = "1234567890ABCDEFG";

    @Resource
    UserMapper userMapper;

    /* 测试接口，返回一个字符串 */
    @GetMapping("/test")
    public String getTest() {
        return "user";
    }

    /* 测试接口，从数据库中查询数据，并返回数据 */
    @GetMapping("/all")
    public List<User> getUser() {
        return userMapper.getAll();
    }

    /* 测试接口，向数据库中插入数据 */
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userMapper.save(user);
        return "success";
    }

    /* 测试接口，根据传参，查询数据库中的数据 */
    @PostMapping("/getLoginInfo")
    public String loginuser(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd) {
        userMapper.findByUsernameAndUserpwd(userName, userPwd);
        return "success";
    }

    /* 收取验证码接口 */
    @GetMapping("getCode")
    public String getCode(@RequestParam("phoneNumber") String phoneNumber) {
        return "1234";
    }

    /* 忘记密码，修改密码接口 */
    @PostMapping("/forgetPwd")
    public String forgetPwd(@RequestParam("userName") String userName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("verifyCode") String verifyCode) {
        return "success";
    }

    /* 注册接口 */
    @PostMapping("/register")
    public String register(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd) {
        // 使用 MD5 对用户输入的密码进行加密
        String md5Pwd = MD5Util.md5(userPwd, md5String);
        System.out.println("加密后的密码： ===  " + md5Pwd);
        // 将加密后的 用户名和密码 存入数据库
        userMapper.register(userName, md5Pwd);
        return "success";
    }

    /* 登录接口 */
    @PostMapping("/login")
    public LoginResultVo loginAccount(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd) {
        LoginResultVo loginResultVo = new LoginResultVo();

        // 先查询数据库中的用户名，如果查到了用户名，再去比对密码，如果没有查到用户名，直接返回用户名或密码错误
        // 查询数据库，找到的用户名和密码
        User userFind = userMapper.findAccountByUserName(userName);
        if(userFind != null){
            // 将用户输入的密码 和 数据库中的密文密码进行比较
            Boolean isRight = MD5Util.verify(userPwd, md5String, userFind.getUserPwd());
            if (isRight) {
                loginResultVo.setCode(200);
                loginResultVo.setInfo("登录成功！");
            } else {
                loginResultVo.setCode(40001);
                loginResultVo.setInfo("用户名或密码错误，登录失败");
            }
        } else {
            loginResultVo.setCode(40001);
            loginResultVo.setInfo("用户名或密码错误，登录失败");
        }
        return loginResultVo;
    }



    /**
     * 错误的请求示范
     *  五个问题：
     * 1、请求参数写的不对，不应该那样请求接口
     * 2、错误代码，不可以和HTTP协议的代码一样，可以是5位或者6位
     * 3、错误提示不对，不应该提示 密码错误，应该是 账号或者密码错误
     * 4、在 catch 异常里面，应该返回错误代码提示
     * 5、try。。。catch 没必要，没有需要处理异常的地方
     *
     * 问题：
     * 1、get 与 post 请求的区别
     * 2、什么是端口
     * 3、什么是接口
     * 4、什么是同步、异步请求
     * 5、串行，并行
     **/
    @PostMapping("/login/demo")
    public LoginResultVo loginDemo(@RequestParam("data") String data) {
        LoginResultVo loginResultVo = new LoginResultVo();
        try {
            Gson gson = new Gson();
            User user = gson.fromJson(data, User.class);
            String username = user.getUserName();
            String userpwd = user.getUserPwd();
            // 查询数据库，找到的用户名和密码
            User userFind = userMapper.findByUsernameAndUserpwd(username, userpwd);
            if(userFind != null){
                loginResultVo.setCode(200);
                loginResultVo.setInfo("登录成功！");
            }else {
                loginResultVo.setCode(400);
                loginResultVo.setInfo("用户名或密码错误，登录失败");
            }
            return loginResultVo;
        } catch (Exception e) {
            e.printStackTrace();
            loginResultVo.setCode(50002);
            loginResultVo.setInfo("服务器错误，请联系管理员！");
        }
//        if (userpwd.equals(userFind.getUserPwd())) {
//            loginResultVo.setCode(200);
//            loginResultVo.setInfo("密码正确，登录成功！");
//        } else {
//            loginResultVo.setCode(400);
//            loginResultVo.setInfo("密码错误，登录失败");
//        }
        return loginResultVo;
    }

}
