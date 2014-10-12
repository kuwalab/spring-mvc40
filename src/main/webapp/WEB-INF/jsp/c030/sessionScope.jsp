<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
 <c:out value="${requestScope.c030Model.name}" />
sessionScope.書名: <c:out value="${sessionScope.c030Model.name}" /><br>
sessionScope.価格: <c:out value="${sessionScope.c030Model.price}" /><br>
modelSessionBook.書名: <c:out value="${modelC030Model.name}" /><br>
modelSessionBook.価格: <c:out value="${modelC030Model.price}" /><br>
scopedSession.sessionBook.書名: <c:out value="${model.c030Model.name}" /><br>
scopedSession.sessionBook.価格: <c:out value="${model.c030Model.price}" /><br>
 </body>
</html>
