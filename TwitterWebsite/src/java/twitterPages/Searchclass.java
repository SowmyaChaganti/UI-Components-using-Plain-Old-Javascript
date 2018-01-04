package twitterPages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class Searchclass {
    Connection conn;
    Statement stat;
    ResultSet rs;
    ResultSet rs1;
    private int twitter_id;
    private String user_id;
    private String Firstname;
    private String Lastname;
    private String serachkey;
    private int selectnumber;
    private int post_id;
    private String post;
    private int likecount;
    private ArrayList<Searchclass> searched_user = new ArrayList<>();
    private ArrayList<Searchclass> searchitem = new ArrayList<>();
    private ArrayList<Searchclass> User = new ArrayList<>();
    

    public Searchclass() {
    }

    
    public Searchclass(int twitter_id, String Firstname, String Lastname, int sn) {
        this.twitter_id = twitter_id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        selectnumber = sn;
    }

    public Searchclass(int twitter_id, String user_id, String Firstname, String Lastname, int post_id, String post, int likecount) {
        this.twitter_id = twitter_id;
        this.user_id = user_id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.post_id = post_id;
        this.post = post;
        this.likecount = likecount;
    }

    public Searchclass(int twitter_id, String user_id, String Firstname, String Lastname) {
        this.twitter_id = twitter_id;
        this.user_id = user_id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
    }
    

    //methods
   public void follow(int logerid, int followerid)
      {
          final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try
        {
            con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
            stat = con.createStatement();
            
            rs = stat.executeQuery("select * from follow where tweet_id = "+logerid+" and follower_id = "+followerid);
            if(!rs.next())
            {
                stat.executeUpdate("insert into follow values ("+null+", "+logerid+", "+followerid+")");
            }
            
            rs = stat.executeQuery("select * from follow where tweet_id = "+logerid+" and follower_id = "+followerid);
            if(rs.next())
            {
                stat.executeQuery("delete from sportinterest where twitter_id = "+followerid);
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
      }
    

      public String search(int id)
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
            con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
            stat = con.createStatement();
            selectnumber = 0;
            rs = stat.executeQuery("Select * from tweet_account where user_id = '"+serachkey+"' or firstname = '"+serachkey+"' and twitter_id != "+id);
            
           while(rs.next())
           {
               searchitem.clear();
               searchitem.add(new Searchclass(rs.getInt("Twitter_id"), rs.getString("firstname"), rs.getString("lastname"), selectnumber));
               selectnumber++;
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
            return "Searchresult";
    }
      public ArrayList<Searchclass> SelectedUser(int id)
      {
           /*try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            return "internalError";
        }*/
          
        //access the database and then login
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try
        {
            con = DriverManager.getConnection(DB_URL, "mirdwald7006", "1467084");
            stat = con.createStatement();
            rs = stat.executeQuery("Select * from tweet_account where twitter_id = "+id);
            if(rs.next())
            {
                User.clear();
               User.add(new Searchclass(rs.getInt("twitter_id"), rs.getString("user_id"), rs.getString("firstname"), rs.getString("lastname")));
            }
            rs = stat.executeQuery("SELECT o.user_id, o.firstname, o.lastname, o.twitter_id, t.post_id, t.post, t.likecount from tweet_account o inner join tweet_post t on o.twitter_id = t.tweet_id where o.twitter_id = "+id);////Write a join query tweetaccount and tweet post.
        while(rs.next()){
            searched_user.add(new Searchclass(rs.getInt(4),rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(5),rs.getString(6), rs.getInt(7)));
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
                //return "internalError!";
            }
        }
          return searched_user;
      }
      
      
      
      //getters and setters
    public int getSelectnumber() {
        return selectnumber;
    }

    public void setSelectnumber(int selectnumber) {
        this.selectnumber = selectnumber;
    }
    
    public String getSerachkey() {
        return serachkey;
    }

    public void setSerachkey(String serachkey) {
        this.serachkey = serachkey;
    }
    
    public int getTwitter_id() {
        return twitter_id;
    }

    public void setTwitter_id(int twitter_id) {
        this.twitter_id = twitter_id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public ArrayList<Searchclass> getSearchitem() {
        return searchitem;
    }

    public void setSearchitem(ArrayList<Searchclass> searchitem) {
        this.searchitem = searchitem;
    }
     public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
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

    public ArrayList<Searchclass> getSearched_user() {
        return searched_user;
    }

    public void setSearched_user(ArrayList<Searchclass> searched_user) {
        this.searched_user = searched_user;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<Searchclass> getUser() {
        return User;
    }

    public void setUser(ArrayList<Searchclass> User) {
        this.User = User;
    }

}
