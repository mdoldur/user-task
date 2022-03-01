package com.mdoldur.usertask.entity;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "\"USER\"")
public class UserEntity {
    @Id
    @SequenceGenerator(name = "UserSequence", sequenceName = "\"USER_SEQ\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
    @Column(name = "\"USER_ID\"")
    Long userId;

    @Column(name = "\"NAME\"")
    String name;

    @Column(name = "\"SURNAME\"")
    String surname;

    @Column(name = "\"ST_ID\"")
    Long stId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"ST_ID\"", referencedColumnName = "\"GNL_ST_ID\"", updatable = false, insertable = false)
    GnlStEntity status;

    @Column(name = "\"UNAME\"")
    String uname;

    @Column(name = "\"PASSWORD\"")
    String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "\"USER_ROLE\"", joinColumns = @JoinColumn(name = "\"USER_ID\""), inverseJoinColumns = @JoinColumn(name = "\"ROLE_ID\""))
    private Set<RoleEntity> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getStId() {
        return stId;
    }

    public void setStId(Long stId) {
        this.stId = stId;
    }

    public GnlStEntity getStatus() {
        return status;
    }

    public void setStatus(GnlStEntity status) {
        this.status = status;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

}