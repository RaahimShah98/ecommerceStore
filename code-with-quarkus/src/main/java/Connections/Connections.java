package Connections;

import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connections {
    private static Connection con;
    
    public static Connection CreateCon(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercestore","root","Tedtalk98");
        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println("hello ");
            Logger.getLogger( Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
