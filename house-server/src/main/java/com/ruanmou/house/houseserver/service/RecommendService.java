package com.ruanmou.house.houseserver.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ruanmou.house.houseserver.domain.House;
import com.ruanmou.house.houseserver.mapper.HouseMapper;
import com.ruanmou.house.houseserver.model.LimitOffset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 推荐业务实现
 */
@Service
public class RecommendService {
  
  private static final String HOT_HOUSE_KEY = "_hot_house";

  @Autowired
  private HouseService houseService;

  @Autowired
  private HouseMapper houseMapper;
  
  @Autowired
  private RedisTemplate redisTemplate;

  @Value("${qiniu.urlPrefix}")
  private String imgPrefix;

  /**
   * 获取热度房产
   * @param size
   * @return
   */
  public List<House> getHotHouse(Integer size) {
    Set<String> idSet =  redisTemplate.opsForZSet().reverseRange(HOT_HOUSE_KEY, 0, size - 1);//根据热度从高到底排序
    List<House> houses = Lists.newArrayList();
    idSet.forEach(s -> {
      House house = JSON.parseObject(s, House.class);
      house.setFirstImg(imgPrefix + house.getFirstImg());
      house.setImageList(house.getImageList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
      house.setFloorPlanList(house.getFloorPlanList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
      houses.add(house);
    });
//    House query = new House();
//    query.setIds(ids);
    return houses;
  }

  /**
   * 增加热度
   * @param id
   */
  public void increaseHot(long id) {
    redisTemplate.opsForZSet().incrementScore(HOT_HOUSE_KEY, "" + JSON.toJSONString(houseMapper.selectByPrimaryKey(id)), 1.0D);
//    redisTemplate.opsForZSet().removeRange(HOT_HOUSE_KEY, 0, -5);
  }

  /**
   * 获取最新的房源信息
   * @return
   */
  public List<House> getLastest() {
    House query = new House();
    query.setSort("create_time");
    return houseService.queryAndSetImg(query, LimitOffset.build(8, 0));
  }
}
