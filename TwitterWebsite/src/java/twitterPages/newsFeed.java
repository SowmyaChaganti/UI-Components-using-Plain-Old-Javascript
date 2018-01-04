package twitterPages;

import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@Named(value = "newsFeed")
@SessionScoped
@RequestScoped
public class newsFeed{

    public newsFeed()
    {
        //twitterID=1;
    }
    
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        int twitterID;
        Util u = new Util();
        
/*    public String likeCount(int postid){
            //int postid = 1;
            int likecount = 0;
            try{
           conn = u.getConnection();
           stat = u.getStatement(conn);
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
            return "newsFeed";
        }*/
    
        

    
}
