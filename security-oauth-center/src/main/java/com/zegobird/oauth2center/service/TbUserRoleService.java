package com.zegobird.oauth2center.service;

import java.util.List;
import java.util.Map;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:46
 */
public interface TbUserRoleService {

    public List<Map> queryUserRole(Long userid);

    public Integer DelRoleUser(Long id);

}

