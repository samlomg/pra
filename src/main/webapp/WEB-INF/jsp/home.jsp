<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">
    <meta name="author" content="Sam.BC.Liu">

    <%--重写的head --%>
    <title>Home Page</title>
    <!-- Timeline CSS -->
    <%--<link href="${TimelineCSS}" rel="stylesheet">--%>

    <!-- 框架中一定有得部分 -->
    <!-- Bootstrap Core CSS -->
    <link href="${BootstrapCoreCSS}" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <%--<link href="${MetisMenuCSS}" rel="stylesheet">--%>

    <!-- Custom CSS -->
    <link href="${CustomCSS}" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${CustomFonts}" rel="stylesheet" type="text/css">

    <link href="${datetimepickerCss}" rel="stylesheet">
</head>
<body>
<%--主体部分 --%>
<div id="wrapper">
    <!-- Navigation -->
    <!-- 菜单栏 -->
    <!-- 导航栏左开始 -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${rootUrl}home">LBC Services</a>
        </div>
        <!-- /.navbar-header -->
        <!-- 导航栏左结束 -->

        <!-- 导航栏右开始 -->
        <ul class="nav navbar-top-links navbar-right">

            <!-- 用户信息开始 -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="${rootUrl}userProfile"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="${rootUrl}userInfo"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="${rootUrl}logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
            <!-- 用户信息结束 -->
        </ul>
        <!-- /.navbar-top-links -->
        <!-- 导航栏左结束 -->

        <!-- 侧边栏开始 -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                        <!-- /input-group -->
                    </li>
                    <li>
                        <a href="${rootUrl}home"><i class="fa fa-dashboard fa-fw"></i> OverView</a>
                    </li>
                    <li>
                        <a href="${rootUrl}message"><i class="fa fa-dashboard fa-fw"></i> message</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
        <!-- 侧边栏结束 -->
    </nav>

    <!-- 内容框 page-wrapper -->
    <%--主体部分 --%>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">收据信息</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12 col-md 10">
                <!-- 操作栏 -->

            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-horizontal" id="queryparamform" name="queryparamform">
                        <!--固定项目-->
                        <input type="hidden" id="page" name="page" value="0"/>
                        <!--用于保存当前页码，方便FORM整个序列化提交 -->
                        <input type="hidden" id="size" name="size" value="10"/>
                        <!--固定项目 -->
                        <fieldset>

                            <div class="form-group">
                                <div class="col-sm-1 ">
                                    <button type="button" onclick="refreshdata();" class="btn btn-primary btn-default"><span
                                            class="glyphicon glyphicon-search"></span>查询
                                    </button>
                                </div>
                                <label class="col-sm-1 control-label" for="beginDate">开始日</label>
                                <div class="col-sm-2">
                                    <input class="form-control" name="beginDate" id="beginDate" type="text"
                                           placeholder="开始日期" value=""/>
                                </div>
                                <label class="col-sm-1 control-label" for="endDate">结束日</label>
                                <div class="col-sm-2">
                                    <input class="form-control" name="endDate" id="endDate" type="text" placeholder="结束日期"
                                           value=""/>
                                </div>
                                <label class="col-sm-1 control-label" for="salesInvoiceId">单据号</label>
                                <div class="col-sm-2">
                                    <input class="form-control" id="salesInvoiceId" name="salesInvoiceId" type="text"
                                           placeholder="单据号" value=""/>
                                </div>
                                <label for="flag" class="col-sm-1 control-label"> 状态:</label>
                                <div class="col-sm-2">
                                    <select id="flag" name="flag" class="form-control">
                                        <option value="">全部</option>
                                        <option value="1">未收款</option>
                                        <option value="400">已收款</option>
                                    </select>
                                </div>
                            </div>
                        </fieldset>
                        <div style="display:None">

                        </div>
                    </form>
                </div>
                <!-- 内容 -->
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr id="trh">

                            </tr>
                            </thead>
                            <tbody id="trs">

                            </tbody>
                        </table>
                    </div>
                    <div>
                        <div class="col-sm-8" id="PageInfoShow">

                        </div>
                        <div class="col-sm-4" id="PageNumber">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- 中间值部分 -->
<div style="display:None">
    <input id="phdoco" name="phdoco" type="hidden" value="${phdoco}"/>
</div>

<%--脚本部分--%>
<!-- jQuery -->
<script src="${jQuery}"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${BootstrapCoreJavaScript}"></script>

<!-- Custom Theme JavaScript -->
<%--<script src="${CustomThemeJavaScript}"></script>--%>

<!-- Metis Menu Plugin JavaScript -->
<%--<script src="${MetisMenuPluginJavaScript}"></script>--%>
<!-- commondjs JavaScript -->
<script src="${commondjs}"></script>

<script src="${datetimepickerJs}"></script>

<!--补充部分  -->
<script type="text/javascript">
    $(document).ready(function () {
        $("#beginDate").datetimepicker({
            format: "yyyy-mm-dd",
            minView: "month",//设置只显示到月份
            autoclose: true,
            todayBtn: true,
            language:'zh-CN',
            pickerPosition:"bottom-left"
        });

        $("#endDate").datetimepicker({
            format: "yyyy-mm-dd",
            minView: "month",//设置只显示到月份
            autoclose: true,
            todayBtn: true,
            language:'zh-CN',
            pickerPosition:"bottom-left"
        });

        refreshdata(0, 10)
    });

    //自定义JavaScript部分
    //清空

    function refreshdata(page, size) {
        $("#page").val(page); //设置当前页码
        $("#size").val(size); //设置当前每页行数
        ajaxPost("listSalesPlan", $("#queryparamform").serialize(), true, function (obj) {
            $("#trh").html("");
            $("#trs").html("");
            $("#PageInfoShow").html("");
            $("#PageNumber").html("");
            showHead();
            showBody(obj);
            showDataTab(obj);
        });
    }

    function sumbitFlag(salesInvoiceId, index) {
        ajaxPost("saveSalesInvoice", "salesInvoiceId=" + salesInvoiceId + "&flag=400", false, function (obj) {
            alert(obj.status);
        });
    }

    function showBody(obj) {
        var index = $("#trs tr:last").attr("rowIndex");
        index = Number((index ? index : -1)) + 1;
        $.each(obj.content, function (index, item) {

            var html = '<tr rowIndex="' + index + '">';
            html = html + '<td>';
            html = html + '<a class="tablelink" onClick="sumbitFlag(\'' + item.salesInvoiceId + '\',' + index + ');">提交</a>&nbsp&nbsp';
            html += '<input type="hidden" id="salesInvoiceId_' + index + '" type="text" name="salesOrderList[' + index + '].salesInvoiceId" readonly="true" value="' + item.salesInvoiceId + '"/>';
            html += '<input type="hidden" id="flag_' + index + '" type="text" name="salesOrderList[' + index + '].flag" value="' + item.flag + '"/>';
            html += '</td>';
            html += '<td class ="tdFormatePrim">' + (index + 1) + '</td>';
            html += '<td class ="tdFormate" id="companyName_' + index + '">' + item.companyName + '</td>';
            html += '<td class ="tdFormate" id="accountReceivable_' + index + '">' + item.accountReceivable + '</td>';
            html += '<td class ="tdFormate" id="documentTime_' + index + '">' + timeStamp2String(item.documentTime) + '</td>';
            html += '<td class ="tdFormate" id="payment_' + index + '">' + item.payment + '</td>';
            html += '<td class ="tdFormate" id="createUser_' + index + '">' + item.createUser + '</td>';
            html += '<td class ="tdFormate" id="createTime_' + index + '">' + timeStamp2String(item.createTime) + '</td>';
            html += '<td class ="tdFormate" id="receiveUser_' + index + '">' + nullToSpace(item.receiveUser) + '</td>';
            html += '<td class ="tdFormate" id="receiveTime_' + index + '">' + timeStamp2String(item.receiveTime) + '</td>';
            html += '<td class ="tdFormate">';
            html += '</td>';
            html = html + '</tr>';
            $("#trs").append(html);

        });
    }

    function showHead() {
        var tableHeadHtml = "";
        tableHeadHtml += '<th ></th>';
        tableHeadHtml += '<th >序号</th>';
        tableHeadHtml += '<th >公司名称</th>';
        tableHeadHtml += '<th >应收款</th>';
        tableHeadHtml += '<th >应收时间</th>';
        tableHeadHtml += '<th >类别</th>';
        tableHeadHtml += '<th >创建人</th>';
        tableHeadHtml += '<th >创建时间</th>';
        tableHeadHtml += '<th >收款人</th>';
        tableHeadHtml += '<th >收款时间</th>';
        tableHeadHtml += '<th >状态</th>';

        $("#trh").html(tableHeadHtml);
    }

    function saveHead() {
        var tableHeadHtml = "";
        tableHeadHtml += '<th ></th>';
        tableHeadHtml += '<th >序号</th>';
        tableHeadHtml += '<th >公司名称</th>';
        tableHeadHtml += '<th >应收款</th>';
        tableHeadHtml += '<th >应收时间</th>';
        tableHeadHtml += '<th >类别</th>';
        $("#trs").html(trh);
    }

    function addRow() {
        var index = $("#trs tr:last").attr("rowIndex");
        index = Number((index ? index : -1)) + 1;
        var html = '<tr rowIndex="' + index + '">';
        html = html + '<td>';
        html = html + '<a class="tablelink" onClick="deltr(' + index + ');">删除</a>&nbsp&nbsp';
        html += '<input type="hidden" id="salesInvoceId_' + index + '" type="text" name="salesOrderList[' + index + '].salesInvoiceId"/>';
        html += '<input type="hidden" id="createTime_' + index + '" type="text" name="salesOrderList[' + index + '].createTime"/>';
        html += '<input type="hidden" id="createUser_' + index + '" type="text" name="salesOrderList[' + index + '].createUser"/>';
        html += '<input type="hidden" id="receiveTime_' + index + '" type="text" name="salesOrderList[' + index + '].receiveTime"/>';
        html += '<input type="hidden" id="receiveUser_' + index + '" type="text" name="salesOrderList[' + index + '].receiveUser"/>';
        html += '<input type="hidden" id="flag_' + index + '" type="text" name="salesOrderList[' + index + '].flag"/>';
        html += '</td>';
        html += '<td class ="tdFormatePrim">" + (index+1) + "</td>';
        html += '<td class ="tdFormate"><input type="text" id="companyName_' + index + '" type="text" name="salesOrderList[' + index + '].companyName"/></td>';
        html += '<td class ="tdFormate"><input type="text" id="accountReceivable_' + index + '" type="text" name="salesOrderList[' + index + '].accountReceivable"/></td>';
        html += '<td class ="tdFormate"><input type="text" id="documentTime_' + index + '" type="text" name="salesOrderList[' + index + '].documentTime"/></td>';
        html += '<td class ="tdFormate"><input type="text" id="payment_' + index + '" type="text" name="salesOrderList[' + index + '].payment"/></td>';

        html = html + '</tr>';
        $("#trs").append(html);
    }

    function del(index) {
        $("#trs tr[rowIndex='" + index + "']").remove();
    }

    function showDataTab(obj) {
//        clearTableRows("dataTab"); //清除旧数据
        var number = obj.number;
        var currentPage = obj.number + 1;//当前页
        var totalPage = obj.totalPages;
        var pageSize = obj.size;//每页size
        var lastFlag = obj.last;
        var fristFlag = obj.first;

        var numberOfElements = obj.numberOfElements;//当前页记录数量

        var previousPageColor;//上一步颜色
        var nextPageColor; //下一步颜色

        var disableColor = "#AAA";
        var avaColor = "#00F";

        //分页页脚，左边是设置显示查询信息，以及当前状态;右边显示翻页
        //左边当前页，以及总页数
        var leftSizeHtml = '当前<span style="color: red;">' +
            currentPage + '</span>/<span style="color: red;">' + totalPage + '</span>,' +
            '共<span style="color: red;">' + numberOfElements + '</span>条记录。';
        $("#PageInfoShow").html(leftSizeHtml);

        //右边翻页状态一共5页翻
        var rightSizeHtml = '<ul class="pagination pagination-sm">';
        //1:判断总页数等于1
        if (totalPage == 1) {
            rightSizeHtml = rightSizeHtml + '<li ><a onclick="refreshdata(' + number + ', ' + pageSize + ')">' + currentPage + '</a></li>'

        } else if (totalPage > 1 && totalPage < 5) {
            if (fristFlag == true) {
                rightSizeHtml = rightSizeHtml + '<li class="disabled"><a >&laquo;</a></li>';
            } else {
                rightSizeHtml = rightSizeHtml + '<li  ><a onclick="refreshdata(0,' + pageSize + ')">&laquo;</a></li>';
            }
            for (var i = 0; i < totalPage; i++) {
                if (i == currentPage-1) {
                    rightSizeHtml = rightSizeHtml + '<li class="active" ><a onclick="refreshdata(' + i + ',' + pageSize + ')">' + (i+1) + '</a></li>';
                } else {
                    rightSizeHtml = rightSizeHtml + '<li ><a onclick="refreshdata(' + i + ',' + pageSize + ')">' + (i+1) + '</a></li>';
                }
            }
            //最后一页
            if (lastFlag == true) {
                rightSizeHtml = rightSizeHtml + '<li class="disabled"><a >&raquo;</a></li>';
            } else {
                rightSizeHtml = rightSizeHtml + '<li><a onclick="refreshdata(' + (totalPage - 1) + ',' + pageSize + ')">&raquo;</a></li>';
            }
        } else if (totalPage > 5) {
            // 到第一页
            if (fristFlag == true) {
                rightSizeHtml = rightSizeHtml + '<li class="disabled"><a >&laquo;</a></li>';
            } else {
                rightSizeHtml = rightSizeHtml + '<li  ><a onclick="refreshdata(0,' + pageSize + ')">&laquo;</a></li>';
            }
            //判断current页数：
            if (currentPage < 3) {
                for (var i = 1; i < 6; i++) {
                    var pagenumx = i - 1;
                    if (i == currentPage) {
                        rightSizeHtml = rightSizeHtml + '<li class="active" ><a onclick="refreshdata(' + pagenumx + ',' + pageSize + ')">' + i + '</a></li>';
                    } else {
                        rightSizeHtml = rightSizeHtml + '<li ><a onclick="refreshdata(' + pagenumx + ',' + pageSize + ')">' + i + '</a></li>';
                    }
                }
            } else if (currentPage > totalPage - 2) {
                for (var i = 0; i < 5; i++) {
                    var pagenumx = totalPage + i - 5;
                    if (pagenumx == currentPage-1) {
                        rightSizeHtml = rightSizeHtml + '<li class="active" ><a onclick="refreshdata(' + pagenumx + ',' + pageSize + ')">' + (pagenumx+1) + '</a></li>';
                    } else {
                        rightSizeHtml = rightSizeHtml + '<li ><a onclick="refreshdata(' + pagenumx + ',' + pageSize + ')">' + (pagenumx+1) + '</a></li>';
                    }
                }
            } else {
                rightSizeHtml = rightSizeHtml + '<li ><a onclick="refreshdata(' + (number - 2) + ',' + pageSize + ')">' + (currentPage - 2) + '</a></li>';
                rightSizeHtml = rightSizeHtml + '<li ><a onclick="refreshdata(' + (number - 1) + ',' + pageSize + ')">' + (currentPage - 1) + '</a></li>';
                rightSizeHtml = rightSizeHtml + '<li class="active" ><a onclick="refreshdata(' + number + ',' + pageSize + ')">' + currentPage + '</a></li>';
                rightSizeHtml = rightSizeHtml + '<li ><a onclick="refreshdata(' + (number + 1) + ',' + pageSize + ')">' + (currentPage + 1) + '</a></li>';
                rightSizeHtml = rightSizeHtml + '<li ><a onclick="refreshdata(' + (number + 2) + ',' + pageSize + ')">' + (currentPage + 2) + '</a></li>';
            }
            //最后一页
            if (lastFlag == true) {
                rightSizeHtml = rightSizeHtml + '<li class="disabled"><a >&raquo;</a></li>';
            } else {
                rightSizeHtml = rightSizeHtml + '<li><a onclick="refreshdata(' + (totalPage - 1) + ',' + pageSize + ')">&raquo;</a></li>';
            }

        }
        rightSizeHtml = rightSizeHtml + '</ul>';
        $("#PageNumber").html(rightSizeHtml);
        //1:判断总页数大于1小于5
        //1:判断总页数大于5


    }

</script>
</body>
</html>