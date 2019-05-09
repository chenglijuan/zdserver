<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
       
    <base href="<%=basePath%>">
        
        <title>新增花名册</title>
        
        
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
                    <%--<li class="nav-item hidden-sm-down">--%>
                    <%--<form class="app-search p-l-20">--%>
                    <%--<input type="text" class="form-control" placeholder="Search for..."> <a class="srh-btn"><i--%>
                    <%--class="ti-search"></i></a>--%>
                    <%--</form>--%>
                    <%--</li>--%>
                </ul>
                <!-- ============================================================== -->
                <!-- User profile and search -->
                <!-- ============================================================== -->
                <ul class="navbar-nav my-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href=""
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">账户：<span
                                id="username"></span> </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href=""
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">退出</a>
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
                            <li id="tab_1" style="display: none">
                                <a href="<%=basePath%>roster/examineListPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>待审核</a>
                            </li>
                            <li id="tab_2" style="display: none">
                                <a href="<%=basePath%>roster/undeterminedExamineListPage?loginId=${loginId}"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>待定人员名单</a>
                            </li>
                            <li id="tab_3" style="display: none">
                                <a href="<%=basePath%>roster/examineListPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>待复审</a>
                            </li>

                        </ul>
                    </li>
                    <li>
                        <a class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>尊老金</a>
                        <ul>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=1"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>城镇居民尊老金</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=2"
                                   class="waves-effect"><i class="fa fa-user m-r-10"
                                                           aria-hidden="true"></i>农村征地人员尊老金</a>
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
                    <h3 class="text-themecolor m-b-0 m-t-0">新增征地人员花名册</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<%=basePath%>roster/rosterList?loginId=${loginId}">列表</a>
                        </li>
                        <li class="breadcrumb-item active">新增</li>
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
                                    <label class="col-md-12"><span style="color: red">*</span><span style="color: #000">身份证号</span></label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入身份证号码"
                                               class="form-control form-control-line" id="idCard">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-md-12"><span style="color: red">*</span><span
                                            style="color: #000">姓名</span></label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入姓名" class="form-control form-control-line"
                                               name="name"
                                               id="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span><span style="color: #000">性别</span></label>
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
                                            style="color: red">*</span><span style="color: #000">出生年月</span></label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请选择出生年月"
                                               class="form-control form-control-line date_picker"
                                               name="birthday" id="birthday" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="address" class="col-md-12"><span style="color: red">*</span><span
                                            style="color: #000">常住地址</span></label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入常住地址" class="form-control form-control-line"
                                               name="address"
                                               id="address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="village" class="col-md-12"><span
                                            style="color: red">*</span><span
                                            style="color: #000">征地时所在村（组）</span></label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入征地时所在村（组）"
                                               class="form-control form-control-line" name="village"
                                               id="village">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span><span style="color: #000">是否迁出</span></label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="isMove">
                                            <option value="">请选择</option>
                                            <option value="1">未迁出</option>
                                            <option value="2">已迁出</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" id="tab_4" style="display: none">
                                    <label class="col-sm-12"><span style="color: red">*</span><span style="color: #000">所属社区</span></label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="community"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="house" class="col-md-12"><span style="color: red">*</span><span
                                            style="color: #000">户籍所在地</span></label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入户籍所在地"
                                               class="form-control form-control-line" name="house"
                                               id="house">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span><span
                                            style="color: #000">发放情况</span></label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="status">
                                            <option value="">请选择</option>
                                            <option value="1">未开始</option>
                                            <option value="2">发放中</option>
                                            <option value="3">已暂停</option>
                                            <option value="4">已退出</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12"><span style="color: #000">备注</span></label>
                                    <div class="col-md-12">
                                        <textarea rows="1" placeholder="请填写内容" class="form-control form-control-line"
                                                  id="remark"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12" style="text-align: center">
                                        <button type="button" class="btn btn-info" id="submit">确认新增</button>
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
    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- End Wrapper -->
<!-- ============================================================== -->
<input type="hidden" id="loginId" value="${loginId}">
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
        $.post("<%=basePath%>user/getUserByUserId", {"userId": loginId}, function (data) {
            if (data.code == -1) {
                alert(data.message);
                window.location.href = "<%=basePath%>/login.jsp";
            } else {
                $("#username").html(data.data.username);
                if (data.data.type == 1) {
                    $("#tab_1").css("display", "block");
                    $("#tab_2").css("display", "block");
                    $("#tab_3").css("display", "none");
                    $("#tab_4").css("display", "block");
                } else if (data.data.type == 2) {
                    $("#tab_1").css("display", "none");
                    $("#tab_2").css("display", "none");
                    $("#tab_3").css("display", "block");
                    $("#tab_4").css("display", "none");
                }
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
                $("#community").html(code);
            }

        });
    }

    $("#submit").on("click", function () {

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

        var loginId = $("#loginId").val();

        var status = $("#status").val();
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

        $.post("<%=basePath%>roster/insertRoster", {
            "loginId": loginId,
            "idCard": idCard,
            "name": name,
            "gender": gender,
            "birthday": birthday,
            "address": address,
            "village": village,
            "isMove": isMove,
            "communityId": communityId,
            "house": house,
            "status": status,
            "remark": remark
        }, function (data) {
            if (data.code == 0) {
                popup({type: "success", msg: "新增成功", delay: 1000});
            } else {
                popup({type: 'error', msg: data.message, delay: 2000, bg: true, clickDomCancel: true});
            }
        })
    })
</script>
</body>
</html>
