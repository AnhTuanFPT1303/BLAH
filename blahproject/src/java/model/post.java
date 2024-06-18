package model;

import java.util.Date;

public class post {
    private int post_id;
    private int user_id;
    private String body; 
    private Date post_time;
    private String title;

    public post() {
        
    }

    public post(int post_id, int user_id, String body, Date post_time, String title) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.body = body;
        this.post_time = post_time;
        this.title = title;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() { 
        return body;
    }

    public void setBody(String body) { 
        this.body = body;
    }

    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }
}