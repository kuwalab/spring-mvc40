<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
書名: <c:out value="${requestScope.requestBook.name}" /><br>
価格: <c:out value="${requestScope.requestBook.price}" />
 </body>
</html>
