$(function () {

    var diskMenu = $("#diskMenu");
    var message = $("#message");
    var diskMenuDialog = $("#diskMenu_dialog");
    var diskMenuForm = $("#diskMenu_form");
    var diskMenuDataGrid = $("#diskMenu_dataGrid");
    var showWindow = $("#show_window");
    var forwardDialog = $("#forward_dialog");
    var uploadForm = $("#upload_form");
    var forwardForm = $("#forward_form");
    /*diskMenu.tree({
     url: '/diskmenu_list',
     animate: true,
     lines: true,
     border:true,
     onClick: function (node) {
     message.html("当前位置 >  " + node.text);
     //加载数据
     diskMenuDataGrid.datagrid("load", {
     menuId: node.id,


     })
     }
     });*/

    diskMenuDialog.dialog({
        title: '编辑框',
        height: 120,
        width: 250,
        closed: true,
        buttons: "#diskMenu_tools"
    });
    forwardDialog.dialog({
        title: '编辑框',
        closed: true,
        buttons: "#diskMenu_tools2"
    });

    diskMenuDataGrid.datagrid({
        url: "/diskinfo_list",
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        pagination: true,
        toolbar: "#diskMenu_dataGridTools",
        columns: [[
            {field: 'id', title: '文件', width: 1},
            {field: 'name', title: '名称', width: 3},
            {field: 'info', title: '文件简介', width: 10}
        ]]
    });
    showWindow.window({
        width: 500,
        height: 200,
        padding: 10,
        collapsible: true,
        closed: true
    });
    var diskmenuCRUD = {
        add: function () {
            var rowData = diskMenu.tree("getSelected");
            if (rowData) {
                // console.debug(rowData);
                message.html(">  " + rowData.text);
                rowData["parent.id"] = rowData.id;
                rowData.text = "";
                diskMenuForm.form("load", rowData);
                diskMenuDialog.dialog("open");

            } else {
                $.messager.alert("温馨提示", "你还未选中根节点");
            }

        },
        save: function () {
            diskMenuForm.form('submit', {
                url: "/diskmenu_save",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.confirm('温馨提示', data.msg, function (yes) {
                            if (yes) {
                                diskMenuDialog.dialog("close");
                                diskMenu.tree({
                                    url: '/diskmenu_list',
                                });
                            }
                        });

                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }

            })
        },
        cancel: function () {
            diskMenuDialog.dialog("close");
        },
        upload: function () {
            uploadForm.form('submit', {
                url: '/diskinfo_upload',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function (yes) {
                            //刷新数据
                            diskMenuDataGrid.datagrid("load");
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            })
        },
        download: function () {
            var rowData = diskMenuDataGrid.datagrid("getSelected");
            if (rowData) {
                uploadForm.form("submit", {
                    url: '/diskinfo_download',
                    onSubmit: function (param) {
                        param['id'] = rowData.id;
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请选中你要下载的资源!");
            }


        },
        show: function () {
            var rowData = diskMenuDataGrid.datagrid("getSelected");
            if (rowData) {

                $("#show_form").form("clear");

                var data = rowData.id;
                var html = $.ajax({
                    url: "/diskmenu_getvalue?id=" + data,
                    type: 'POST',
                    async: false,
                }).responseText;
                $("#showTextarea").html(decodeURI(html));
                showWindow.window("setTitle", "预览文件");
                showWindow.window("open");
            } else {
                $.messager.alert("温馨提示", "你要先选中你要的资源才行!");
            }


        },
        openSource: function () {
            var rowData = diskMenuDataGrid.datagrid("getSelected");

            if (rowData) {
                $.post("/diskmenu_opensource?id=" + rowData.id, function (data) {
                    if (data.success) {
                        diskMenuDataGrid.datagrid("load");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                })
            } else {
                $.messager.alert("温馨提示", "请选择你要共享的文件!");
            }

        },
        remove: function () {
            var rowData = diskMenuDataGrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "你确定移除此数据吗?", function (yes) {

                    if (yes) {
                        $.post("/diskmenu_remove?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg);
                                diskMenuDataGrid.datagrid("load");
                            } else {
                                $.messager.alert("温馨提示", data.msg);
                            }
                        })
                    }

                })

            } else {
                $.messager.alert("温馨提示", "你还未选中资源!");
            }
        },
        forward: function () {
            var rowData = diskMenuDataGrid.datagrid("getSelected");
            if (rowData) {
                forwardDialog.dialog("open");
                forwardForm.form("load", {
                    menuId: rowData.id,
                })
            } else {
                $.messager.alert("温馨提示", "你还未选中资源!");
            }
        },
        save2: function () {
            forwardForm.form("submit", {
                url: '/diskmenu_forward',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        forwardDialog.dialog("close");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            })
        },
        cancel2: function () {
            forwardDialog.dialog("close");
        }

    }

    $("a[data-crud]").on("click", function () {
        diskmenuCRUD[$(this).data("crud")]();

    })
});
/*
function imgFrommater(value, row, index) {
    return "<img src='/img/txt.png' style='width: 30px;height: 30px'/>"
}*/
