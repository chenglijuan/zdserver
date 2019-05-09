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
    <div class="page-wrapper">
        <div class="container-fluid">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>社区管理</legend>
            </fieldset>
            <div class="row">
                <!-- column -->
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-block" id="operatorBtn">
                            <button type="button" class="btn btn-info" id="addUser"><span class=" fa fa-plus-square"></span> 新增</button>
                        </div>
                        <div class="table-responsive">
                            <table class="table" id="table">
                            </table>
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
<!-- ============================================================== -->
<!-- End Wrapper -->
<!-- ============================================================== -->
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
<script src="<%=basePath%>js/bootstrap-table.js"></script>
<script src="<%=basePath%>js/bootstrap-table-fixed-columns.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>laydate/laydate.js"></script>
<script src="<%=basePath%>assets/plugins/styleswitcher/jQuery.style.switcher.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>

<script>
    var pageSize = 5;
    $(function () {
        var loginId = $("#loginId").val();
        verification(loginId);
    })

    function verification(loginId) {
        $.post("<%=basePath%>user/getUserByUserId", {"userId": loginId}, function (data) {
            if (data.code == 0) {
                selectExamine(1, pageSize);
            } else {
                alert(data.message);
                window.location.href = "<%=basePath%>/login.jsp";
            }
        });
    }


    $("#search").on("click", function () {
        selectExamine(1, 10);
    })

    function selectExamine(pageNum, pageSize) {
        var columns = [];
        var a = {
            field: 'name',
            title: '社区名称',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.name;
            }
        };
        var b = {
            field: 'linkman',
            title: '社区联系人',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.linkman;
            }
        };
        var c = {
            field: 'phone',
            title: '联系电话',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.phone ? row.phone : "-";
            }
        };
        var d = {
            field: 'address',
            title: '社区地址',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.address ? row.address : "-";
            }
        };
        var e = {
            field: 'cz',
            title: '操作',
            align: 'center',
            valign: 'middle',
            width: 240,
            formatter: function (value, row, index) {
                return "<a class='btn btn-info' style='color: #fff' onclick='updateUserById(" + row.id + ")'><span class='fa fa-edit'></span> 编辑</a>&nbsp;&nbsp;"+
                    "<a class='btn btn-info' style='color: #fff' onclick='deleteById(" + row.id + ")'><span class='fa fa-edit'></span> 删除</a>";
            }
        };
        columns.push(a);
        columns.push(b);
        columns.push(c);
        columns.push(d);
        columns.push(e);

        $.post("<%=basePath%>community/selectCommunity", {
            "pageNum": pageNum,
            "pageSize": pageSize,
            "loginId": $("#loginId").val()
        }, function (data) {
            var list = data.data.list;
            var totalPage = data.data.totalPage;

            $('#table').bootstrapTable('destroy').bootstrapTable({
                data: list,
                cache: false,
                pagination: false,
                fixedColumns: true,
                //fixedNumber: 3,
                columns: columns
            })

            selectByPager(pageNum, totalPage);
        })
    }

    function selectByPager(pageNum, totalPage) {
        $('#pageLimit').bootstrapPaginator({
            currentPage: pageNum,//当前的请求页面。
            totalPages: totalPage,//一共多少页。
            size: "normal",//应该是页眉的大小。
            bootstrapMajorVersion: 3,//bootstrap的版本要求。
            alignment: "right",
            numberOfPages: pageSize,//一页列出多少数据。
            itemTexts: function (type, page, current) {//如下的代码是将页眉显示的中文显示我们自定义的中文。
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
                selectExamine(page, pageSize);
            }
        });
    }

    $("#addUser").on("click", function () {
        window.location.href = "<%=basePath%>community/addCommunityForm?loginId=" + $("#loginId").val();
    })

    function updateUserById(communityId) {
        window.location.href = "<%=basePath%>community/updateCommunityForm?loginId=" + $("#loginId").val() + "&communityId=" + communityId;
    }

    function deleteById(communityId) {
        $.post("<%=basePath%>community/deleteById", {"communityId": communityId}, function (data) {
            if (data.code == 0) {
                window.location.href = "<%=basePath%>community/communityPage?loginId=${loginId}";
            } else {
                popup({type: 'error', msg: data.message, delay: 2000, bg: true, clickDomCancel: true});
            }
        });
    }

</script>
</body>
</html>
