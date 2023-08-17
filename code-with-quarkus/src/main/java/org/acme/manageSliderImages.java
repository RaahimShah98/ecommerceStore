
import Connections.Connections;
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

import org.json.JSONObject;

class sliderImageObject {

    private String id;
    private String name;
    private String imgURL;

    public sliderImageObject() {
        this.id = "";
        this.name = "";
        this.imgURL = "";

    }

    public sliderImageObject(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.imgURL = url;

    }

    //functions 
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getURL() {
        return this.imgURL;
    }

}

@Path("/manageSliderImage")
public class manageSliderImages {

    @Path("/viewSliderImage")
    @GET
    @Produces(MediaType.TEXT_PLAIN)

    public ArrayList viewSliderImages() throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        Statement st = conn.createStatement();
        String query = "Select * From sliderimages";
        ResultSet rs = st.executeQuery(query);
        ArrayList sliderArray = new ArrayList();

        while (rs.next()) {
            JSONObject sliderImage = new JSONObject();
            String id = rs.getString("imgID");
            String name = rs.getString("imgname");
            String imgURL = rs.getString("imgURL");

            sliderImage.put("imgId", id);
            sliderImage.put("imgName", name);
            sliderImage.put("imgUrl", imgURL);

            sliderArray.add(sliderImage);
        }
        rs.close();
        st.close();
        conn.close();

        return sliderArray;

    }

    @Path("/viewSliderImagewtihid")
    @GET
    @Produces(MediaType.TEXT_PLAIN)

    public String viewSliderImageswithId(@QueryParam("param1") int param1) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
//        Statement st = conn.createStatement();

        String query = "SELECT * FROM sliderimages WHERE imgId = ? ";
        PreparedStatement pt = conn.prepareStatement(query);
        pt.setInt(1, param1);

        ResultSet rs = pt.executeQuery();
        ArrayList<String> sliderArray = new ArrayList();

        String message = "";
        rs.next();

        String id = rs.getString("imgID");
        String name = rs.getString("imgname");
        String imgURL = rs.getString("imgURL");

        message = "image ID: " + id + ", image Name: " + name + " imageURL: " + imgURL + "\n";

        rs.close();
        pt.close();
        conn.close();

        return message;

    }

    @Path("/addSliderImagewithId")
    @POST
    public String addSliderImage(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insertStatement = "INSERT INTO sliderimages( imgId , imgName, imgURL) VALUES ( ? , ? , ? )";
        PreparedStatement statement = conn.prepareStatement(insertStatement);

        sliderImageObject objectCreate;

        JSONObject jsonObject = new JSONObject(body);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("Name");
        String url = jsonObject.getString("imgSrc");

        objectCreate = new sliderImageObject();
        objectCreate = new sliderImageObject(id, name, url);

        System.out.println(name);

        statement.setString(1, objectCreate.getId());
        statement.setString(2, objectCreate.getName());
        statement.setString(3, objectCreate.getURL());

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Image : id: " + id + ", Name: " + name + " ,URL" + url + " added  successfully.";

        } else {
            message = "Failed to insert Image";
        }

        statement.close();
        conn.close();

        return message;

    }

//    ADDUSER JSON
//    {
//    "id" : "10",
//    "name" : "tempName",
//    "email" : "tempEmail",
//    "password" : "tempPass",
//    "address" : "tempAddress",
//    "status" : "active",
//
//}
    @Path("/deleteSliderImage")
    @POST
    public String deleteSliderImage(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insrtStatement = "DELETE FROM sliderimages WHERE imgid = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        statement.setString(1, body);

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Slider Image with id: " + body + "Deleted Succesfully";

        } else {
            message = "Slider Image delete failed";
        }
        statement.close();
        conn.close();

        return message;
    }

    @Path("/updateSliderImage")
    @POST
    public String updateImageSlider(String body) throws SQLException, MalformedURLException, ProtocolException, IOException {

        Connection conn = Connections.CreateCon();
        String insrtStatement = "UPDATE sliderimages SET imgName = ?, imgURL = ? WHERE imgId = ?";
        PreparedStatement statement = conn.prepareStatement(insrtStatement);

        sliderImageObject categoryCreate;

        JSONObject jsonObject = new JSONObject(body);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("Name");
        String url = jsonObject.getString("imgSrc");

        categoryCreate = new sliderImageObject();
        categoryCreate = new sliderImageObject(id, name, url);

        System.out.println(name);

        statement.setString(3, categoryCreate.getId());
        statement.setString(1, categoryCreate.getName());
        statement.setString(2, categoryCreate.getURL());

        int rowsAffected = statement.executeUpdate();

        String message = "";
        if (rowsAffected > 0) {
            message = "Slider with id: " + id + " Updated";
        } else {
            message = "Slider with id: " + id + " Not Found";
        }
        statement.close();
        conn.close();

        return message;
    }
}
