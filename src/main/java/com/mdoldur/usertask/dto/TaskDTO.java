package com.mdoldur.usertask.dto;

import com.mdoldur.usertask.entity.TaskEntity;

import java.io.Serializable;

public class TaskDTO implements Serializable {
    String name;
    String status;

    private TaskDTO(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public static TaskDTO of(TaskEntity taskEntity) {
        return new TaskDTO(taskEntity.getName(), taskEntity.getStatus().getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
