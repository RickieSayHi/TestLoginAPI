package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    /**
     * Spring boot 简单步骤
     * 1、建立实体类，跟数据库表字段保持一致
     * 2、建立mapper接口，定义要操作数据库的动作
     * 3、建立mapper的xml文件，写具体的sql语句
     * 4、建立service类，处理业务逻辑
     * 5、在controller类中展示处理的结果
     */
    @Resource
    private UserService userService;

    @RequestMapping("/abc")
    public String getName() {
        return "genggeng";
    }

    @RequestMapping("/abcd")
    public Animal getAnimalName() {
        return new Animal("dog", 5);
    }

    @RequestMapping("/abcdf")
    public List<User> getUser() {
        return userService.findAll();
    }
}
