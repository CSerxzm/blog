package com.xzm.blog.dao;

import com.xzm.blog.bean.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    //新增方法
    List<Blog> selectAll();
    List<Blog> selectAllAdmin();
    int count();
    List<Blog> selectRecommendBlogTop(Integer size);
    List<Blog> selectHotBlogTop(Integer size);
    List<String> selectGroupYear();
    List<Blog> selectByYear(String year);
    List<Blog> selectByQuery(Map<String,Object> map);
    List<Blog> selectByTitlelike(String title);
    List<Blog> selectBlogByTypeId(Integer id);
    List<Blog> selectByIds(@Param("ids") List<Integer> ids);
    int addBlogViews(Integer id);
}