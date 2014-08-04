<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
 <c:out value="${requestScope.sessionBook.name}" />
sessionScope.書名: <c:out value="${sessionScope.sessionBook.name}" /><br>
sessionScope.価格: <c:out value="${sessionScope.sessionBook.price}" /><br>
modelSessionBook.書名: <c:out value="${modelSessionBook.name}" /><br>
modelSessionBook.価格: <c:out value="${modelSessionBook.price}" /><br>
scopedSession.sessionBook.書名: <c:out value="${model.sessionBook.name}" /><br>
scopedSession.sessionBook.価格: <c:out value="${model.sessionBook.price}" /><br>
 </body>
</html>
