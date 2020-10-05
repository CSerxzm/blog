package com.xzm.service;

import com.xzm.NotFoundException;
import com.xzm.bean.Blog;
import com.xzm.bean.Tag;
import com.xzm.dao.BlogMapper;
import com.xzm.dao.TagMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "tag",key="#id")
    @Override
    public Tag selectTag(int id) {
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
    public List<Tag> selectTagTop(int size) {
        return tagMapper.selectTagTop(size);
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
    public int updateTag(int id, Tag tag) {
        Tag t = tagMapper.selectByPrimaryKey(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        return tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Transactional
    @Override
    public int deleteTag(int id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Blog> selectBlogByTagId(int id){
        List<Integer> blogIds = tagMapper.selectBlogIdByTagId(id);
        List<Blog> blogs = blogMapper.selectByIds(blogIds);
        return blogs;
    }

    @Override
    public List<Tag> selectTagByBlogId(int id){
        List<Tag> tags=tagMapper.selectTagByBlogId(id);
        return tags;
    }
    @Transactional
    @Override
    public int saveBlogAndTag(int blogId,List<Integer> tagIds){
        return tagMapper.saveBlogAndTag(blogId,tagIds);
    }
}
