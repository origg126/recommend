<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cuifenghuai
  Date: 2019/12/9
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预测评分</title>
    <!--引入bootstrap css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/statics/bootstrap/css/bootstrap.css">
    <!--引入jquery核心js-->
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <!--引入bootstrap组件js-->
    <script src="${pageContext.request.contextPath}/back/statics/bootstrap/js/bootstrap.js"></script>
    <script>

        $(function () {
            init();
        });

        function init() {
            $('#tb').empty();
            $('#tb1').empty();
            $('#tb2').empty();
            $.get('${pageContext.request.contextPath}/video/showAll', function (data) {
                if (data.status == 200) {
                    let videos = data.videos;
                    videos.forEach(function (n, i) {
                        let v = $('<tr>\n' +
                            '            <td>' + n.id + '</td>\n' +
                            '            <td>' + n.name + '</td>\n' +
                            '            <td><button type="button" value="' + n.id + '" name="' + n.name + '" onclick="changeScore(this)" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">\n' +
                            '  评分\n' +
                            '</button></td>\n' +
                            '        </tr>');
                        $('#tb').append(v);
                    })
                    let watchVideo = data.user.userLikes;
                    watchVideo.forEach(function (n, i) {
                        let tr = $('<tr>\n' +
                            '                <td>' + n.videoId + '</td>\n' +
                            '                <td>' + n.video.name + '</td>\n' +
                            '                <td>' + n.count + '</td>\n' +
                            '            </tr>');
                        $('#tb1').append(tr);
                    });
                    let noWatchVideo = data.userLike;
                    $.each(noWatchVideo, function (k, v) {
                        let key = k.split('\'');
                        let tr;
                        if (v == 'NaN') {
                            tr = $('<tr>\n' +
                                '                <td>' + key[1] + '</td>\n' +
                                '                <td>' + key[3] + '</td>\n' +
                                '                <td>样本不足，无法预测</td>\n' +
                                '            </tr>');

                        } else {
                            tr = $('<tr>\n' +
                                '                <td>' + key[1] + '</td>\n' +
                                '                <td>' + key[3] + '</td>\n' +
                                '                <td>' + v.toFixed(2) + '</td>\n' +
                                '            </tr>');
                        }

                        $('#tb2').append(tr);

                    });
                }
            }, 'JSON');
        }

        function changeScore(e) {
            $('#videoId').val($(e).attr("value"))
            $('#videoName').val($(e).attr("name"))
            $('#videoScore').val('');
        }
    </script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">影片预测</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:;">欢迎您：<span>${sessionScope.user.username}</span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-5">
            <h3>全部视频</h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <td>视频id</td>
                    <td>视频名称</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody id="tb">
                </tbody>
            </table>
        </div>
        <div class="col-sm-7">
            <div class="row">
                <h3>已看视频</h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>视频id</td>
                        <td>视频名称</td>
                        <td>评分</td>
                    </tr>
                    </thead>
                    <tbody id="tb1">

                    </tbody>
                </table>
            </div>
            <div class="row">
                <h3>未观看视频预测评分</h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>视频id</td>
                        <td>视频名称</td>
                        <td>预测评分</td>
                    </tr>
                    </thead>
                    <tbody id="tb2">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改评分</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">影片id</label>
                        <div class="col-sm-10">
                            <input type="text" id="videoId" disabled class="form-control" id="inputEmail3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">影片名</label>
                        <div class="col-sm-10">
                            <input type="text" id="videoName" disabled class="form-control" id="inputPassword3">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword4" class="col-sm-2 control-label">评分</label>
                        <div class="col-sm-10">
                            <input type="number" max="5" min="0" step="0.1" id="videoScore" class="form-control"
                                   id="inputPassword4">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12" id="danger">

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" onclick="uploadVideoScore()" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
<script>
    function uploadVideoScore() {
        console.log($('#videoId').val());
        console.log('${sessionScope.user.id}');
        console.log($('#videoScore').val());
        let videoId = $('#videoId').val();
        let userId = '${sessionScope.user.id}';
        let score = $('#videoScore').val();
        if (score > 5 || score < 0) {
            $('#danger').empty();
            let btn = $('<div class="alert alert-danger alert-dismissible" role="alert">\n' +
                '  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n' +
                '   请输入0-5之间的数字，可以为小数\n' +
                '</div>');
            $('#danger').append(btn);
        } else {
            let k = 'userId=' + userId + '&videoId=' + videoId + '&score=' + score;
            $.get('${pageContext.request.contextPath}/user/changeUserLike', k, function (data) {
                console.log(data);
                $('#myModal').modal('hide')
                init();
            });
        }

    }
</script>

</body>
</html>
