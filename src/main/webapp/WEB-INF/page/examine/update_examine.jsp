<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
       
    <base href="<%=basePath%>">
        
        <title>编辑征地人员社会救济金</title>
        
        
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
<style type="text/css">
    #navbar_ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: visible;
    }

    #navbar_ul li {
        float: left;
    }

    #navbar_ul li a, .dropbtn {
        display: inline-block;
        color: #5e5e5e;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    #navbar_ul li a:hover, .dropdown:hover .dropbtn {
        background-color: #1f75cf;
    }

    #navbar_ul li.dropdown {
        display: inline-block;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #fafafa;
        min-width: 220px;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    }

    #navbar_ul .dropdown-content a {

        padding: 12px 16px;
        text-decoration: none;
        display: block;
        text-align: left;
    }

    #navbar_ul .dropdown-content a:hover {
        color: white;
        background-color: #1f75cf;
    }

    .show {
        display: block;
    }
</style>
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
                <ul class="navbar-nav my-lg-0" id="navbar_ul">
                    <li class="nav-item dropdown">
                        <a id="a" class="nav-link dropdown-toggle text-muted waves-effect waves-dark dropbtn" href=""
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                           onclick="dropdownContent()">
                            <img src="<%=basePath%>assets/images/buling.png" alt="user" class="profile-pic m-r-5"/>
                            <span style="font-size:11px;position:absolute;left:33px;top:10px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;">
                            <span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center"
                                  id="total_count"></span>
                            </span>&nbsp;
                        </a>
                        <div class="dropdown-content" id="dropdown-a">
                            <a href="<%=basePath%>roster/startWarningExamineListPage?loginId=${loginId}">征地进入待处理 <span
                                    style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span
                                    style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center"
                                    id="start_count"></span></span></a>
                            <a href="<%=basePath%>roster/endWarningExamineListPage?loginId=${loginId}">征地退出待处理 <span
                                    style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span
                                    style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center"
                                    id="end_count"></span></span></a>
                            <a href="<%=basePath%>roster/examineListPage?loginId=${loginId}" id="navbar_tab">征地进入/退出待复审
                                <span style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span
                                        style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center"
                                        id="again_examine_count"></span></span></a>
                            <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=1">尊老金城镇居民待处理 <span
                                    style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span
                                    style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center"
                                    id="respect_town_count"></span></span></a>
                            <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=2">尊老金农村居民待处理 <span
                                    style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span
                                    style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center"
                                    id="respect_country_count"></span></span></a>
                        </div>
                    </li>
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
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=5"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>全部</a>
                            </li>
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
                        <a href="<%=basePath%>respect/longevityPager?loginId=${loginId}" class="waves-effect"><i
                                class="fa fa-address-card m-r-10" aria-hidden="true"></i>长寿金</a>
                    </li>
                    <li>
                        <a class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>已故人员花名册</a>
                        <ul>
                            <li>
                                <a href="<%=basePath%>examine/deceasedPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>征地人员已故名单</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=4"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>
                                    居民尊老金已故名单</a>
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
                    <h3 class="text-themecolor m-b-0 m-t-0">新增征地人员社会救济金</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="<%=basePath%>roster/allExamineListPage?loginId=${loginId}">列表</a>
                        </li>
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
                            <div style="height: 60px;background-color: #efefef;font-size: 16px; font-weight: bold; line-height: 60px; vertical-align: middle;">
                                <span class="fa fa-database" style="padding-left: 20px"></span> 编辑居民信息
                            </div>
                            <form class="form-horizontal form-material">
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
                                    <label class="col-md-12"><span style="color: red">*</span>身份证号</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入身份证号码"
                                               class="form-control form-control-line" id="idCard">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">联系电话</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入联系电话"
                                               class="form-control form-control-line" id="phone">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="house" class="col-md-12"><span style="color: red">*</span>户籍所在地</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入户籍所在地"
                                               class="form-control form-control-line" name="house"
                                               id="house">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="address" class="col-md-12"><span style="color: red">*</span>现住地</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入现住地" class="form-control form-control-line"
                                               name="address"
                                               id="address">
                                    </div>
                                </div>
                                <div class="form-group" id="tab_4" style="display: none">
                                    <label class="col-sm-12"><span style="color: red">*</span>所属社区</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="community"></select>
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
                                    <label for="villageTime" class="col-md-12"><span
                                            style="color: red">*</span>征地时间</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请选择征地时间"
                                               class="form-control form-control-line date_picker"
                                               name="villageTime" id="villageTime" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">征地时年龄</label>
                                    <div class="col-md-12">
                                        <input type="number" placeholder="请输入征地时年龄"
                                               class="form-control form-control-line" id="villageAge">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="village" class="col-md-12"><span
                                            style="color: red">*</span>征地时所在村组</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入征地时所在村组"
                                               class="form-control form-control-line" name="village"
                                               id="village">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>撤队时安置情况分类</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="cdState">
                                            <option value="">请选择</option>
                                            <option value="1">未撤队先安置</option>
                                            <option value="2">撤队时安置</option>
                                            <option value="2">领取征地待业</option>
                                            <option value="2">领取一次性补偿金</option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Column -->
            </div>

            <div class="row">
                <!-- Column -->
                <div class="col-lg-8 col-xlg-9 col-md-7">
                    <div class="card">
                        <div class="card-block">
                            <div style="height: 60px;background-color: #efefef;font-size: 16px; font-weight: bold; line-height: 60px; vertical-align: middle;">
                                <span class="fa fa-database" style="padding-left: 20px"></span> 发放补助信息
                            </div>
                            <form class="form-horizontal form-material">
                                <div class="form-group">
                                    <label for="startTime" class="col-md-12"><span
                                            style="color: red">*</span>开始发放时间</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请选择开始发放时间"
                                               class="form-control form-control-line date_picker"
                                               name="startTime" id="startTime" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="stopTime" class="col-md-12"><span
                                            style="color: red">*</span>停止发放时间</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请选择停止发放时间"
                                               class="form-control form-control-line date_picker"
                                               name="stopTime" id="stopTime" readonly="readonly">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-12"><span style="color: red">*</span>动态享受年月</label>
                                    <div class="col-md-12">
                                        <input type="text" class="form-control form-control-line" id="dtxsny"
                                               readonly="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12"><span style="color: red">*</span>发放标准</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入发放标准"
                                               class="form-control form-control-line" id="ffbj" readonly>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="house" class="col-md-12">新增批次</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="请输入新增批次"
                                               class="form-control form-control-line" name="batch"
                                               id="batch">
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
                <!-- Column -->
            </div>

            <div class="row">
                <!-- Column -->
                <div class="col-lg-8 col-xlg-9 col-md-7">
                    <div class="card">
                        <div class="card-block">
                            <div style="height: 60px;background-color: #efefef;font-size: 16px; font-weight: bold; line-height: 60px; vertical-align: middle;">
                                <span class="fa fa-database" style="padding-left: 20px"></span> 审核与操作
                            </div>
                            <form class="form-horizontal form-material">
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>是否企业参保</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="isInsured">
                                            <option value="">请选择</option>
                                            <option value="1">是</option>
                                            <option value="2">否</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>失业状态</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="unemployment">
                                            <option value="">请选择</option>
                                            <option value="1">领取失业金</option>
                                            <option value="2">未领取失业金</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" id="unStartDom" style="display:none;">
                                    <label class="col-sm-12"><span style="color: red">*</span>领取失业金开始时间</label>
                                    <div class="col-sm-12">
                                        <input type="text" placeholder="领取失业金开始时间"
                                               class="form-control form-control-line date_picker"
                                               name="unStart" id="unStart" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group" id="unEndDom" style="display:none;">
                                    <label class="col-sm-12"><span style="color: red">*</span>领取失业金截止时间</label>
                                    <div class="col-sm-12">
                                        <input type="text" placeholder="领取失业金截止时间"
                                               class="form-control form-control-line date_picker"
                                               name="unEnd" id="unEnd" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>是否并轨</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="comping">
                                            <option value="">请选择</option>
                                            <option value="1">是</option>
                                            <option value="2">否</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>变动情况</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="changes">
                                            <option value="">请选择</option>
                                            <option value="1">迁出</option>
                                            <option value="2">新增</option>
                                            <option value="3">死亡</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12"><span style="color: red">*</span>发放情况</label>
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
                                <div class="form-group" id="stopTypeDom" style="display: none;">
                                    <label class="col-sm-12"><span style="color: red">*</span>暂停原因</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" id="stopType">
                                            <option value="">请选择</option>
                                            <option value="1">就业</option>
                                            <option value="2">退休</option>
                                            <option value="3">其他</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" id="stopReasonDom" style="display: none;">
                                    <label class="col-md-12">暂停原因备注</label>
                                    <div class="col-md-12">
                                        <textarea rows="1" placeholder="请填写内容" class="form-control form-control-line"
                                                  id="stopReason"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Column -->
            </div>
            <div class="row">
                <!-- Column -->
                <div class="col-lg-8 col-xlg-9 col-md-7">
                    <div class="card">
                        <div class="card-block">
                            <form class="form-horizontal form-material">
                                <div class="form-group" style="text-align: center">
                                    <div class="col-sm-12">
                                        <button type="button" class="btn btn-info" id="submit">确认</button>
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
<input type="hidden" id="examineId" value="${examineId}">
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
    //        lay('.date_picker').each(function () {
    //            laydate.render({
    //                elem: this,
    //                done: function(value, date, endDate){
    //                    var elemId = $(this.elem[0]).attr("id");
    //                    changeDate(value,elemId);
    //                }
    //            });
    //        });

    laydate.render({
        elem: "#birthday"
    });

    laydate.render({
        elem: "#villageTime"
    });

    laydate.render({
        elem: "#startTime",
        type: 'month',
        done: function (value, date, endDate) {
            var elemId = $(this.elem[0]).attr("id");
            changeDate(value, elemId);
        }
    });
    laydate.render({
        elem: "#stopTime",
        type: 'month',
        done: function (value, date, endDate) {
            var elemId = $(this.elem[0]).attr("id");
            changeDate(value, elemId);
        }
    });
    laydate.render({
        elem: "#unStart",
        type: 'month'
    });
    laydate.render({
        elem: "#unEnd",
        type: 'month'
    });

    findAllCommunity();
    getExamineById();
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
                    $("#navbar_tab").css("display", "block");
                } else if (data.data.type == 2) {
                    $("#tab_1").css("display", "none");
                    $("#tab_2").css("display", "none");
                    $("#tab_3").css("display", "block");
                    $("#tab_4").css("display", "none");
                    $("#navbar_tab").css("display", "none");
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

    $("#status").on("change", function () {
        var status = $("#status").val();
        if (status == 3) {
            $("#stopTypeDom").css("display", "block");
            $("#stopReasonDom").css("display", "block");
        } else {
            $("#stopTypeDom").css("display", "none");
            $("#stopReasonDom").css("display", "none");
        }
    })

    $("#unemployment").on("change", function () {
        var unemployment = $("#unemployment").val();
        if (unemployment == 1) {
            $("#unStartDom").css("display", "block");
            $("#unEndDom").css("display", "block");
        } else {
            $("#unStartDom").css("display", "none");
            $("#unEndDom").css("display", "none");
        }
    })

    $("#submit").on("click", function () {

        if (confirm("是否确认提交?")) {
            var name = $("#name").val();
            var gender = $("#gender").val();
            var birthday = $("#birthday").val();
            var idCard = $("#idCard").val();
            var phone = $("#phone").val();
            var house = $("#house").val();
            var address = $("#address").val();
            var communityId = $("#community").val();
            var villageTime = $("#villageTime").val();
            var villageAge = $("#villageAge").val();
            var village = $("#village").val();
            var cdState = $("#cdState").val();
            var startTime = $("#startTime").val();
            var stopTime = $("#stopTime").val();
            var dtxsny = $("#dtxsny").val();
            var ffbj = $("#ffbj").val();
            var batch = $("#batch").val();
            var isInsured = $("#isInsured").val();
            var unemployment = $("#unemployment").val();
            var unStart = $("#unStart").val();
            var unEnd = $("#unEnd").val();
            var comping = $("#comping").val();
            var changes = $("#changes").val();
            var status = $("#status").val();
            var stopType = $("#stopType").val();
            var stopReason = $("#stopReason").val();
            var isMove = $("#isMove").val();
            var examineId = $("#examineId").val();
            var loginId = $("#loginId").val();
            if (startTime!=null&&startTime!=""){
                startTime = startTime+"-01";
            }
            if (stopTime!=null&&stopTime!=""){
                stopTime = stopTime+"-01";
            }
            if (unStart!=null&&unStart!=""){
                unStart = unStart+"-01";
            }
            if (unEnd!=null&&unEnd!=""){
                unEnd = unEnd+"-01";
            }
            $.post("<%=basePath%>examine/updateExamineById", {
                "loginId": loginId,
                "examineId": examineId,
                "name": name,
                "gender": gender,
                "birthday": birthday,
                "idCard": idCard,
                "phone": phone,
                "house": house,
                "address": address,
                "communityId": communityId,
                "villageTime": villageTime,
                "villageAge": villageAge,
                "village": village,
                "cdState": cdState,
                "startTime": startTime,
                "stopTime": stopTime,
                "dtxsny": dtxsny,
                "ffbj": ffbj,
                "batch": batch,
                "isInsured": isInsured,
                "unemployment": unemployment,
                "unStart": unStart,
                "unEnd": unEnd,
                "comping": comping,
                "changes": changes,
                "status": status,
                "stopType": stopType,
                "stopReason": stopReason,
                "isMove": isMove
            }, function (data) {
                alert(data.message);
            });
        }
    })

    function getExamineById() {
        var examineId = $("#examineId").val();
        $.post("<%=basePath%>examine/getExamineById", {"examineId": examineId}, function (data) {
            var object = data.data;
            $("#name").val(object.name);
            $("#gender").val(object.gender);
            $("#birthday").val(fmtDate(object.birthday));
            $("#idCard").val(object.idCard);
            $("#phone").val(object.phone);
            $("#house").val(object.house);
            $("#address").val(object.address);
            $("#community").val(object.communityId);
            $("#villageTime").val(fmtDate(object.villageTime));
            $("#villageAge").val(object.villageAge);
            $("#village").val(object.village);
            $("#cdState").val(object.cdState);
            $("#startTime").val(fmtDate1(object.startTime));
            $("#stopTime").val(fmtDate1(object.stopTime));
            $("#dtxsny").val(object.dtxsny);
            $("#ffbj").val(object.ffbj);
            $("#batch").val(object.batch);
            $("#isInsured").val(object.isInsured);
            $("#unemployment").val(object.unemployment);
            $("#unStart").val(fmtDate1(object.unStart));
            $("#unEnd").val(fmtDate1(object.unEnd));
            $("#comping").val(object.comping);
            $("#changes").val(object.changes);
            $("#status").val(object.status);
            $("#stopType").val(object.stopType);
            $("#stopReason").val(object.stopReason);
            $("#isMove").val(object.isMove);
            if (object.unemployment == 1) {
                $("#unStartDom").css("display", "block");
                $("#unEndDom").css("display", "block");
            } else {
                $("#unStartDom").css("display", "none");
                $("#unEndDom").css("display", "none");
            }
            if (object.status == 3) {
                $("#stopTypeDom").css("display", "block");
                $("#stopReasonDom").css("display", "block");
            } else {
                $("#stopTypeDom").css("display", "none");
                $("#stopReasonDom").css("display", "none");
            }
        })
    }

    function fmtDate(date) {
        var date = new Date(date);
        var y = 1900 + date.getYear();
        var m = "0" + (date.getMonth() + 1);
        var d = "0" + date.getDate();
        return y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length);
    }
    function fmtDate1(birthday) {
        var date = new Date(birthday);
        var y = 1900 + date.getYear();
        var m = "0" + (date.getMonth() + 1);
        var d = "0" + date.getDate();
//        return y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length);
        return y + "-" + m.substring(m.length - 2, m.length);
    }
    var startTime = "";
    var stopTime = "";

    function changeDate(value, elemId) {

        if (elemId == "stopTime") {
            startTime = value;
        } else if (elemId == "startTime") {
            stopTime = value;
        }
        if (startTime != "" && stopTime != "") {
//            var days = datedifference(startTime, stopTime);
//            $("#dtxsny").val(days + "天");
            var month = getMonthBetween(stopTime+"-01",startTime+"-01");
            $("#dtxsny").val(month + "个月");
        }
    }

    function datedifference(sDate1, sDate2) {    //sDate1和sDate2是2006-12-18格式
        var dateSpan,
            tempDate,
            iDays;
        sDate1 = Date.parse(sDate1);
        sDate2 = Date.parse(sDate2);
        dateSpan = sDate2 - sDate1;
        dateSpan = Math.abs(dateSpan);
        iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
        return iDays
    }
</script>
<script type="text/javascript">
    function dropdownContent() {
        if ($("#dropdown-a").hasClass("show")) {
            $("#dropdown-a").removeClass("show");
        } else {
            $("#dropdown-a").addClass("show");
        }
    }

    window.onclick = function (e) {
        if ($("#dropdown-a").hasClass("show")) {
            $("#dropdown-a").removeClass("show");
        }
    }

    $(function () {
        getTotalCount();
    });
    function getTotalCount() {
        var loginId = $("#loginId").val();
        $.post("<%=basePath%>examine/getTotalCount", {"loginId": loginId}, function (data) {

            if (data.code == 0) {
                var json = data.data;
                var startCount = json.startCount;
                var endCount = json.endCount;
                var respectTownCount = json.respectTownCount;
                var respectCountryCount = json.respectCountryCount;
                var againExamineCount = json.againExamineCount;
                var total = json.total;
                $("#total_count").html(total);
                $("#start_count").html(startCount);
                $("#end_count").html(endCount);
                $("#respect_town_count").html(respectTownCount);
                $("#respect_country_count").html(respectCountryCount);
                $("#again_examine_count").html(againExamineCount);
            }

        })
    }

    function getMonthBetween(startDate,endDate){
        startDate=new Date(startDate.replace(/-/g,'/'));
        endDate=new Date(endDate.replace(/-/g,'/'));
        var num=0;
        var year=endDate.getFullYear()-startDate.getFullYear();
        num+=year*12;
        var month=endDate.getMonth()-startDate.getMonth();
        num+=month;
        var day=endDate.getDate()-startDate.getDate();
        if(day>0){
            num+=1;
        }else if(day<0){
        }
        return num;
    }
</script>
</body>
</html>
