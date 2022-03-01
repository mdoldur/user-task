package com.mdoldur.usertask.entity;

import javax.persistence.*;

@Entity
@Table(name = "\"USER_TASK\"")
public class UserTaskEntity {
    @Id
    @SequenceGenerator(name = "UserTaskSequence", sequenceName = "\"USER_TASK_SEQ\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserTaskSequence")
    @Column(name = "\"USER_TASK_ID\"")
    Long userTaskId;

    @Column(name = "\"USER_ID\"")
    Long userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"USER_ID\"", referencedColumnName = "\"USER_ID\"", updatable = false, insertable = false)
    UserEntity user;

    @Column(name = "\"TASK_ID\"")
    Long taskId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"TASK_ID\"", referencedColumnName = "\"TASK_ID\"", updatable = false, insertable = false)
    TaskEntity task;

    @Column(name = "\"IS_ACTV\"")
    Integer isActv;

    public Long getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Long userTaskId) {
        this.userTaskId = userTaskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }

}
