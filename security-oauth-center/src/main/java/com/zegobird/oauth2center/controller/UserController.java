package com.zegobird.oauth2center.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zegobird.common.enums.ErrorCodeEnum;
import com.zegobird.common.framework.BaseController;
import com.zegobird.common.util.ApiAssert;
import com.zegobird.oauth2center.domain.TbRole;
import com.zegobird.oauth2center.domain.TbUser;
import com.zegobird.oauth2center.dto.*;
import com.zegobird.oauth2center.dto.query.UserQuery;
import com.zegobird.oauth2center.dto.query.UserQueryByRole;
import com.zegobird.oauth2center.service.TbRoleService;
import com.zegobird.oauth2center.service.TbUserRoleService;
import com.zegobird.oauth2center.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/10 17:53
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "User", description = "用户操作相关接口")
public class UserController extends BaseController {

    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private TbRoleService roleService;
    @Autowired
    private TbUserRoleService tbUserRoleService;

    @Value("${default_password}")
    private String DEFAULT_PASSWORD;


    @ApiOperation("分页获取所有用户信息")
    @GetMapping("/page")
    public String page(UserQuery query) {
        Page page =PageHelper
                .startPage(query.getPage(), query.getRows());
        List<UserOutput> userList = tbUserService.getUserOutPutList(query);
        //List<UserOutput> collect = userList.stream().map(e -> e.convert(UserOutput.class)).collect(Collectors.toList());
        PageInfo uesrPageInfo = new PageInfo<>(userList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", uesrPageInfo);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @ApiOperation("分页获取所有用户信息")
    @GetMapping("/list")
    public String UserList(UserQuery query) {
        Page page =PageHelper
                .startPage(query.getPage(), query.getRows());
        List<TbUser> userList = tbUserService.getUserList(query);
        //List<UserOutput> collect = userList.stream().map(e -> e.convert(UserOutput.class)).collect(Collectors.toList());
        PageInfo uesrPageInfo = new PageInfo<>(userList);
        List<UserOutput> collect = userList.stream().map(e -> e.convert(UserOutput.class)).collect(Collectors.toList());
        uesrPageInfo.setList(collect);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", uesrPageInfo);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @ApiOperation("获取某个用户的信息")
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('查看用户')")
    public String getUser(@PathVariable("id") Long id) {
        TbUser user = tbUserService.findById(id);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, user);
        UserOutput userDto = user.convert(UserOutput.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", userDto);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @ApiOperation("获取某个用户的信息")
    @GetMapping("/getByUsername")
    public String getByUsername(String username) {
        TbUser user = tbUserService.getByUsername(username);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", user);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @ApiOperation("添加用户信息")
    @PostMapping("/add")
    public String addUser(@Valid @RequestBody AddUserInput input) {
        ApiAssert.equals(ErrorCodeEnum.PASSWORDCONFIRM_IS_INCORRECT, input.getPassword(), input.getPasswordConfirm());
        //用户名不能重复
        TbUser tbUser = tbUserService.getByUsername(input.getUsername());
        ApiAssert.isNull(ErrorCodeEnum.USERNAME_ALREADY_EXISTS,tbUser);
        tbUserService.add(input);
        return formatResponseParams(EXEC_OK, null);
    }


    @ApiOperation("更新某个用户信息")
    @PutMapping("/update")
    public String updateUser(@Valid @RequestBody UpdateUserInput input) {
        TbUser user = tbUserService.findById(input.getId());
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, user);
        TbUser newuser = tbUserService.updateUser(input);
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("res", newuser);
        return formatResponseParams(EXEC_OK, jsonobj);
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/updatepassword")
    public String updatePassword(@Valid @RequestBody UserPasswordInput input) {
        ApiAssert.equals(ErrorCodeEnum.PASSWORDCONFIRM_IS_INCORRECT, input.getPassword(), input.getPasswordConfirm());
        tbUserService.updateUserPwd(input);
        return formatResponseParams(EXEC_OK, null);
    }

    @ApiOperation("重置密码为123456")
    @PutMapping("/{id}/password")
    public String resetPwd(@PathVariable("id") Long id) {
        tbUserService.resetPwd(id, DEFAULT_PASSWORD);
        return formatResponseParams(EXEC_OK, null);
    }

    @ApiOperation("更新用户状态")
    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable("id") Long id, @Valid @RequestParam Short status) {
        tbUserService.updateUserStatus(id, status);
        return formatResponseParams(EXEC_OK, null);
    }


    @ApiOperation("根据用户名查询用户信息")
    @GetMapping("query/{name}")
    public String queryByName(@PathVariable("name") String name) {
        List<TbUser> list = tbUserService.queryByName(name);
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("res", list);
        return formatResponseParams(EXEC_OK, jsonobj);
    }

    @ApiOperation("根据用户名查询用户信息")
    @GetMapping("queryByNames")
    public String queryByNames(@RequestParam("names") String[] names) {
        List<TbUser> list = tbUserService.queryByNames(names);
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("res", list);
        return formatResponseParams(EXEC_OK, jsonobj);
    }
    @ApiOperation("查询某角色下的用户信息")
    @GetMapping("/role/list/{roleId}")
    public String queryUserlistbyRoleid(@PathVariable("roleId") Long roleId, UserQueryByRole query){
        query.setRoleid(roleId);
        TbRole tbRole = roleService.getRoleById(query.getRoleid());
        ApiAssert.notNull(ErrorCodeEnum.ID_QUERY_DATA_NOT_FOUND,tbRole);
        Page page =PageHelper
                .startPage(query.getPage(), query.getRows());
        List<RoleUserOutput> userList = tbUserService.getUserOutPutListByRole(query);
        PageInfo uesrPageInfo = new PageInfo<>(userList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", uesrPageInfo);
        return formatResponseParams(EXEC_OK, jsonObject);
    }

    @ApiOperation("删除某角色下的用户")
    @DeleteMapping("/delRoleUser/{user_role_id}")
    public String delRoleUser(@PathVariable("user_role_id") Long user_role_id){
        tbUserRoleService.DelRoleUser(user_role_id);
        return formatResponseParams(EXEC_OK,null);
    }
}
