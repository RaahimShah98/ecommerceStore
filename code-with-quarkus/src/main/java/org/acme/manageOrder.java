package org.acme;

import Connections.Connections;
import static com.ibm.asyncutil.iteration.AsyncIterator.error;
import static io.quarkus.logging.Log.error;
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

@Path("/manageOrder")
public class manageOrder {

//view all users
    @Path("/viewOrders")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList viewOrders() throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "Select * From orders ORDER BY order_id ASC";
        ResultSet rs = st.executeQuery(query);

        ArrayList userInfoArray = new ArrayList();

        String message = "";

        while (rs.next()) {
            JSONObject order = new JSONObject();
            String id = rs.getString("order_id");
            String email = rs.getString("custEmail");
            String status = rs.getString("status");

            System.out.println(message);

            order.put("id", id);
            order.put("email", email);
            order.put("status", status);
            

            userInfoArray.add(order);

        }

        rs.close();
        st.close();
        conn.close();

        return userInfoArray;
    }

    @Path("/viewItemSales")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList viewItemSales() throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "SELECT product_id , Count(*) as itemQuantity FROM ecommercestore.orderdetail GROUP BY product_id";
        ResultSet rs = st.executeQuery(query);

        ArrayList userInfoArray = new ArrayList();
        System.out.println(rs);
        String message = "";

        while (rs.next()) {
            JSONObject order = new JSONObject();
            String id = rs.getString("product_id");
            String  quantity= rs.getString("itemQuantity");

            System.out.println(message);

            order.put("productId", id);
            order.put("quantity", quantity );
           
            userInfoArray.add(order);

        }

        rs.close();
        st.close();
        conn.close();

        return userInfoArray;
    }
    
    @Path("/viewCustomerByArea")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList viewCustomerByArea() throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "SELECT custCity , COUNT(*) as people FROM ecommercestore.customer GROUP BY custCity ;";
        ResultSet rs = st.executeQuery(query);

        ArrayList userInfoArray = new ArrayList();
        System.out.println(rs);
        String message = "";

        while (rs.next()) {
            JSONObject order = new JSONObject();
            String id = rs.getString("custCity");
            String  quantity= rs.getString("people");

            System.out.println(message);

            order.put("productId", id);
            order.put("quantity", quantity );
           
            userInfoArray.add(order);

        }

        rs.close();
        st.close();
        conn.close();

        return userInfoArray;
    }
    
    
    @Path("/viewOrdersById")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public ArrayList viewOrdersById(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        System.out.println("BODY PASSED IS: " + body);

        Connection conn = Connections.CreateCon();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM orderdetail WHERE order_id = ?");

        preparedStatement.setString(1, body); // Bind value for the placeholder
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList orderInfoArray = new ArrayList();

        while (rs.next()) {
            JSONObject order = new JSONObject();
            String orderId = rs.getString("order_id");
            String productId = rs.getString("product_id");
            String productQuantity = rs.getString("quantity");

            order.put("orderId", orderId);
            order.put("productId", productId);
            order.put("productQuantity", productQuantity);

            orderInfoArray.add(order);

        }

        System.out.println(orderInfoArray);
//        rs.close();
//        st.close();
        conn.close();

        return orderInfoArray;
    }

//ADD A ORDER
    @Path("/addOrder")
    @POST
    public String addOrder(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {
        Connection conn = Connections.CreateCon();
        String message = "";
        JSONObject jsonObject = new JSONObject(body);

        String orderID = jsonObject.getString("orderid");

        //MAINTAINS orders Tables
        try {
            String insrtStatement = "INSERT INTO orders( order_id , custEmail, custPhone , status) VALUES ( ? , ?, ? , ? )";
            PreparedStatement statement = conn.prepareStatement(insrtStatement);

            JSONObject orderDetail = jsonObject.getJSONObject("orderDetail");

            String email = orderDetail.getString("email");
            String phone = orderDetail.getString("phone");

//            System.out.println("ORD DETAILS ARE: " + orderDetail);
            statement.setString(1, orderID);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, "active");

            int rowsAffected = statement.executeUpdate();
//

            if (rowsAffected > 0) {
                message += "Order added to DataBase";
            } else {
                message += "Order Failed To Add";
            }

            statement.close();

        } catch (SQLException e) {
            String error = "Error in 1st " + e.getMessage();
            return error;
        }

        //MAINTAINS customer Tables
        try {
            String insrtStatement = "INSERT INTO customer( custName , custPhone, custEmail, custAddress, custCity ) VALUES ( ? , ? , ? , ? , ? )";
            PreparedStatement statement = conn.prepareStatement(insrtStatement);

            JSONObject customerDetail = jsonObject.getJSONObject("customerDetail");

            String name = customerDetail.getString("firstName") + " " + customerDetail.getString("lastName");
            String phone = customerDetail.getString("phoneNo");
            String address = customerDetail.getString("Address");
            String email = customerDetail.getString("email");
            String city = customerDetail.getString("city");
            
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, email);
            statement.setString(4, address);
            statement.setString(5, city);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                message += " Customer added to DataBase";
            } else {
                message += "Customer Already exists";
            }

            statement.close();

        } catch (SQLException e) {
            String error = "Error in 2nd " + e.getMessage();
            return error;

        }

        //MAINTAINS orderDetail Tables
        try {
            JSONArray orderDetail = jsonObject.getJSONArray("orderItems");
            
            for (int i = 0; i < orderDetail.length(); i++) {

                String insrtStatement = "INSERT INTO orderdetail( order_id , product_id, quantity ) VALUES ( ? , ? , ?  )";
                PreparedStatement statement = conn.prepareStatement(insrtStatement);

                JSONObject orderItem = orderDetail.getJSONObject(i);
                String productId = orderItem.getString("id");
                int productQuantity = orderItem.getInt("quantity");

                statement.setString(1, orderID);
                statement.setString(2, productId);
                statement.setInt(3, productQuantity);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    message += " product with prod id: " + productId + " added to DataBase\n";
                } else {
                    message = "Product with prod id " + productId + " cannot be added to DataBase";
                }

                System.out.println("ORDER DETAILS: " + orderItem.getString("Name"));
                statement.close();
            }

        } catch (SQLException e) {
            String error = "Error in 3rd " + e.getMessage();
            return error;
        }

        conn.close();

        return message;
    }

    
    
// CONFRIM ORDER
     @Path("/confirmOrder")
    @POST

    public String confirmOrder(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {
        Connection conn = Connections.CreateCon();
        
        String insrtStatement = "UPDATE orders SET status = 'completed' WHERE order_id = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        
        System.out.println(body);

        statement.setString(1, body);

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Order Confirm";
        } else {
            message = "Order not confirm";
        }
        statement.close();
        conn.close();

        return message;
    }
    
    
   
     @Path("/cancelOrder")
    @POST

    public String cancelOrder(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {
        Connection conn = Connections.CreateCon();
        
        String insrtStatement = "UPDATE orders SET status = 'cancelled' WHERE order_id = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        
        System.out.println(body);

        statement.setString(1, body);

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Order Confirm";
        } else {
            message = "Order not confirm";
        }
        statement.close();
        conn.close();

        return message;
    }

}
