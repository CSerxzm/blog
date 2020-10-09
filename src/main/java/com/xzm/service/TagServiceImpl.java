package com.xzm.service;

import com.xzm.NotFoundException;
import com.xzm.bean.Blog;
import com.xzm.bean.Tag;
import com.xzm.dao.BlogMapper;
import com.xzm.dao.TagMapper;
import com.xzm.util.BlogConstant;
import com.xzm.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Resource(name="tagRedisTemplate")
    private RedisTemplate tagRedisTemplate;

    @Autowired
    private RedisUtils<Tag> redisUtils;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagMapper.insert(tag);
    }

    @Cacheable(value = "tag",key="#id")
    @Override
    public Tag selectTag(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = "tag",key="#name")
    @Override
    public Tag selectTagByName(String name) {
        return tagMapper.selectTagByName(name);
    }

    @Override
    public List<Tag> selectTag() {
        return tagMapper.selectAll();
    }

    @Override
    public List<Tag> selectTagTop(Integer size) {
        List<Tag> tags;
        if(redisUtils.isEmpty(tagRedisTemplate, BlogConstant.TAGTOP)){
            tags=tagMapper.selectTagTop(size);
            redisUtils.setValueList(tagRedisTemplate,BlogConstant.TAGTOP,tags);
        }else{
            tags=redisUtils.getValueList(tagRedisTemplate,BlogConstant.TAGTOP);
        }
        return tags;
    }

    private List<Integer> convertToList(String ids) {
        List<Integer> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Integer(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public int updateTag(Integer id, Tag tag) {
        Tag t = tagMapper.selectByPrimaryKey(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        return tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Transactional
    @Override
    public int deleteTag(Integer id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Blog> selectBlogByTagId(Integer id){
        List<Integer> blogIds = tagMapper.selectBlogIdByTagId(id);
        List<Blog> blogs = blogMapper.selectByIds(blogIds);
        return blogs;
    }

    @Override
    public List<Tag> selectTagByBlogId(Integer id){
        List<Tag> tags=tagMapper.selectTagByBlogId(id);
        return tags;
    }
    @Transactional
    @Override
    public int saveBlogAndTag(int blogId,List<Integer> tagIds){
        return tagMapper.saveBlogAndTag(blogId,tagIds);
    }
}
