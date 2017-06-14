$(function () {
    //代码重构,统一管理变量和方法
    var permissionDatagrid, permissionDatagridBtnA;
    permissionDatagrid = $("#permission_datagrid");
    permissionDatagridBtnA = $("#permission_datagrid_btn a");
    permissionDatagrid.datagrid({
        url: "/permission_list",
        fit: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {title: "权限名称", field: "name", width: 1, align: "center"},
            {title: "权限表达式", field: "resource", width: 1, align: "center"}
        ]],
        toolbar: "#permission_datagrid_btn",
        pageList: [1, 10, 20, 30]
    });
    //统一管理方法,放在一个对象之内
    var cmdObj = {
        //加载权限
        reload: function () {
            //发送请求加载数据
            $.get("/permission_reload", function (data) {
                if (data.success) {
                    //响应数据回显
                    $.messager.alert("温馨提示", data.msg, "info", function () {
                        //刷新
                        permissionDatagrid.datagrid("load");
                    });
                } else {
                    $.messager.alert("温馨提示", data.msg, "warning");
                }
            })
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





