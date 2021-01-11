package main;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String index(Model model){
        ArrayList<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskCount", tasks.size());
        return "index";
    }

}
