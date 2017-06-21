<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.520it.com/java/crm" prefix="myFn" %>
<html>
<head>
    <title>广告管理页面</title>
    <%@include file="../common/common.jsp" %>
</head>
<body>
<table id="banner_datagrid"></table>
<%--页面按钮--%>
<div id="banner_datagrid_btn">
    <div>
        <%--引用自定义标签的方法--%>
        <c:if test="${myFn:checkPermission('com._520it.crm.web.controller.EmployeeController:save')}">
        </c:if>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <c:if test="${myFn:checkPermission('com._520it.crm.web.controller.EmployeeController:update')}">
        </c:if>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">离职</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
    <div>
        <form id="banner_SearchForm">
            关键字:<input type="text" name="keyword"/>
            日期:<input class="easyui-datebox" name="beginDate">
            -<input class="easyui-datebox" name="endDate">
            状态:<select name="state">
            <option value="">全部</option>
            <option value="1">在职</option>
            <option value="0">离职</option>
        </select>
            <a class="easyui-linkbutton" iconCls="icon-search" data-cmd="searchContent">查询</a>
        </form>
    </div>
</div>
<%--对话框--%>
<div id="banner_dialog">
    <form id="banner_dialog_form">
        <input type="hidden" name="id"/>
        <table style="margin-top: 20px" align="center">
            <tr>
                <td>账号</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td><input type="text" name="realName"/></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input type="text" name="tel"/></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>部门</td>
                <td><input class="easyui-combobox" name="dept.id"
                           data-options="url:'/department_list',valueField:'id',textField:'name'"/></td>
            </tr>
            <tr>
                <td>角色</td>
                <td><input class="easyui-combobox" id="banner_roleCombo"
                           data-options="url:'/role_queryByEmp',valueField:'id',textField:'name',multiple:true"/></td>
            </tr>
        </table>
    </form>
</div>
<%--对话按钮--%>
<div id="banner_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="save">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
<script type="text/javascript" >
    $(function () {
        //代码重构,统一管理变量和方法
        var bannerDatagrid, bannerDatagridBtnA, bannerDialog, bannerDialogForm, bannerSearchForm, inputNameId;
        bannerDatagrid = $("#banner_datagrid");
        bannerDatagridBtnA = $("#banner_datagrid_btn a");
        bannerDialog = $("#banner_dialog");
        bannerDialogForm = $("#banner_dialog_form");
        bannerSearchForm = $("#banner_SearchForm");
        inputNameId = $("input[name=id]");
        bannerDatagrid.datagrid({
            url: "/supervisor/banner/bannerList.do",
            fit: true,
            pagination: true,
            rownumbers: true,
            singleSelect: true,
            fitColumns: true,
            columns: [[
                {title: "账号", field: "id", width: 1, align: "center"},
                {title: "图片路径", field: "imagePath", width: 1, align: "center"},
                {title: "类型", field: "type", width: 1,formatter: deptFormatter, align: "center"},
                {title: "广告链接", field: "adUrl", width: 1, align: "center"}
            ]],
            toolbar: "#banner_datagrid_btn",
            pageList: [ 10, 20],
            onClickRow: function (rowIndex, rowData) {
                //点击到离职人员数据让按钮变灰
                if (rowData.state) {
                    bannerDatagridBtnA.eq(1).linkbutton('enable');
                    bannerDatagridBtnA.eq(2).linkbutton('enable');
                } else {
                    bannerDatagridBtnA.eq(1).linkbutton('disable');
                    bannerDatagridBtnA.eq(2).linkbutton('disable');
                }
            }
        });
        //对话框
        bannerDialog.dialog({
            width: 300,
            height: 330,
            buttons: "#banner_dialog_btn",
            closed: true
        });
        //统一管理方法,放在一个对象之内
        var cmdObj = {
            //新增
            add: function () {
                bannerDialog.dialog("open");
                bannerDialog.dialog("setTitle", "新增");
                bannerDialogForm.form("clear");
            },
            //编辑
            edit: function () {
                //获取选择的数据
                var rowData = bannerDatagrid.datagrid("getSelected");
                if (rowData) {
                    //打开对话框
                    bannerDialog.dialog("open");
                    bannerDialog.dialog("setTitle", "编辑");
                    //清空表单数据
                    bannerDialogForm.form("clear");
                    //部门数据回显
                    if (rowData.dept) {
                        rowData["dept.id"] = rowData.dept.id;
                    }
                    //回显数据
                    bannerDialogForm.form("load", rowData);
                    //角色数据回显,需要发送请求获取
                    var html = $.ajax({
                        url:"/role_queryRoleByEid?eid="+rowData.id,//请求
                        async:false//不是异步请求
                    }).responseText;//获取响应的文本数据
                    html = $.parseJSON(html);//文本转换JSON格式
                    $("#banner_roleCombo").combobox("setValues", html);

                } else {
                    $.messager.alert("温馨提示", "选择需要的编辑的数据", "warning");
                }
            },
            //离职
            remove: function () {
                //是否选择数据
                var rowData = bannerDatagrid.datagrid("getSelected");
                if (rowData) {
                    //询问是否需要删除,防止手贱
                    $.messager.confirm("温馨提示", "你确定要离职该员工吗?", function (yes) {
                        if (yes) {
                            //发送请求删除数据
                            $.get("/banner_delete?id=" + rowData.id, function (data) {
                                if (data.success) {
                                    //响应数据回显
                                    $.messager.alert("温馨提示", data.msg, "info", function () {
                                        //刷新
                                        bannerDatagrid.datagrid("load");
                                    });
                                } else {
                                    $.messager.alert("温馨提示", data.msg, "warning");
                                }
                            })
                        }
                    });
                } else {
                    //没有选择的情况
                    $.messager.alert("温馨提示", "选择需要编辑的数据", "warning");
                }
            },
            //刷新
            reload: function () {
                bannerDatagrid.datagrid("load");
            },
            //确定
            save: function () {
                //获取id值,判断是新增还是编辑
                var id = inputNameId.val();
                //确定发送请求的URL
                var url;
                if (id) {
                    url = "/banner_update";
                } else {
                    url = "/banner_save";
                }
                //提交表单前
                bannerDialogForm.form("submit", {
                    url: url,
                    onSubmit:function (param) {
                        var ids = $("#banner_roleCombo").combobox("getValues");
                        for(var i=0;i<ids.length;i++){
                            param["roles["+i+"].id"] = ids[i];
                        }
                    },
                    success: function (data) {
                        //JOSN字符串转对象
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg, "info", function () {
                                //关闭窗口
                                bannerDialog.dialog("close");
                                //刷新
                                bannerDatagrid.datagrid("load");
                            })
                        } else {
                            $.messager.alert("温馨提示", data.msg, "warning");
                        }
                    }
                });
            },
            //取消
            cancel: function () {

            },
            //查询
            searchContent: function () {
                //在表单中有serializeArray方法获取所有的参数
                var paramArr = bannerSearchForm.serializeArray();
                //定义对象,把所有表单的参数设置到该对象中去
                var param = {};
                for (var i = 0; i < paramArr.length; i++) {
                    param[paramArr[i].name] = paramArr[i].value;
                }
                //传递参数,加载数据
                bannerDatagrid.datagrid("load", param);
            }
        };
        //给按钮添加事件
        $("a").on("click", function () {
            var cmd = $(this).data("cmd");
            if (cmd) {
                cmdObj[cmd]();
            }
        });
    });
    function deptFormatter(value, record, index) {
        if (value) {
            return value.name;
        } else {
            return value;
        }
    }
    function stateFormatter(value, record, index) {
        if (value) {
            return "在职";
        } else {
            return "离职";
        }
    }
</script>

</html>
