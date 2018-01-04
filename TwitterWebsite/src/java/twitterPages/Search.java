package twitterPages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped 
@ManagedBean
public class Search {
        private String comment;
        
    public Search() {}
    
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
    
    Util u = new Util();
    public String postComment(int currentUserId){
        int postid=1;
        int nextCommentId = 0;
        int to_tweetId = 0;
        try{
            conn=u.getConnection();
            stat=u.getStatement(conn);
            rs = stat.executeQuery("Select max(comment_id) from tweet_comment");
            if(rs.next()){
                nextCommentId = rs.getInt(1);
                nextCommentId++;
            }
            rs = stat.executeQuery("Select * from tweet_post where post_id="+postid);
            if(rs.next()){
                to_tweetId = rs.getInt("tweet_id");
            }
            stat.executeUpdate("Insert into tweet_comment values ("+postid+", "
            + ""+nextCommentId+", "+currentUserId+", "+to_tweetId+", '"+comment+"')");
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            u.closeConnection(conn, stat, rs);
        }
        return "newsFeed";
    }
    
    /*public String postComment(int postid, int currentUserId)
    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            return "internalError";
        }
        //access the database and then login
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try
        {
              int nextCommentId = 0;
              int to_tweetId = 0;
            con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
            stat = con.createStatement();
            
             rs = stat.executeQuery("Select max(comment_id) from tweet_comment");
            if(rs.next()){
                nextCommentId = rs.getInt(1);
                nextCommentId++;
            }
            rs = stat.executeQuery("Select * from tweet_post where post_id="+postid);
            if(rs.next()){
                to_tweetId = rs.getInt("tweet_id");
            }
            stat.executeUpdate("Insert into tweet_comment values ("+postid+", "
            + ""+nextCommentId+", "+currentUserId+", "+to_tweetId+", '"+comment+"')");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                stat.close();
                con.close();              
              
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                return "internalError!";
            }
        }
        return "newsFeed";
    }*/
    
    
    //getters and setters

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
    
}
