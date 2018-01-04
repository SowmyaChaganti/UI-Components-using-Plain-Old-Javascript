/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterPages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Deepak
 */
@Named(value = "post")
@RequestScoped
public class post {

    String postcontent="";

    public String getPostcontent() {
        return postcontent;
    }
    
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String postComment(){
        String check = comment;
       // System.out.println(comment);
        return "newsFeed";
    }

    public void setPostcontent(String postcontent) {
        this.postcontent = postcontent;
    }
    
    public post() {
    }
    public String post(int twitterID)
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
        String maxid = "";
        int likecount=0;
        Timestamp time = new Timestamp(System.currentTimeMillis());

        try
        {
            con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
            stat = con.createStatement();
            rs = stat.executeQuery("Select max(post_id) from tweet_post");
            if(rs.next())
            {
                maxid = rs.getString(1);
                maxid =maxid+1;
                maxid =Integer.toString(Integer.parseInt(maxid));
                int t = stat.executeUpdate("Insert into tweet_post values ( '"+twitterID+"','"+maxid+"','"+postcontent+"','"+likecount+"','"+time+"')"); 
                
                
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
                postcontent = "";
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
      }  
    

    
}
