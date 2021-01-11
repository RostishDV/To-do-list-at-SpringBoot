package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    public static int currentId = 1;
    private static HashMap<Integer, Task> tasks = new HashMap<>();

    public static List<Task> getAllTasks(){
        return new ArrayList<>(tasks.values());
    }

    public static int addTask(Task task){
        int id = currentId++;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public static Task get(int id){
        if (tasks.containsKey(id)){
            return tasks.get(id);
        }
        return null;
    }

    public static int deleteTaskById(int id){
        if (tasks.containsKey(id)){
            tasks.remove(id);
            return 0;
        }
        return -1;
    }

    public static void clear(){
        tasks = new HashMap<>();
        currentId = 1;
    }

    public static int put(int id, Task task){
        if(tasks.containsKey(id)){
            tasks.put(id, task);
            return 0;
        }
        return -1;
    }

    public static int putAll(List<Task> taskList){
        clear();
        taskList.forEach(t -> tasks.put(currentId++, t));
        return 0;
    }
}
