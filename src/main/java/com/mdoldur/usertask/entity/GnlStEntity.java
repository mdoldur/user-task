package com.mdoldur.usertask.entity;

import javax.persistence.*;

@Entity
@Table(name = "\"GNL_ST\"")
public class GnlStEntity {
    @Id
    @SequenceGenerator(name = "GnlStSequence", sequenceName = "\"GNL_ST_SEQ\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GnlStSequence")
    @Column(name = "\"GNL_ST_ID\"")
    Long gnlStId;

    @Column(name = "\"ENT_CODE_NAME\"")
    String entCodeName;

    @Column(name = "\"SHRT_CODE\"")
    String shrtCode;

    @Column(name = "\"NAME\"")
    String name;

    @Column(name = "\"IS_ACTV\"")
    Integer isActv;

    public Long getGnlStId() {
        return gnlStId;
    }

    public void setGnlStId(Long gnlStId) {
        this.gnlStId = gnlStId;
    }

    public String getEntCodeName() {
        return entCodeName;
    }

    public void setEntCodeName(String entCodeName) {
        this.entCodeName = entCodeName;
    }

    public String getShrtCode() {
        return shrtCode;
    }

    public void setShrtCode(String shrtCode) {
        this.shrtCode = shrtCode;
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
