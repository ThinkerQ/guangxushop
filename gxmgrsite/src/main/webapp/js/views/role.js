$(function () {
    //代码重构,统一管理变量和方法
    var roleDatagrid, roleDatagridBtnA, roleDialog, roleDialogForm, roleSearchForm, inputNameId, selfPermission, allPermission;
    roleDatagrid = $("#role_datagrid");
    roleDatagridBtnA = $("#role_datagrid_btn a");
    roleDialog = $("#role_dialog");
    roleDialogForm = $("#role_dialog_form");
    roleSearchForm = $("#role_SearchForm");
    inputNameId = $("input[name=id]");
    selfPermission = $("#selfPermission");
    allPermission = $("#allPermission");
    roleDatagrid.datagrid({
        url: "/role_list",
        fit: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {title: "角色编号", field: "sn", width: 1, align: "center"},
            {title: "角色名称", field: "name", width: 1, align: "center"}
        ]],
        toolbar: "#role_datagrid_btn",
        pageList: [1, 10, 20, 30],
        /*onClickRow: function (rowIndex, rowData) {
            //点击到离职人员数据让按钮变灰
            if (rowData.state) {
                roleDatagridBtnA.eq(1).linkbutton('enable');
                roleDatagridBtnA.eq(2).linkbutton('enable');
            } else {
                roleDatagridBtnA.eq(1).linkbutton('disable');
                roleDatagridBtnA.eq(2).linkbutton('disable');
            }
        }*/
    });
    selfPermission.datagrid({
        title: "自身权限",
        width: 300,
        height: 300,
        fit: true,
        rownumbers: true,//序号
        fitColumns: true,
        columns: [[
            {title: "权限名称", field: "name", width: 1, align: "center"}
        ]],
        singleSelect: true,//单选
        onDblClickRow: function (rowIndex, rowData) {
            selfPermission.datagrid("deleteRow",rowIndex);
        }
    });
    allPermission.datagrid({
        width: 300,
        height: 300,
        title: "所有权限",
        url: "/permission_list",
        pagination: true,
        rownumbers: true,//序号
        fitColumns: true,
        columns: [[
            {title: "权限名称", field: "name", width: 1, align: "center"}
        ]],
        singleSelect: true,//单选
        onDblClickRow: function (rowIndex, rowData) {
            var selfRows = selfPermission.datagrid("getRows");
            var flag = false;//标记是否重复
            var index = -1;
            for (var i = 0; i < selfRows.length; i++) {
                if (selfRows[i].id == rowData.id) {
                    flag = true;//重复出现了
                    index = i;//第几条
                }
            }
            if (flag) {
                selfPermission.datagrid("selectRow", index);
            } else {
                selfPermission.datagrid("appendRow", rowData);
            }
        }
    });
    var page = allPermission.datagrid("getPager");
    page.pagination({
        showPageList: false,
        showRefresh: false,
        displayMsg: ''
    });

    //对话框
    roleDialog.dialog({
        width: 700,
        height: 450,
        buttons: "#role_dialog_btn",
        closed: true
    });
    //统一管理方法,放在一个对象之内
    var cmdObj = {
        //新增
        add: function () {
            roleDialog.dialog("open");
            roleDialog.dialog("setTitle", "新增");
            //roleDialogForm.form("clear");
            $("input[name=id], input[name=name],input[name=sn]").val("");
            selfPermission.datagrid("loadData",{rows:[]});
        },
        //编辑
        edit: function () {
            var rowData = roleDatagrid.datagrid("getSelected");
            if (rowData) {
                roleDialog.dialog("open");
                roleDialog.dialog("setTitle", "编辑");
                roleDialogForm.form("clear");
                if (rowData.dept) {
                    rowData["dept.id"] = rowData.dept.id;
                }
                //数据回显
                roleDialogForm.form("load", rowData);
                selfPermission.datagrid("options").url = "/permission_queryByRid";
                selfPermission.datagrid("load",{
                    rid:rowData.id
                })
            } else {
                $.messager.alert("温馨提示", "选择需要的编辑的数据", "warning");
            }
        },
        //离职
        remove: function () {
            //是否选择数据
            var rowData = roleDatagrid.datagrid("getSelected");
            if (rowData) {
                //询问是否需要删除,防止手贱
                $.messager.confirm("温馨提示", "你确定要离职该员工吗?", function (yes) {
                    if (yes) {
                        //发送请求删除数据
                        $.get("/role_delete?id=" + rowData.id, function (data) {
                            if (data.success) {
                                //响应数据回显
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    //刷新
                                    roleDatagrid.datagrid("load");
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
            roleDatagrid.datagrid("load");
        },
        //确定
        save: function () {
            //获取id值,判断是新增还是编辑
            var id = inputNameId.val();
            //确定发送请求的URL
            var url;
            if (id) {
                url = "/role_update";
            } else {
                url = "/role_save";
            }
            //提交表单前
            roleDialogForm.form("submit", {
                url: url,
                //额外传参
                onSubmit:function (param) {
                    //获取自身权限集合
                    var selfRows = selfPermission.datagrid("getRows");
                    for(var i=0;i<selfRows.length;i++){
                        param["permission["+i+"].id"] = selfRows[i].id;
                    }
                },
                success: function (data) {
                    //JOSN字符串转对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            //关闭窗口
                            roleDialog.dialog("close");
                            //刷新
                            roleDatagrid.datagrid("load");
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
            var paramArr = roleSearchForm.serializeArray();
            //定义对象,把所有表单的参数设置到该对象中去
            var param = {};
            for (var i = 0; i < paramArr.length; i++) {
                param[paramArr[i].name] = paramArr[i].value;
            }
            //传递参数,加载数据
            roleDatagrid.datagrid("load", param);
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





