package com.zegobird.oauth2center.mapper;

import com.zegobird.oauth2center.domain.TbUserRole;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TbUserRoleMapper extends MyMapper<TbUserRole> {

    public List<Map> queryUserRole(Long userid);

}