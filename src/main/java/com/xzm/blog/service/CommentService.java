package com.xzm.blog.service;

import com.xzm.blog.bean.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectCommentByBlogId(Integer blogId);

    int saveComment(Comment comment);

    List<Comment> selectAll();

    int deleteByPrimaryKey(Integer id);

}
