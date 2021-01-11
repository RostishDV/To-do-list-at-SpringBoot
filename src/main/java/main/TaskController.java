package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/")
    public List<Task> tasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskRepository.findAll()){
            tasks.add(task);
        }
        return tasks;
    }

    @GetMapping("/task/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(task.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/tasks/")
    public int add(Task task){
        System.out.println(task.getDescription());
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @DeleteMapping("/tasks/")
    public ResponseEntity deleteAll(){
        taskRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity delete(@PathVariable int id){
        taskRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/tasks/")
    public ResponseEntity putAll(List<Task> tasks){
        for (Task task : taskRepository.findAll()){
            tasks.forEach(t->{ if(task.getId() == t.getId()){
                taskRepository.save(t);
            }});
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity put(@PathVariable int id, Task task){
        Optional<Task> opTask = taskRepository.findById(id);
        if (opTask.isPresent()) {
            Task updatedTask = opTask.get();
            updatedTask.setDate(task.getDate());
            updatedTask.setDescription(task.getDescription());
            taskRepository.save(updatedTask);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
