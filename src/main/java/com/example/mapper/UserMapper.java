package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserMapper {
    List<User> findAll();


    @Select("select * from tb_user")
    List<User> getAll();

    //    @Update("INSERT INTO 'tb_user' ('userName', 'userPwd') VALUES (#{userName}, #{userPwd});")
    @Update("INSERT INTO `tb_user` (`userName`, `userPwd`) VALUES (#{userName}, #{userPwd})")
    @Transactional
    void save(User user);

    @Select("SELECT * FROM `tb_user` WHERE userName=#{userName}")
    @Transactional
    User findAccountByUserName(String userName);

    //    @Select("SELECT * FROM  tb_user WHERE username = #{username} AND PASSWORD=#{password};")
    @Select("SELECT * FROM `tb_user` WHERE userName=#{userName} AND userPwd=#{userPwd}")
    @Transactional
    User findByUsernameAndUserpwd(String userName, String userPwd);


    @Update("INSERT INTO `tb_user` (`userName`, `userPwd`) VALUES (#{userName}, #{userPwd})")
    @Transactional
    void register(String userName, String userPwd);
}
