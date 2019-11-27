
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<!-- ============================================================== -->
<!-- Topbar header - style you can find in pages.scss -->
<!-- ============================================================== -->
<header class="topbar">
    <nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
        <div class="navbar-header">
            <a class="navbar-brand" href="<%=basePath%>/roster/indexPage?loginId=${loginId}">
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
                        <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=1&dealAuditState=1">尊老金城镇居民待处理 <span style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center" id="respect_town_count"></span></span></a>
                        <a href="<%=basePath%>respect/respectPager?loginId=${loginId}&pageType=2&dealAuditState=1">尊老金农村居民待处理 <span style="font-size:11px;border-radius: 50%;height: 20px;width: 20px;display: inline-block;background: #f20c55;vertical-align: top;"><span style="display: block;color: #FFFFFF;height: 20px;line-height: 20px;text-align: center" id="respect_country_count"></span></span></a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href=""
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">账户：<span
                            id="username"></span> </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href=""
                       data-toggle="dropdown" aria-haspopup="true" onclick="loginOut()" aria-expanded="false">退出</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<script type="text/javascript">
    $(function () {
        var loginId = $("#loginId").val();
        verification(loginId);
    })
    function verification(loginId) {
        $.post("<%=basePath%>user/getUserByUserId", {"userId": loginId}, function (data) {
            if (data.code == -1) {
                alert(data.message);
                window.location.href = "<%=basePath%>/user/userLoginPage";
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
            }
        });
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
    function loginOut() {
        var loginId = $("#loginId").val();
        $.post("<%=basePath%>user/loginOut",{"loginId":loginId},function (data) {
            if (data.code == 0){
                window.location.href = "<%=basePath%>/user/userLoginPage";
            }
        });
    }
</script>

</html>
