<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <base href="<%=basePath%>">

     <title>修改尊老金</title>
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
    <%--<header class="topbar">
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
    </header>--%>
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
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=1"
                                   class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>城镇居民尊老金</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=2"
                                   class="waves-effect"><i class="fa fa-user m-r-10"
                                                           aria-hidden="true"></i>农村居民尊老金</a>
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
                <div class="col-md-6 col-8 align-self-center" id="respect">
                    <h3 class="text-themecolor m-b-0 m-t-0" id="addHTitle"></h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item" id="longBackHref"><a
                                href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=${pageType}">列表</a>
                        </li>
                        <li class="breadcrumb-item active">审核</li>
                    </ol>
                </div>
                <%--<div class="col-md-6 col-8 align-self-center" id="longevity" style="display: none">
                    <h3 class="text-themecolor m-b-0 m-t-0">长寿金审核</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a
                                href="<%=basePath%>respect/longevityPager?loginId=${loginId}&pageType=${pageType}">列表</a>
                        </li>
                        <li class="breadcrumb-item active">审核</li>
                    </ol>
                </div>--%>
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
                                    <label for="name" class="col-md-3">姓名</label>
                                    <input type="text" placeholder="请输入姓名" readonly class="form-control col-md-8"
                                           name="name"
                                           id="name">
                                </div>
                                <div class="form-group form-control-line" style="display: none" id="respectTypeDiv">
                                    <label for="gender" class="col-md-3">尊老金类型</label>
                                    <select class="form-control col-md-8" readonly="" id="respectType">
                                        <option value="1" selected>城镇</option>
                                        <option value="2">农村</option>
                                    </select>
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="gender" class="col-md-3">性别</label>
                                    <select class="form-control col-md-8" readonly="" id="gender">
                                        <option value="1">男</option>
                                        <option value="2">女</option>
                                    </select>
                                </div>
                                <div class="form-group form-control-line">
                                    <label class="col-md-3">身份证号</label>
                                    <input type="text" placeholder="请输入身份证号码"
                                           class="form-control col-md-8" readonly id="idCard">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="birthday" class="col-md-3">出生年月</label>
                                    <input type="text" placeholder="请选择出生年月"
                                           class="form-control col-md-8 date_picker"
                                           name="birthday" id="birthday" readonly="readonly">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="birthday" class="col-md-3">年龄周岁</label>
                                    <input type="text" placeholder="" class="form-control col-md-8"
                                           name="age" id="age" readonly="readonly">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="house" class="col-md-3">发放标准</label>
                                    <input type="text" placeholder="发放标准"
                                           class="form-control col-md-8" name="issuStandard" readonly id="issuStandard">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="phone" class="col-md-3">联系电话</label>
                                    <input type="text" placeholder="联系电话"
                                           class="form-control col-md-8" readonly name="phone"
                                           id="phone">
                                </div>
                                <div class="form-group form-control-line" style="display: none" id="communityIdDiv">
                                    <label for="communityId" class="col-md-3">所属社区：</label>
                                    <select class="form-control col-md-8" readonly="" id="communityId">
                                        <option selected value="">请选择</option>
                                    </select>
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
                                <div class="form-group form-control-line">
                                    <label for="house" class="col-md-3">现户籍所在地</label>
                                    <input type="text" placeholder="请输入现户籍所在地"
                                           class="form-control col-md-8" readonly name="house" id="house">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="grantTimeStr" class="col-md-3">起始发放时间</label>
                                    <input type="text" placeholder="请选择出生年月"
                                           class="form-control col-md-8 date_picker"
                                           name="grantTimeStr" id="grantTimeStr" readonly="readonly">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="phone" class="col-md-3">动态享受年月</label>
                                    <input type="text" placeholder="动态享受年月"
                                           class="form-control col-md-8" readonly name="dynamicYearMonth"
                                           id="dynamicYearMonth">
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="changeState" class="col-md-3">变动情况说明：</label>
                                    <select class="form-control col-md-8" readonly="" id="changeState">
                                        <option selected value="">==请选择==</option>
                                        <option value="1">迁出</option>
                                        <option value="2">死亡</option>
                                    </select>
                                </div>
                                <div class="form-group form-control-line">
                                    <label for="grantState" class="col-md-3">发放状态：</label>
                                    <select class="form-control col-md-8" readonly="" id="grantState">
                                        <option selected value="">==请选择==</option>
                                        <option value="1">已暂停</option>
                                        <option value="2">发放中</option>
                                    </select>
                                </div>
                                <div class="form-group" rows="3">
                                    <label class="col-md-3">备注</label>
                                    <textarea rows="3" placeholder="请填写内容" class="form-control col-md-8"
                                              id="remark"></textarea>
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
                            <form class="form-horizontal form-material" style="text-align: center">
                                <div class="form-group" rows="3">
                                    <label class="col-md-3">备注</label>
                                    <textarea rows="3" placeholder="请填写内容" class="form-control col-md-8"
                                              id="auditRemark"></textarea>
                                </div>
                                <button type="button" style="display: none" id="button1" class="btn btn-success"
                                        onclick="examineSH(2)">审核通过
                                </button>
                                <button type="button" style="display: none" id="button2" class="btn btn-warning"
                                        onclick="examineSH(3)">审核不通过
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="loginId" value="${loginId}">
<input type="hidden" id="respectId" value="${respectId}"/>
<input type="hidden" id="pageType" value="${pageType}">
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
<script src="<%=basePath%>js/util.js"></script>
<script type="text/javascript" src="<%=basePath%>myjs/dialog.min.js"></script>
<script type="text/javascript">
    var roleType = 2;
    var pageType = 1;
    $(function () {
        $("#headerpage").load("page/header");
        var loginId = $("#loginId").val();
        verification(loginId);
        pageType = $("#pageType").val();
        console.log("pageType="+pageType);
    })

    function initDesc() {
        if (pageType == 1) {
            $("#addHTitle").append("审核城镇居民尊老金");
        } else if (pageType == 2) {
            $("#addHTitle").append("审核农村居民尊老金");
        } else if (pageType == 5) {
            $("#addHTitle").append("审核居民尊老金");
            $("#respectTypeDiv").show();
        } else if (pageType == 3) {
            $("#addHTitle").append("审核居民长寿金");
            $("#respectTypeDiv").show();
            $('#longBackHref').attr('href','<%=basePath%>respect/longevityPager?loginId=${loginId}&pageType=${pageType}');
        }
    }

    function verification(loginId) {
        $.post("<%=basePath%>user/getUserByUserId", {"userId": loginId}, function (data) {
            if (data.code == 0) {
                roleType = data.data.type;
                //如果是社区管理员默认
                if (roleType == 1) {
                    $("#communityIdDiv").show();
                    findAllCommunity();
                }
                initDesc();
                getRespectById();
            } else if (data.code == -1) {
                window.location.href = "../login/login.jsp";
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

    function getRespectById() {
        var respectId = $("#respectId").val();
        $.post("<%=basePath%>respect/getRespectById", {"respectId": respectId}, function (data) {
            var object = data.data;
            $("#name").val(object.name);
            $("#gender").val(object.gender);
            $("#idCard").val(object.idCard);
            $("#birthday").val(object.birthday);
            if (object.birthday) {
                var age = jsMyGetAge(object.birthday);
                $("#age").val(age);
                setIssuStandard(age, object.type);
            }
            $("#phone").val(object.phone);
            $("#house").val(object.house);
            $("#communityId").val(object.communityId);
            $("#grantTimeStr").val(object.grantTime);
            $("#changeState").val(object.changeState);
            $("#grantState").val(object.grantState);
            $("#remark").val(object.remark);

            var auditState = object.auditState;
            if (auditState == 1) {
                $("#button1").show();
                $("#button2").show();
            } else if (auditState == 2) {
                $("#button2").show();
            } else if (auditState == 3) {
                $("#button1").show();
            }
        })
    }


    function examineSH(state) {
        var auditRemark = $("#auditRemark").val();
        var respectId = $("#respectId").val();
        var pageType = $("#pageType").val();
        if (confirm("是否确认提交？")) {
            $.post("<%=basePath%>respect/auditReaspectById", {
                "loginId": $("#loginId").val(),
                "respectId": respectId,
                "remark": auditRemark,
                "state": state,
                "pageType": pageType
            }, function (data) {
                if (data.code == 0) {
                    console.log(data.data);
                    if (data.data == 3) {
                        window.location.href = "<%=basePath%>respect/longevityPager?loginId=${loginId}";
                    } else {
                        window.location.href = "<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=" + pageType;
                    }
                } else {
                    popup({type: 'error', msg: data.message, delay: 2000, bg: true, clickDomCancel: true});
                }
            });
        }
    }

    function setIssuStandard(age, resepectType) {
        // resepectType 1 城镇  2.农村
        // 2农村  1. 城镇
        if (resepectType == 1) {
            if (age < 79) {
                $("#issuStandard").val(0);
            } else if (age >= 80 && age <= 89) {
                $("#issuStandard").val(50);
            } else if (age >= 90 && age <= 99) {
                $("#issuStandard").val(100);
            } else if (age >= 100) {
                $("#issuStandard").val(300);
            }
        } else if (resepectType == 2) {
            if (age < 70) {
                $("#issuStandard").val(0);
            } else if (age >= 70 && age <= 79) {
                $("#issuStandard").val(50);
            } else if (age >= 80 && age <= 89) {
                $("#issuStandard").val(200);
            } else if (age >= 90 && age <= 99) {
                $("#issuStandard").val(500);
            } else if (age >= 100) {
                $("#issuStandard").val(1000);
            }
        }
    }

</script>
</body>
</html>
