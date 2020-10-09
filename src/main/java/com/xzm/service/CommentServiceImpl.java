package com.xzm.service;

import com.xzm.bean.Comment;
import com.xzm.dao.CommentMapper;
import com.xzm.util.BlogConstant;
import com.xzm.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Resource(name="commentRedisTemplate")
    private RedisTemplate commentRedisTemplate;

    @Autowired
    private RedisUtils<Comment> redisUtils;

    @Override
    public List<Comment> selectCommentByBlogId(Integer blogId) {
        List<Comment> comments;
        if(redisUtils.isEmpty(commentRedisTemplate, BlogConstant.COMMENTS+blogId)){
            comments=commentMapper.selectByBlogId(blogId);
            if(!comments.isEmpty()){
                redisUtils.setValueList(commentRedisTemplate,BlogConstant.COMMENTS+blogId,comments);
            }
        }else{
            comments = redisUtils.getValueList(commentRedisTemplate, BlogConstant.COMMENTS+blogId);
        }
        return comments;
    }

    @Transactional
    @Override
    public int saveComment(Comment comment){
        redisUtils.removeValue(commentRedisTemplate,BlogConstant.COMMENTS+comment.getBlogId());
        comment.setCreateTime(new Date());
        return commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> selectAll( ){
        List<Comment> comments;
        if(redisUtils.isEmpty(commentRedisTemplate, BlogConstant.COMMENTS)){
            comments=commentMapper.selectAll();
            redisUtils.setValueList(commentRedisTemplate,BlogConstant.COMMENTS,comments);
        }else{
            comments = redisUtils.getValueList(commentRedisTemplate, BlogConstant.COMMENTS);
        }
        return comments;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        redisUtils.removeValue(commentRedisTemplate,BlogConstant.COMMENTS);
        return commentMapper.deleteByPrimaryKey(id);
    }
}
