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
    <link rel="stylesheet" href="<%=basePath%>css/timeline.css">
    <style type="text/css">
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
            /*height: 55%;*/
        }
    </style>
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
                                aria-hidden="true"></i>报表分析</a>
                        <ul>
                            <li>
                                <a href="<%=basePath%>examine/examineStatisticPage?loginId=${loginId}"
                                   class="waves-effect"><i
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
                    <h3 class="text-themecolor m-b-0 m-t-0">征地人员社会救济金</h3>
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
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="age">人员年龄：</label>
                        <input type="number" class="form-control" id="age" placeholder="请输年龄">
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="comping">性别：</label>
                        <select class="form-control" id="gender">
                            <option selected value="">请选择</option>
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="comping">是否并轨：</label>
                        <select class="form-control" id="comping">
                            <option selected value="">请选择</option>
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="changes">变动情况：</label>
                        <select class="form-control" id="changes">
                            <option selected value="">请选择</option>
                            <option value="1">迁出</option>
                            <option value="2">新增</option>
                            <option value="2">死亡</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="status">发放状态：</label>
                        <select class="form-control" id="status">
                            <option selected value="">请选择</option>
                            <option value="1">未开始</option>
                            <option value="2">发放中</option>
                            <option value="3">已暂停</option>
                            <option value="4">已退出</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="isInsured">参保状态：</label>
                        <select class="form-control" id="isInsured">
                            <option selected value="">请选择</option>
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="unemployment">失业状态：</label>
                        <select class="form-control" id="unemployment">
                            <option selected value="">请选择</option>
                            <option value="1">领取失业金</option>
                            <option value="2">未领取失业金</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px" id="tab_4">
                        <label for="communityId">所属社区：</label>
                        <select class="form-control" id="communityId"></select>
                    </div>

                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <button type="button" class="btn btn-info" id="search"><span
                                class=" fa fa-search"></span> 搜索
                        </button>
                        <button type="reset" class="btn btn-info" style="margin-left: 5px;"><span
                                class=" fa fa-refresh"></span> 重置
                        </button>
                    </div>


                </form>
            </div>
            <div class="row">
                <!-- column -->
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-block">
                            <button type="button" class="btn btn-info" onclick="$('#file').click()"><span
                                    class=" fa fa-upload"></span> 批量导入
                            </button>
                            <input type="file" style="display: none" id="file" name="file" onchange="uploadData(this)">
                            <a class="btn btn-info" href="<%=basePath%>/temp/model2.xlsx" style="color: #fff"><span
                                    class=" fa fa-download"></span> 下载模板</a>
                            <button type="button" class="btn btn-info" id="exportExamine"><span
                                    class=" fa fa-download"></span> 导出
                            </button>
                            <button type="button" class="btn btn-info" id="addExamine"><span
                                    class=" fa fa-plus-square"></span> 新增
                            </button>
                            <span>总人数：<span id="totalCount" style="color: red"></span>（人）</span>
                            <div class="table-responsive">
                                <table class="table" id="table">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12" style="text-align: center">
                    <ul id="pageLimit"></ul>
                </div>
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

<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="col-md-12">
                        <div class="row">
                            <h2>备注查看</h2>
                            <div class="timeline timeline-line-dotted">

                                <div class="timeline-item">
                                    <div class="timeline-point timeline-point-success">
                                        <i class="fa fa-money"></i>
                                    </div>
                                    <div class="timeline-event">
                                        <div class="timeline-heading">
                                            <h4>社区进入审核备注</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p id="remark1"></p>
                                        </div>
                                        <div class="timeline-footer">
                                            <p class="text-right" id="time1"></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="timeline-item">
                                    <div class="timeline-point timeline-point-success">
                                        <i class="fa fa-money"></i>
                                    </div>
                                    <div class="timeline-event">
                                        <div class="timeline-heading">
                                            <h4>街道进入审核备注</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p id="remark3"></p>
                                        </div>
                                        <div class="timeline-footer">
                                            <p class="text-right" id="time3"></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="timeline-item">
                                    <div class="timeline-point timeline-point-success">
                                        <i class="fa fa-money"></i>
                                    </div>
                                    <div class="timeline-event">
                                        <div class="timeline-heading">
                                            <h4>社区退出审核备注</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p id="remark2"></p>
                                        </div>
                                        <div class="timeline-footer">
                                            <p class="text-right" id="time2"></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="timeline-item">
                                    <div class="timeline-point timeline-point-success">
                                        <i class="fa fa-money"></i>
                                    </div>
                                    <div class="timeline-event">
                                        <div class="timeline-heading">
                                            <h4>街道退出审核备注</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p id="remark4"></p>
                                        </div>
                                        <div class="timeline-footer">
                                            <p class="text-right" id="time4"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<input type="hidden" id="loginId" value="${loginId}">
<!-- ============================================================== -->
<!-- All Jquery -->
<!-- ============================================================== -->
<%--<script src="<%=basePath%>assets/plugins/jquery/jquery.min.js"></script>--%>
<script src="<%=basePath%>myjs/jquery.min.js"></script>

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
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>

<script src="<%=basePath%>myjs/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>myjs/dialog.min.js"></script>
<a href="http://localhost:8080/model/upload/8a43f07859a74f25b3e27f48013cf367.docx" id="temp_a"></a>
<script>
    $(function () {
        selectRosterExamine(1, 10);
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
            }
        });
    }
    $("#search").on("click", function () {
        selectRosterExamine(1, 10);
    })

    $("#addExamine").on("click", function () {
        window.location.href = "<%=basePath%>roster/addExaminePage?loginId=" + $("#loginId").val();
    })

    $("#exportExamine").on("click", function () {
        var gender = $("#gender").val();
        var name = $("#name").val();
        var idCard = $("#idCard").val();
        var house = $("#house").val();
        var comping = $("#comping").val();
        var age = $("#age").val();
        var changes = $("#changes").val();
        var status = $("#status").val();
        var unemployment = $("#unemployment").val();
        var isInsured = $("#isInsured").val();
        var communityId = $("#communityId").val();
        var url = "<%=basePath%>examine/exportExamine?gender=" + gender + "&name=" + name + "&idCard=" + idCard + "&house=" + house + "&comping=" + comping + "&age=" + age + "&changes=" + changes + "&status=" + status + "&unemployment=" + unemployment + "&isInsured=" + isInsured + "&communityId=" + communityId;
        console.log(url);
        window.location.href = url;
    })

    function selectRosterExamine(pageNum, pageSize) {
        var gender = $("#gender").val();
        var name = $("#name").val();
        var idCard = $("#idCard").val();
        var house = $("#house").val();
        var comping = $("#comping").val();
        var age = $("#age").val();
        var changes = $("#changes").val();
        var status = $("#status").val();
        var unemployment = $("#unemployment").val();
        var isInsured = $("#isInsured").val();
        var communityId = $("#communityId").val();
        var columns = [];
        var NUM = {
            field: 'NUM',
            title: '序号',
            align: 'center',
            valign: 'middle',
            width: 90,
            formatter: function (value, row, index) {
                return ((pageNum - 1) * pageSize) + index + 1;
            }
        };
        var a = {
            field: 'name',
            title: '姓名',
            align: 'center',
            valign: 'middle',
            width: 90,
            formatter: function (value, row, index) {
                return row.name == null ? "-" : row.name;
            }
        };
        var b = {
            field: 'gender',
            title: '性别',
            align: 'center',
            valign: 'middle',
            width: 60,
            formatter: function (value, row, index) {
                return row.gender == null ? "-" : row.gender == 1 ? "男" : "女";
            }
        };
        var c = {
            field: 'age',
            title: '年龄',
            align: 'center',
            valign: 'middle',
            width: 60,
            formatter: function (value, row, index) {
                return row.age == null ? "-" : row.age;
            }
        };
        var d = {
            field: 'birthday',
            title: '出生年月',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.birthday == null ? "-" : fmtDate(row.birthday);
            }
        };
        var e = {
            field: 'idCard',
            title: '身份证号',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.idCard == null ? "-" : row.idCard;
            }
        };
        var f = {
            field: 'house',
            title: '户籍所在地',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.house == null ? "-" : row.house;
            }
        };
        var g = {
            field: 'address',
            title: '现住地',
            align: 'center',
            valign: 'middle',
            width: 240,
            formatter: function (value, row, index) {
                return row.address == null ? "-" : row.address;
            }
        };
        var h = {
            field: 'villageTime',
            title: '征地时间',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.villageTime == null ? "-" : fmtDate(row.villageTime);
            }
        };
        var i = {
            field: 'villageAge',
            title: '征地时年龄',
            align: 'center',
            valign: 'middle',
            width: 150,
            formatter: function (value, row, index) {
                return row.villageAge == null ? "-" : row.villageAge;
            }
        };
        var j = {
            field: 'village',
            title: '征地时所在村（组）',
            align: 'center',
            valign: 'middle',
            width: 240,
            formatter: function (value, row, index) {
                return row.village == null ? "-" : row.village;
            }
        };
        var k = {
            field: 'cdState',
            title: '撤队安置情况',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.cdState == null ? "-" : row.cdState == 1 ? "未撤队先安置" : row.cdState == 2 ? "撤队时安置" : row.cdState == 3 ? "领取征地待业" : row.cdState == 4 ? "领取一次性补偿金" : "";
            }
        };
        var l = {
            field: 'ffbj',
            title: '发放标准',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return row.ffbj == null ? "-" : row.ffbj;
            }
        };
        var m = {
            field: 'startTime',
            title: '开始发放时间',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.startTime == null ? "-" : fmtDate2(row.startTime);
            }
        };
        var n = {
            field: 'stopTime',
            title: '停止发放时间',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.stopTime == null ? "-" : fmtDate2(row.stopTime);
            }
        };
        var o = {
            field: 'dtxsny',
            title: '动态享受年月',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.dtxsny == null ? "-" : row.dtxsny;
            }
        };
        var p = {
            field: 'batch',
            title: '新增批次',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return row.batch == null ? "-" : row.batch;
            }
        };
        var q = {
            field: 'changes',
            title: '变动情况',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return row.changes == null ? "-" : row.changes == 1 ? "迁出" : row.changes == 2 ? "新增" : row.changes == 3 ? "死亡" : "-";
            }
        };
        var r = {
            field: 'isInsured',
            title: '参保状态',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return row.isInsured == null ? "-" : row.isInsured == 1 ? "已参保" : row.isInsured == 2 ? "未参保" : "-";
            }
        };
        var s = {
            field: 'unemployment',
            title: '失业状态',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return row.unemployment == null ? "-" : row.unemployment == 1 ? "领取失业金" : row.unemployment == 2 ? "未领取失业金" : "-";
            }
        };
        var t = {
            field: 'comping',
            title: '是否并轨',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return row.comping == null ? "-" : row.comping == 1 ? "是" : "否";
            }
        };
//        var u = {
//            field: 'state',
//            title: '审核状态',
//            align: 'center',
//            valign: 'middle',
//            width: 120,
//            formatter: function (value, row, index) {
//                return row.state == null ? "-" : row.state == 1 ? "审核通过" : row.state == 2 ? "审核不通过" : row.state == 3 ? "待定" : row.state == 4 ? "待复审" : "未审核";
//            }
//        };
        var v = {
            field: 'status',
            title: '发放状态',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return row.status == null ? "-" : row.status == 1 ? "未开始" : row.status == 2 ? "发放中" : row.status == 3 ? "已暂停" : row.status == 4 ? "已退出" : "-";
            }
        };
        var x = {
            field: 'remark',
            title: '备注',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return "<a class='btn btn-info' style='color: #fff' onclick='lookRemarkById(" + row.id + ")'><span class='fa fa-eye'></span> 查看</a>";
            }
        };
        var w = {
            field: 'cz',
            title: '操作',
            align: 'center',
            valign: 'middle',
            width: 240,
            formatter: function (value, row, index) {

                if (row.status == 4) {
                    return "<a class='btn btn-info' style='color: #fff' onclick='exportExamine(" + row.id + ")'><span class='fa fa-download'></span> 退出通知单</a>";
                } else {
                    return "<a class='btn btn-info' style='color: #fff' onclick='updateExamineById(" + row.id + ")'><span class='fa fa-edit'></span> 编辑</a>";
                }
            }
        };
        columns.push(NUM);
        columns.push(a);
        columns.push(b);
        columns.push(c);
        columns.push(d);
        columns.push(e);
        columns.push(f);
        columns.push(g);
        columns.push(h);
        columns.push(i);
        columns.push(j);
        columns.push(k);
        columns.push(l);
        columns.push(m);
        columns.push(n);
        columns.push(o);
        columns.push(p);
        columns.push(q);
        columns.push(r);
        columns.push(s);
        columns.push(t);
//        columns.push(u);
        columns.push(v);
        columns.push(x);
        columns.push(w);
        var loginId = $("#loginId").val();
        $.post("<%=basePath%>examine/findAllExamine", {
            "gender": gender,
            "loginId": loginId,
            "name": name,
            "idCard": idCard,
            "house": house,
            "comping": comping,
            "age": age,
            "changes": changes,
            "status": status,
            "unemployment": unemployment,
            "isInsured": isInsured,
            "communityId": communityId,
            "pageNum": pageNum,
            "pageSize": pageSize
        }, function (data) {
            var list = data.data.list;
            var count = data.data.count;
            $("#totalCount").html(count);
            $('#table').bootstrapTable('destroy').bootstrapTable({
                data: list,
                cache: false,
                pagination: false,
                fixedColumns: true,
                fixedNumber: 3,
                height: 500,
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
                selectRosterExamine(page, 10);
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

    function fmtDate2(birthday) {
        var date = new Date(birthday);
        var y = 1900 + date.getYear();
        var m = "0" + (date.getMonth() + 1);
        var d = "0" + date.getDate();
        return y + "年" + m.substring(m.length - 2, m.length) + "月";
    }

    function getAge(birthday) {
        //出生时间 毫秒
        var birthDayTime = new Date(birthday).getTime();
        //当前时间 毫秒
        var nowTime = new Date().getTime();
        //一年毫秒数(365 * 86400000 = 31536000000)
        return Math.ceil((nowTime - birthDayTime) / 31536000000);
    }

    function updateExamineById(examineId) {
        window.location.href = "<%=basePath%>examine/updateExaminePager?loginId=" + $("#loginId").val() + "&examineId=" + examineId;
    }

    function uploadData(fileObj) {
        var allowExtention = ".xlsx,.xls";
        var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
        if (allowExtention.indexOf(extention) > -1) {
            $.ajaxFileUpload({
                url: '<%=basePath%>examine/importExamine',
                type: 'post',
                data: {
                    "loginId": $("#loginId").val()
                },
                secureuri: false,
                fileElementId: "file",
                dataType: 'json',
                success: function (data, status) {
                    popup({type: "success", msg: data, delay: 2000});
                }
            });
        } else {
            popup({type: "error", msg: "仅支持xlsx、xls文件!", delay: 2000});
            fileObj.value = "";
        }
    }

    function lookRemarkById(examineId) {


        $.post("<%=basePath%>examine/getExamineById", {"examineId": examineId}, function (data) {
            var object = data.data;

            if (object.remark1 != null && object.remark1 != "") {
                $("#remark1").html(object.remark1);
            } else {
                $("#remark1").html("暂无备注");
            }
            if (object.remark2 != null && object.remark2 != "") {
                $("#remark2").html(object.remark2);
            } else {
                $("#remark2").html("暂无备注");
            }
            if (object.remark3 != null && object.remark3 != "") {
                $("#remark3").html(object.remark3);
            } else {
                $("#remark3").html("暂无备注");
            }
            if (object.remark4 != null && object.remark4 != "") {
                $("#remark4").html(object.remark4);
            } else {
                $("#remark4").html("暂无备注");
            }

            if (object.time1 != null && object.time1 != "") {
                $("#time1").html(fmtDate1(object.time1));
            }

            if (object.time2 != null && object.time2 != "") {
                $("#time2").html(fmtDate1(object.time2));
            }

            if (object.time3 != null && object.time3 != "") {
                $("#time3").html(fmtDate1(object.time3));
            }

            if (object.time4 != null && object.time4 != "") {
                $("#time4").html(fmtDate1(object.time4));
            }
            $("#myModal").modal('show');
        })

    }

    function fmtDate1(day) {
        var date = new Date(day);
        var y = 1900 + date.getYear();
        var m = "0" + (date.getMonth() + 1);
        var d = "0" + date.getDate();
        var h = date.getHours();
        var min = date.getMinutes();
        return y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length) + " " + h + ":" + min;
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

    function exportExamine(id) {

        $.post("<%=basePath%>/examine/exportExit", {"examineId": id}, function (data) {
            if (data != null && data != "") {
                $("#temp_a").attr("href", "<%=basePath%>" + data);
                popup({type: "load", msg: "正在生成文件，请稍后!", delay: 5000});
                setTimeout(function () {
                    $("#temp_a")[0].click();
                }, 5000);
            }
        })
    }
</script>
</body>
</html>
