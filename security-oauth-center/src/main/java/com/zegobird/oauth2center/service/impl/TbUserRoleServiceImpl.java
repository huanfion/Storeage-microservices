package com.zegobird.oauth2center.service.impl;

import com.zegobird.oauth2center.mapper.TbUserRoleMapper;
import com.zegobird.oauth2center.service.TbUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:46
 */
@Service
public class TbUserRoleServiceImpl implements TbUserRoleService {

    @Resource
    private TbUserRoleMapper tbUserRoleMapper;

    @Override
    public List<Map> queryUserRole(Long userid) {
        List<Map> list = null;
        list = tbUserRoleMapper.queryUserRole(userid);
        return list;
    }

    @Override
    public Integer DelRoleUser(Long id){
        return tbUserRoleMapper.deleteByPrimaryKey(id);
    }
}

