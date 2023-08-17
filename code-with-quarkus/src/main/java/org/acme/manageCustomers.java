package org.acme;

import Connections.Connections;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

class customerObject {

    private String name;
    private String email;
    private String phoneNo;

    private String address;

     customerObject() {
        this.name = "";
        this.email = "";
        this.phoneNo = "";
        this.address = "";
     }
     
    customerObject(String fname, String lname, String email, String phoneNo, String address) {
        this.name = fname + " " + lname;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

   

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }
}

@Path("/manageCustomer")
public class manageCustomers {

//view all users
    @Path("/viewCustomers")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList viewUser() throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "Select * From customer";
        ResultSet rs = st.executeQuery(query);

        ArrayList userInfoArray = new ArrayList();

        String message = "";
        int i = 0;
        while (rs.next()) {

            JSONObject user = new JSONObject();
            String id = rs.getString("cust_id");
            String name = rs.getString("custName");
            String phoneNo = rs.getString("custPhone");
            String email = rs.getString("custEmail");

            String address = rs.getString("custAddress");

            message = "id: " + id;
            System.out.println(message);
            user.put("id", id);
            user.put("name", name);
            user.put("email", email);
            user.put("phoneNo", phoneNo);
            user.put("address", address);

            userInfoArray.add(user);

        }

        rs.close();
        st.close();
        conn.close();

        return userInfoArray;
    }

//ADD A USER 
    @Path("/addCustomer")
    @POST
    public String addOrder(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {
        Connection conn = Connections.CreateCon();
        System.out.println(body);

        String insrtStatement = "INSERT INTO customer( custName , custPhone, custEmail , custAddress) VALUES ( ? , ? , ? , ? )";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        customerObject customerCreate;

        JSONObject jsonObject = new JSONObject(body);
        System.out.println(jsonObject);

        String fname = jsonObject.getString("firstName");
        String lname = jsonObject.getString("lastName");
        String email = jsonObject.getString("email");
        String phoneNo = jsonObject.getString("phoneNo");
        String address = jsonObject.getString("Address");

        customerCreate = new customerObject();
        customerCreate = new customerObject(fname, lname, email, phoneNo, address);

        statement.setString(1, customerCreate.getName());
        statement.setString(2, customerCreate.getPhoneNo());
        statement.setString(3, customerCreate.getEmail());
        statement.setString(4, customerCreate.getAddress());

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Customer with id: " + email + " added to DataBase";
        } else {
            message = "User with id: " + email + " Already Exists";
        }
        statement.close();
        conn.close();

        return message;
    }

// DELETE A USER
    @Path("/deleteOrder")
    @POST

    public String deleteUser(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {
        Connection conn = Connections.CreateCon();
        String insrtStatement = "DELETE FROM Users WHERE user_id = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

//        JSONObject jsonObject = new JSONObject(body);
//        String id = jsonObject.getString("id");
        System.out.println(body);

        statement.setString(1, body);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("User Deleted successfully.");

        } else {
            System.out.println("User delete Failed");
        }
        statement.close();

        String message = "";
        if (rowsAffected > 0) {
            message = "User with id: " + body + " Deleted";
        } else {
            message = "User with id: " + body + " Not Found";
        }
        statement.close();
        conn.close();

        return message;
    }

}
