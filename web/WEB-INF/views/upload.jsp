<%--
  Created by IntelliJ IDEA.
  User: jsc
  Date: 2019/4/25
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*,java.text.*" errorPage="/500.jsp" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="/springmvc/file/mvcUpload" method="post" enctype="multipart/form-data">
        文件描述：<input type="text" name="fileDercribe"> <br>
        文件上传：<input type="file" name="file"> <br>
        <input type="submit" value="开始上传">
    </form>
</body>
</html>
