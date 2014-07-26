<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
 バインディングエラー <form:errors path="book.*" /><br>
book.nameの値は <c:out value="${book.name}" /><br>
book.priceの値は <c:out value="${book.price}" /><br>
 </body>
</html>
