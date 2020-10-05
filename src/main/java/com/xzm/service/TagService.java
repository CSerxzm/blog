package com.xzm.service;

import com.xzm.bean.Blog;
import com.xzm.bean.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TagService {

    int saveTag(Tag type);

    Tag selectTag(int id);

    Tag selectTagByName(String name);

    List<Tag> selectTag();

    List<Tag> selectTagTop(int size);

    int updateTag(int id, Tag type);

    int deleteTag(int id);

    List<Blog> selectBlogByTagId(int id);

    List<Tag> selectTagByBlogId(int id);

    int saveBlogAndTag(int blogId,List<Integer> tagIds);
}
