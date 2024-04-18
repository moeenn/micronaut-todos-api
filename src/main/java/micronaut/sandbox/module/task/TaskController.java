package micronaut.sandbox.module.task;


import java.util.Optional;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import micronaut.sandbox.module.task.dto.TaskAddUpdateDTO;
import micronaut.sandbox.module.task.dto.TaskListDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;

@Singleton
@Controller("/task")
public class TaskController {
  private TaskRepository taskRepository;

  public TaskController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Get
  public TaskListDTO index() {
    return new TaskListDTO(this.taskRepository.listTasks());
  }

  @Post
  @Validated
  public Task addTask(@Body TaskAddUpdateDTO req) {
    return this.taskRepository.addTask(req.title);
  }

  @Put("/{id}/title")
  @Validated
  public HttpResponse<Task> updateTaskTitle(@NotBlank @PathVariable String id, @Body TaskAddUpdateDTO req) {
    Optional<Task> updatedTask = this.taskRepository.updateTaskTitle(id, req.title);
    if (updatedTask.isEmpty()) {
      return HttpResponse.badRequest();
    }

    return HttpResponse.ok(updatedTask.get());
  }

  @Put("/{id}/toggle")
  @Validated
  public HttpResponse<Task> toggleTaskDone(@NotBlank @PathVariable String id) {
    Optional<Task> updatedTask = this.taskRepository.toggleTaskDone(id);
    if (updatedTask.isEmpty()) {
      return HttpResponse.badRequest();
    }

    return HttpResponse.ok(updatedTask.get());
  }

  @Delete("/{id}")
  public void deleteTask(String id) {
    this.taskRepository.deleteTask(id);
  }
}