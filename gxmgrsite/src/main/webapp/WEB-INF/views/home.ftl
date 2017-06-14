<html>
<head>
    <title>系统首页</title>
<#include "common/common.ftl" />
    <script type="text/javascript" src="/js/views/index.js"></script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" style="width:600px;height:400px;">
    <div data-options="region:'north'"
         style="height:100px;background: url('/js/banner-pic.gif'),no-repeat;background-size: cover">
        <h1>后台管理系统</h1>
    </div>
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
            <div title="欢迎页面" closable="true">芳芳老师欢迎您</div>
        </div>
    </div>
    <div data-options="region:'south'"  style="height:50px;background: url('/js/banner-pic.gif'),no-repeat;background-size: cover">
        <center>版权声明</center>
    </div>
</div>
</body>
</html>
