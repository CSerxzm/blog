package com.xzm.blog.service;

import com.xzm.blog.NotFoundException;
import com.xzm.blog.bean.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog selectBlog(Integer id);

    Blog selectAndConvert(Integer id) throws NotFoundException;

    List<Blog> selectBlog();

    Boolean saveViews();

    List<Blog> selectBlogAdmin();

    List<Blog> selectRecommendBlogTop(Integer size);

    List<Blog> selectHotBlogTop(Integer size);

    Map<String, List<Blog>> archiveBlog();

    int countBlog();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog) throws NotFoundException;

    int deleteBlog(Integer id);

    List<Blog> selectBlogByTypeId(Integer id);

    List<Blog> selectBlog(String ids);

    List<Integer> tagIdstoList(String tagIds);

    List<Blog> selectByTitlelike(String title);

    List<Blog> selectByTitleTypeandRecommend(String title, Integer type_id, Boolean recommend);

}
