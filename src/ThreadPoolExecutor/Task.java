package ThreadPoolExecutor;

public class Task {
    private Integer id;
    private String title;
    private Boolean completed;

    public Task() {}

    public Task(Integer id, String title, Boolean completed) {
        this.completed = completed;
        this.id = id;
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Task{" +
                "completed=" + completed +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
