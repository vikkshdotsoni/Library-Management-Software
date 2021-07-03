
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Db { //Connection class : 
    public Connection con; //connect java program with database
    private PreparedStatement pst; //Quwry ko database me leke jaega
    private ResultSet rs;//Result database se java tak leke aaega
    
    public static Connection getConnect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");//Driver of my sql
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS", "root", "Vikash@123");
            return con;
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }
    
    public PreparedStatement getPre(String query)//jab bhi pass krna hoga tab query use hoga
    {
        try
        {
            con=getConnect();
            pst=(PreparedStatement)con.prepareStatement(query);//Query Passes
        }catch(Exception e)
        {
            System.out.println("Exception in Connection"+e);
        }
        return pst;
    }
    public ResultSet getResult(String query)//jab bhi data lena hoga result se java tak
    {
        try
        {
           con=getConnect();
           pst=(PreparedStatement)con.prepareStatement(query);
           rs=pst.executeQuery();
        }catch(Exception e)
        {
            System.out.println("Exception in ResultSet"+e);
        }
        return rs;
    }
    
    public void close()//Session management expire ho jaega ek certain time pe 
    {
        try
        {
            if(con!=null)
            {
                con.close();
            }
        }catch(Exception e)
        {
            System.out.println("Exception in close(): "+e.getMessage());
        }
    }
    
}
