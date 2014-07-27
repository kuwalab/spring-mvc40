<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
  <form:errors path="*" />
  <form action="bookRecv" method="post">
   書名: <form:input path="book.name" size="20" /><form:errors path="book.name" /><br>
   価格: <form:input path="book.price" size="20" /><form:errors path="book.price" /><form:errors path="book.validPrice" /><br>
   定価: <form:input path="book.listPrice" size="20" /><form:errors path="book.listPrice" /><br>
   <input type="submit" value="送信">
  </form>
 </body>
</html>
