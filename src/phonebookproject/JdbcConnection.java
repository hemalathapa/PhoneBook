
package phonebookproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcConnection 
{
    public static Connection conn;

    public void Setup() 
    {
        
        
        try
        {
            
            
            File f= new File("src/Phonebookdb.properties");
            FileInputStream fis = new FileInputStream(f);
            
            Properties p= new Properties();
            p.load(fis);
            
            String driverName=p.getProperty("driverName");
            String url=p.getProperty("url");
            String user=p.getProperty("user");
            String password=p.getProperty("password");
            
            
            Class.forName(driverName);  //invoking jdbc driver
            conn = DriverManager.getConnection(url,user,password); //Establishing the connection to the database.
            System.out.println("Connection Established");
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JdbcConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(JdbcConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
