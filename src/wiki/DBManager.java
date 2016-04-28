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
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

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
    
    public int InsertPage(page p )
    {
        try
        {
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            statement1 = connection1.createStatement();        
            String query_string = "INSERT INTO page VALUES(NULL , '" +  p.title + "')";
            System.out.println(query_string); 
            statement1.execute(query_string) ;
            System.out.println("page added"); 
                               
            String select_string = "SELECT MAX(page_id) AS page_id FROM page LIMIT 1";
            statement1 = connection1.createStatement();
            resultset1 = statement1.executeQuery(select_string);
            while(resultset1.next())
            {
                //System.out.println(resultset1.getInt("page_id"));
                return resultset1.getInt("page_id");
            }
            return 0 ; 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return 0 ;
        }
    }
    
    public void InsertUserPage(user_page up)
    {
       try
        {
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            statement1 = connection1.createStatement();        
            String query_string = "INSERT INTO user_page VALUES ( NULL , " + up.user_id + "," + up.page_id + ")";
            System.out.println(query_string); 
            statement1.execute(query_string) ;
            System.out.println("user_page added"); 
        }            
        catch(Exception e)
        {
            System.out.println(e.getMessage());          
        }
    }
    
    public int InsertFile(file f)
    {
        try 
        {
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            statement1 = connection1.createStatement();        
            String query_string = "INSERT INTO file VALUES (NULL , '" + f.file_name + "')";
            System.out.println(query_string); 
            statement1.execute(query_string) ;
            System.out.println("file added"); 
                               
            String select_string = "SELECT MAX(file_id) AS file_id FROM file LIMIT 1";
            statement1 = connection1.createStatement();
            resultset1 = statement1.executeQuery(select_string);
            while(resultset1.next())
            {
                //System.out.println(resultset1.getInt("page_id"));
                return resultset1.getInt("file_id");
            }
            return 0 ; 
        }
        catch(Exception e)
        {
            return 0 ; 
        }
    }
    
    public void InsertPageFile(page_file pf)
    {
        try
        {
            String query_string = "";
            int max_version ; 
            // get max version number
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            String select_string = "SELECT MAX(version_number) as max_version from page_file where page_file.page_id = "+pf.page_id+" limit 1";
            statement1 = connection1.createStatement();
            try
            {            
                // trying to get largest version number
                resultset1 = statement1.executeQuery(select_string);
                while(resultset1.next())
                {
                    //System.out.println(resultset1.getInt("page_id"));
                    max_version = resultset1.getInt("max_version") ;
                    query_string = "INSERT INTO page_file VALUES (NULL , " +pf.page_id+ "," +pf.file_id+ "," +(max_version + 1)+","+ pf.user_id+ ")";
                }            
            }
            catch(Exception exp)
            {
                System.out.println("exp");
                //if it fails, there is no version, hence add the first o
                query_string = "INSERT INTO page_file VALUES (NULL , " +pf.page_id+ "," +pf.file_id+ "," +1+","+pf.user_id+ ")";
                
            }
            statement1 = connection1.createStatement();
            System.out.println(query_string); 
            statement1.execute(query_string) ;
            System.out.println("page_file added");                 
            
        }
        catch(Exception e)
        {
            System.out.println("error " + e.getMessage());
        }
    }
    
    public ArrayList<page> GetPages()
    {
        try
        {
            ArrayList<page> pagelist= new ArrayList<page>();
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            String select_string = "SELECT * FROM page";
            statement1 = connection1.createStatement();
            resultset1 = statement1.executeQuery(select_string);
            while(resultset1.next())
            {
                System.out.println(resultset1.getInt("page_id"));
                page  p = new page();
                p.page_id = resultset1.getInt("page_id");
                p.title = resultset1.getString("title");                
                pagelist.add(p);
            }
            
            return pagelist ;
        }
        catch(Exception e)
        {
            
            System.out.println(e.getMessage());
            return null ;
        }
        
    }
        
    public DefaultListModel<page> GetPagess()
    {
        try
        {
            DefaultListModel<page> pagelist= new DefaultListModel<page>();
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            String select_string = "SELECT * FROM page";
            statement1 = connection1.createStatement();
            resultset1 = statement1.executeQuery(select_string);
            while(resultset1.next())
            {
                System.out.println(resultset1.getInt("page_id"));
                page  p = new page();
                p.page_id = resultset1.getInt("page_id");
                p.title = resultset1.getString("title");                
                pagelist.addElement(p);
            }
            
            return pagelist ;
        }
        catch(Exception e)
        {
            
            System.out.println(e.getMessage());
            return null ;
        }
        
    }
    
    public ArrayList<file> GetPageFiles(page p)
    {
        try        
        {
            ArrayList<file> filelist= new ArrayList<file>();
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            String select_string = "SELECT * FROM file a , page_file b where a.file_id = b.file_id and b.page_id = "+p.page_id+" ORDER BY version_number DESC";
            
            statement1 = connection1.createStatement();
            resultset1 = statement1.executeQuery(select_string);
            while(resultset1.next())
            {
                System.out.println(resultset1.getInt("file_id"));
                file f = new file();
                f.file_id = resultset1.getInt("file_id") ; 
                f.file_name = resultset1.getString("file_name");
                filelist.add(f);
            }
            
            return filelist ;   
        }
        catch(Exception e)
        {
            return null ; 
        }
    }
    
    public ArrayList<user> GetPageUsers(page p)
    {
        try        
        {
            ArrayList<user> userlist= new ArrayList<user>();
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            String select_string = "SELECT * FROM users a , page_file b where a.user_id = b.user_id and b.page_id = "+p.page_id +" ORDER BY version_number DESC";
            
            statement1 = connection1.createStatement();
            resultset1 = statement1.executeQuery(select_string);
            while(resultset1.next())
            {
                System.out.println(resultset1.getInt("user_id"));
                user u = new user();
                u.user_id = resultset1.getInt("user_id");
                u.fname = resultset1.getString("fname");
                u.lname = resultset1.getString("lname");
                userlist.add(u);
            }
            
            return userlist ;   
        }
        catch(Exception e)
        {
            return null ; 
        }
    }
    
    public boolean ValidateUser(user u )
    {
        try
        {
            
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            String select_string = "SELECT * FROM `users` WHERE user_id = "+u.user_id+" and password = '"+ u.password + "' limit 1";
            statement1 = connection1.createStatement();
            try
            {
                resultset1 = statement1.executeQuery(select_string);
                int count = 0 ; 
                if(resultset1.next())
                {                 
                    count += resultset1.getInt("user_id");
                    System.out.println(count);
                    return true ;
                }
                else
                {                
                    return false;
                }
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
                return false ; 
            }
            
        }
        catch(Exception e)
        {
            return false ;
        }
    }
    
    public user GetUser(user u )
    {
        try
        {
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            String select_string = "SELECT * FROM `users` WHERE user_id = "+u.user_id+" limit 1";
            statement1 = connection1.createStatement();
            resultset1 = statement1.executeQuery(select_string);            
            while(resultset1.next())
            {                 
                u = new  user() ; 
                u.user_id = resultset1.getInt("user_id");
                u.fname = resultset1.getString("fname");
                u.lname = resultset1.getString("lname");
                u.role_id = resultset1.getInt("role_id");
                u.password = resultset1.getString("password");
                
                return u ; 

            }
            return null ;
        }
        catch(Exception e )
        {
            return null;
        }
    }
    
    public void InsertUser(user u )
    {
         try
        {
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            statement1 = connection1.createStatement();        
            String query_string = "INSERT INTO users values(Null , '" +u.fname+ "','" +u.lname+ "','"+u.password+"'," + u.role_id+")";
            System.out.println(query_string); 
            statement1.execute(query_string) ;
            System.out.println("user added"); 
        }            
        catch(Exception e)
        {
            System.out.println(e.getMessage());          
        }
    }
    
    public void UpdateUser(user u )
    {
         try
        {
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
            statement1 = connection1.createStatement();        
            String query_string = "UPDATE users SET fname = '"+u.fname+"' , lname = '"+u.lname+"' , passowrd = '"+u.password+"' , role_id = "+u.role_id+" WHERE user_id = "+u.user_id;
            System.out.println(query_string); 
            statement1.execute(query_string) ;
            System.out.println("user added"); 
        }            
        catch(Exception e)
        {
            System.out.println(e.getMessage());          
        }
    }
    
}
