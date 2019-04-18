<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
       
    <base href="<%=basePath%>">
        
        <title>编辑花名册</title>
        
        
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
    <header class="topbar">
        <nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
            <!-- ============================================================== -->
            <!-- Logo -->
            <!-- ============================================================== -->
            <div class="navbar-header">
                <a class="navbar-brand" href="index.html">
                    <!-- Logo icon -->
                    <b>
                        <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                        <!-- Dark Logo icon -->
                        <img src="assets/images/logo-icon.png" alt="homepage" class="dark-logo"/>

                    </b>
                    <!--End Logo icon -->
                    <!-- Logo text -->
                    <span>
                            <!-- dark Logo text -->
                            <img src="assets/images/logo-text.png" alt="homepage" class="dark-logo"/>
                        </span>
                </a>
            </div>
            <!-- ============================================================== -->
            <!-- End Logo -->
            <!-- ============================================================== -->
            <div class="navbar-collapse">
                <!-- ============================================================== -->
                <!-- toggle and nav items -->
                <!-- ============================================================== -->
                <ul class="navbar-nav mr-auto mt-md-0 ">
                    <!-- This is  -->
                    <li class="nav-item"><a class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark"
                                            href="javascript:void(0)"><i class="ti-menu"></i></a></li>
                    <li class="nav-item hidden-sm-down">
                        <form class="app-search p-l-20">
                            <input type="text" class="form-control" placeholder="Search for..."> <a class="srh-btn"><i
                                class="ti-search"></i></a>
                        </form>
                    </li>
                </ul>
                <!-- ============================================================== -->
                <!-- User profile and search -->
                <!-- ============================================================== -->
                <ul class="navbar-nav my-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href=""
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img
                                src="assets/images/users/1.jpg" alt="user" class="profile-pic m-r-5"/>Markarn Doe</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
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
                                <a href="<%=basePath%>roster/allExamineListPage?loginId=${loginId}" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>全部</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/startWarningExamineListPage?loginId=${loginId}" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>到龄进入预警</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/endWarningExamineListPage?loginId=${loginId}" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>到龄退出预警</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/examineListPage?loginId=${loginId}" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>待审核</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/undeterminedExamineListPage?loginId=${loginId}" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>待定人员名单</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="pages-profile.html" class="waves-effect"><i class="fa fa-user m-r-10"
                                                                             aria-hidden="true"></i>Profile</a>
                    </li>
                    <li>
                        <a href="table-basic.html" class="waves-effect"><i class="fa fa-table m-r-10"
                                                                           aria-hidden="true"></i>Basic Table</a>
                    </li>
                    <li>
                        <a href="icon-fontawesome.html" class="waves-effect"><i class="fa fa-font m-r-10"
                                                                                aria-hidden="true"></i>Icons</a>
                    </li>
                    <li>
                        <a href="map-google.html" class="waves-effect"><i class="fa fa-globe m-r-10"
                                                                          aria-hidden="true"></i>Google Map</a>
                    </li>
                    <li>
                        <a href="pages-blank.html" class="waves-effect"><i class="fa fa-columns m-r-10"
                                                                           aria-hidden="true"></i>Blank Page</a>
                    </li>
                    <li>
                        <a href="pages-error-404.html" class="waves-effect"><i class="fa fa-info-circle m-r-10"
                                                                               aria-hidden="true"></i>Error 404</a>
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
                    <h3 class="text-themecolor m-b-0 m-t-0">编辑花名册</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<%=basePath%>roster/rosterList">列表</a></li>
                        <li class="breadcrumb-item active">编辑</li>
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
                                <div class="form-group">
                                    <label class="col-md-12"><span style="color: red">*</span>身份证号</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入身份证号码"
                                               class="form-control form-control-line" id="idCard">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-md-12"><span style="color: red">*</span>姓名</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入姓名" class="form-control form-control-line"
                                               name="name"
                                               id="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>性别</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="gender">
                                            <option value="">请选择</option>
                                            <option value="1">男</option>
                                            <option value="2">女</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="birthday" class="col-md-12"><span
                                            style="color: red">*</span>出生年月</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请选择出生年月"
                                               class="form-control form-control-line date_picker"
                                               name="birthday" id="birthday" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="address" class="col-md-12"><span style="color: red">*</span>常住地址</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入常住地址" class="form-control form-control-line"
                                               name="address"
                                               id="address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="village" class="col-md-12"><span
                                            style="color: red">*</span>征地时所在村（组）</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入征地时所在村（组）"
                                               class="form-control form-control-line" name="village"
                                               id="village">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>是否迁出</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="isMove">
                                            <option value="">请选择</option>
                                            <option value="1">未迁出</option>
                                            <option value="2">已迁出</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>现所属社区</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="community"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="house" class="col-md-12"><span style="color: red">*</span>现户籍所在地</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入现户籍所在地"
                                               class="form-control form-control-line" name="house"
                                               id="house">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">备注</label>
                                    <div class="col-md-12">
                                        <textarea rows="1" placeholder="请填写内容" class="form-control form-control-line"
                                                  id="remark"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="button" class="btn btn-success" id="submit">提交</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Column -->
            </div>
            <!-- Row -->
            <!-- ============================================================== -->
            <!-- End PAge Content -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Container fluid  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- footer -->
        <!-- ============================================================== -->
        <footer class="footer text-center">
            © 2017 Monster Admin by wrappixel.More Templates <a href="http://www.cssmoban.com/" target="_blank"
                                                                title="模板之家">模板之家</a> - Collect from <a
                href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
        </footer>
        <!-- ============================================================== -->
        <!-- End footer -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->
</div>
<input type="hidden" value="${rosterId}" id="rosterId">
<input type="hidden" value="${loginId}" id="loginId">
<!-- ============================================================== -->
<!-- End Wrapper -->
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- All Jquery -->
<!-- ============================================================== -->
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
<script type="text/javascript" src="<%=basePath%>myjs/dialog.min.js"></script>
<script type="text/javascript">
    $(function () {
        laydate.render({
            elem: '.date_picker'
        });
        findAllCommunity();
        var loginId = $("#loginId").val();
        verification(loginId);
    })
    function verification(loginId) {
        $.post("<%=basePath%>user/getUserByUserId",{"userId":loginId},function (data) {
            if (data.code == -1){
                alert(data.message);
                window.location.href="<%=basePath%>/login.jsp";
            }
        });
    }
    function getRosterById() {
        var rosterId = $("#rosterId").val();
        $.post("<%=basePath%>roster/selectRosterById",{"rosterId":rosterId},function (data) {
            var obj = data.data;
            $("#idCard").val(obj.idCard);

            $("#name").val(obj.name);

            $("#gender").val(obj.gender);

            $("#birthday").val(new Date(obj.birthday).getFullYear()+"-"+new Date(obj.birthday).getMonth()+"-"+new Date(obj.birthday).getDate());

            $("#address").val(obj.address);

            $("#village").val(obj.village);

            $("#isMove").val(obj.isMove);

            $("#community").val(obj.communityId);

            $("#house").val(obj.house);

            $("#remark").val(obj.remark);
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
                $("#community").html(code);
                getRosterById();
            }

        });
    }

    $("#submit").on("click", function () {

        var rosterId = $("#rosterId").val();

        var idCard = $("#idCard").val();

        var name = $("#name").val();

        var gender = $("#gender").val();

        var birthday = $("#birthday").val();

        var address = $("#address").val();

        var village = $("#village").val();

        var isMove = $("#isMove").val();

        var communityId = $("#community").val();

        var house = $("#house").val();

        var remark = $("#remark").val();

        if (idCard == null || idCard == "") {
            popup({type: 'error', msg: "请输入身份证号", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        if (name == null || name == "") {
            popup({type: 'error', msg: "请输入姓名", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        if (gender == null || gender == "") {
            popup({type: 'error', msg: "请选择性别", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        if (birthday == null || birthday == "") {
            popup({type: 'error', msg: "请选择出生年月", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        if (address == null || address == "") {
            popup({type: 'error', msg: "请输入常住地址", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        if (village == null || village == "") {
            popup({type: 'error', msg: "请输入征地时所在村（组）", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        if (isMove == null || isMove == "") {
            popup({type: 'error', msg: "请选择是否迁出", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        if (communityId == null || communityId == "") {
            popup({type: 'error', msg: "请选择现所属社区", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        if (house == null || house == "") {
            popup({type: 'error', msg: "请输入现户籍所在地", delay: 2000, bg: true, clickDomCancel: true});
            return;
        }

        $.post("<%=basePath%>roster/updateRosterById", {
            "rosterId":rosterId,
            "idCard": idCard,
            "name": name,
            "gender": gender,
            "birthday": birthday,
            "address": address,
            "village": village,
            "isMove": isMove,
            "communityId": communityId,
            "house": house,
            "remark": remark
        }, function (data) {
            if (data.code == 0) {
                popup({type: "success", msg: "提交成功", delay: 1000});
            } else {
                popup({type: 'error', msg: data.message, delay: 2000, bg: true, clickDomCancel: true});
            }
        })
    })
</script>
</body>
</html>
