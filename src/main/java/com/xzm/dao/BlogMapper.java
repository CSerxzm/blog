package com.xzm.dao;

import com.xzm.bean.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    //新增方法
    List<Blog> selectAll();
    int count();
    List<Blog> selectRecommendBlogTop(Integer size);
    List<Blog> selectHotBlogTop(Integer size);
    List<String> selectGroupYear();
    List<Blog> selectByYear(String year);
    List<Blog> selectByQuery(Map<String,Object> map);
    List<Blog> selectByTitlelike(String title);
    List<Blog> selectByIds(@Param("ids") List<Integer> ids);
}