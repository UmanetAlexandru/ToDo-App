package md.tekwill.todo.controller;

import md.tekwill.todo.model.Task;
import md.tekwill.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("{id}")
    public Task getById(@PathVariable long id) {
        return taskService.getById(id).orElseThrow();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.add(task);
    }

    @PutMapping("{id}")
    public boolean update(@PathVariable long id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        taskService.delete(id);
    }
}
