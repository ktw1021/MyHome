package himedia.board.model;

public class Post {
    private int id;
    private String title;
    private String author;
    private String createdDate;
    private int viewCount;

    public Post(int id, String title, String author, String createdDate, int viewCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdDate = createdDate;
        this.viewCount = viewCount;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
