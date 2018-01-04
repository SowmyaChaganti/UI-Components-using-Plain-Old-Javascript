package twitterPages;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped

public class forgot_password  implements Serializable {
        private String id;
        private String answer;
        private String inputanswer;
        private String psw;
        private String showpsw;

    public String getShowpsw() {
        return showpsw;
    }

    public void setShowpsw(String showpsw) {
        this.showpsw = showpsw;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    public String getInputanswer() {
        return inputanswer;
    }

    public void setInputanswer(String inputanswer) {
        this.inputanswer = inputanswer;
    }
        private String password;
         private String que;

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }
        public forgot_password(){
       
        }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
       
   public String display_password()
   {
       return("");
   }
   public String checkanswer()
   {
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
       }
        Connection connection = null; 
        Statement statement = null;   
        ResultSet rs = null;   
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "mirdwald7006", "1467084");   
            statement = connection.createStatement();
            rs=statement.executeQuery("Select * from tweet_account where email= '"+id+"'");
            if(rs.next())
            {
            answer=rs.getString(9);
             
            if(inputanswer.equals(answer))
            {
               psw=rs.getString(6); 
                return "forgotPassword";
            }
             
            }
            else
            {
             return("you dont have account of twitter for this email id");
            }
            return "forgotPassword";
             
        }
        catch (SQLException e)
        {
            e.printStackTrace();
                
        }
        finally
        {
            try
            {
                rs.close();
                statement.close();
                connection.close();
                
            }
            catch (Exception e)
            {
                 
                e.printStackTrace();
            }
        }
         return "psw";    
        
     
   }
   public String submit_id()
   {
       try
        {
           Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
       }
       
        Connection connection = null; 
        Statement statement = null;   
        ResultSet rs = null;   
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "mirdwald7006", "1467084");   
            statement = connection.createStatement();
            rs=statement.executeQuery("Select * from tweet_account where email= '"+id+"'");
            if(rs.next())
            {
            que=rs.getString(8);
            
            return "forgotPassword";
           
            }
            else
            {
              return("you dont have account of twitter for this email id");
            }
             
        }
        catch (SQLException e)
        {
            e.printStackTrace();
                
        }
        finally
        {
            try
            {
                rs.close();
                statement.close();
                connection.close();
                
            }
            catch (Exception e)
            {
                 
                e.printStackTrace();
            }
        }
         return "forgotPassword";    
        }
        
    }
    

