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
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

class userObject {

    private String id;

    private String name;
    private String email;
    private String phoneNo;
    private String password;

    private String address;
    private String status;
    private String Img;

    public userObject() {

        this.name = "";
        this.email = "";
        this.phoneNo = "";
        this.address = "";
        this.status = "";
        this.Img = "";
    }

    public userObject(String name, String email, String phoneNo, String address, String status, String image) {

        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.status = status;
        this.Img = image;
    }

    public userObject(String name, String email, String phoneNo, String password, String address, String status, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.address = address;
        this.status = status;
        this.Img = image;
    }

    //functions
    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return this.address;
    }

    public String getStatus() {
        return this.status;
    }

    public String getImg() {
        return Img;
    }
}

@Path("/manageUsers")
public class manageUser {

//view all users
    @Path("/viewUser")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList viewUser() throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "Select * From Users";
        ResultSet rs = st.executeQuery(query);

        ArrayList userInfoArray = new ArrayList();

        String message = "";
        int i = 0;
        while (rs.next()) {
            JSONObject user = new JSONObject();
            String id = rs.getString("user_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String phoneNo = rs.getString("phoneNo");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String status = rs.getString("status");
            String image = rs.getString("image");

            message = "id: " + id;
            System.out.println(message);
            user.put("id", id);
            user.put("name", name);
            user.put("email", email);
            user.put("phoneNo", phoneNo);
            user.put("password", password);
            user.put("address", address);
            user.put("status", status);
            user.put("image", image);
            userInfoArray.add(user);

        }

        rs.close();
        st.close();
        conn.close();

        return userInfoArray;
    }

//GET LOGIN FOR USERS
    @Path("/getUserStatus")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserStatus(@QueryParam("email") String email, @QueryParam("password") String password) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
//        Statement st = conn.createStatement();

        String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
        PreparedStatement pt = conn.prepareStatement(query);
        pt.setString(1, email);
        pt.setString(2, password);

        ResultSet rs = pt.executeQuery();

        String status = "";
        if (rs.next()) {

            status = rs.getString("admin");

            System.out.println(status);
        }else{
            status = "Invalid Login User Does Not Exist";
        }
        
        rs.close();
        pt.close();
        conn.close();

        return status;

    }

//ADD A USER 
    @Path("/addUser")
    @POST
    public String addUser(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {
        Connection conn = Connections.CreateCon();
        String insrtStatement = "INSERT INTO Users( name , email, phoneNo , password , address , status , image) VALUES ( ? , ?, ? , ? , ? , ?, ? )";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        userObject userCreate;

        JSONObject jsonObject = new JSONObject(body);

        String name = jsonObject.getString("Name");
        String email = jsonObject.getString("email");
        String phoneNo = jsonObject.getString("phoneNo");
        String address = jsonObject.getString("address");
        String password = jsonObject.getString("password");
        String status = jsonObject.getString("status");
        String image = jsonObject.getString("profilePicture");

        userCreate = new userObject();
        userCreate = new userObject(name, email, phoneNo, password, address, status, image);

        System.out.println(name);

        int i = 4;

        statement.setString(1, userCreate.getName());
        statement.setString(2, userCreate.getEmail());
        statement.setString(3, userCreate.getPhoneNo());
        statement.setString(4, userCreate.getPassword());
        statement.setString(5, userCreate.getAddress());
        statement.setString(6, userCreate.getStatus());
        statement.setString(7, userCreate.getImg());
        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "User with id: " + name + " added to DataBase";
        } else {
            message = "User with id: " + name + " Already Exists";
        }
        statement.close();
        conn.close();

        return message;
    }

// DELETE A USER
    @Path("/deleteUser")
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

// UPDATE USER 
    @Path("/updateUser")
    @POST
    public String updateUser(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insrtStatement = "UPDATE Users SET name = ? , email = ? , phoneNo=? , password = ? , address = ? , status = ? ,image = ? WHERE email = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        userObject userCreate;

        System.out.println("HELLO THIS IS UPDATE");

        JSONObject jsonObject = new JSONObject(body);

//        String id = jsonObject.getString("id");
        String name = jsonObject.getString("Name");
        String email = jsonObject.getString("email");
        String phoneNo = jsonObject.getString("phoneNo");
        String password = jsonObject.getString("password");
        String address = jsonObject.getString("address");
        String status = jsonObject.getString("status");
        String profilePicture = jsonObject.getString("profilePicture");

        userCreate = new userObject();
        userCreate = new userObject(name, email, phoneNo, password, address, status, profilePicture);

        System.out.println("HELLO THIS IS UPDATE");

        statement.setString(1, userCreate.getName());
        statement.setString(2, userCreate.getEmail());
        statement.setString(3, userCreate.getPhoneNo());
        statement.setString(4, userCreate.getPassword());
        statement.setString(5, userCreate.getAddress());
        statement.setString(6, userCreate.getStatus());
        statement.setString(7, userCreate.getImg());
        statement.setString(8, userCreate.getEmail());

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "User with email: " + email + " Updated";
        } else {
            message = "User with email: " + email + " Not Found";
        }
        statement.close();
        conn.close();

        return message;
    }

}
