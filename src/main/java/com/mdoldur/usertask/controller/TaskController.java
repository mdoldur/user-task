package com.mdoldur.usertask.controller;

import com.mdoldur.usertask.dto.TaskDTO;
import com.mdoldur.usertask.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(Model model) {
        List<TaskDTO> taskDTO = taskService.listTasks();
        model.addAttribute("tasks", taskDTO);
        return "home"; // resources/templates/home.html
    }

    /*
    // Alternative
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        List<TaskDTO> taskDTO = taskService.listTasks();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks", taskDTO);
        modelAndView.setViewName("home");
        return modelAndView;
    }*/

    @GetMapping(value = "/task")
    public ResponseEntity<TaskDTO> getTask() {
        return null;
    }

}
