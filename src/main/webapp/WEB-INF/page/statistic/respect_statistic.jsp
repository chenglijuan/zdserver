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
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>农村居民尊老金</a>
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
                    <li>
                        <a class="waves-effect"><i
                                class="fa fa-address-card m-r-10"
                                aria-hidden="true"></i>报表分析</a>
                        <ul>
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
            <fieldset class="layui-elem-field layui-field-title">
                <legend><span id="pageDesc"></span></legend>
            </fieldset>
            <div class="row">
                <form class="form-inline">
                    <div class="form-group form-control-line">
                        <label for="grantTimes">查询时间:</label>
                        <input type="text" placeholder=""
                               class="form-control col-md-8 date_picker"
                               name="grantTimes" id="grantTimes" readonly="readonly">
                        <div class="form-group " style="margin-top: 20px">
                            <button type="button" class="btn btn-info" id="search">搜索</button>
                            <button type="reset" class="btn btn-primary" style="margin-left: 5px;">重置</button>
                        </div>
                    </div>

                </form>
            </div>
            <div class="row">
                <!-- column -->
                <div class="col-sm-12">
                    <div class="card">
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

    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- End Wrapper -->
<!-- ============================================================== -->
<input type="hidden" id="loginId" value="${loginId}">
<input type="hidden" id="pageType" value="${pageType}">
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
    var pageSize = 10;
    var pageType = 1;
    $(function () {
        $("#headerpage").load("page/header");
        pageType = $("#pageType").val();
        initDesc();
        var loginId = $("#loginId").val();
        verification(loginId);
        laydate.render({
            elem: '#grantTimes'
            ,type:'month'
            ,range: true
        });
    })

    function initDesc() {
        if (pageType == 1) {
            $("#pageDesc").append("城镇居民尊老金报表111");
        } else if (pageType == 2) {
            $("#pageDesc").append("农村居民尊老金报表111");
        }
    }

    function verification(loginId) {
        $.post("<%=basePath%>user/getUserByUserId", {"userId": loginId}, function (data) {
            if (data.code == 0) {
                roleType = data.data.type;
                selectExamine(1, pageSize);
            } else {
                alert(data.message);
                window.location.href = "<%=basePath%>/user/userLoginPage";
            }
        });
    }

    $("#search").on("click", function () {
        selectExamine(1, 10);
    })


    /*function selectExamine() {
        $.post("<%=basePath%>respect/getStatisticRespect", {
            "type": $("#pageType").val(),
            "loginId": $("#loginId").val(),
            "grantTimes":$("#grantTimes").val()
        }, function (data) {
            var list = data.data.statisticResults;
            var month = data.data.months;
            var columns = [];
            /!*for (var i = 0; i < ; i++) {

            }
           *!/



            $('#table').bootstrapTable('destroy').bootstrapTable({
                data: list,
                cache: false,
                pagination: false,
                fixedColumns: true,
                fixedNumber: 3,
                columns: columns
            })
        })
    }*/

    function selectExamine() {
        $.post("<%=basePath%>respect/getStatisticRespect", {
            "type": $("#pageType").val(),
            "loginId": $("#loginId").val(),
            "grantTimes":$("#grantTimes").val()
        }, function (data) {
            var list = data.data.statisticResults;
            var month = data.data.months;
            var code = "<thead border='8'><tr ><th width='100px'>社区</th>";
            var length = month.length;
            for (var i = 0; i < length; i++) {
                code += "<th colspan='2'>" + month[i].replace("-","") + "</th>";
            }
            code += "</tr></thead>";
            code += "<tbody>";
            for (var i = 0; i < list.length; i++) {
                var community = list[i].community;
                var statistic = list[i].respectStatisticList;
                var statisticLength = statistic.length;

               code += "<tr><td>" + community.name + "</td>";

                for (var j = 0; j < statisticLength; j++) {
                    code += "<td> "+statistic[j].totalCount+"（人）</td>";
                    code += "<td> "+statistic[j].totalMoney+"（元）</td>";
                }
            }
            code += "</tbody>";
            $("#table").html(code);
        })
    }
</script>
</body>
</html>
