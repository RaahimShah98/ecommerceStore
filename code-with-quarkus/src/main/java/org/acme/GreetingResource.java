package org.acme;

import Connections.Connections;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/hello1")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    
    public void hello() throws SQLException {
        
        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "Select * From Users";
        ResultSet rs = st.executeQuery(query);
        System.out.println("");
        while (rs.next()) {
        
            String user_id = rs.getString("user_id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String status = rs.getString("status");
            
            System.out.println("User ID: " + user_id + ", Email: " + email + ", Password: " + password + ", address: "+ address + ", status: " + status + "\n");
        
        }
        rs.close();
        st.close();
        conn.close();
    }

//    @POST
//    @Produces(MediaType.TEXT_PLAIN)
//    public String send() {
//        return "yayyyyyyyyyyy its working";
//    }
}
