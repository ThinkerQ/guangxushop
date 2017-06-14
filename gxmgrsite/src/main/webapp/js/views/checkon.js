$(function () {
    //缓存需要对象的变量,避免每次都对jsp进行扫描
    var checkonDatagrid, checkonDatagridBtn, checkonDialogAdd, checkonDialogEdit, checkonFormAdd, checkonFormEdit, checkonSearchForm, checkonRoleCombo;
    checkonDatagrid = $("#checkon_datagrid");
    checkonDatagridBtn = $("#checkon_datagrid_bt a");

    //补签的新增的对话框
    checkonDialogAdd = $("#checkon_dialog_add");
    //补签的编辑的对话框
    checkonDialogEdit = $("#checkon_dialog_edit");

    //补签的新增的对话框的表单
    checkonFormAdd = $("#checkon_form_add");
    //补签的编辑的对话框的表单
    checkonFormEdit = $("#checkon_form_edit");


    checkonSearchForm = $("#checkon_searchForm");
    checkonRoleCombo = $("#checkon_roleCombo");

    var flag;
    //一加载页面就去查询该当前的登录的用户是否是人事部的人员
    $.ajax({
        type: "POST",
        url: "/checkon_checkHR",
        //使用同步的请求先锁住浏览器,才能给全局变量赋值
        async: false,
        success: function (data) {
            if (data) {
                flag = false;
            } else {
                flag = true;
            }
        },
        dataType: "json"
    });

    checkonDatagrid.datagrid({
        //从远程请求数据
        url: "/checkon_list",
        fit: true,
        fitColumns: true,
        singleSelect: true,
        //顶部按钮
        toolbar: "#checkon_datagrid_bt",
        pagination: true,
        columns: [[
            {title: "员工ID", field: "id", width: 1, align: "center",formatter:userIdFormat},
            {title: "姓名", field: "user", width: 1, align: "center", formatter: usernameFormat},
            {title: "签到IP", field: "userIp", width: 1, align: "center"},
            {title: "签到时间", field: "signInTime", width: 1, align: "center", formatter: morningtimeFormat},
            {title: "签退时间", field: "signOutTime", width: 1, align: "center", formatter: eveingtimeFormat},
            {title: "状态", field: "status", width: 1, align: "center", formatter: statusFormat},
            {title: "补签人", field: "resignUser", width: 1, align: "center", hidden: flag, formatter: usernameFormat},
            {title: "补签时间", field: "resignTime", width: 1, align: "center", hidden: flag}
        ]]
    });

    //补签的新增的对话框
    checkonDialogAdd.dialog({
        width: 300,
        height: 300,
        //对话框的底部按钮
        buttons: "#checkon_dialog_bt_add",
        closed: true
    });

    //补签的编辑的对话框
    checkonDialogEdit.dialog({
        width: 300,
        height: 300,
        //对话框的底部按钮
        buttons: "#checkon_dialog_bt_edit",
        closed: true
    });

    //将部分方法及方法名作为json对象的key和value
    //后面当点击指定按钮时触发a标签的按钮绑定的事件,来调用对象的方法属性
    var cmdObj = {
        //签到
        signin: function () {
            $.ajax({
                type: "POST",
                url: "/checkon_signin",
                success: function (data) {
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            //刷新表格
                            checkonDatagrid.datagrid("load");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                }

                ,
                dataType: "json"
            })
            ;
        },
        //签退
        signout: function () {
            $.ajax({
                type: "POST",
                url: "/checkon_signout",
                success: function (data) {
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            //刷新表格
                            checkonDatagrid.datagrid("load");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "warning");
                    }
                },
                dataType: "json"
            });
        },
        //补签
        resign: function () {
            var rowData = checkonDatagrid.datagrid("getSelected");
            //编辑操作
            if (rowData) {
                checkonFormEdit.form("clear");
                //回显数据
                checkonFormEdit.form("load", rowData);
                checkonDialogEdit.dialog("open");
                checkonDialogEdit.dialog("setTitle", "补签考勤窗口");
            } else {
                //新增操作
                checkonDialogAdd.dialog("open");
                checkonDialogAdd.dialog("setTitle", "新增考勤窗口");
                checkonFormAdd.form("clear");
            }
        },
        //高级查询
        searchContent: function () {
            var paramArr = checkonSearchForm.serializeArray();
            //用于储存的json对象
            var param = {};
            //处理数据格式
            for (var i = 0; i < paramArr.length; i++) {
                param[paramArr[i].name] = paramArr[i].value;
            }
            checkonDatagrid.datagrid("load", param);
        },
        //保存
        save: function () {
            //获取表单中的id
            var id = $(":input[name=id]").val();
            var url;
            if (id) {
                url = "/checkon_update";
                //使用easyUI的异步提交,后台只需要保存数据,只是把保存结果返回即可
                //页面处理交给页面刷新
                checkonFormEdit.form('submit', {
                    url: url,
                    success: function (data) {
                        //返回的是字符串不是对象
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("操作提示", data.msg, "info", function () {
                                checkonDialogEdit.dialog("close");
                                checkonDatagrid.datagrid("load");
                            });
                        } else {
                            $.messager.alert("操作提示", data.msg, "error");
                        }
                    }
                });
            } else {
                url = "/checkon_save";
                //使用easyUI的异步提交,后台只需要保存数据,只是把保存结果返回即可
                //页面处理交给页面刷新
                checkonFormAdd.form('submit', {
                    url: url,
                    success: function (data) {
                        //返回的是字符串不是对象
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("操作提示", data.msg, "info", function () {
                                checkonDialogAdd.dialog("close");
                                checkonDatagrid.datagrid("load");
                            });
                        } else {
                            $.messager.alert("操作提示", data.msg, "error");
                        }
                    }
                });
            }

        },
        //取消
        cancel: function () {
            checkonDialogAdd.dialog("close");
            checkonDialogEdit.dialog("close");
        },
        //刷新
        refresh: function () {
            checkonDatagrid.datagrid("reload");
        }
    };
    //此方式获取带有data-cmd属性的a标签就是按钮组件
    //将方法作为对象的属性,会使disabe失效,这里不要用onclick
    //只能使用统一事件绑定的方式
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        //判断避免cmd为空
        if (cmd) {
            //获取cmdObj的属性并调用
            cmdObj[cmd]();
        }
    });
});

//签到人和补签人显示名字的方法
function usernameFormat(value, record, index) {
    if (value) {
        return value.username;
    }

}


//日期显示的格式化的方法
function morningtimeFormat(value, record, index) {
    if (value) {
        var date = new Date(Date.parse(value.replace(/-/g, "/")));
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();

        var morningStr = year + "-" + month + "-" + day + " 08:30:00";
        var morningDate = new Date(Date.parse(morningStr.replace(/-/g, "/")));

        if (date.getTime() < morningDate.getTime()) {
            return "<font color='green'>" + value + "</font>";
        } else {
            return "<font color='red'>" + value + "</font>";
        }

    }
}
//日期显示的格式化的方法
function eveingtimeFormat(value, record, index) {
    if (value) {
        var date = new Date(Date.parse(value.replace(/-/g, "/")));
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();

        var eveingStr = year + "-" + month + "-" + day + " 17:30:00";
        var eveingDate = new Date(Date.parse(eveingStr.replace(/-/g, "/")));

        if (date.getTime() > eveingDate.getTime()) {
            return "<font color='green'>" + value + "</font>";
        } else {
            return "<font color='red'>" + value + "</font>";
        }

    }
}


//考勤状态的显示的方法
function statusFormat(value, rowData, rowIndex) {
    if (value == 0) {
        return "<font color='green'>正常</font>";
    } else if (value == 1) {
        return "<font color='red'>迟到</font>";
    } else if (value == 2) {
        return "<font color='blue'>早退</font>";
    } else if (value == 3) {
        return "<font color='#8b0000'>迟到早退</font>";
    }
}

//姓名的下拉框显示部门名称的方法
function deptFormatter(value, record, index) {
    if (value) {
        return value.name;
    }
    return null;
}

//显示状态的方法
function stateFormatter(value, record, index) {
    if (value) {
        return "<font color='green'>在职</font>";
    } else {
        return "<font color='red'>已离职</font>";
    }
}


//员工显示id的方法
function userIdFormat(value, record, index) {
    if (record.user) {
        return record.user.id;
    }
}
















