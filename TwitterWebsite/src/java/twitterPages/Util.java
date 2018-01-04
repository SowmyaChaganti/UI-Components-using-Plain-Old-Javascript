package twitterPages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named(value = "util")
@Dependent
public class Util {
        ArrayList<UserPost> allPosts = new ArrayList<UserPost>();   
        ArrayList<UserPost> currentUserPost = new ArrayList<UserPost>();
        ArrayList<UserPost> whotofollow = new ArrayList<UserPost>();
        ArrayList<PostComments> commentsOnPost = new ArrayList<PostComments>();
        ArrayList<Integer> followerid = new ArrayList<>();
    public Util() {
        //twitterID=1;
        
    }
    public Util(String userEmail){
        
    }
    
    final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;        
        int twitterID;
        
    
    //connection methods
    public Connection getConnection(){
        //load the driver
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
        }
        
    public Statement getStatement(Connection conn){
        try {
            stat = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stat;
        }
    
    public void closeConnection(Connection conn, Statement stat, ResultSet rs){
        try {
                rs.close();
                conn.close();
                stat.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    
    public  static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public User userDetails(int twitterID){
            User user = new User();
            try{
            conn=getConnection();
            stat=getStatement(conn);
            rs = stat.executeQuery("Select * from tweet_account where Twitter_id="+twitterID);
            if(rs.next()){
                user.setCountry(rs.getString("country"));
                user.setEmail(rs.getString("email"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setPassword(rs.getString("psw"));
                user.setUserID(rs.getString("user_id"));
                user.setTwitterID(twitterID);
            }
            else{
                user=null;
            }
        }
        catch(Exception e){
        }
        closeConnection(conn, stat, rs);
        return user;
        }
        
    public ArrayList<UserPost> userPosts(int twitterID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //access the database and then login
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";

        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            String Const = "";
            con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
            stat = con.createStatement();

            rs = stat.executeQuery("select follower_id from follow where tweet_id = " + twitterID);
            while (rs.next()) {
                followerid.add(rs.getInt(1));
            }

            for (int i = 0; i < followerid.size(); i++) {
                Const += " Or b.tweet_id = " + followerid.get(i);
            }

            rs = stat.executeQuery("select a.firstname, a.lastname, a.user_id, b.post_id, b.post, b.likecount from tweet_account a join tweet_post b on a.twitter_id = b.tweet_id where b.tweet_id = " + twitterID + Const);////Change this query little bit
            while (rs.next()) {
                currentUserPost.add(new UserPost(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stat.close();
                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return currentUserPost;
    }
    
     public ArrayList<UserPost> whoToFollow(int tweet_id)
    {
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try
        {
            con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
            stat = con.createStatement();
            
            rs = stat.executeQuery("SELECT o.firstname, o.lastname from tweet_account o inner join sportinterest t on o.twitter_id = t.twitter_id where t.twitter_id != "+tweet_id);
            
            while(rs.next())
            {
                whotofollow.add(new UserPost(rs.getString(1), rs.getString(2)));
            }
            
         }
              catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                stat.close();
                rs.close();
                
              
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }    
            
        return whotofollow;
    }

    
    public ArrayList<PostComments> displayComments(int twitterID, int postid){
        try{
            conn= getConnection();
            stat = getStatement(conn);
            rs = stat.executeQuery("Select * from tweet_comment where tweet_id_from="+twitterID+" and post_id="+postid);
            
            while(rs.next()){
                commentsOnPost.add(new PostComments(rs.getInt("post_id"), rs.getInt("comment_id"), 
                rs.getInt("tweet_id_from"), rs.getInt("tweet_id_to"), rs.getString("comment"),
                rs.getInt("comment_likes")) );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection(conn, stat, rs);
        }
        return commentsOnPost;
    }

    public String likeCount(int postid){
            //int postid = 1;
            int likecount = 0;
            try{
           conn = getConnection();
           stat = getStatement(conn);
           rs = stat.executeQuery("Select * from tweet_post where post_id="+postid);
           if(rs.next()){
               likecount = Integer.parseInt(rs.getString("likecount"));
               postid = Integer.parseInt(rs.getString("post_id"));
           }
           likecount++;
           stat.executeUpdate("Update tweet_post set likecount="+likecount+" where post_id="+postid);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                closeConnection(conn, stat, rs);
            }
            return "newsFeed";
        }
    
    public String retweet(int postid){
        int twitterid =0, likecount=0;
        String post = "";
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int nextPostId=0;
        try{
        conn = getConnection();
        stat = getStatement(conn);
        rs = stat.executeQuery("Select * from tweet_post where post_id="+postid);
        if(rs.next()){
            twitterid = rs.getInt("tweet_id");
            post = rs.getString("post");
            //likecount = rs.getInt("likecount");
            //time = rs.getTimestamp("post_time");
        }
        rs = stat.executeQuery("Select max(post_id) from tweet_post");
        if(rs.next()){
            nextPostId = rs.getInt(1);
            nextPostId++;
        }
        stat.executeUpdate("Insert into tweet_post values ("+twitterid+", "+nextPostId+", '"+post+"', "+likecount+", '"+time+"')");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection(conn, stat, rs);
        }
        return "newsFeed";
    }
    
//getters and setters

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStat() {
        return stat;
    }

    public void setStat(Statement stat) {
        this.stat = stat;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public int getTwitterID() {
        return twitterID;
    }

    public void setTwitterID(int twitterID) {
        this.twitterID = twitterID;
    }

    public ArrayList<UserPost> getAllPosts() {
        return allPosts;
    }

    public void setAllPosts(ArrayList<UserPost> allPosts) {
        this.allPosts = allPosts;
    }

    public ArrayList<UserPost> getCurrentUserPost() {
        return currentUserPost;
    }

    public void setCurrentUserPost(ArrayList<UserPost> currentUserPost) {
        this.currentUserPost = currentUserPost;
    }

    public ArrayList<PostComments> getCommentsOnPost() {
        return commentsOnPost;
    }

    public void setCommentsOnPost(ArrayList<PostComments> commentsOnPost) {
        this.commentsOnPost = commentsOnPost;
    }

    public ArrayList<Integer> getFollowerid() {
        return followerid;
    }

    public void setFollowerid(ArrayList<Integer> followerid) {
        this.followerid = followerid;
    }

    public ArrayList<UserPost> getWhotofollow() {
        return whotofollow;
    }

    public void setWhotofollow(ArrayList<UserPost> whotofollow) {
        this.whotofollow = whotofollow;
    }
    
    
    
}
