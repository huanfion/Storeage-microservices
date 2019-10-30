package com.zegobird.oauth2center.mapper;

import com.zegobird.oauth2center.domain.TbUser;
import com.zegobird.oauth2center.dto.RoleUserOutput;
import com.zegobird.oauth2center.dto.UserOutput;
import com.zegobird.oauth2center.dto.query.UserQueryByRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;
import java.util.List;
//@CacheNamespace(implementation = MybatisRedisCache.class)
public interface TbUserMapper extends MyMapper<TbUser> {
    int insertUserRole(Long userId, @Param("roleId") Long roleId, @Param("created") Date created);

    int insertSelective(TbUser record);

    int deleteUserRoleByUserId(Long userId);

    List<TbUser> selectAllUserByRoleId(Long roleId);

    List<UserOutput> selectByPage(String nickname, String username, Integer status);

    List<RoleUserOutput> selectPageByRole(Long roleid);
}