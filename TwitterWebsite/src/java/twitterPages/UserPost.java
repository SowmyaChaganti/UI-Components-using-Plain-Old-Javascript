package twitterPages;

import java.sql.Timestamp;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "userPost")
@Dependent
public class UserPost {
    
    private String firstname;
    private String lastname;
    private String user_id;
    private int twitterID;
    private int postID;
    private String post;
    private int likeCount;
    private Timestamp time;
    
    
    public UserPost(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
     }

      public UserPost(String firstname, String lastname,String ui, int posID, String pos, int lc) {
        this.firstname = firstname;
        this.lastname = lastname;
        user_id = ui;
        postID=posID;
        post=pos;
        likeCount=lc;
    }
     
    public UserPost(int twID, int posID, String pos, int lc) {
        twitterID=twID;
        postID=posID;
        post=pos;
        likeCount=lc;
    }
    

    public String getFirstname() {    
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUser_id() {
        return user_id;
    }

    //getters and setters
    public void setUser_id(String user_id) {    
        this.user_id = user_id;
    }

    public int getTwitterID() {
        return twitterID;
    }

    public void setTwitterID(int twitterID) {
        this.twitterID = twitterID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
}
