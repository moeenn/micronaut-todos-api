package micronaut.sandbox.module.task.request;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Introspected
@Serdeable.Deserializable 
public class TaskAddRequest {
  @NotBlank
  @NotEmpty
  public final String title;

  public TaskAddRequest(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "TaskAddRequest [title=" + title + "]";
  }
}
