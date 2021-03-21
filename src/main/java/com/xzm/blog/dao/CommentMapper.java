package com.xzm.blog.dao;

import com.xzm.blog.bean.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //新添加
    List<Object> selectByBlogId(Integer id);

    List<Comment> selectAll();
}