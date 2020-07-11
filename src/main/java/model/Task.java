package model;
import java.time.LocalDate;

public class Task {

    private Long id;
    private String title;
    private int user_id;
    private String description;
    private LocalDate targetDate;
    private boolean status;
    private String goal_title;

    protected Task() {

    }

    public Task(long id, String title, int user_id, String description, LocalDate targetDate, boolean isDone, String goal_title) {
        super();
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
        this.goal_title = goal_title;
    }

    public Task(String title, int user_id, String description, LocalDate targetDate, boolean isDone, String goal_title) {
        super();
        this.title = title;
        this.user_id = user_id;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
        this.goal_title = goal_title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public String getGoal_title() {
        return goal_title;
    }

    public void setGoal_title(String goal_title) {
        this.goal_title = goal_title;
    }
}
