/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author bim26
 */
import java.sql.Timestamp;

public class Post {
    private int post_id;
    private int user_id;
    private String body;
    private Timestamp post_time;
    private String first_name;
    private String last_name;
    private String image_path;

    public Post(int post_id, int user_id, String body, Timestamp post_time, String first_name, String last_name, String image_path) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.body = body;
        this.post_time = post_time;
        this.first_name = first_name;
        this.last_name = last_name;
        this.image_path = image_path;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
    
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Post(int post_id, int user_id, String body, Timestamp post_time, String first_name, String last_name) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.body = body;
        this.post_time = post_time;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Post(int post_id, int user_id, String body, Timestamp post_time) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.body = body;
        this.post_time = post_time;

    }
    

    public Post() {
    }

    public Post(int user_id, String body) {
        this.user_id = user_id;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getPost_time() {
        return post_time;
    }

    public void setPost_time(Timestamp post_time) {
        this.post_time = post_time;
    }

    }

    
 