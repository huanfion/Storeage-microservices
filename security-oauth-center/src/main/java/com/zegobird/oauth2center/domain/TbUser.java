package com.zegobird.oauth2center.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class TbUser extends BaseEntity {
    private static final long serialVersionUID = 828977041573535L;
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 所属员工id
     */
    @Column(name = "employee_id")
    private Long employeeId;

    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码，加密存储
     */
    @Column(name = "\"password\"")
    private String password;

    /**
     * 注册手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 注册邮箱
     */
    @Column(name = "email")
    private String email;

    @Column(name = "created")
    private Date created;

    @Column(name = "lastlogined")
    private Date lastlogined;

    @Column(name = "updated")
    private Date updated;

    /**
     * 登录次数
     */
    @Column(name = "\"count\"")
    private Long count;

    /**
     * 状态 0正常 1禁用
     */
    @Column(name = "\"status\"")
    private Short status;

    /**
     * 是否删除 0正常 1 删除
     */
    @Column(name = "isdeleted")
    private Short isdeleted;
}