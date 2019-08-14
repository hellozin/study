package me.hellozin.study;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String tag;

    private int likes;

    protected Post() {

    }

    public Post(String title, String tag, int likes) {
        this.title = title;
        this.tag = tag;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public int getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", likes=" + likes +
                '}';
    }
}
