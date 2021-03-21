package com.xzm.blog.service;

import com.alibaba.fastjson.JSON;
import com.xzm.blog.NotFoundException;
import com.xzm.blog.bean.Blog;
import com.xzm.blog.dao.BlogMapper;
import com.xzm.blog.dao.TagMapper;
import com.xzm.blog.util.MarkdownUtils;
import com.xzm.blog.util.RedisUtils;
import com.xzm.blog.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Blog selectBlog(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    //重点这里，缓存处理，减少markdown语言向html的转换
    @Transactional
    @Override
    public Blog selectAndConvert(Integer id) {
        Blog blog=null;
        if (RedisUtils.isEmpty(BlogConstant.ONEBLOG + id)) {
            blog = blogMapper.selectByPrimaryKey(id);
            if (blog == null) {
                throw new NotFoundException("该blog不存在");
            }
            blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
            blog.setViews(blog.getViews()+1);
            //存储内容
            RedisUtils.hPut(BlogConstant.ONEBLOG + id,"blog",JSON.toJSONString(blog));
            //存储浏览量
            RedisUtils.hPut(BlogConstant.ONEBLOG + id,"views",blog.getViews().toString());
        } else {
            //存在该blog
            RedisUtils.hIncrement(BlogConstant.ONEBLOG + id,"views");
            blog = JSON.parseObject(RedisUtils.hGet(BlogConstant.ONEBLOG + id,"blog").toString(),Blog.class);
            String views = (String)RedisUtils.hGet(BlogConstant.ONEBLOG + id,"views").toString();
            blog.setViews(Integer.valueOf(views));
        }
        return blog;
    }

    @Override
    public List<Blog> selectBlog() {
        return blogMapper.selectAll();
    }

    @Override
    public List<Blog> selectBlogAdmin() {
        return blogMapper.selectAllAdmin();
    }

    @Override
    public List<Blog> selectRecommendBlogTop(Integer size) {
        List<Blog> blogs;
        if (RedisUtils.isEmpty(BlogConstant.RECOMMENDBLOGS)) {
            blogs = blogMapper.selectRecommendBlogTop(size);
            if (!blogs.isEmpty()) {
                String s = JSON.toJSONString(blogs);
                RedisUtils.set(BlogConstant.RECOMMENDBLOGS,s);
            }
        } else {
            blogs = JSON.parseArray(RedisUtils.get(BlogConstant.RECOMMENDBLOGS).toString(),Blog.class);
        }
        return blogs;
    }

    @Override
    public List<Blog> selectHotBlogTop(Integer size) {
        List<Blog> blogs;
        if (RedisUtils.isEmpty(BlogConstant.HOTBLOGS)) {
            blogs = blogMapper.selectHotBlogTop(size);
            if (!blogs.isEmpty()) {
                String s = JSON.toJSONString(blogs);
                RedisUtils.set(BlogConstant.HOTBLOGS, s);
            }
        } else {
            blogs = JSON.parseArray(RedisUtils.get(BlogConstant.HOTBLOGS).toString(),Blog.class);
        }
        return blogs;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.selectGroupYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        Map<String, Object> query = new HashMap();
        for (String year : years) {
            map.put(year, blogMapper.selectByYear(year));
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
        int b = tagMapper.saveBlogAndTag(blog.getId(), TagIdstoList(blog.getTagIds()));
        return b;
    }

    // 1,2,3  -----> [1,2,3]
    public List<Integer> TagIdstoList(String tagIds) {
        List<Integer> res = new ArrayList<>();
        String[] tagid = tagIds.split(",");
        for (int i = 0; i < tagid.length; i++) {
            res.add(Integer.valueOf(tagid[i]));
        }
        return res;
    }

    @Transactional
    @Override
    public int updateBlog(Integer id, Blog blog) {
        Blog b = blogMapper.selectByPrimaryKey(id);
        if (b == null) {
            throw new NotFoundException("该blog不存在");
        }
        blog.setUpdateTime(new Date());

        //对标签进行修改
        tagMapper.deleteTagByBlogId(blog.getId());
        tagMapper.saveBlogAndTag(blog.getId(), TagIdstoList(blog.getTagIds()));

        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    @Transactional
    @Override
    public int deleteBlog(Integer id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Blog> selectBlogByTypeId(Integer id) {
        Map<String, Object> query = new HashMap();
        query.put("type_id", id);
        query.put("published", true);
        List<Blog> blogs = blogMapper.selectBlogByTypeId(id);
        return blogs;
    }

    @Override
    public List<Blog> selectBlog(String ids) {
        return null;
    }

    @Override
    public List<Blog> selectByTitlelike(String title) {
        return blogMapper.selectByTitlelike(title);
    }

    public List<Blog> selectByTitleTypeandRecommend(String title, Integer type_id, Boolean recommend) {
        Map<String, Object> query = new HashMap();
        query.put("title", title);
        query.put("type_id", type_id);
        query.put("recommend", recommend);
        return blogMapper.selectByQuery(query);
    }

    /**
     * 在定时任务中调用，每过20分钟，缓存刷回数据库
     * @return
     */
    @Override
    public Boolean saveViews() {
        Set<String> keys = RedisUtils.getKeys("blog*");
        for(String key:keys){
            String views = ((String) RedisUtils.hGet(key, "views"));
            String id = key.replaceAll(".*[^\\d](?=(\\d+))","");
            blogMapper.saveBlogViews(Integer.valueOf(id),Integer.valueOf(views));
            RedisUtils.del(key);
        }
        return true;
    }
}
