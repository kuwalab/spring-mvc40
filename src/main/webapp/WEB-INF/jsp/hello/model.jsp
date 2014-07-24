<%@page contentType="text/html; charset=utf-8" %><%--
--%><!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8">
  <title>サンプル</title>
 </head>
 <body>
 <form:errors path="customer.*" /><br>
nameパラメータは: <c:out value="${customer.name}" /><br>
ageパラメータは: <c:out value="${customer.age}" /><br>
priceパラメータは: <c:out value="${customer.price}" /><br>
 </body>
</html>
