package com.zhang.mybatisplus_demo;

import com.zhang.mybatisplus_demo.entity.User;
import com.zhang.mybatisplus_demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
//@EnableDiscoveryClient
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println("===selectALL method test===");
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        for (User user : userList) {
            System.out.println(user);
        }

        /*User user = userMapper.selectById(1);
        System.out.println(user);*/
    }

    @Test
    public void testLambda() {
        List<String> list = Arrays.asList("北京", "南京", "东京", "西安");
        List<String> resultList = filterString(list, s -> s.contains("京"));
        System.out.println(resultList);
    }

    /**
     * 根据给定的规则，过滤集合中的字符串，规则由Predicate中的自定义方法决定
     *
     * @param list
     * @param predicate
     * @return
     */
    List<String> filterString(List<String> list, Predicate<String> predicate) {
        ArrayList<String> filterList = new ArrayList<>();
        //循环遍历传入的集合，满足条件的就放入自定义集合中，最终返回集合
        for (String s : list) {
            if (predicate.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }
}
