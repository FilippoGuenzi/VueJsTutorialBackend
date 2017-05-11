package re.play.tasks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import re.play.tasks.data_access.TasksDAO;
import re.play.tasks.model.Task;

import javax.inject.Inject;

@Controller
@RequestMapping(path = "/")
public class TaskController {

    private TasksDAO tasksDAO;

    @Inject
    public TaskController(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/task")
    @ResponseBody
    public void addTask(@RequestBody Task task) {
        tasksDAO.addTask(task);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/task/{id}")
    @ResponseBody
    public ResponseEntity getTasks(@RequestParam(value = "id") Integer id) {
        if (id == null) {
            return new ResponseEntity(tasksDAO.getAllTasks(), HttpStatus.OK);
        } else {
            return new ResponseEntity(tasksDAO.getTask(id), HttpStatus.OK);
        }
    }

}
