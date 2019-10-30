package com.zegobird.oauth2center.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_permission")
public class TbPermission extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父权限
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限名称
     */
    @Column(name = "\"name\"")
    private String name;

    /**
     * 权限英文名称
     */
    @Column(name = "code")
    private String code;

    /**
     * 权限类型 0一级菜单 1子菜单 2按钮 3其他
     */
    @Column(name = "type")
    private String type;

    /**
     * 备注
     */
    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

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

    /**
     * 权限资源id，权限类型是菜单时，对应的就是菜单id
     */
    @Column(name = "resourceid")
    private Long resourceid;
}