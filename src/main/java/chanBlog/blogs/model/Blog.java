package chanBlog.blogs.model;

import java.util.Objects;

public class Blog {  //dto, vo 객체

    private long id;
    private  String name;
    private  String email;
    private  String title;
    private  String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return id == blog.id && email.equals(blog.email) && title.equals(blog.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, title);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + title + '\'' +
                ", message='" + content + '\'' +
                '}';
    }
}
