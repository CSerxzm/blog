package com.xzm.blog.service;

import com.alibaba.fastjson.JSON;
import com.xzm.blog.NotFoundException;
import com.xzm.blog.bean.Type;
import com.xzm.blog.dao.TypeMapper;
import com.xzm.blog.util.RedisUtils;
import com.xzm.blog.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeMapper.insert(type);
    }

    @Cacheable(value = "type", key = "#id")
    @Override
    public Type selectType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Type selectTypeByName(String name) {
        return typeMapper.selectTypeByName(name);
    }

    @Override
    public List<Type> selectType() {
        return typeMapper.selectAll();
    }

    @Override
    public List<Type> selectTypeTop(Integer size) {
        List<Type> types;
        if (RedisUtils.isEmpty(BlogConstant.TYPETOP)) {
            types = typeMapper.selectTypeTop(size);
            if (!types.isEmpty()) {
                String s = JSON.toJSONString(types);
                RedisUtils.set(BlogConstant.TYPETOP,s);
            }
        } else {
            types = JSON.parseArray(RedisUtils.get(BlogConstant.TYPETOP).toString(),Type.class);
        }
        return types;
    }


    @Transactional
    @Override
    public int updateType(Integer id, Type type) {
        Type t = typeMapper.selectByPrimaryKey(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        return typeMapper.updateByPrimaryKey(type);
    }

    @Transactional
    @Override
    public int deleteType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }
}
