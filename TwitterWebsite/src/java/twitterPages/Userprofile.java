 package twitterPages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Userprofile {
    
    private Login log;
    private String username;
    private String Birthdate;
    private String School;
    private String[] interest = new String[3];
    public int tweet_id;
    public String post_id;
    public String post;
    public int likecount;
    Timestamp time = new Timestamp(System.currentTimeMillis());
    ArrayList<Userprofile> postrecords= new ArrayList<>();
    
    public Userprofile() {
    }
    
    public Userprofile(int tweet_id, String post_id, String post, int likecount, Timestamp time) {
        this.tweet_id = tweet_id;
        this.post_id = post_id;
        this.post = post;
        this.likecount = likecount;
        this.time=time;
    }

   
     public String setprofile(String id)
     {
           try
            {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch(Exception e)
            {
                return "internalError";
            }
            final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        
            Connection con = null;
            Statement stat = null;
            ResultSet rs = null;
            
            if(username != null)
            {
                try
                {
                 con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
                    stat = con.createStatement();

                rs = stat.executeQuery("select * from tweet_account where email = '"+id+"'");

                if(rs.next())
                {
                    int r = stat.executeUpdate("update tweet_account set user_id= '"+username+"' where twitter_id = '"+rs.getInt(1)+"'");
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
                        return "internalError!";
                    }
                }   
     }
            
            if(Birthdate != null && School != null)
            {
                try
                {
                 con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
                  stat = con.createStatement();
                  
                  rs = stat.executeQuery("select * from tweet_account where email = '"+id+"'");
                  
                   
                  
                  
                  if(rs.next())
                  {
                      int r = stat.executeUpdate("insert into tweet_profile values ('"+rs.getInt(1)+"', '"+Birthdate+"', '"+School+"')");
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
                        return "internalError!";
                    }
                }   
            }
            if(interest.length == 3&& interest[0].equals("sport") && interest[1].equals("technology") && interest[2].equals("politics"))
            {
                try
                {
                 con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
                  stat = con.createStatement();
                  
                  rs = stat.executeQuery("select * from tweet_account where email = '"+id+"'");
                  
                   
                  
                  
                  if(rs.next())
                  {
                      int s = stat.executeUpdate("insert into sportinterest values ("+rs.getInt(1)+", '"+interest[0]+"')");
                      int t = stat.executeUpdate("insert into techinterest values ("+rs.getInt(1)+", '"+interest[1]+"')");
                      int p = stat.executeUpdate("insert into politicsinterest values ("+rs.getInt(1)+", '"+interest[2]+"')");
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
                        return "internalError!";
                    }
                }   
                
            }
            if(interest[0].equals("sport") && interest[1].equals("technology"))
            {
                
                try
                {
                 con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
                  stat = con.createStatement();
                  
                  
                  rs = stat.executeQuery("select * from tweet_account where email = '"+id+"'");
                  if(rs.next())
                  {
                      int s = stat.executeUpdate("insert into sportinterest values ("+rs.getInt(1)+", '"+interest[0]+"')");
                  }
                  
                  rs = stat.executeQuery("select * from tweet_account where email = '"+id+"'");
                  if(rs.next())
                  {
                      int t = stat.executeUpdate("insert into techinterest values ("+rs.getInt(1)+", '"+interest[1]+"')");
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
                        return "internalError!";
                    }
                }   
                
            }
            
            return"newsFeed";
}
     public String blockaccount(String email)
    {
        int id=0;
        try
            {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch(Exception e)
            {
                return "internalError";
            }
            final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        
            Connection con = null;
            Statement stat = null;
            ResultSet rs = null;
             try
                {
                    con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
                    stat = con.createStatement();

        rs=stat.executeQuery("Select Twitter_id from tweet_account where email='"+email+"'");
       if (rs.next())
       {
        id = rs.getInt(1);
       }
        rs = stat.executeQuery("Select * from tweet_post where tweet_id="+id);
        while(rs.next())
            {
                String s1=rs.getString(2);
                String s2=rs.getString(3);
                
              postrecords.add(new Userprofile(rs.getInt(1),s1,s2,rs.getInt(4),rs.getTimestamp(5)));
            }
       stat.executeUpdate("Delete from tweet_post where tweet_id="+id);
        for(Userprofile records: postrecords)
        {
            stat.executeUpdate("Insert into deactive_tweet_post values ('"+records.getTweet_id()+"','"+records.getPost_id()+"','"+records.getPost()+"','"+records.getLikecount()+"','"+records.getTime()+"')");
        }
        }
        catch(SQLException e){
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
              return "deactivationpage";
}
      public Login getLog() {
        return log;
    }

    public void setLog(Login log) {
        this.log = log;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String Birthdate) {
        this.Birthdate = Birthdate;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String School) {
        this.School = School;
    }

    public String[] getInterest() {
        return interest;
    }

    public void setInterest(String[] interest) {
        this.interest = interest;
    }
    
     public int getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(int tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
}
