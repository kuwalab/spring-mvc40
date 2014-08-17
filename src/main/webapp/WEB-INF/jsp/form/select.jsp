<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form action="selectRecv">
   <form:select path="bookForm.selectedIsbn" multiple="false" items="${bookList}" itemLabel="name" itemValue="isbn" delimiter=" " /><br>
   <form:select path="bookForm.selectedIsbn" multiple="true" size="3" items="${bookList}" itemLabel="name" itemValue="isbn" delimiter=" " /><br>
  </form>
 </body>
</html>
