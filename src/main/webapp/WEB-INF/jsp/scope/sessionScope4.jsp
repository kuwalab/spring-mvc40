<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
 <c:out value="${requestScope.sessionBook.name}" />
書名: <c:out value="${sessionScope.sessionBook.name}" /><br>
価格: <c:out value="${sessionScope.sessionBook.price}" /><br>
<a href="sessionScope5">セッションをクリアせず再表示</a><br>
<a href="sessionScope6">セッションをクリアして再表示</a>
 </body>
</html>
