package micronaut.sandbox.module.task.dto;

import micronaut.sandbox.module.task.Task;
import java.util.List;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable.Serializable
@Serdeable.Deserializable
public class TaskListDTO {
  private List<Task> tasks;

  public TaskListDTO(List<Task> tasks) {
    this.tasks = tasks;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  @Override
  public String toString() {
    return "TaskListDTO [tasks=" + tasks + "]";
  }
}
