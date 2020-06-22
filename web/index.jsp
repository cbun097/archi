<%--
  Created by IntelliJ IDEA.
  User: clair
  Date: 6/10/2020
  Time: 8:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$
  <form action="rest/validerIsbn/get?isbn={isbn}" method="post">
    Enter ISBN:<input type="text" name="isbn"/><br/><br/>
    <input type="submit" value="Valider"/>
  </form>
  </body>
</html>
