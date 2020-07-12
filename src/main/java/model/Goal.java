package model;

public class Goal {
    private Long id;
    private String title;
    private int user_id;

    protected Goal() {

    }

    public Goal(long id, String title, int user_id) {
        super();
        this.id = id;
        this.title = title;
        this.user_id = user_id;
    }

    public Goal(String title, int user_id) {
        super();
        this.title = title;
        this.user_id = user_id;
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
}
