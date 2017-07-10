<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统首页</title>
    <%@include file="common/common.jsp"%>

</head>
<body>
<div class="easyui-layout" data-options="fit:true" style="width:600px;height:400px;">

    <div data-options="region:'west'" style="width:200px;">
        <!--手风琴加菜单-->
        <div class="easyui-accordion" fit="true">
            <div title="菜单">
                <!--tree菜单-->
                <ul id="myTree"></ul>
            </div>
            <div title="简介"></div>
            <div title="帮助"></div>
        </div>
    </div>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <!--正文内容-->
        <div id="myTabs" class="easyui-tabs" fit="true">
            <div title="首页" closable="true">后台管理系统</div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" >
    $(function () {
        $("#myTree").tree({
            url: "/supervisor/menu.do",
            onClick: function (node) {
                	console.log(node);
            	
                //在选项卡中添加面板
                var tt = $("#myTabs");
                //选项卡中是否已经有该节点的面板
                if (tt.tabs("exists", node.text)) {
                    //选中面板
                    tt.tabs("select", node.text);
                } else {
                	console.log(node.attributes);
                	if (node.attributes=="" || node.attributes == null) {
					}else{
	                    //添加面板
	                    tt.tabs("add", {
	                        title: node.text,
	                        closable: true,
	                        content:"<iframe src='"+node.attributes+"' style='width:100%;height:100%' frameborder=0></iframe>"
	                    });
					}
                }
            }
        });
    });
</script>
</html>
