<html>
<head>
    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
</head>
<body>
<h2>Tomcat1!</h2>
<h2>Tomcat1!</h2>
<h2>Tomcat1!</h2>

<h3>SpringMvc上传文件</h3>
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="upload">
</form>
<h3>富文本图片上传</h3>
<form name="form2" action="/manage/product/rich_text_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="upload">
</form>
</body>
</html>
