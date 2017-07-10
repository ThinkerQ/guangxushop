$(function () {
        //代码重构,统一管理变量和方法
        var productDatagrid, productDatagridBtnA, productDialog, productDialogForm, productSearchForm, inputNameId,uploadForm,imagePath;
        productDatagrid = $("#product_datagrid");
        productDatagridBtnA = $("#product_datagrid_btn a");
        productDialog = $("#product_dialog");
        productDialogForm = $("#product_dialog_form");
        productSearchForm = $("#product_SearchForm");
        uploadForm = $("#upload_form");
        imagePath = $("#imagePath");
        inputNameId = $("input[name=id]");
        
        //数据表单
        productDatagrid.datagrid({
            url: "/supervisor/product/productList.do",
            fit: true,
            pagination: true,
            rownumbers: true,
            singleSelect: true,
            fitColumns: true,
            loadMsg:'数据加载中......',
            columns: [[
                {title: "编号", field: "id", width: 1, align: "center"},
                {title: "分类", field: "category", width: 1, align: "center",formatter:categoryFormatter},
                {title: "品牌", field: "brand", width: 1, align: "center",formatter:categoryFormatter},
                {title: "商品主名称", field: "firstName", width: 1, align: "center"},
                {title: "商品副名称", field: "secondName", width: 1, align: "center"},
                {title: "小图", field: "littlePath", width: 1, align: "center"},
                {title: "大图", field: "bigPath", width: 1, align: "center"},
                {title: "状态", field: "status", width: 1, align: "center",formatter:productFormatter},
                {title: "时间", field: "time", width: 1, align: "center"}
            ]],
            toolbar: "#product_datagrid_btn",
            pageList: [ 10, 20]
        });
        
        //对话框
        productDialog.dialog({
            width: 400,
            height: 350,
            buttons: "#product_dialog_btn",
            closed: true
        });
        //统一管理方法,放在一个对象之内
        var cmdObj = {
            //新增
            add: function () {
                productDialog.dialog("open");
                productDialog.dialog("setTitle", "新增");
                productDialogForm.form("clear");
            },
            
            //编辑
            edit: function () {
                //获取选择的数据
                var rowData = productDatagrid.datagrid("getSelected");
                if (rowData) {
                    //打开对话框
                    productDialog.dialog("open");
                    productDialog.dialog("setTitle", "编辑");
                    //清空表单数据
                    productDialogForm.form("clear");
                    //部门数据回显
                    if (rowData.dept) {
                        rowData["dept.id"] = rowData.dept.id;
                    }
                    //回显数据
                    productDialogForm.form("load", rowData);
                    //角色数据回显,需要发送请求获取
                    var html = $.ajax({
                        url:"/supervisor/product/productUpdate.do?id="+rowData.id,//请求
                        async:false//不是异步请求
                    }).responseText;//获取响应的文本数据
                    html = $.parseJSON(html);//文本转换JSON格式
                    $("#product_roleCombo").combobox("setValues", html);

                } else {
                    $.messager.alert("温馨提示", "选择需要的编辑的数据", "warning");
                }
            },
            //删除
            remove: function () {
                //是否选择数据
                var rowData = productDatagrid.datagrid("getSelected");
                if (rowData) {
                    //询问是否需要删除,防止手贱
                    $.messager.confirm("温馨提示", "你确定要离职该员工吗?", function (yes) {
                        if (yes) {
                            //发送请求删除数据
                            $.get("/supervisor/product/productDelete.do?id=" + rowData.id, function (data) {
                                if (data.success) {
                                    //响应数据回显
                                    $.messager.alert("温馨提示", data.message, "info", function () {
                                        //刷新
                                        productDatagrid.datagrid("load");
                                    });
                                } else {
                                    $.messager.alert("温馨提示", data.message, "warning");
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
                productDatagrid.datagrid("load");
            },
            //确定
            save: function () {
                //获取id值,判断是新增还是编辑
                var id = inputNameId.val();
                //确定发送请求的URL
                var url;
                if (id) {
                    url = "/supervisor/product/productUpdate.do";
                } else {
                    url = "/supervisor/product/productSave.do";
                }
                //提交表单前
                productDialogForm.form("submit", {
                    url: url,
                    success: function (data) {
                        //JOSN字符串转对象
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("温馨提示", data.message, "info", function () {
                                //关闭窗口
                                productDialog.dialog("close");
                                //刷新
                                productDatagrid.datagrid("load");
                            })
                        } else {
                            $.messager.alert("温馨提示", data.message, "warning");
                        }
                    }
                });
            },
            //取消
            cancel: function () {
            	//关闭窗口
                productDialog.dialog("close");
            },
            //查询
            searchContent: function () {
                //在表单中有serializeArray方法获取所有的参数
                var paramArr = productSearchForm.serializeArray();
                //定义对象,把所有表单的参数设置到该对象中去
                var param = {};
                for (var i = 0; i < paramArr.length; i++) {
                    param[paramArr[i].name] = paramArr[i].value;
                }
                //传递参数,加载数据
                productDatagrid.datagrid("load", param);
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

function categoryFormatter(value, record, index) {
    if (value) {
        return value.name;
    } else {
        return value;
    }
}

function brandFormatter(value, record, index) {
    if (value) {
        return value.name;
    } else {
        return value;
    }
}

function productFormatter(value, record, index) {
    if (value) {
        return "可用";
    } else {
        return "停用";
    }
}
