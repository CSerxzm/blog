package com.xzm.service;

import com.xzm.NotFoundException;
import com.xzm.bean.Blog;
import com.xzm.dao.BlogMapper;
import com.xzm.dao.TagMapper;
import com.xzm.util.MarkdownUtils;
import com.xzm.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagMapper tagMapper;

    @Cacheable(value = "blog",key="#id")
    @Override
    public Blog selectBlog(int id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public Blog selectAndConvert(int id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        blog.setViews(blog.getViews()+1);
        blogMapper.updateByPrimaryKeySelective(blog);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Override
    public List<Blog> selectBlog() {
        return blogMapper.selectAll();
    }

    @Override
    public List<Blog> selectRecommendBlogTop(int size) {
        return blogMapper.selectRecommendBlogTop(size);
    }

    @Override
    public List<Blog> selectHotBlogTop(Integer size){
        return blogMapper.selectHotBlogTop(size);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.selectGroupYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        Map<String,Object> query = new HashMap();
        for (String year : years) {
            query.clear();
            query.put("year",year);
            map.put(year, blogMapper.selectByQuery(query));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogMapper.count();
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }

        blogMapper.insert(blog);
        int b = tagMapper.saveBlogAndTag(blog.getId(),TagIdstoList(blog.getTagIds()));
        return b;
    }

    // 1,2,3  -----> [1,2,3]
    public List<Integer> TagIdstoList(String tagIds){
        List<Integer> res = new ArrayList<>();
        String[] tagid = tagIds.split(",");
        for (int i = 0; i < tagid.length; i++) {
            res.add(Integer.valueOf(tagid[i]));
        }
        return res;
    }

    @Transactional
    @Override
    public int updateBlog(int id, Blog blog) {
        Blog b = blogMapper.selectByPrimaryKey(id);
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());

        return blogMapper.updateByPrimaryKey(b);
    }

    @Transactional
    @Override
    public void deleteBlog(int id) {
        blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Blog> selectBlogByTypeId(int id){
        Map<String,Object> query = new HashMap();
        query.put("type_id",id);
        List<Blog> blogs = blogMapper.selectByQuery(query);
        return blogs;
    }

    @Override
    public List<Blog> selectBlog(String ids){
        return null;
    }

    @Override
    public List<Blog> selectByTitlelike(String title){
        return blogMapper.selectByTitlelike(title);
    }

    public List<Blog> selectByTitleTypeandRecommend(String title,Integer type_id,Boolean recommend){
        System.out.println("\ntitle="+title+"\n"
                +"\ntype_id="+type_id+"\n"
                +"\nrecommend="+recommend+"\n");
        Map<String,Object> query = new HashMap();
        query.put("title",title);
        query.put("type_id",type_id);
        query.put("recommend",recommend);
        return blogMapper.selectByQuery(query);
    }
}
