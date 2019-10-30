package com.zegobird.oauth2center.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_role")
public class TbRole extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 父角色
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 角色名称
     */
    @Column(name = "\"name\"")
    private String name;

    /**
     * 角色英文名称
     */
    @Column(name = "enname")
    private String enname;

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
}