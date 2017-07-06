$(function(){
	$("#systemDictionary_datagrid").datagrid({
		url:"/Category/categoryOneListAll.do",
		fit:true,
		rownumbers:true,
		pagination:true,
		pageList: [3, 5, 10,15, 20, 50],
		pageSize:15,
		singleSelect:true,
	    fitColumns:true,
		columns:[[
		          {title:"一级类目编号",field:"id",width:10,align:'center'},
	               {title:"类目名称",field:"name",width:10,align:'center'},
	               {title:"创建日期",field:"createDate",width:10,align:'center'},
	               {title:"更新日期",field:"updateDate",width:10,align:'center'}
	               ]],
	               onClickRow:function(rowIndex, rowData){
	            	   $("#systemDictionaryItem_datagrid").datagrid({
    	            		   url:"/Category/categoryTwoListSelectItemById.do?pid="+rowData.id,
    	            			fit:true,
    	            			rownumbers:true,
    	            			pagination:true,
    	            			pageList:[5,10,20],
    	            			singleSelect:true,
    	            		    toolbar:'#tt',
							    columns:[[
									{title:"二级类目编号",field:"id",width:10,align:'center'},
									{title:"类目名称",field:"name",width:10,align:'center'},
									{title:"父级类目",field:"parentName",width:10,align:'center'},
									{title:"创建日期",field:"createDate",width:10,align:'center'}
    			               ]],
							   fitColumns:true
	            	   });
	            	   $("#systemDIctionaryItem_dialog").dialog({
	            			height:250,
	            			width:300,
	            			buttons:'#bb',
	            			closed:true
	            		});
	               }
	});
	
});
function add(){
	var data = $("#systemDictionary_datagrid").datagrid("getSelected");
	$("#parentName").text(data.name);
	console.log($("input[name='parent']"));
	$("#systemDIctionaryItem_dialog").dialog("open");
	$("#systemDIctionaryItem_dialog").dialog("setTitle","新增");
	$("#systemDIctionaryItem_form").form("clear");
}
function edit(){
	var datas = $("#systemDictionary_datagrid").datagrid("getSelected");
	var data = $("#systemDictionaryItem_datagrid").datagrid("getSelected");
	$("#parentName").text(datas.name);
	if(data){
		$("#systemDIctionaryItem_dialog").dialog("open");
		$("#systemDIctionaryItem_dialog").dialog("setTitle","编辑");
		$("#systemDIctionaryItem_form").form("clear");
		$("#systemDIctionaryItem_form").form("load",data);
	}else{
		$.messager.alert("温馨提示","请选择需要跟新的数据","warning")
	}
}
function del(){
	var rowdata = $("#systemDictionaryItem_datagrid").datagrid("getSelected");
	if(rowdata){
	$.messager.confirm("温馨提示'","您确定需要禁用该条字典明细",function(r){
			if(r){
				  $.get("/systemDictionaryItem_delete?id="+rowdata.id,function(data){
			        	if(data.success){
			        		$.messager.alert("温馨提示",data.msg,"info",function(){
			        			$("#systemDictionaryItem_datagrid").datagrid("reload");
			        		});
			        	}else{
			        		$.messager.alert("温馨提示",data.msg,"error");
			        	}
			        	
			        },"json")	;	
			}
		});
      
	}else{
		$.messager.alert("温馨提示","请选择需要删除的数据","warning")
	}
}
function reload(){
	$("#employee_datagrid").datagrid("reload'");
}
function save(){
	var url;
	var id = $("input[name='id']").val();
	if(id){
		url="/systemDictionaryItem_update";
	}else{
		url="/systemDictionaryItem_save";
	}
	
$("#systemDIctionaryItem_form").form("submit",{
		url:url,
		onSubmit:function(param){
			var row = $("#systemDictionary_datagrid").datagrid("getSelected");
			param["parent.id"]=row.id;
		},
      success:function(data){
	    data=$.parseJSON(data);
	   if(data.success){
		 $.messager.alert("温馨提示",data.msg,"info",function(){
			 var row = $("#systemDictionary_datagrid").datagrid("getSelected");
			$("#systemDIctionaryItem_dialog").dialog("close");
			$("#systemDictionaryItem_datagrid").datagrid({
				url:"/systemDictionaryItem_selectItemById?id=" +row.id
			});
		});
	}else{
		$.messager.alert("温馨提示",data.msg,"error");
	}
}
	});
}
function cancle(){
	$("#employee_dialog").dialog("close");
}
function deptFormatter(value,record,index){
	if(value){
		return value.name;
	}else{
		return value;
	}
}
function searchContent(){
	var param = {};
	var paramArray = $("#employee_searchform").serializeArray();
	for ( var i = 0; i < paramArray.length; i++) {
		param[paramArray[i].name]=paramArray[i].value
	}
	console.log(param);
	$("#employee_datagrid").datagrid("load",param);
}
function parentFormatter(value,record,index){
		return value.name;
}