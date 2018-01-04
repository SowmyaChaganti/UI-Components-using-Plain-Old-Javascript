package twitterPages;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

@Named(value = "login")
@ManagedBean
@SessionScoped
public class Login implements Serializable {

     private String email;
     private int count;
     private String username;
     private String psw;
     int twitterid;
     User currentUser;
     
    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    public String login()
    {
        
        
        boolean profileset = false;
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
            
            rs = stat.executeQuery("select * from tweet_account where email = '"+username+"'");
            
            if(rs.next())
            { 
                twitterid = rs.getInt("Twitter_id");
                    if(psw.equals(rs.getString(6)))
                    {
                        Util u = new Util();
                        currentUser = u.userDetails(twitterid);
                        HttpSession hs = Util.getSession();
                        hs.setAttribute("CurrentUser", currentUser);
                        rs = stat.executeQuery("select * from tweet_profile where twitter_id = "+twitterid+"");
                        if(rs.next())
                        {
                            profileset = false;
                        }
                        else
                        {
                            profileset = true;
                        }
                        
                    }
                    else
                    {
                        return "password is not right.";
                    }
            }
                else
                {
                    return "incorrectlogin";
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
        
        if(profileset == true)
        {
            count++;
            return "createUserProfile";
                    
        }
        else
            return "newsFeed";
        
        
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
}
