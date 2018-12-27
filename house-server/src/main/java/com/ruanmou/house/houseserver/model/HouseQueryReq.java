package com.ruanmou.house.houseserver.model;

import com.ruanmou.house.houseserver.domain.House;

/**
 * 房产查询的分页条件对象
 */
public class HouseQueryReq {
  
  private House query;
  
  private Integer limit;
  
  private Integer offset;


  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public House getQuery() {
    return query;
  }

  public void setQuery(House query) {
    this.query = query;
  }
  

}
