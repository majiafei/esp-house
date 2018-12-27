package com.esp.house.comment.commentserver.controller;

import com.esp.house.comment.commentserver.domain.Blog;
import com.esp.house.comment.commentserver.model.ApiResponse;
import com.esp.house.comment.commentserver.model.BlogQueryReq;
import com.esp.house.comment.commentserver.model.ListResponse;
import com.esp.house.comment.commentserver.service.BlogService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {
  
  @Autowired(required = false)
  private BlogService blogService;
  
  @PostMapping("list")
  public ApiResponse<ListResponse<Blog>> list(@RequestBody BlogQueryReq req){
    Pair<List<Blog>,Long> pair = blogService.queryBlog(req.getBlog(),req.getLimit(),req.getOffset());
    return ApiResponse.ofSuccess(ListResponse.build(pair.getKey(), pair.getValue()));
  }
  
  @GetMapping("one")
  public ApiResponse<Blog> one(Integer id){
    Blog blog = blogService.queryOneBlog(id);
    return ApiResponse.ofSuccess(blog);
  }

}