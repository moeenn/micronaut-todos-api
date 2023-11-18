package micronaut.sandbox.module.task;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import jakarta.inject.Singleton;

@Singleton
public class TaskRepository {
  private List<Task> tasks;

  public TaskRepository() {
    this.tasks = new ArrayList<Task>() {
      {
        add(new Task("Task one"));
        add(new Task("Task two"));
        add(new Task("Task three"));
      }
    };
  }

  public List<Task> listTasks() {
    return this.tasks;
  }

  public Optional<Task> findById(String id) {
    Optional<Task> task = this.tasks.stream()
      .filter(t -> t.getId().equals(id))
      .findFirst();

    return task;
  }

  public Task addTask(String taskTitle) {
    Task task = new Task(taskTitle);
    this.tasks.add(task);
    return task;
  }

  public Optional<Task> updateTaskTitle(String id, String taskTitle) {
    Optional<Task> task = this.findById(id);
    if (task.isPresent()) {
      task.get().setTitle(taskTitle);
    }

    return task;
  }

  public Optional<Task> toggleTaskDone(String id) {
    Optional<Task> task = this.findById(id);
    if (task.isPresent()) {
      task.get().setDone(!task.get().getDone());
    }

    return task;
  }

  public void deleteTask(String id) {
    this.tasks.removeIf((task) -> task.getId().equals(id));
  }
}