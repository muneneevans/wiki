/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wiki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author overmars
 */
public class DBManager 
{
    Connection connection1 = null ; 
    Statement statement1 = null ; 
    ResultSet resultset1 = null ;
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    String DB_URL = "jdbc:mysql://localhost/wiki";

    //  Database credentials
    String USER = "root";
    String PASS = "";
    
    public DBManager() 
    {
        
        System.out.println("starting");
        
    }
    
    public void Insert(String query_string ) 
    {
        try{
        connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
        statement1 = connection1.createStatement();        
        statement1.execute(query_string) ;
        
        System.out.println(query_string);
        }
        catch(Exception e)
        {
            System.out.println("error " + e );
        }
    }
        
}
