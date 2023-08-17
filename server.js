// const express = require('express');
// const app = express();
// const port = 8080;

// Define routes or middleware

const cors = require('cors');

app.use(cors({
  origin: 'http://localhost:8080/',
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  headers: ['Content-Type', 'Accept']
}));

app.listen(port, () => {
  console.log(`Express server running on port ${port}`);
});