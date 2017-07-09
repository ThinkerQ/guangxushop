<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <%@include file="../common/common.jsp" %>
    <script type="text/javascript" src="/js/views/category2.js"></script>
    
</head>
<body>
<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
    <div data-options="region:'west', split:true" title="一级商品类目" style="width:500px;">
        <table id="categorys_datagrid"></table>
    </div>
    <div data-options="region:'center' " title="二级商品类目" style="padding:5px;width:100px;">
        <table id="categorysItem_datagrid"></table>
    </div>
</div>

<div id="categorysItem_dialog">
    <form id="categorysItem_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 15px ">
            <tr>
                <td>父级类目</td>
                <td><font style="color:pink " size="5px"><span id="parentName"></span></font></td>
            </tr>
            <tr>
                <td>新增类目名称</td>
                <td><input type="text" name="name"></td>
            </tr>
        </table>
    </form>
</div>
<div id="tt">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">禁用</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload()">刷新</a>
    </div>
</div>
<div id="bb">
    <a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存2</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancle()">取消</a>
</div>
</body>
</html>