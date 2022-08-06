package md.tekwill.todo.service;

import md.tekwill.todo.model.Task;
import md.tekwill.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getById(long id) {
        return taskRepository.findById(id);
    }

    public Task add(Task task) {
        return taskRepository.save(task);
    }

    public boolean update(long id, Task task) {
        Optional<Task> byId = getById(id);
        if (byId.isEmpty()) {
            return false;
        }
        Task newTask = byId.get();
        newTask.setDescription(task.getDescription());
        newTask.setName(task.getName());
        newTask.setCompleted(task.isCompleted());
        taskRepository.save(newTask);
        return true;
    }

    public void delete(long id) {
        taskRepository.deleteById(id);
    }
}
