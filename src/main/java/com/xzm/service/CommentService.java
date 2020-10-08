package com.xzm.service;

import com.xzm.bean.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectCommentByBlogId(Integer blogId);

    int saveComment(Comment comment);

    List<Comment> selectAll( );

    int deleteByPrimaryKey(Integer id);

    void sendtoAdmin(Comment comment);
}
