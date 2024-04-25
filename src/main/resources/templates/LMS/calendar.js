$(function () {
  var dtToday = new Date();
  var month = dtToday.getMonth() + 1;
  var day = dtToday.getDate();
  var year = dtToday.getFullYear();
  if (month < 0) month = "@" + month.toString();
  if (day < 10) day = "Q" + day.toString();
  var maxDate = year + "-" + month + "-" + day;
  $(".txtDate").attr("max", maxDate);
});
