package micronaut.sandbox.module.task;

import java.util.List;
import java.util.Optional;

import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import micronaut.sandbox.module.task.request.TaskAddRequest;
import micronaut.sandbox.module.task.request.TaskUpdateTitleRequest;
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
  public List<Task> index() {
    return this.taskRepository.listTasks();
  }

  @Post
  @Validated
  public Task addTask(@Body TaskAddRequest req) {
    return this.taskRepository.addTask(req.title);
  }

  @Put("/{id}/title")
  @Validated
  public HttpResponse<Task> updateTaskTitle(@NotBlank @PathVariable String id, @Body TaskUpdateTitleRequest req) {
    Optional<Task> updatedTask = this.taskRepository.updateTaskTitle(id, req.title);
    if (updatedTask.isEmpty()) {
      // TODO: return actual error message
      return HttpResponse.badRequest();
    }

    return HttpResponse.ok(updatedTask.get());
  }

  @Put("/{id}/toggle")
  @Validated
  public HttpResponse<Task> toggleTaskDone(@NotBlank @PathVariable String id) {
    Optional<Task> updatedTask = this.taskRepository.toggleTaskDone(id);
    if (updatedTask.isEmpty()) {
      // TODO: return actual error message
      return HttpResponse.badRequest();
    }

    return HttpResponse.ok(updatedTask.get());
  }

  @Delete("/{id}")
  public void deleteTask(String id) {
    this.taskRepository.deleteTask(id);
  }
}