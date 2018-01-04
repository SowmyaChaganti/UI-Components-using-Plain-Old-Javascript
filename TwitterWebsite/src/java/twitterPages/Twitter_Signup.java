/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;

/**
 *
 @author Deepak
 */
@ManagedBean
public class Twitter_Signup{
 private String Fisrtname;
    private String Lastname;
    private String Email;
    private String Password;
    private String SecurityQuestion;
    private String Answer;
    private String Country;
    
    public String signup()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        int countSuccesful=0;
        final String DB_URL= "jdbc:mysql://mis-sql.uhcl.edu/mirdwald7006";
        Connection conn=null;
        Statement stat = null;
        ResultSet rs = null;
        
        try
        {
            conn= DriverManager.getConnection(DB_URL,"mirdwald7006", "1467084");
            stat = conn.createStatement();
            
            rs = stat.executeQuery("select * from tweet_account where email = '"+Email+"'" );
            
            if(rs.next())
            {
                return "internalError";
                        
            }
            else
            {
                int zero = 0;
                int t = stat.executeUpdate("insert into tweet_account values ( "+zero+", '"+Fisrtname+Lastname+"', '"+Fisrtname+"', '"+Lastname+"', '"
                +Email+"', '"+Password+"', '"+Country+"', '"+SecurityQuestion+"', '"+Answer+"')");  
                countSuccesful++;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            try
            {
                conn.close();
                stat.close();
                rs.close();
                
                Fisrtname ="";
                Lastname ="";
                Email ="";
                Password ="";
                Country ="";
                SecurityQuestion ="";
                Answer ="";
                if(countSuccesful==1)
                   return "index";
                else
                    return "";
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return "internalError";
            }
            
        }
    }
     
   
public String getFisrtname() {
        return Fisrtname;
    }

    public void setFisrtname(String Fisrtname) {
        this.Fisrtname = Fisrtname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getSecurityQuestion() {
        return SecurityQuestion;
    }

    public void setSecurityQuestion(String SecurityQuestion) {
        this.SecurityQuestion = SecurityQuestion;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }
    
}
  
