<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <base href="<%=basePath%>">

        <title>后台管理</title>


    <meta http-equiv="pragma" content="no-cache">

    <meta http-equiv="cache-control" content="no-cache">

    <meta http-equiv="expires" content="0">


    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

    <meta http-equiv="description" content="This is my page">


    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-table.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-table-fixed-columns.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="<%=basePath%>css/colors/blue.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="<%=basePath%>js/html5shiv.js"></script>
    <script src="<%=basePath%>js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>mycss/dialog.css">
    <style type="text/css">
        #example {

            width: 500px;
        }

        #pageLimit li {
            height: 50px;
            width: 50px;
        }

        #table {
            table-layout: fixed;
        }

        .bootstrap-table .fixed-table-container .fixed-table-body {
            overflow-x: auto;
            overflow-y: auto;
            height: 55%;
        }
    </style>
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
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onclick="dropdownContent()">
                            <img src="<%=basePath%>assets/images/buling.png" alt="user" class="profile-pic m-r-5"/>
                            <span style="font-size:11px;position:absolute;left:33px;top:10px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;">
                            <span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center" id="total_count"></span>
                            </span>&nbsp;
                        </a>
                        <div class="dropdown-content" id="dropdown-a">
                            <a href="<%=basePath%>roster/startWarningExamineListPage?loginId=${loginId}">征地进入待处理 <span style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center" id="start_count"></span></span></a>
                            <a href="<%=basePath%>roster/endWarningExamineListPage?loginId=${loginId}">征地退出待处理 <span style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center" id="end_count"></span></span></a>
                            <a href="<%=basePath%>roster/examineListPage?loginId=${loginId}" id="navbar_tab">征地进入/退出待复审 <span style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center" id="again_examine_count"></span></span></a>
                            <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=1">尊老金城镇居民待处理 <span style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center" id="respect_town_count"></span></span></a>
                            <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=2">尊老金农村居民待处理 <span style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center" id="respect_country_count"></span></span></a>
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
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=2" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>农村征地人员尊老金</a>
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
                    <h3 class="text-themecolor m-b-0 m-t-0">待审核人员名单</h3>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <div class="row">
                <form class="form-inline">
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="name">人员姓名：</label>
                        <input type="text" class="form-control" id="name" placeholder="请输姓名">
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="idCard">身份证号：</label>
                        <input type="text" class="form-control" id="idCard" placeholder="请输姓名身份证号">
                    </div>

                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="house">户籍所在地：</label>
                        <input type="text" class="form-control" id="house" placeholder="请输户籍所在地">
                    </div>
                    <%--<div class="form-group col-md-3" style="margin-top: 20px">--%>
                        <%--<label for="comping">是否并轨：</label>--%>
                        <%--<select class="form-control" id="comping">--%>
                            <%--<option selected value="">请选择</option>--%>
                            <%--<option value="1">是</option>--%>
                            <%--<option value="2">否</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <%--<div class="form-group col-md-3" style="margin-top: 20px">--%>
                        <%--<label for="changes">变动情况：</label>--%>
                        <%--<select class="form-control" id="changes">--%>
                            <%--<option selected value="">请选择</option>--%>
                            <%--<option value="1">迁出</option>--%>
                            <%--<option value="2">新增</option>--%>
                            <%--<option value="2">死亡</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <%--<div class="form-group col-md-3" style="margin-top: 20px">--%>
                        <%--<label for="status">发放状态：</label>--%>
                        <%--<select class="form-control" id="status">--%>
                            <%--<option selected value="">请选择</option>--%>
                            <%--<option value="1">未开始</option>--%>
                            <%--<option value="2">发放中</option>--%>
                            <%--<option value="3">已暂停</option>--%>
                            <%--<option value="4">已退出</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <%--<div class="form-group col-md-3" style="margin-top: 20px">--%>
                        <%--<label for="unemployment">失业状态：</label>--%>
                        <%--<select class="form-control" id="unemployment">--%>
                            <%--<option selected value="">请选择</option>--%>
                            <%--<option value="1">领取失业金</option>--%>
                            <%--<option value="2">未领取失业金</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <div class="form-group col-md-3" style="margin-top: 20px" id="tab_4">
                        <label for="communityId">所属社区：</label>
                        <select class="form-control" id="communityId"></select>
                    </div>

                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="gender">性别：</label>
                        <select class="form-control" id="gender">
                            <option selected value="">请选择</option>
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="grantTimes">查询时间:</label>
                        <input type="text" placeholder=""
                               class="form-control col-md-8 date_picker"
                               name="grantTimes" id="grantTimes" readonly="readonly">
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <button type="button" class="btn btn-info" id="search">搜索</button>
                        <button type="reset" class="btn btn-primary" style="margin-left: 5px;">重置</button>
                    </div>


                </form>
            </div>
            <div class="row">
                <!-- column -->
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-block">
                            <div class="table-responsive">
                                <table class="table" id="table">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div style="text-align: center">
                <ul id="pageLimit"></ul>
            </div>
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
<input type="hidden" id="examineId" value="">
<!-- ============================================================== -->
<div class="modal fade bs-example-modal-lg" id="examineModal" tabindex="-1" role="dialog"
     aria-labelledby="examineModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="examineModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div>
                    <div style="height: 40px;background-color: #efefef;font-size: 16px; font-weight: bold; line-height: 40px; vertical-align: middle;margin-bottom: 20px">
                        <label><span class="fa fa-database" style="padding-left: 20px"></span>用户基本信息</label>
                    </div>
                    <div class="form-inline">
                        <div class="col-md-6 form-inline">
                            <label class="">姓名：</label>
                            <label class="" id="name_tab"></label>
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">性别：</label>
                            <label class="" id="gender_tab"></label>
                        </div>
                    </div>
                    <div class="form-inline" style="padding-top: 20px">
                        <div class="col-md-6 form-inline">
                            <label class="">生日：</label>
                            <label class="" id="birthday_tab"></label>
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">年龄：</label>
                            <label class="" id="age_tab"></label>
                        </div>
                    </div>
                    <div class="form-inline" style="padding-top: 20px">
                        <div class="col-md-6 form-inline">
                            <label class="">所属社区：</label>
                            <label class="" id="community_tab"></label>
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">是否迁出：</label>
                            <label class="" id="isMove_tab"></label>
                        </div>
                    </div>
                    <div class="form-inline" style="padding-top: 20px">
                        <div class="col-md-6 form-inline">
                            <label class="">常住地址：</label>
                            <label class="" id="address_tab"></label>
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">身份证号：</label>
                            <label class="" id="idCard_tab"></label>
                        </div>
                    </div>
                    <div class="form-inline" style="padding-top: 20px">
                        <div class="col-md-6 form-inline">
                            <label class="">户籍所在地：</label>
                            <label class="" id="house_tab"></label>
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">联系方式：</label>
                            <label class="" id="phone_tab"></label>
                        </div>
                    </div>
                </div>

                <div style="padding-top: 40px">
                    <div style="height: 40px;background-color: #efefef;font-size: 16px; font-weight: bold; line-height: 40px; vertical-align: middle;margin-bottom: 20px">
                        <label><span class="fa fa-database" style="padding-left: 20px"></span>发放补助信息</label>
                    </div>
                    <div class="form-inline">
                        <div class="col-md-6 form-inline">
                            <label class="">开始发放时间：</label>
                            <input class="form-control date_picker" id="startTime_tab" readonly="readonly">
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">停止发放时间：</label>
                            <input class="form-control date_picker" id="stopTime_tab" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-inline" style="padding-top: 20px">
                        <div class="col-md-6 form-inline">
                            <label class="">动态享受年月：</label>
                            <input class="form-control" id="dtxsny_tab"/>
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">发放标准：</label>
                            <input class="form-control" id="ffbj_tab"/>
                        </div>
                    </div>
                    <div class="form-inline" style="padding-top: 20px">
                        <div class="col-md-6 form-inline">
                            <label class="">新增批次：</label>
                            <input class="form-control" id="batch_tab"/>
                        </div>
                    </div>
                </div>

                <div style="padding-top: 40px">
                    <div style="height: 40px;background-color: #efefef;font-size: 16px; font-weight: bold; line-height: 40px; vertical-align: middle;margin-bottom: 20px">
                        <label><span class="fa fa-database" style="padding-left: 20px"></span>审核与操作</label>
                    </div>
                    <div class="form-inline">
                        <div class="col-md-6 form-inline">
                            <label class="">是否企业参保：</label>
                            <select class="form-control form-control-line" id="isInsured_tab">
                                <option value="">请选择</option>
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">失业状态：</label>
                            <select class="form-control" id="unemployment_tab">
                                <option value="">请选择</option>
                                <option value="1">领取失业金</option>
                                <option value="2">未领取失业金</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-inline" style="padding-top: 20px" id="unemploymentDom">
                        <div class="col-md-6 form-inline">
                            <label class="">领取开始时间：</label>
                            <input class="form-control date_picker" id="unStart_tab" readonly="readonly">
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">领取截止时间：</label>
                            <input class="form-control date_picker" id="unEnd_tab" readonly="readonly"/>
                        </div>
                    </div>

                    <div class="form-inline" style="padding-top: 20px">
                        <div class="col-md-6 form-inline">
                            <label class="">是否并轨：</label>
                            <select class="form-control" id="comping_tab">
                                <option value="">请选择</option>
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>
                        <div class="col-md-6 form-inline">
                            <label class="">变动情况：</label>
                            <select class="form-control" id="changes_tab">
                                <option value="">请选择</option>
                                <option value="1">迁出</option>
                                <option value="2">新增</option>
                                <option value="3">死亡</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-inline" style="padding-top: 20px">
                        <div class="col-md-12 form-inline">
                            <label class="">备注：</label>
                            <textarea class="form-control" id="remark_tab" cols="70" rows="3"></textarea>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="submit">
                    确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<input type="hidden" id="userType" value="" />
<!-- All Jquery -->
<!-- ============================================================== -->
<script src="<%=basePath%>assets/plugins/jquery/jquery.min.js"></script>

<!-- Bootstrap tether Core JavaScript -->
<script src="<%=basePath%>assets/plugins/bootstrap/js/tether.min.js"></script>
<script src="<%=basePath%>assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>assets/plugins/bootstrap/js/bootstrap-paginator.js"></script>
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
<script src="<%=basePath%>js/bootstrap-table.js"></script>
<script src="<%=basePath%>js/bootstrap-table-fixed-columns.js"></script>

<script src="<%=basePath%>laydate/laydate.js"></script>

<script src="<%=basePath%>myjs/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>myjs/dialog.min.js"></script>
<script>
    $(function () {
        laydate.render({
            elem: '#grantTimes'
            ,type:'month'
            ,range: true
        });

        findAllCommunity();
        var loginId = $("#loginId").val();
        verification(loginId);
    })
    function verification(loginId) {
        $.post("<%=basePath%>user/getUserByUserId", {"userId": loginId}, function (data) {
            if (data.code == -1) {
                alert(data.message);
                window.location.href = "../login/login.jsp";
            } else {
                $("#userType").val(data.data.type);
                $("#username").html(data.data.username);
                if (data.data.type == 1) {
                    $("#tab_1").css("display", "block");
                    $("#tab_2").css("display", "block");
                    $("#tab_3").css("display", "none");
                    $("#navbar_tab").css("display", "block");
                } else if (data.data.type == 2) {
                    $("#tab_1").css("display", "none");
                    $("#tab_2").css("display", "none");
                    $("#tab_3").css("display", "block");
                    $("#tab_4").css("display", "none");
                    $("#navbar_tab").css("display", "none");
                }
                selectExamine(1, 10);
            }
        });
    }
    $("#search").on("click", function () {
        selectExamine(1, 10);
    })

    function selectExamine(pageNum, pageSize) {
        var gender = $("#gender").val();
        var name = $("#name").val();
        var idCard = $("#idCard").val();
        var house = $("#house").val();
        // var comping = $("#comping").val();
        // var changes = $("#changes").val();
        // var status = $("#status").val();
        // var unemployment = $("#unemployment").val();
        var communityId = $("#communityId").val();
        var columns = [];
        var a = {
            field: 'name',
            title: '姓名',
            align: 'center',
            valign: 'middle',
            width: 90,
            formatter: function (value, row, index) {
                return row.name;
            }
        };
        var b = {
            field: 'gender',
            title: '性别',
            align: 'center',
            valign: 'middle',
            width: 60,
            formatter: function (value, row, index) {
                return row.gender == 1 ? "男" : "女";
            }
        };
        var c = {
            field: 'age',
            title: '年龄',
            align: 'center',
            valign: 'middle',
            width: 60,
            formatter: function (value, row, index) {
                return row.age;
            }
        };
        var d = {
            field: 'birthday',
            title: '出生年月',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return fmtDate(row.birthday);
            }
        };
        var e = {
            field: 'idCard',
            title: '身份证号',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.idCard;
            }
        };
        var f = {
            field: 'house',
            title: '户籍所在地',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.house;
            }
        };
        var g = {
            field: 'address',
            title: '现住地',
            align: 'center',
            valign: 'middle',
            width: 150,
            formatter: function (value, row, index) {
                return row.address;
            }
        };
        var l = {
            field: 'ffbj',
            title: '发放标准',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
//                return row.examine == null ? "" : row.examine.ffbj;
                return row.ffbj == null ? "" : row.ffbj;
            }
        };
        var h = {
            field: 'ffje',
            title: '发放金额（元）',
            align: 'center',
            valign: 'middle',
            width: 150,
            formatter: function (value, row, index) {
//                return row.examine == null ? "" : row.examine.ffbj;
                return row.ffbj == null ? "" : row.ffje;
            }
        };
        var m = {
            field: 'startTime',
            title: '开始发放时间',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
//                return row.examine == null ? "" : fmtDate(row.examine.startTime);
                return row.startTime == null ? "" : fmtDate3(row.startTime);
            }
        };
        var n = {
            field: 'stopTime',
            title: '停止发放时间',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
//                return row.examine == null ? "" : row.examine == null ? "" : fmtDate(row.examine.stopTime);
                return row.stopTime == null ? "" : fmtDate3(row.stopTime);
            }
        };
        columns.push(a);
        columns.push(b);
        columns.push(c);
        columns.push(d);
        columns.push(e);
        columns.push(f);
        columns.push(g);
        columns.push(l);
        columns.push(h);
        columns.push(m);
        columns.push(n);
        var loginId = $("#loginId").val();
        var grantTimes = $("#grantTimes").val()
        $.post("<%=basePath%>examine/findFftjExamineList", {
            "gender" : gender,
            "loginId":loginId,
            "name": name,
            "idCard": idCard,
            "house": house,
            "communityId": communityId,
            "grantTimes": grantTimes,
            "pageNum": pageNum,
            "pageSize": pageSize
        }, function (data) {
            var list = data.data.list;
            var count = data.data.count;
            $('#table').bootstrapTable('destroy').bootstrapTable({
                data: list,
                cache: false,
                pagination: false,
                fixedColumns: true,
                fixedNumber: 3,
                columns: columns
            })
            if (new Number(count) != 0) {
                limitPage(pageNum, getTotalPage(count));
            }
        })
    }
    function getTotalPage(total) {
        var count = new Number(total);
        var tatalPage = 0;
        if (count % 10 == 0) {
            tatalPage = count / 10;
        } else {
            tatalPage = Math.ceil(count / 10);
        }
        return tatalPage;
    }
    function limitPage(pageNum, totalPage) {
        $('#pageLimit').bootstrapPaginator({
            currentPage: pageNum,
            totalPages: totalPage,
            size: "normal",
            bootstrapMajorVersion: 3,
            alignment: "right",
            numberOfPages: 5,
            itemTexts: function (type, page, current) {
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            },
            onPageClicked: function (event, originalEvent, type, page) {
               // selectRoster(page, 10);
                selectExamine(page,10);
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
    function fmtDate(birthday) {
        var date = new Date(birthday);
        var y = 1900 + date.getYear();
        var m = "0" + (date.getMonth() + 1);
        var d = "0" + date.getDate();
        return y + "年" + m.substring(m.length - 2, m.length) + "月" + d.substring(d.length - 2, d.length) + "日";
    }

    function fmtDate3(birthday) {
        var date = new Date(birthday);
        var y = 1900 + date.getYear();
        var m = "0" + (date.getMonth() + 1);
        var d = "0" + date.getDate();
        return y + "年" + m.substring(m.length - 2, m.length) + "月";
    }

    $("#submit").on("click",function () {
        var loginId = $("#loginId").val();
        var examineId = $("#examineId").val();
        var startTime = $("#startTime_tab").val();
        var stopTime = $("#stopTime_tab").val();
        var dtxsny = $("#dtxsny_tab").val();
        var ffbj = $("#ffbj_tab").val();
        var batch = $("#batch_tab").val();
        var isInsured = $("#isInsured_tab").val();
        var unemployment = $("#unemployment_tab").val();
        var unStart = $("#unStart_tab").val();
        var unEnd = $("#unEnd_tab").val();
        var comping = $("#comping_tab").val();
        var changes = $("#changes_tab").val();
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
        $.post("<%=basePath%>examine/startExamine", {
            "loginId": loginId,
            "examineId": examineId,
            "startTime": startTime,
            "stopTime":stopTime,
            "dtxsny":dtxsny,
            "ffbj":ffbj,
            "batch":batch,
            "isInsured":isInsured,
            "unemployment":unemployment,
            "unStart":unStart,
            "unEnd":unEnd,
            "comping":comping,
            "changes":changes

        },function (data) {
            if (data.code == 0) {
                $("#examineModal").modal('toggle');
                popup({type: "success", msg: "操作成功", delay: 1000});
                selectExamine(1, 10);
            } else {
                popup({type: 'error', msg: data.message, delay: 2000, bg: true, clickDomCancel: true});
            }
        })
    })

    var startTime = "";
    var stopTime = "";
    function changeDate(value, elemId) {

        if (elemId == "stopTime_tab") {
            startTime = value;
        } else if (elemId == "startTime_tab") {
            stopTime = value;
        }
        if (startTime != "" && stopTime != "") {
            var month = getMonthBetween(stopTime+"-01",startTime+"-01");
            $("#dtxsny_tab").val(month + "个月");
        }
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
<script type="text/javascript">
    function dropdownContent() {
        if ($("#dropdown-a").hasClass("show")){
            $("#dropdown-a").removeClass("show");
        }else {
            $("#dropdown-a").addClass("show");
        }
    }

    window.onclick = function(e) {
        if ($("#dropdown-a").hasClass("show")){
            $("#dropdown-a").removeClass("show");
        }
    }

    $(function () {
        getTotalCount();
    });
    function getTotalCount() {
        var loginId = $("#loginId").val();
        $.post("<%=basePath%>examine/getTotalCount",{"loginId":loginId},function (data) {

            if (data.code == 0){
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
</script>
</body>
</html>
