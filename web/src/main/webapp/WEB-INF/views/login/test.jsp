<%--
  Created by IntelliJ IDEA.
  User: erdan
  Date: 2014/12/16
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <script>
        $(function(){
            $('#send').click(function(){
                $.ajax({
                    type: "post",
                    url: "test",
                    data: {type:$("#type").val(), year:$("#year").val()},
                    dataType: "json",
                    success: function(data){
                        alert(data.taskName);
                    }
                });
            });
        });
    </script>
    <title></title>
</head>
<body>
 年份：<input id="type" type="text" /> <br/>
 类型: <input id="year" type="text" /> <br/>
 <button id="send" value="获取值" >获取值</button>
</body>
</html>
