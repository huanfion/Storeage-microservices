package com.zegobird.oauth2center.service;

import com.zegobird.oauth2center.domain.TbRole;
import com.zegobird.oauth2center.dto.AddRoleInput;
import com.zegobird.oauth2center.dto.query.RoleQuery;

import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:46
 */
public interface TbRoleService {

    List<TbRole> getRoleList(RoleQuery query);
    TbRole add(AddRoleInput input);
    TbRole getRoleById(Long id);
    Integer update (TbRole role);
    Integer delete (Long id);
}

