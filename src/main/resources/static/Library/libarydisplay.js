var mysql = require("mysql");

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "lms",
});

con.connect(function (err) {
  //   var table = "recent library user";
  if (err) throw err;
  con.query("SELECT * FROM  recent_notif", function (err, result, fields) {
    if (err) throw err;
    console.log(result);
  });
});
