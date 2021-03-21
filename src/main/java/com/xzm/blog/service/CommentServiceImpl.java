package com.xzm.blog.service;

import com.xzm.blog.bean.Comment;
import com.xzm.blog.util.RedisUtils;
import com.xzm.blog.dao.CommentMapper;
import com.xzm.blog.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Resource(name = "commentRedisTemplate")
    private RedisTemplate commentRedisTemplate;

    @Autowired
    private RedisUtils<Comment> redisUtils;

    @Override
    public List<Comment> selectCommentByBlogId(Integer blogId) {
        List<Comment> comments;
        if (redisUtils.isEmpty(commentRedisTemplate, BlogConstant.COMMENTS + blogId)) {
            comments = commentMapper.selectByBlogId(blogId);
            if (!comments.isEmpty()) {
                redisUtils.setValueList(commentRedisTemplate, BlogConstant.COMMENTS + blogId, comments);
            }
        } else {
            comments = redisUtils.getValueList(commentRedisTemplate, BlogConstant.COMMENTS + blogId);
        }
        return comments;
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        redisUtils.removeValue(commentRedisTemplate, BlogConstant.COMMENTS + comment.getBlogId());
        comment.setCreateTime(new Date());
        return commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> selectAll() {
        List<Comment> comments;
        if (redisUtils.isEmpty(commentRedisTemplate, BlogConstant.COMMENTS)) {
            comments = commentMapper.selectAll();
            redisUtils.setValueList(commentRedisTemplate, BlogConstant.COMMENTS, comments);
        } else {
            comments = redisUtils.getValueList(commentRedisTemplate, BlogConstant.COMMENTS);
        }
        return comments;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        redisUtils.removeValue(commentRedisTemplate, BlogConstant.COMMENTS);
        return commentMapper.deleteByPrimaryKey(id);
    }
}
