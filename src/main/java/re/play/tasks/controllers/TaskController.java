package re.play.tasks.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import re.play.tasks.data_access.TasksDAO;
import re.play.tasks.model.Task;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(path = "/")
public class TaskController {

    private TasksDAO tasksDAO;

    @Inject
    public TaskController(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    @RequestMapping(method = POST, path = "/task")
    @ResponseBody
    public ResponseEntity addTask(@RequestBody Task task) {
        tasksDAO.addTask(task);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Access-Control-Allow-Origin", Arrays.asList("*"));
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = { "task/{id}", "task/", "task" })
    @ResponseBody
    public ResponseEntity getTasks(@PathVariable(name = "id") Optional<Integer> id) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Access-Control-Allow-Origin", Arrays.asList("*"));
        if (id.isPresent()) {
            return new ResponseEntity(tasksDAO.getTask(id.get()), headers, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(tasksDAO.getAllTasks(), headers, HttpStatus.OK);
        }
    }

}
