package com.mdoldur.usertask.repository;

import com.mdoldur.usertask.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT Task " +
           "  FROM TaskEntity Task, GnlStEntity GnlSt " +
           " WHERE Task.stId = GnlSt.gnlStId " +
           "   AND GnlSt.isActv = 1 " +
           "   AND GnlSt.entCodeName = 'TASK' " +
           "   AND GnlSt.shrtCode = 'ACTV' " +
           "   AND Task.taskId NOT IN (SELECT UserTask.taskId FROM UserTaskEntity UserTask WHERE UserTask.isActv = 1)")
    List<TaskEntity> findAvailableTasks();

}
