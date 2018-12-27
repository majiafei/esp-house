package com.esp.house.comment.commentserver.mapper;

import com.esp.house.comment.commentserver.common.mapper.HouseBaseMapper;
import com.esp.house.comment.commentserver.domain.Blog;
import com.esp.house.comment.commentserver.model.LimitOffset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper extends HouseBaseMapper<Blog> {
  
  List<Blog> selectBlog(@Param("blog") Blog blog, @Param("pageParams") LimitOffset limitOffset);
  
  Long selectBlogCount(Blog query);

}
