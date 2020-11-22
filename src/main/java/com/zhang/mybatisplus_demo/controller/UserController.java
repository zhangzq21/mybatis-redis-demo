package com.zhang.mybatisplus_demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhang.mybatisplus_demo.entity.User;
import com.zhang.mybatisplus_demo.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangzq
 */
@RequestMapping("/user")
@RestController
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 添加用户，添加前先查询
     *
     * @param user
     */
    @PostMapping("/user")
    @ApiOperation("根据用户信息查询用户是否存在")
    public String addUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        User resultUser = userMapper.selectOne(queryWrapper);
        //有则更新,无则新增
        if (resultUser != null) {
            userMapper.updateById(user);
            log.info("更新用户信息");
            return "用户已经存在";
        } else {
            userMapper.insert(user);
            log.info("新增用户");
            return "新增用户";
        }
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    @ApiOperation("根据用户id查询用户")
    public User findById(@PathVariable Long id) {
        User user = userMapper.selectById(id);
        log.info(String.valueOf(user));
        return user;
    }

}
