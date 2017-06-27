<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>广告管理页面</title>
    <%@include file="../common/common.jsp" %>
</head>
<body>
<table id="banner_datagrid"></table>
<%--页面按钮--%>
<div id="banner_datagrid_btn">
        <%--引用自定义标签的方法--%>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
</div>
<%--对话框--%>
<div id="banner_dialog">
    <form id="banner_dialog_form" method="post">
        <input type="hidden" name="id"/>
        <input type="hidden" name="imagePath" id="imagePath" />

        <table style="margin-top: 20px" align="center">
            <tr>
                <td>广告类型:</td>
                <td><input type="text" name="type"/></td>
            </tr>
            <tr>
                <td>广告链接:</td>
                <td><input type="text" name="adUrl"/></td>
            </tr>
        </table>
    </form>
    <div align="center">
        <form id="upload_form" enctype="multipart/form-data" method="post">
            <input type="file" name="file" value="选择上传文件"/>
        </form>
        <a class="easyui-linkbutton" iconCls="icon-add" data-cmd="upload">上传</a>
        <img src="" id="loanImage">
    </div>
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
        var bannerDataGrid, bannerDialog, bannerDialogForm, bannerSearchForm, inputNameId,uploadForm,imagePath;
        bannerDataGrid = $("#banner_datagrid");
        bannerDialog = $("#banner_dialog");
        bannerDialogForm = $("#banner_dialog_form");
        bannerSearchForm = $("#banner_SearchForm");
        uploadForm = $("#upload_form");
        imagePath = $("#imagePath");
        inputNameId = $("input[name=id]");
        bannerDataGrid.datagrid({
            url: "/supervisor/banner/bannerList.do",
            fit: true,
            pagination: true,
            rownumbers: true,
            singleSelect: true,
            fitColumns: true,
            columns: [[
                {title: "编号", field: "id", width: 1, align: "center"},
                {title: "图片路径", field: "imagePath", width: 1, align: "center"},
                {title: "类型", field: "type", width: 1, align: "center"},
                {title: "广告链接", field: "adUrl", width: 1, align: "center"}
            ]],
            toolbar: "#banner_datagrid_btn",
            pageList: [ 10, 20],
            onClickRow: function (rowIndex, rowData) {
                //点击到离职人员数据让按钮变灰
            }
        });
        //对话框
        bannerDialog.dialog({
            width: 350,
            height: 350,
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
            //上传
            upload: function () {
                var path = $("imagePath").val();

                if(path == "") {
                    alert("请选择上传的图片");
                    return;
                }
                uploadForm.form('submit', {
                    url: '/supervisor/banner/bannerUpload.do',
                    success: function (data) {
                        imagePath.val(data);
                        $("#loanImage").src(data);
                    }
                })
            },
            //编辑
            edit: function () {
                //获取选择的数据
                var rowData = bannerDataGrid.datagrid("getSelected");
                if (rowData) {
                    //打开对话框
                    bannerDialog.dialog("open");
                    bannerDialog.dialog("setTitle", "编辑");
                    //清空表单数据
                    bannerDialogForm.form("clear");
                    //部门数据回显
                    /*if (rowData.dept) {
                        rowData["dept.id"] = rowData.dept.id;
                    }*/
                    //回显数据
                    bannerDialogForm.form("load", rowData);
                    //角色数据回显,需要发送请求获取
                    /*var html = $.ajax({
                        url:"/role_queryRoleByEid?eid="+rowData.id,//请求
                        async:false//不是异步请求
                    }).responseText;//获取响应的文本数据
                    html = $.parseJSON(html);//文本转换JSON格式
                    $("#banner_roleCombo").combobox("setValues", html);*/

                } else {
                    $.messager.alert("温馨提示", "选择需要的编辑的数据", "warning");
                }
            },
            //离职
            remove: function () {
                //是否选择数据
                var rowData = bannerDataGrid.datagrid("getSelected");
                if (rowData) {
                    //询问是否需要删除,防止手贱
                    $.messager.confirm("温馨提示", "你确定要删除吗?", function (yes) {
                        if (yes) {
                            //发送请求删除数据
                            $.get("/bannerDelete?id=" + rowData.id, function (data) {
                                if (data.success) {
                                    //响应数据回显
                                    $.messager.alert("温馨提示", data.msg, "info", function () {
                                        //刷新
                                        bannerDataGrid.datagrid("load");
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
                bannerDataGrid.datagrid("load");
            },
            //确定
            save: function () {
                //获取id值,判断是新增还是编辑
                var id = inputNameId.val();
                //确定发送请求的URL
                var url;
                if (id) {
                    url = "/supervisor/banner/bannerUpdate.do";
                } else {
                    url = "/supervisor/banner/bannerSave.do";
                }
                //提交表单前
                bannerDialogForm.form("submit", {
                    url: url,
                    success: function (data) {
                        //JOSN字符串转对象
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("温馨提示", data.message, "info", function () {
                                //关闭窗口
                                bannerDialog.dialog("close");
                                //刷新
                                bannerDataGrid.datagrid("load");
                            })
                        } else {
                            $.messager.alert("温馨提示", data.message, "warning");
                        }
                    }
                });
            },
            //取消
            cancel: function () {
                bannerDialog.dialog("close");
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
                bannerDataGrid.datagrid("load", param);
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
</script>

</html>
