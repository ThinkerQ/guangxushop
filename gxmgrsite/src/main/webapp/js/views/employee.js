$(function () {
    //代码重构,统一管理变量和方法
    var employeeDatagrid, employeeDatagridBtnA, employeeDialog, employeeDialogForm, employeeSearchForm, inputNameId;
    employeeDatagrid = $("#employee_datagrid");
    employeeDatagridBtnA = $("#employee_datagrid_btn a");
    employeeDialog = $("#employee_dialog");
    employeeDialogForm = $("#employee_dialog_form");
    employeeSearchForm = $("#employee_SearchForm");
    inputNameId = $("input[name=id]");
    employeeDatagrid.datagrid({
        url: "/employee_list",
        fit: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {title: "账号", field: "username", width: 1, align: "center"},
            {title: "真实姓名", field: "realName", width: 1, align: "center"},
            {title: "联系电话", field: "tel", width: 1, align: "center"},
            {title: "邮箱", field: "email", width: 1, align: "center"},
            {title: "部门", field: "dept", width: 1, formatter: deptFormatter, align: "center"},
            {title: "入职时间", field: "inputTime", width: 1},
            {title: "状态", field: "state", width: 1, formatter: stateFormatter, align: "center"}
        ]],
        toolbar: "#employee_datagrid_btn",
        pageList: [1, 10, 20, 30],
        onClickRow: function (rowIndex, rowData) {
            //点击到离职人员数据让按钮变灰
            if (rowData.state) {
                employeeDatagridBtnA.eq(1).linkbutton('enable');
                employeeDatagridBtnA.eq(2).linkbutton('enable');
            } else {
                employeeDatagridBtnA.eq(1).linkbutton('disable');
                employeeDatagridBtnA.eq(2).linkbutton('disable');
            }
        }
    });
    //对话框
    employeeDialog.dialog({
        width: 300,
        height: 330,
        buttons: "#employee_dialog_btn",
        closed: true
    });
    //统一管理方法,放在一个对象之内
    var cmdObj = {
        //新增
        add: function () {
            employeeDialog.dialog("open");
            employeeDialog.dialog("setTitle", "新增");
            employeeDialogForm.form("clear");
        },
        //编辑
        edit: function () {
            //获取选择的数据
            var rowData = employeeDatagrid.datagrid("getSelected");
            if (rowData) {
                //打开对话框
                employeeDialog.dialog("open");
                employeeDialog.dialog("setTitle", "编辑");
                //清空表单数据
                employeeDialogForm.form("clear");
                //部门数据回显
                if (rowData.dept) {
                    rowData["dept.id"] = rowData.dept.id;
                }
                //回显数据
                employeeDialogForm.form("load", rowData);
                //角色数据回显,需要发送请求获取
                var html = $.ajax({
                    url:"/role_queryRoleByEid?eid="+rowData.id,//请求
                    async:false//不是异步请求
                }).responseText;//获取响应的文本数据
                html = $.parseJSON(html);//文本转换JSON格式
                $("#employee_roleCombo").combobox("setValues", html);

            } else {
                $.messager.alert("温馨提示", "选择需要的编辑的数据", "warning");
            }
        },
        //离职
        remove: function () {
            //是否选择数据
            var rowData = employeeDatagrid.datagrid("getSelected");
            if (rowData) {
                //询问是否需要删除,防止手贱
                $.messager.confirm("温馨提示", "你确定要离职该员工吗?", function (yes) {
                    if (yes) {
                        //发送请求删除数据
                        $.get("/employee_delete?id=" + rowData.id, function (data) {
                            if (data.success) {
                                //响应数据回显
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    //刷新
                                    employeeDatagrid.datagrid("load");
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
            employeeDatagrid.datagrid("load");
        },
        //确定
        save: function () {
            //获取id值,判断是新增还是编辑
            var id = inputNameId.val();
            //确定发送请求的URL
            var url;
            if (id) {
                url = "/employee_update";
            } else {
                url = "/employee_save";
            }
            //提交表单前
            employeeDialogForm.form("submit", {
                url: url,
                onSubmit:function (param) {
                    var ids = $("#employee_roleCombo").combobox("getValues");
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
                            employeeDialog.dialog("close");
                            //刷新
                            employeeDatagrid.datagrid("load");
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
            var paramArr = employeeSearchForm.serializeArray();
            //定义对象,把所有表单的参数设置到该对象中去
            var param = {};
            for (var i = 0; i < paramArr.length; i++) {
                param[paramArr[i].name] = paramArr[i].value;
            }
            //传递参数,加载数据
            employeeDatagrid.datagrid("load", param);
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





