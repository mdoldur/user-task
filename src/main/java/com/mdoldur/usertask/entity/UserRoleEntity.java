package com.mdoldur.usertask.entity;

import javax.persistence.*;

@Entity
@Table(name = "\"USER_ROLE\"")
public class UserRoleEntity {

    @Id
    @SequenceGenerator(name = "UserRoleSequence", sequenceName = "\"USER_ROLE_SEQ\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserRoleSequence")
    @Column(name = "\"USER_ROLE_ID\"")
    Long userRoleId;

    @Column(name = "\"USER_ID\"")
    Long userId;

/*    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"USER_ID\"", referencedColumnName = "\"USER_ID\"", updatable = false, insertable = false)
    UserEntity user;*/

    @Column(name = "\"ROLE_ID\"")
    Long roleId;

/*    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"ROLE_ID\"", referencedColumnName = "\"ROLE_ID\"", updatable = false, insertable = false)
    RoleEntity role;*/

    @Column(name = "\"IS_ACTV\"")
    Integer isActv;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /*public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }*/

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /*public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }*/

    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }
}
