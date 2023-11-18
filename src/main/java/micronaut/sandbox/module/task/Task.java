package micronaut.sandbox.module.task;

import java.util.UUID;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Task {
  private String id;
  private String title;
  private Boolean done;

  public Task(String title) {
    this.id = UUID.randomUUID().toString();
    this.title = title;
    this.done = false;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }

  @Override
  public String toString() {
    return "Task [id=" + id + ", title=" + title + ", done=" + done + "]";
  }  
}
