$(function () {
        //代码重构,统一管理变量和方法
        var brandDatagrid, brandDatagridBtnA, brandDialog, brandDialogForm, brandSearchForm, inputNameId,uploadForm,imagePath;
        brandDatagrid = $("#brand_datagrid");
        brandDatagridBtnA = $("#brand_datagrid_btn a");
        brandDialog = $("#brand_dialog");
        brandDialogForm = $("#brand_dialog_form");
        brandSearchForm = $("#brand_SearchForm");
        uploadForm = $("#upload_form");
        imagePath = $("#imagePath");
        inputNameId = $("input[name=id]");
        
        //数据表单
        brandDatagrid.datagrid({
            url: "/supervisor/brand/brandList.do",
            fit: true,
            pagination: true,
            rownumbers: true,
            singleSelect: true,
            fitColumns: true,
            loadMsg:'数据加载中......',
            columns: [[
                {title: "编号", field: "id", width: 1, align: "center"},
                {title: "品牌名称", field: "name", width: 1, align: "center"},
                {title: "品牌编码", field: "sn", width: 1, align: "center"}
            ]],
            toolbar: "#brand_datagrid_btn",
            pageList: [ 10, 20]
        });
        
        //对话框
        brandDialog.dialog({
            width: 350,
            height: 200,
            buttons: "#brand_dialog_btn",
            closed: true
        });
        //统一管理方法,放在一个对象之内
        var cmdObj = {
            //新增
            add: function () {
                brandDialog.dialog("open");
                brandDialog.dialog("setTitle", "新增");
                brandDialogForm.form("clear");
            },
            
            //编辑
            edit: function () {
                //获取选择的数据
                var rowData = brandDatagrid.datagrid("getSelected");
                if (rowData) {
                    //打开对话框
                    brandDialog.dialog("open");
                    brandDialog.dialog("setTitle", "编辑");
                    //清空表单数据
                    brandDialogForm.form("clear");
                    //部门数据回显
                    if (rowData.dept) {
                        rowData["dept.id"] = rowData.dept.id;
                    }
                    //回显数据
                    brandDialogForm.form("load", rowData);
                    //角色数据回显,需要发送请求获取
                    var html = $.ajax({
                        url:"/supervisor/brand/brandUpdate.do?id="+rowData.id,//请求
                        async:false//不是异步请求
                    }).responseText;//获取响应的文本数据
                    html = $.parseJSON(html);//文本转换JSON格式
                    $("#brand_roleCombo").combobox("setValues", html);

                } else {
                    $.messager.alert("温馨提示", "选择需要的编辑的数据", "warning");
                }
            },
            //删除
            remove: function () {
                //是否选择数据
                var rowData = brandDatagrid.datagrid("getSelected");
                if (rowData) {
                    //询问是否需要删除,防止手贱
                    $.messager.confirm("温馨提示", "你确定要离职该员工吗?", function (yes) {
                        if (yes) {
                            //发送请求删除数据
                            $.get("/supervisor/brand/brandDelete.do?id=" + rowData.id, function (data) {
                                if (data.success) {
                                    //响应数据回显
                                    $.messager.alert("温馨提示", data.message, "info", function () {
                                        //刷新
                                        brandDatagrid.datagrid("load");
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
                brandDatagrid.datagrid("load");
            },
            //确定
            save: function () {
                //获取id值,判断是新增还是编辑
                var id = inputNameId.val();
                //确定发送请求的URL
                var url;
                if (id) {
                    url = "/supervisor/brand/brandUpdate.do";
                } else {
                    url = "/supervisor/brand/brandSave.do";
                }
                //提交表单前
                brandDialogForm.form("submit", {
                    url: url,
                    success: function (data) {
                        //JOSN字符串转对象
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("温馨提示", data.message, "info", function () {
                                //关闭窗口
                                brandDialog.dialog("close");
                                //刷新
                                brandDatagrid.datagrid("load");
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
                brandDialog.dialog("close");
            },
            //查询
            searchContent: function () {
                //在表单中有serializeArray方法获取所有的参数
                var paramArr = brandSearchForm.serializeArray();
                //定义对象,把所有表单的参数设置到该对象中去
                var param = {};
                for (var i = 0; i < paramArr.length; i++) {
                    param[paramArr[i].name] = paramArr[i].value;
                }
                //传递参数,加载数据
                brandDatagrid.datagrid("load", param);
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