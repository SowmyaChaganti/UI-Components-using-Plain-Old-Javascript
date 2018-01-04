package twitterPages;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "postComments")
@Dependent
public class PostComments {

    public PostComments(int pid, int cid, int twfr, int twto, String com, int comLik) 
    {
        postid=pid;
        commmentid=cid;
        tweet_from=twfr;
        tweet_to=twto;
        comment=com;
        comment_likes=comLik;
    }
    
    private int postid;
    private int commmentid;
    private int tweet_from;
    private int tweet_to;
    private String comment;
    private int comment_likes;
    
    //getters ans setters

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getCommmentid() {
        return commmentid;
    }

    public void setCommmentid(int commmentid) {
        this.commmentid = commmentid;
    }

    public int getTweet_from() {
        return tweet_from;
    }

    public void setTweet_from(int tweet_from) {
        this.tweet_from = tweet_from;
    }

    public int getTweet_to() {
        return tweet_to;
    }

    public void setTweet_to(int tweet_to) {
        this.tweet_to = tweet_to;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getComment_likes() {
        return comment_likes;
    }

    public void setComment_likes(int comment_likes) {
        this.comment_likes = comment_likes;
    }
    
    
    
}
