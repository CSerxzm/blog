package com.xzm.service;

import com.xzm.bean.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectCommentByBlogId(int blogId);

    int saveComment(Comment comment);
}