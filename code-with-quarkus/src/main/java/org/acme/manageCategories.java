/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.acme;

import Connections.Connections;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

/**
 *
 * @author raahi
 */
class categoryObject {

    private String id;
    private String name;

    public categoryObject() {
        this.id = "";
        this.name = "";

    }

    public categoryObject(String id, String name) {
        this.id = id;
        this.name = name;

    }

    //functions 
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}


@Path("/manageCategory")
public class manageCategories {

//VIEW CATEGORIES
    @Path("/viewCategory")
    @GET
    @Produces(MediaType.TEXT_PLAIN)

    public ArrayList viewCategory() throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "Select * From Categories";
        ResultSet rs = st.executeQuery(query);
        String message = "";
        
        
        ArrayList categoryArray = new ArrayList();
        
        while (rs.next()) {
            JSONObject category = new JSONObject();
            
            String id = rs.getString("category_id");
            String name = rs.getString("name");

//            message = "Category ID: " + id + ", Category Name: " + name + "\n";
            
            System.out.println(message);
            category.put("id" , id);
            category.put("name" , name);
            
            categoryArray.add(category);
        }
        rs.close();
        st.close();
        conn.close();

        return categoryArray;
    }

    @Path("/addCategory")
    @POST
    public String addUser(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insertStatement = "INSERT INTO Categories( category_id , name) VALUES ( ? , ? )";
        PreparedStatement statement = conn.prepareStatement(insertStatement);

        categoryObject objectCreate;

        JSONObject jsonObject = new JSONObject(body);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");

        objectCreate = new categoryObject();
        objectCreate = new categoryObject(id, name);

        System.out.println(name);


        statement.setString(1, objectCreate.getId());
        statement.setString(2, objectCreate.getName());

        String message = "";
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            message = "Category : " + name + " added to database. q";

        } else {
            message = "Category : " + name + " already exists";

        }
        statement.close();
        conn.close();

        return message;

    }

    @Path("/deleteCategory")
    @POST
    public String deleteUser(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insrtStatement = "DELETE FROM Categories WHERE category_id = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        statement.setString(1, body);

        String message = "";
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            message = "Category : " + body + " Removed ";

        } else {
            message = "Category : " + body + " does not exist";

        }
        statement.close();
        conn.close();

        return message;
    }
    
    @Path("/updateCategory")
    @POST
    public String updateCategory(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insrtStatement = "UPDATE Categories SET name = ? WHERE category_id = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        categoryObject categoryCreate;

        JSONObject jsonObject = new JSONObject(body);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
   

        categoryCreate = new categoryObject();
        categoryCreate = new categoryObject(id, name);

        System.out.println(name);


        statement.setString(2, categoryCreate.getId());
        statement.setString(1, categoryCreate.getName());
        

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Category with id: " + id + " Updated";
        } else {
            message = "Category with id: " + id + " Not Found";
        }
        statement.close();
        conn.close();

        return message;
    }
}
