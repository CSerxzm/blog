package com.xzm.blog.service;

import com.alibaba.fastjson.JSON;
import com.xzm.blog.bean.Comment;
import com.xzm.blog.util.RedisUtils;
import com.xzm.blog.dao.CommentMapper;
import com.xzm.blog.util.BlogConstant;
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
        List<Comment> comments;
        if (RedisUtils.isEmpty(BlogConstant.COMMENTS + blogId)) {
            comments = commentMapper.selectByBlogId(blogId);
            if (!comments.isEmpty()) {
                String s = JSON.toJSONString(comments);
                RedisUtils.set(BlogConstant.COMMENTS + blogId, s);
            }
        } else {
            comments = JSON.parseArray(RedisUtils.get(BlogConstant.COMMENTS + blogId).toString(), Comment.class);
        }
        return comments;
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        RedisUtils.del(BlogConstant.COMMENTS + comment.getBlogId());
        comment.setCreateTime(new Date());
        return commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> selectAll() {
        List<Comment> comments;
        if (RedisUtils.isEmpty(BlogConstant.COMMENTS)) {
            comments = commentMapper.selectAll();
            String s = JSON.toJSONString(comments);
            RedisUtils.set(BlogConstant.COMMENTS, s);
        } else {
            comments = JSON.parseArray(RedisUtils.get(BlogConstant.COMMENTS).toString(),Comment.class);
        }
        return comments;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        RedisUtils.del(BlogConstant.COMMENTS);
        return commentMapper.deleteByPrimaryKey(id);
    }
}
