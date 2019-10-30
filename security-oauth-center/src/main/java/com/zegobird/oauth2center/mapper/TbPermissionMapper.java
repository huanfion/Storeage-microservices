package com.zegobird.oauth2center.mapper;

import com.zegobird.oauth2center.domain.TbPermission;
import com.zegobird.oauth2center.dto.FunctionPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbPermissionMapper extends MyMapper<TbPermission> {
    List<TbPermission> selectByUserId(@Param("userId") Long userId);

    TbPermission selectByMenuId(@Param("menuid") Long menuid);

    @Override
    int insert(TbPermission record);

    List<FunctionPermission> getFunctionList();

    List<FunctionPermission> getAllFunctionsByRole(@Param("roleId") Long roleId);

    List<FunctionPermission> getRoleFunctions(@Param("roleId") Long roleId);
}