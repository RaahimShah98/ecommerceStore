package org.acme;

import Connections.Connections;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
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

class itemObject {

    private String id;
    private String name;
    private String category;
    private String price;
    private String description;
    private String quantity;
    private String imgSrc;

    public itemObject() {
        this.id = "";
        this.name = "";
        this.category = "";
        this.price = "";
        this.description = "";
        this.quantity = "";
        this.imgSrc = "";
    }

    public itemObject(String id, String name, String category, String price, String description, String quantity, String imgSrc) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.imgSrc = imgSrc;
    }

    //functions 
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getimgSrc() {
        return imgSrc;
    }

}

@QuarkusMain
@Path("/manageItems")
public class manageItems {
    public static void main(String args[]){
        Quarkus.run(args);
    }

//view all users
    @Path("/viewItems")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList viewItems() throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "Select * From items";
        ResultSet rs = st.executeQuery(query);

        ArrayList itemInfoArray = new ArrayList();

        String message = "";
        System.out.println("in View Item");
        while (rs.next()) {
            JSONObject item = new JSONObject();
            String id = rs.getString("item_id");
            String name = rs.getString("itemName");
            String category = rs.getString("category_id");
            String price = rs.getString("price");
            String description = rs.getString("imgDescription");
            String quantity = rs.getString("quantity");
            String imgUrl = rs.getString("imageURL");

//            message = "id: " + id;
//            System.out.println(message);
            item.put("id", id);
            item.put("name", name);
            item.put("category", category);
            item.put("price", price);
            item.put("description", description);
            item.put("quantity", quantity);
            item.put("imgURL", imgUrl);

            itemInfoArray.add(item);

        }

        rs.close();
        st.close();
        conn.close();

        return itemInfoArray;
    }

//    GET LOGIN FOR USERS
    @Path("/getItemByCategory")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList getUserStatus(@QueryParam("categoryID") int categoryID) throws SQLException, MalformedURLException, ProtocolException, IOException {

         Connection conn = Connections.CreateCon();
       
         System.out.println("IN VIEW BY ID: " + categoryID);
//        System.out.println("categoryID: " + categoryID);
        String query = " Select * From items where category_id = " + categoryID;
        
        PreparedStatement pt = conn.prepareStatement(query);
        
//        pt.setInt(1, categoryID);
        
        ResultSet rs = pt.executeQuery(query);

        ArrayList itemInfoArray = new ArrayList();

        String message = "";

        while (rs.next()) {
            JSONObject item = new JSONObject();
            String id = rs.getString("item_id");
            String name = rs.getString("itemName");
            String category = rs.getString("category_id");
            String price = rs.getString("price");
            String description = rs.getString("imgDescription");
            String quantity = rs.getString("quantity");
            String imgUrl = rs.getString("imageURL");

   
            item.put("id", id);
            item.put("name", name);
            item.put("category", category);
            item.put("price", price);
            item.put("description", description);
            item.put("quantity", quantity);
            item.put("imgURL", imgUrl);

            itemInfoArray.add(item);

        }

        rs.close();
        pt.close();
        conn.close();

        return itemInfoArray;

    }
    
//ADD A USER 
    @Path("/addItem")
    @POST
    public String addItem(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insrtStatement = "INSERT INTO Items(item_id , itemName , category_id , price , imgDescription, quantity , imageURL) VALUES (? , ? , ? , ? , ? , ?,? )";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

//        System.out.println("CALLED");
        itemObject itemCreate;

        JSONObject jsonObject = new JSONObject(body);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("Name");
        String category = jsonObject.getString("Category");
        String description = jsonObject.getString("Description");
        String price = jsonObject.getString("Price");
        String quantity = jsonObject.getString("Quantity");
        String imgURL = jsonObject.getString("imgSrc");

        itemCreate = new itemObject();
        itemCreate = new itemObject(id, name, category, price, description, quantity, imgURL);

  

        statement.setString(1, itemCreate.getId());
        statement.setString(2, itemCreate.getName());
        statement.setString(3, itemCreate.getCategory());
        statement.setString(4, itemCreate.getPrice());

        statement.setString(5, itemCreate.getDescription());

        statement.setString(6, itemCreate.getQuantity());
        statement.setString(7, itemCreate.getimgSrc());

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Item with id: " + id + " added to DataBase";
        } else {
            message = "Item with id: " + id + " Already Exists";
        }
        statement.close();
        conn.close();

        return message;
    }

// DELETE A USER
    @Path("/deleteItem")
    @POST
    public String deleteItem(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {
        Connection conn = Connections.CreateCon();
        String insrtStatement = "DELETE FROM items WHERE item_id = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        System.out.println(body);

        statement.setString(1, body);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Item Deleted successfully.");
        } else {
            System.out.println("Item delete Failed");
        }
        statement.close();

        String message = "";
        if (rowsAffected > 0) {
            message = "Item with id: " + body + " Deleted";
        } else {
            message = "Item with id: " + body + " Not Found";
        }
        statement.close();
        conn.close();

        return message;
    }

// UPDATE USER 
    @Path("/updateItem")
    @POST
    public String updateUser(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insrtStatement = "UPDATE items SET itemName = ? , category_id = ? , price = ? , imgDescription = ? , quantity = ? , imageURL = ? WHERE item_id = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        itemObject itemCreate;

        JSONObject jsonObject = new JSONObject(body);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("Name");
        String price = jsonObject.getString("Price");
        String description = jsonObject.getString("Description");
        String quantity = jsonObject.getString("Quantity");
        String category = jsonObject.getString("Category");
        String image = jsonObject.getString("imgSrc");

        itemCreate = new itemObject();
       
                 itemCreate = new itemObject(id, name, category, price, description, quantity, image);

        System.out.println(name);


        statement.setString(7, itemCreate.getId());
        statement.setString(1, itemCreate.getName());
        statement.setString(2, itemCreate.getCategory());
        statement.setString(3, itemCreate.getPrice());
        statement.setString(4, itemCreate.getDescription());
        statement.setString(5, itemCreate.getQuantity());
        statement.setString(6, itemCreate.getimgSrc());
        

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Item with id: " + id + " Updated";
        } else {
            message = "Item with id: " + id + " Not Found";
        }
        statement.close();
        conn.close();

        return message;
    }

}
