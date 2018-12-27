package com.esp.house.comment.commentserver.model;

import com.esp.house.comment.commentserver.domain.Blog;
import lombok.Data;

@Data
public class BlogQueryReq {
  
  private Blog blog;
  
  private Integer limit;
  
  private Integer offset;
}
