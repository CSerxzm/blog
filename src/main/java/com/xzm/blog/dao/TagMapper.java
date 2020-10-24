package com.xzm.blog.dao;

import com.xzm.blog.bean.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    //新增
    Tag selectTagByName(String name);
    List<Tag> selectAll();
    int count();
    List<Tag> selectTagTop(Integer size);

    //在blog_tag表进行操作
    List<Integer> selectBlogIdByTagId(Integer id);
    List<Tag> selectTagByBlogId(Integer id);
    int saveBlogAndTag(@Param("blogId") int blogId,@Param("tagIds") List<Integer> tagIds);
    int deleteTagByBlogId(Integer id);
}