<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>品牌管理页面</title>
    <%@include file="../common/common.jsp" %>
    <script type="text/javascript" src="/../js/views/brand.js" charset ="utf-8"></script>
</head>
<body>
<table id="brand_datagrid"></table>
<%--页面按钮--%>
<div id="brand_datagrid_btn">
        <%--引用自定义标签的方法--%>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
</div>
<%--对话框--%>
<div id="brand_dialog">
    <form id="brand_dialog_form">
        <input type="hidden" name="id"/>

        <table style="margin-top: 20px" align="center">
            <tr>
                <td>品牌名称:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            </br>
            <tr>
                <td>品牌编码:</td>
                <td><input type="text" name="sn"/></td>
            </tr>
        </table>
    </form>
</div>
<%--对话按钮--%>
<div id="brand_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="save">确定</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
