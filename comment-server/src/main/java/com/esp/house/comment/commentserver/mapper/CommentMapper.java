package com.esp.house.comment.commentserver.mapper;

import com.esp.house.comment.commentserver.common.mapper.HouseBaseMapper;
import com.esp.house.comment.commentserver.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper extends HouseBaseMapper<Comment> {

  List<Comment> selectComments(@Param("houseId") long houseId, @Param("size") int size);
  
  List<Comment> selectBlogComments(@Param("blogId") long blogId, @Param("size") int size);
  
}

