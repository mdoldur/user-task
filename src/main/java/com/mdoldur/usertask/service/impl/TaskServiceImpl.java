package com.mdoldur.usertask.service.impl;

import com.mdoldur.usertask.entity.TaskEntity;
import com.mdoldur.usertask.entity.UserTaskEntity;
import com.mdoldur.usertask.repository.TaskRepository;
import com.mdoldur.usertask.dto.TaskDTO;
import com.mdoldur.usertask.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskDTO> listTasks() {
        List<TaskDTO> result = new ArrayList<>();

        List<TaskEntity> taskEntities = taskRepository.findAvailableTasks();
        for (TaskEntity taskEntity : taskEntities) {
            TaskDTO task = null;
            result.add(task.create(taskEntity));
        }

        return result;
    }

	@Override
	public List<TaskDTO> listUserTasks(Long userId) {
		List<TaskDTO> result = new ArrayList<>();
		
		List<TaskEntity> userTasks = taskRepository.findTasksByUserId(userId);
		for (TaskEntity item : userTasks) {
            TaskDTO task = null;
            result.add(task.create(item));
        }
        return result;
	}
	
}
