package com.mdoldur.usertask.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"TASK\"")
public class TaskEntity {
    @Id
    @SequenceGenerator(name = "TaskSequence", sequenceName = "\"TASK_SEQ\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaskSequence")
    @Column(name = "\"TASK_ID\"")
    Long taskId;

    @Column(name = "\"NAME\"")
    String name;

    @Column(name = "\"ST_ID\"")
    Long stId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"ST_ID\"", referencedColumnName = "\"GNL_ST_ID\"", insertable = false, updatable = false)
    GnlStEntity status;

    @Column(name = "\"CDATE\"")
    Date cdate;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

}
