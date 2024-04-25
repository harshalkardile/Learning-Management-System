const express = require("express");
const port = process.env.PORT || 5000;

const app = express();
app.use(express.json());
app.use(express.urlencoded());
app.use(express.static("public"));

// this is our port number
// this is our app or instance of express
// API ROUTES
app.get("/form", (req, res) => {
  res.sendFile(__dirname + "../../Faculty/classroomform.html");
});

app.post("/formPost", (req, res) => {
  console.log(req.body);
});

app.listen(port, () => {
  console.log("Server started at http://localhost:${port}");
});
