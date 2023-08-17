
// const express = require("express");

// const mysql = require("mysql");

// const app = express();
// const port = 5000;
// const cors = require('cors')


// // app.use(cors())
// // app.use(express.static("dist"));
// // app.use(express.json())



// const connection = mysql.createPool({
//   host: "localhost",
//   user: "root",
//   password: "Tedtalk98",
//   database: "ecommercestore",
// });

// connection.getConnection((error) => {
//   if (error) {
//     console.error("Error connecting to the database:", error.message);
//   } else {
//     console.log("Connected to the database");
//   }
// });



// app.get("/userData", async (req, res) => {
//   connection.query("SELECT * FROM Users", (error, result) => {
//     if (error) {
//        console.error(error);
//       res.status(500).send("Internal Server Error");
//     } else {
//       console.log(result);
//       res.json(result)
      
//     }
//   });
// });

// app.get("/userData", async (req, res) => {
//   connection.query(`SELECT * FROM ${req}`, (error, result) => {
//     if (error) {
//        console.error(error);
//       res.status(500).send("Internal Server Error");
//     } else {
//       console.log(result);
//       res.json(result)
      
//     }
//   });
// });

// app.post("/userData", async (req, res) => {
//   connection.query(`SELECT * FROM ${req}`, (error, result) => {
//     if (error) {
//        console.error(error);
//       res.status(500).send("Internal Server Error");
//     } else {
//       console.log(result);
//       res.json(result)
      
//     }
//   });
// });


app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});













