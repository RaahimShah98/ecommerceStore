const express = require('express');
const app = express();
const port = process.env.PORT || 3000;

// Define your routes or middleware here

app.listen(port, () => {
  console.log(`Express server running on port ${port}`);
});