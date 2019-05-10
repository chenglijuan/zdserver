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
                                <a href="<%=basePath%>roster/allExamineListPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>全部</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/startWarningExamineListPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>到龄进入预警</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/endWarningExamineListPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>到龄退出预警</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/examineListPage?loginId=${loginId}" class="waves-effect"><i
                                        class="fa fa-user m-r-10" aria-hidden="true"></i>待审核</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>roster/undeterminedExamineListPage?loginId=${loginId}" class="waves-effect"><i
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
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=1" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>城镇居民尊老金</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=2" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>农村征地人员尊老金</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/longevityPager?loginId=${loginId}" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>长寿金</a>
                            </li>
                        </ul>
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
                            <li>
                                <a href="<%=basePath%>authrity/authrityPager?loginId=${loginId}&pageType=4"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>权限管理</a>
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
            <%--<div class="row page-titles">
                <div class="col-md-6 col-8 align-self-center">
                    <h3 class="text-themecolor m-b-0 m-t-0">尊老金</h3>
                </div>
            </div>--%>
            <fieldset class="layui-elem-field layui-field-title" >
                <legend>尊老金</legend>
            </fieldset>
            <div class="row">
                <form class="form-inline">
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="name">姓名：</label>
                        <input type="text" class="form-control" id="name" placeholder="请输姓名">
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="idCard">身份证号：</label>
                        <input type="text" class="form-control" id="idCard" placeholder="请输姓名身份证号">
                    </div>

                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="phone">联系电话：</label>
                        <input type="text" class="form-control" id="phone" placeholder="联系电话">
                    </div>

                    <div class="form-group col-md-3" style="margin-top: 20px">
                        <label for="grantTimes">起始发放时间：</label>
                        <div class="layui-input-inline">
                            <input type="text" class="form-control" id="grantTimes" placeholder="-">
                        </div>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px" id="changeStateDiv">
                        <label for="changeState">变动情况：</label>
                        <select class="form-control" id="changeState">
                            <option selected value="">==请选择==</option>
                            <option value="1">迁出</option>
                            <option value="2">死亡</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px" id="auditStateDiv">
                        <label for="auditState">审核状态：</label>
                        <select class="form-control" id="auditState">
                            <option selected value="">==请选择==</option>
                            <option value="1">待审核</option>
                            <option value="2">通过</option>
                            <option value="3">未通过</option>
                        </select>
                    </div>
                    <div class="form-group col-md-3" style="margin-top: 20px;display: none" id="communityIdDiv">
                        <label for="communityId">所属社区：</label>
                        <select class="form-control" id="communityId">
                            <option selected value="">==请选择==</option>
                        </select>
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
                        <div class="card-block" id="operatorBtn">
                            <button type="button" class="btn btn-info" onclick="$('#file').click()">批量导入</button>
                            <input type="file" style="display: none" id="file" name="file" onchange="uploadData(this)">
                            <a class="btn btn-info" href="<%=basePath%>respect/downRespectExcel" style="color: #fff">下载模板</a>
                            <button type="button" class="btn btn-info" id="addRespect"><span class=" fa fa-plus-square"></span> 新增</button>
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


        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">审核详情</h4>
                    </div>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>审核备注</th>
                                <th>审核状态</th>
                                <th>审核人</th>
                                <th>审核时间</th>
                            </tr>
                            </thead>
                            <tbody id="tbody">
                            </tbody>
                        </table>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
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
    var roleType = 2;
    var pageType = 1;
    $(function () {
        laydate.render({
            elem: '#grantTimes'
            ,range: true
        });
        pageType = $("#pageType").val();
        if(pageType == 4){
            $("#operatorBtn").hide();
            $("#changeStateDiv").hide();
            $("#auditStateDiv").hide();
        }

        var loginId = $("#loginId").val();
        verification(loginId);
    })
    function verification(loginId) {
        $.post("<%=basePath%>user/getUserByUserId",{"userId":loginId},function (data) {
            if (data.code == 0){
                roleType = data.data.type;
                //如果是社区管理员默认
                if(roleType == 1){
                    $("#communityIdDiv").show();
                    findAllCommunity();
                }
                selectExamine(1, pageSize);
            }else{
                alert(data.message);
                window.location.href="<%=basePath%>/login.jsp";
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
                var age = jsMyGetAge(row.birthday);
                return isEmpty(age) ? "-" : age;
            }
        };
        var d = {
            field: 'birthday',
            title: '出生年月',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return isEmpty(row.birthday) ? "-" : fmtDate(row.birthday);
            }
        };
        var e = {
            field: 'idCard',
            title: '身份证号',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return isEmpty(row.idCard) ? "-" : row.idCard;
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
            field: 'communityName',
            title: '所属社区',
            align: 'center',
            valign: 'middle',
            width: 150,
            formatter: function (value, row, index) {
                return row.communityId == null ? "-" : row.communityName;
            }
        };
        var h = {
            field: 'dynamicYearMonth',
            title: '动态享受年月',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.dynamicYearMonth;
            }
        };
        var i = {
            field: 'grantTime',
            title: '起始发放时间',
            align: 'center',
            valign: 'middle',
            width: 150,
            formatter: function (value, row, index) {
                return row.grantTime == null ? "-" : fmtDate(row.grantTime);
            }
        };
        var j = {
            field: 'phone',
            title: '联系电话',
            align: 'center',
            valign: 'middle',
            width: 240,
            formatter: function (value, row, index) {
                return row.phone;
            }
        };
        var k = {
            field: 'changeState',
            title: '变动情况说明',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.changeState == 1 ? "迁出" : row.changeState == 2 ? "死亡" : "-";
            }
        };
        var l = {
            field: 'issuStandard',
            title: '发放标准',
            align: 'center',
            valign: 'middle',
            width: 120,
            formatter: function (value, row, index) {
                return row.issuStandard == null ? "-" : row.issuStandard;
            }
        };
        var m = {
            field: 'auditState',
            title: '审核状态',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                var htmlRow = row.auditState == null ? "-" : row.auditState == 1 ? "待审核" : row.auditState == 2 ? "审核通过" :row.auditState == 3 ? "审核未通过" : "-";
                htmlRow = htmlRow + "<br/><span style='color: red'><a onclick='remarkDetail("+row.id+")'>审核详情</a></span>";
                return htmlRow;
            }
        };
        var n = {
            field: 'grantState',
            title: '发放状态',
            align: 'center',
            valign: 'middle',
            width: 180,
            formatter: function (value, row, index) {
                return row.grantState == null ? "-" : row.grantState == 1 ? "已暂停" : row.auditState == 2 ? "发放中" : "-";
            }
        };

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
        if(pageType != 4){
            var o = {
                field: 'cz',
                title: '操作',
                align: 'center',
                valign: 'middle',
                width: 240,
                formatter: function (value, row, index) {
                    return "<a class='btn btn-info' style='color: #fff' onclick='updateExamineById("+row.id+")'><span class='fa fa-edit'></span> 编辑</a>&nbsp;&nbsp;" +
                        "<a class='btn btn-info' style='color: #fff' onclick='auditById("+row.id+")'><span class='fa fa-edit'></span>审核</a>";
                }
            };
            columns.push(o);
        }
        var name = $("#name").val();
        var changeState  = $("#changeState").val();
        var idCard = $("#idCard").val();
        var phone = $("#phone").val();
        var grantTimes  = $("#grantTimes").val();
        var ageRange  = $("#ageRange").val();
        var communityId  = $("#communityId").val();
        var auditState =$("#auditState").val();

        $.post("<%=basePath%>respect/selectRespect", {
            "pageNum": pageNum,
            "pageSize": pageSize,
            "name":name,
            "changeState":changeState,
            "idCard":idCard,
            "phone":phone,
            "grantTimes":grantTimes,
            "ageRange":ageRange,
            "communityId":communityId,
            "type":$("#pageType").val(),
            "auditState":auditState,
            "loginId":$("#loginId").val()
        }, function (data) {
            var list = data.data.list;
            var totalPage = data.data.totalPage;

            $('#table').bootstrapTable('destroy').bootstrapTable({
                data: list,
                cache: false,
                pagination: false,
                fixedColumns: true,
                fixedNumber: 3,
                columns: columns
            })

            selectByPager(pageNum,totalPage);
        })
    }

    function selectByPager(pageNum,totalPage){
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

    $("#addRespect").on("click",function () {
        var pageType = $("#pageType").val();
        window.location.href="<%=basePath%>respect/addRespect?pageType="+pageType+"&loginId="+$("#loginId").val();
    })

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

    function updateExamineById(respectId) {
        var pageType = $("#pageType").val();
        window.location.href="<%=basePath%>respect/updateRespect?loginId="+$("#loginId").val()+"&respectId="+respectId+"&pageType="+pageType;
    }
    function auditById(respectId) {
        var pageType = $("#pageType").val();
        window.location.href="<%=basePath%>respect/auditRespect?loginId="+$("#loginId").val()+"&respectId="+respectId+"&pageType="+pageType;
    }


    function remarkDetail(respectId) {
        $.post("<%=basePath%>respect/getRemarksByRespectId", {
            "respectId":respectId,
            "loginId":$("#loginId").val()
        }, function (data) {
            if(data.success){
                var list = data.data;
                if(list == null || list.length > 0){
                    var code = "";
                    for (var i = 0, j = list.length; i < j; i++) {
                        code += "<tr>";
                        code += "<td>" + list[i].remark + "</td>";
                        if (list[i].auditState === 2) {
                            code += "<td>审核通过</td>";
                        } else if (list[i].auditState === 3) {
                            code += "<td>审核未通过</td>";
                        } else {
                            code += "<td>-</td>";
                        }
                        code += "<td>" + list[i].operatorName + "</td>";
                        code += "<td>" + fmtDate(list[i].createTime) + "</td>";

                        code += "</tr>";
                    }
                    $("#tbody").html(code);

                    $('#myModal').modal('show');
                }else{
                    alert("暂无数据");
                }
            }
        })
    }

    function uploadData(fileObj) {
        var allowExtention = ".xlsx,.xls";
        var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
        if(allowExtention.indexOf(extention) > -1){
            $.ajaxFileUpload({
                url: '<%=basePath%>respect/importRespect',
                type: 'post',
                data : {
                    "loginId":$("#loginId").val(),
                    "pageType":$("#pageType").val()
                },
                secureuri: false,
                fileElementId: "file",
                dataType: 'json',
                success: function(data, status){
                    console.log(data);
                    if(status){
                        alert("操作成功");
                        selectExamine(1, pageSize);
                    }

                }
            });
        }else{
            alert("仅支持" + allowExtention + "为后缀名的文件!");
            fileObj.value = "";
        }
    }


</script>
</body>
</html>
