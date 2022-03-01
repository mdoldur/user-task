package com.mdoldur.usertask.entity;

import javax.persistence.*;

@Entity
@Table(name = "\"ROLE\"")
public class RoleEntity {
    @Id
    @SequenceGenerator(name = "RoleSequence", sequenceName = "\"ROLE_SEQ\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RoleSequence")
    @Column(name = "\"ROLE_ID\"")
    Long roleId;

    @Column(name = "\"NAME\"")
    String name;

    @Column(name = "\"IS_ACTV\"")
    Integer isActv;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }
}
