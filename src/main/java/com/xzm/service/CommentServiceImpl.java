package com.xzm.service;

import com.xzm.bean.Comment;
import com.xzm.dao.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectCommentByBlogId(Integer blogId) {
        List<Comment> comments = commentMapper.selectByBlogId(blogId);
        return comments;
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        return commentMapper.insertSelective(comment);
    }

}
