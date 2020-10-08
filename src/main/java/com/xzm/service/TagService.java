package com.xzm.service;

import com.xzm.bean.Blog;
import com.xzm.bean.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TagService {

    int saveTag(Tag type);

    Tag selectTag(Integer id);

    Tag selectTagByName(String name);

    List<Tag> selectTag();

    List<Tag> selectTagTop(Integer size);

    int updateTag(Integer id, Tag type);

    int deleteTag(Integer id);

    List<Blog> selectBlogByTagId(Integer id);

    List<Tag> selectTagByBlogId(Integer id);

    int saveBlogAndTag(int blogId,List<Integer> tagIds);
}
