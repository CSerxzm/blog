package com.xzm.service;

import com.xzm.bean.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog selectBlog(int id);

    Blog selectAndConvert(int id);

    List<Blog> selectBlog();

    List<Blog> selectRecommendBlogTop(int size);

    List<Blog> selectHotBlogTop(Integer size);

    Map<String,List<Blog>> archiveBlog();

    int countBlog();

    int saveBlog(Blog blog);

    int updateBlog(int id,Blog blog);

    void deleteBlog(int id);

    List<Blog> selectBlogByTypeId(int id);

    List<Blog> selectBlog(String ids);

    List<Integer> TagIdstoList(String tagIds);

    List<Blog> selectByTitlelike(String title);

    List<Blog> selectByTitleTypeandRecommend(String title,Integer type_id,Boolean recommend);

}
