package com.example.mybatis_plus_demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatis_plus_demo.entity.User;
import com.example.mybatis_plus_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    void insert() {
        User user = new User();
        user.setName("kangkang2");
        user.setAge(11);
        user.setEmail("111@qq.com");

        int insert = userMapper.insert(user);
    }

    // 测试乐观锁
    @Test
    void testOptimisticLocker() {
        User user = userMapper.selectById(1367313176806436865L);

        user.setAge(12);

        int update = userMapper.updateById(user);

        System.out.println(update);
    }


    /**
     * 测试分页
     */
    @Test
    void TestPage(){
        // 1.创建page对象
        // 传入两个参数：当前页 和 每页显示记录数
        Page<User> page = new Page<>(1, 3);
        // 调用mp分页查询的方法
        // 调用mp分页查询过程中，底层封装
        // 把分页所有数据封装到page对象里面
        userMapper.selectPage(page, null);

        // 通过page对象获取分页数据
        System.out.println(page.getCurrent());  // 当前页
        System.out.println(page.getRecords());  // 每页数据list集合
        System.out.println(page.getSize()); // 每页显示记录数
        System.out.println(page.getTotal());    // 总数量
        System.out.println(page.getPages());    // 总页数

        System.out.println(page.hasNext()); // 是否有下一页
        System.out.println(page.hasPrevious()); //  是否有下一页
    }

    // 测试删除
    @Test
    void testDelete(){
        int delete = userMapper.deleteById(1L);
        System.out.println(delete);
    }
}
