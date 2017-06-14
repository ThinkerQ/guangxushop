$(function () {
    $("#myTree").tree({
        url: "/supervisor/menu.do",
        onClick: function (node) {
            //在选项卡中添加面板
            var tt = $("#myTabs");
            //选项卡中是否已经有该节点的面板
            if (tt.tabs("exists", node.text)) {
                //选中面板
                tt.tabs("select", node.text);
            } else {
                //添加面板
                tt.tabs("add", {
                    title: node.text,
                    closable: true,
                    content:"<iframe src='"+node.attributes+"' style='width:100%;height:100%' frameborder=0></iframe>"
                })
            }
        }
    });
});