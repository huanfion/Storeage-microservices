package com.zegobird.oauth2center.service;

import com.zegobird.oauth2center.domain.TbUser;
import com.zegobird.oauth2center.dto.*;
import com.zegobird.oauth2center.dto.query.UserQuery;
import com.zegobird.oauth2center.dto.query.UserQueryByRole;

import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:46
 */
public interface TbUserService {

    TbUser getByUsername(String username);

    List<TbUser> getUserList(UserQuery userQuery);
    List<TbUser> getUserListByRoleId(Long roleId);
    List<UserOutput> getUserOutPutList(UserQuery userQuery);
    List<RoleUserOutput> getUserOutPutListByRole(UserQueryByRole userQuery);
    TbUser findById(Long id);

    TbUser add(AddUserInput input);

    TbUser updateUser(UpdateUserInput input);

    int updateUserPwd(UserPasswordInput input);

    void resetPwd(Long uid,String defaultPassword);

    int updateUserStatus(long id, Short status);

    List<TbUser> queryByName(String name);

    List<TbUser> queryByNames(String[] names);
}

