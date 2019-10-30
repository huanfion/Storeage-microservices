package com.zegobird.oauth2center.mapper;

import com.zegobird.oauth2center.domain.TbRole;
import tk.mybatis.mapper.MyMapper;

public interface TbRoleMapper extends MyMapper<TbRole> {
    @Override
    int insert(TbRole record);
}