<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <base href="<%=basePath%>">

     <title>修改账号</title>
    <meta http-equiv="pragma" content="no-cache">

    <meta http-equiv="cache-control" content="no-cache">

    <meta http-equiv="expires" content="0">


    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

    <meta http-equiv="description" content="This is my page">

    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<%=basePath%>assets/images/favicon.png">
    <title>Monster Admin Template - The Most Complete & Trusted Bootstrap 4 Admin Template</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="<%=basePath%>css/colors/blue.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>mycss/dialog.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="fix-header card-no-border">
<!-- ============================================================== -->
<!-- Preloader - style you can find in spinners.css -->
<!-- ============================================================== -->
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>
<!-- ============================================================== -->
<!-- Main wrapper - style you can find in pages.scss -->
<!-- ============================================================== -->
<div id="main-wrapper">
    <!-- ============================================================== -->
    <!-- Topbar header - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <%--<header class="topbar">--%>
        <%--<nav class="navbar top-navbar navbar-toggleable-sm navbar-light">--%>
            <%--<!-- ============================================================== -->--%>
            <%--<!-- Logo -->--%>
            <%--<!-- ============================================================== -->--%>
            <%--<div class="navbar-header">--%>
                <%--<a class="navbar-brand" href="index.html">--%>
                    <%--<!-- Logo icon -->--%>
                    <%--<b>--%>
                        <%--<!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->--%>
                        <%--<!-- Dark Logo icon -->--%>
                        <%--<img src="assets/images/logo-icon.png" alt="homepage" class="dark-logo"/>--%>

                    <%--</b>--%>
                    <%--<!--End Logo icon -->--%>
                    <%--<!-- Logo text -->--%>
                    <%--<span>--%>
                            <%--<!-- dark Logo text -->--%>
                            <%--<img src="assets/images/logo-text.png" alt="homepage" class="dark-logo"/>--%>
                        <%--</span>--%>
                <%--</a>--%>
            <%--</div>--%>
            <%--<!-- ============================================================== -->--%>
            <%--<!-- End Logo -->--%>
            <%--<!-- ============================================================== -->--%>
            <%--<div class="navbar-collapse">--%>
                <%--<!-- ============================================================== -->--%>
                <%--<!-- toggle and nav items -->--%>
                <%--<!-- ============================================================== -->--%>
                <%--<ul class="navbar-nav mr-auto mt-md-0 ">--%>
                    <%--<!-- This is  -->--%>
                    <%--<li class="nav-item"><a class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark"--%>
                                            <%--href="javascript:void(0)"><i class="ti-menu"></i></a></li>--%>
                    <%--<li class="nav-item hidden-sm-down">--%>
                        <%--<form class="app-search p-l-20">--%>
                            <%--<input type="text" class="form-control" placeholder="Search for..."> <a class="srh-btn"><i--%>
                                <%--class="ti-search"></i></a>--%>
                        <%--</form>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<!-- ============================================================== -->--%>
                <%--<!-- User profile and search -->--%>
                <%--<!-- ============================================================== -->--%>
                <%--<ul class="navbar-nav my-lg-0">--%>
                    <%--<li class="nav-item dropdown">--%>
                        <%--<a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href=""--%>
                           <%--data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img--%>
                                <%--src="assets/images/users/1.jpg" alt="user" class="profile-pic m-r-5"/>Markarn Doe</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</nav>--%>
    <%--</header>--%>
    <div id="headerpage"></div>
    <!-- ============================================================== -->
    <!-- End Topbar header -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div class="scroll-sidebar">
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <li>
                        <a href="<%=basePath%>roster/rosterList?loginId=${loginId}" class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>征地人员花名册</a>
                    </li>
                    <li>
                        <a class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>征地人员社会救济金</a>
                        <ul>
                            <li>
                                <a href="<%=basePath%>roster/allExamineListPage?loginId=${loginId}"
                                   class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>全部</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/startWarningExamineListPage?loginId=${loginId}"
                                   class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>到龄进入预警</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/endWarningExamineListPage?loginId=${loginId}"
                                   class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>到龄退出预警</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/examineListPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>待审核</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/undeterminedExamineListPage?loginId=${loginId}"
                                   class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>待定人员名单</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>examine/fftjExamineList?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>发放统计</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>尊老金</a>
                        <ul>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=5"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>全部</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=1" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>城镇居民尊老金</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=2" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>农村居民尊老金</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="<%=basePath%>respect/longevityPager?loginId=${loginId}" class="waves-effect"><i class="fa fa-address-card m-r-10" aria-hidden="true"></i>长寿金</a>
                    </li>
                    <li>
                        <a class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>已故人员花名册</a>
                        <ul>
                            <li>
                                <a href="<%=basePath%>examine/deceasedPage?loginId=${loginId}" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>征地人员已故名单</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=4" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i> 居民尊老金已故名单</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>报表分析</a>
                        <ul>
                            <li>
                                <a href="<%=basePath%>examine/examineStatisticPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-address-card m-r-10"
                                        aria-hidden="true"></i>征地统计</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectStatistic?loginId=${loginId}&pageType=1"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>城镇人员尊老金</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectStatistic?loginId=${loginId}&pageType=2"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>农村人员尊老金</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>账号设置</a>
                        <ul>
                            <li>
                                <a href="<%=basePath%>user/userPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>账号管理</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>community/communityPage?loginId=${loginId}"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>社区管理</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>
    <!-- ============================================================== -->
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Page wrapper  -->
    <!-- ============================================================== -->
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->
        <div class="container-fluid">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="row page-titles">
                <div class="col-md-6 col-8 align-self-center">
                    <h3 class="text-themecolor m-b-0 m-t-0">修改账号</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a
                                href="<%=basePath%>user/userPage?loginId=${loginId}&pageType=${pageType}">列表</a></li>
                        <li class="breadcrumb-item active">修改</li>
                    </ol>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <!-- Row -->
            <div class="row">
                <!-- Column -->
                <div class="col-lg-8 col-xlg-9 col-md-7">
                    <div class="card">
                        <div class="card-block">
                            <form class="form-horizontal form-material">
                                <div class="form-group form-control-line">
                                    <label for="username" class="col-md-3"><span style="color: red">*</span>账号名称</label>
                                    <input type="text" readonly placeholder="请输入账号名称" class="form-control col-md-8" name="username"
                                           id="username">
                                </div>
                                <div class="form-group form-control-line">
                                    <label class="col-md-3">姓名</label>
                                    <input type="text" placeholder="请输入账号姓名" class="form-control col-md-8" id="nickname">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="communityId" class="col-md-3"><span
                                            style="color: red">*</span>所属社区：</label>
                                    <select class="form-control col-md-8" id="communityId">
                                        <option selected value="">请选择</option>
                                    </select>
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="phone" class="col-md-3">登录密码</label>
                                    <input type="password" placeholder="登录密码" class="form-control col-md-8" name="password" id="password">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="phone" class="col-md-3">确认密码</label>
                                    <input type="password" placeholder="确认密码" class="form-control col-md-8" name="comfirePwd" id="comfirePwd">
                                </div>

                            </form>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="button" class="btn btn-success" id="submit">确定</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Column -->
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="loginId" value="${loginId}">
<input type="hidden" id="userId" value="${userId}">
<script src="<%=basePath%>assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="<%=basePath%>assets/plugins/bootstrap/js/tether.min.js"></script>
<script src="<%=basePath%>assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="<%=basePath%>js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="<%=basePath%>js/waves.js"></script>
<!--Menu sidebar -->
<script src="<%=basePath%>js/sidebarmenu.js"></script>
<!--stickey kit -->
<script src="<%=basePath%>assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
<!--Custom JavaScript -->
<script src="<%=basePath%>js/custom.min.js"></script>
<!-- ============================================================== -->
<!-- Style switcher -->
<!-- ============================================================== -->
<script src="<%=basePath%>assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>

<script src="<%=basePath%>laydate/laydate.js"></script>

<script src="<%=basePath%>myjs/zepto.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script type="text/javascript" src="<%=basePath%>myjs/dialog.min.js"></script>
<script type="text/javascript">
    var roleType = 2;
    $(function () {
        $("#headerpage").load("page/header");
        var loginId = $("#loginId").val();
        verification(loginId);
    })

    function verification(loginId) {
        $.post("<%=basePath%>user/getUserByUserId", {"userId": loginId}, function (data) {
            if (data.code == 0){
                roleType = data.data.type;
                //如果是社区管理员默认
                if(roleType == 1){
                    $("#communityIdDiv").show();
                    findAllCommunity();
                    getUserById();
                }
            }else{
                alert(data.message);
                window.location.href="../login/login.jsp";
            }
        });
    }

    function findAllCommunity() {
        $.post("<%=basePath%>roster/findAllCommunity", {}, function (data) {
            if (data.code == 0) {
                var list = data.data;
                var code = "<option value=''>请选择</option>";
                for (var i = 0; i < list.length; i++) {
                    code += "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
                $("#communityId").html(code);
            }

        });
    }

    $("#submit").on("click", function () {

        var username = $("#username").val();
        var nickname = $("#nickname").val();
        var loginId = $("#loginId").val();
        var communityId = $("#communityId").val();
        var password = $("#password").val();
        var comfirePwd = $("#comfirePwd").val();
        var userId = $("#userId").val();
        if (username == null || username == "") {
            popup({type: 'error', msg: "账号名称不能为空", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }
        if (password == null || password == "") {
            popup({type: 'error', msg: "密码不能为空", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }
        if (comfirePwd == null || comfirePwd == "") {
            popup({type: 'error', msg: "确认密码不能为空", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }
        if (comfirePwd != password ) {
            popup({type: 'error', msg: "密码和确认密码不一致", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }
        if (communityId == null || communityId == "") {
            popup({type: 'error', msg: "所属社区不能为空", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }
        $.post("<%=basePath%>user/updateUser", {
            "username": username,
            "nickname": nickname,
            "communityId": communityId,
            "password": password,
            "comfirePwd": comfirePwd,
            "loginId":loginId,
            "userId":userId
        }, function (data) {
            if (data.code == 0) {
                popup({type: "success", msg: "提交成功", delay: 1000});
                window.location.href = "<%=basePath%>user/userPage?loginId=${loginId}";
            } else {
                popup({type: 'error', msg: data.message, delay: 2000, bg: true, clickDomCancel: true});
            }
        })
    })

    function getUserById() {
        var userId = $("#userId").val();
        $.post("<%=basePath%>user/getUserInfo",{"userId":userId},function (data) {
            var object = data.data;
            $("#username").val(object.username);
            $("#nickname").val(object.nickname);
            if(object.community){
                $("#communityId").val(object.community.id);
            }
            $("#password").val(object.password);
            $("#comfirePwd").val(object.password);


        })
    }

</script>
</body>
</html>
