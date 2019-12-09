<%--
  Created by IntelliJ IDEA.
  User: cuifenghuai
  Date: 2019/12/9
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <!--引入bootstrap css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/statics/bootstrap/css/bootstrap.css">
    <!--引入jquery核心js-->
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <!--引入bootstrap组件js-->
    <script src="${pageContext.request.contextPath}/back/statics/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        function adminLogin() {
            $.get('${pageContext.request.contextPath}/user/login', 'username=' + $('#username').val() + '&password=' + $('#password').val() , function (data) {
                $('#msg').empty();
                if (data.status == 200) {
                    window.location.href = '${pageContext.request.contextPath}/back/index.jsp';
                }else {
                    let msg2 = $('<div class="alert alert-danger alert-dismissible" role="alert">\n' +
                        '  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n' +
                        '  <strong>' + data.message + '</strong>' +
                        '</div>');
                    $('#msg').append(msg2);
                }

            });
        }
    </script>
</head>
<body >
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div style="margin-top: 35%;" class="panel panel-default">
                <div style="height: 50px" class="panel-heading">
                    <h1 style="font-size: 20px" class="panel-title">登陆</h1>
                </div>
                <div class="panel-body">
                    <form>
                        <div class="form-group">
                            <label for="username">用户名</label>
                            <input type="text" class="form-control" id="username" placeholder="请输入用户名...">
                        </div>
                        <div class="form-group">
                            <label for="password">密码</label>
                            <input type="password" class="form-control" id="password" placeholder="请输入密码...">
                        </div>
                        <div id="msg">

                        </div>
                        <button type="button" onclick="adminLogin()" class="btn btn-primary btn-block">登陆</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

