<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cuifenghuai
  Date: 2019/12/9
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page  isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
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
            $.get('${pageContext.request.contextPath}/video/showAll', function (data) {
                if(data.status==200){
                    let videos = data.videos;
                    videos.forEach(function(n,i){
                        let v = $('<tr>\n' +
                            '            <td>'+n.id+'</td>\n' +
                            '            <td>'+n.name+'</td>\n' +
                            '        </tr>');
                        $('#tb').append(v);
                    })
                    let watchVideo = data.user.userLikes;
                    watchVideo.forEach(function (n, i) {
                        let tr = $('<tr>\n' +
                            '                <td>'+n.videoId+'</td>\n' +
                            '                <td>'+n.video.name+'</td>\n' +
                            '                <td>'+n.count+'</td>\n' +
                            '            </tr>');
                        $('#tb1').append(tr);
                    });
                    let noWatchVideo = data.userLike;
                    $.each(noWatchVideo, function (k, v) {
                        let key = k.split('\'');
                        let tr = $('<tr>\n' +
                            '                <td>'+key[1]+'</td>\n' +
                            '                <td>'+key[3]+'</td>\n' +
                            '                <td>'+v+'</td>\n' +
                            '            </tr>');
                        $('#tb2').append(tr);
                    });
                }
            },'JSON');
        });
    </script>
</head>
<body>
<div class="row">
    <div class="col-sm-7">
        <h3>全部视频</h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <td>视频id</td>
                <td>视频名称</td>
            </tr>
            </thead>
            <tbody id="tb">

            </tbody>
        </table>
    </div>
    <div class="col-sm-5">
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
</body>
</html>
