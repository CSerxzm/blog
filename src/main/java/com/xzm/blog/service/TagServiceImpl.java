package com.xzm.blog.service;

import com.alibaba.fastjson.JSON;
import com.xzm.blog.NotFoundException;
import com.xzm.blog.bean.Blog;
import com.xzm.blog.bean.Tag;
import com.xzm.blog.dao.BlogMapper;
import com.xzm.blog.dao.TagMapper;
import com.xzm.blog.constant.BlogConstant;
import com.xzm.blog.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagMapper.insert(tag);
    }

    @Override
    public Tag selectTag(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

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
        if (RedisUtils.isEmpty(BlogConstant.TAGTOP)) {
            tags = tagMapper.selectTagTop(size);
            if (!tags.isEmpty()) {
                String s = JSON.toJSONString(tags);
                RedisUtils.set(BlogConstant.TAGTOP, s);
            }
        } else {
            tags = JSON.parseArray(RedisUtils.get(BlogConstant.TAGTOP).toString(),Tag.class);
        }
        return tags;
    }

    private List<Integer> convertToList(String ids) {
        List<Integer> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
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
    public List<Blog> selectBlogByTagId(Integer id) {
        List<Integer> blogIds = tagMapper.selectBlogIdByTagId(id);
        List<Blog> blogs = blogMapper.selectByIds(blogIds);
        return blogs;
    }

    @Override
    public List<Tag> selectTagByBlogId(Integer id) {
        List<Tag> tags = tagMapper.selectTagByBlogId(id);
        return tags;
    }

    @Transactional
    @Override
    public int saveBlogAndTag(int blogId, List<Integer> tagIds) {
        return tagMapper.saveBlogAndTag(blogId, tagIds);
    }
}
