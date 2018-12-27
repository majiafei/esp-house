package com.ruanmou.house.houseserver;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruanmou.house.houseserver.domain.House;
import com.ruanmou.house.houseserver.mapper.HouseMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseServerApplicationTests {

    @Autowired(required = false)
    private HouseMapper houseMapper;

    @Test
    public void testSelect() {
        // 设置第几页，每页几条数据
        PageHelper.startPage(0, 10);
        List<House> houseList = houseMapper.selectAll();
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        // 列表
        System.out.println(pageInfo.getList());
        // 总页数
        System.out.println(pageInfo.getPages());
        // 当前第几页
        System.out.println(pageInfo.getPageNum());
    }

}

