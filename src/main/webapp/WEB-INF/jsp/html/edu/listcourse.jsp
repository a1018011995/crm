<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="/WEB-INF/jsp/lib.jsp"></jsp:include>
<div>
	<label>班级姓名</label>
	<input id="qcoursename" type="text">
	<input type="button" id="queryCourse" value="查询">
</div>
<table id="dg"></table>
<div id="addcourse">	
	<label>课程名称</label>
	<input id="coursename" type="text"  ></input><br>
	<label>课程简介</label>
	<input id="description" type="text" ></input><br>	
	<label>课程学时</label>
	<input id="learntime" type="text"></input><br>
	<label>收费标准</label>
	<input id="money" type="text" ></input><br>
	<input type="button" id= 'isave' value="保存">
</div>
<html>
<script type="text/javascript">
$(function(){
	$("#queryCourse").click(function(){
		var coursename = $("#qcoursename").val();
		$("#dg").datagrid({
			queryParams: {
				coursename:coursename
			}
		})
	})
			$("#dg").datagrid({
				url:'course/list.do',
				//{"courseid":1,"coursename":"java班级","description":
					//"java学习","learntime":188,"money":12000}
				columns:[[
				          {field:'coursename',title:'课程名称',width:100},
				          {field:'description',title:'班级简介',width:100},
				          {field:'learntime',title:'总学时',width:100},
				          {field:'money',title:'收费标准',width:100}
				          ]],
				fit:true,
				fitColumns:true,
				singleSelect:true,
				pagination:true,
				toolbar:[{
					text:'增加',
		        	iconCls:'icon-add',
		        	handler:function(){
						//{"courseid":1,"coursename":"java班级","description":
						//"java学习","learntime":188,"money":12000}
		        		$("#addcourse").dialog("open");
						$("#isave").unbind();
						$("#addcourse").dialog("setTitle","增加课程");
						$("#isave").click(function(){
							var icoursename = $("#coursename").val();
							var idescription = $("#description").val();
							var ilearntime = $("#learntime").val();
							var imoney = $("#money").val();
							$.ajax({
								url:'course/add.do',
								data:{coursename:icoursename,description:idescription,learntime:ilearntime,money:imoney},
								success:function(data){
									if(data.flag){
										$("#dg").datagrid("reload");
										$("#addcourse").dialog("close");
									}
								}
							})
							
						});
		        	}
				},{
					text:'删除',
		        	iconCls:'icon-remove',
					//{"courseid":1,"coursename":"java班级","description":
					//"java学习","learntime":188,"money":12000}
		        	handler:function(){
		        		var row = $("#dg").datagrid("getSelected");
		        		if(row){
		        			$.ajax({
		        				url:'course/del.do',
		        				data:{id:row.courseid},
		        				success:function(data){
		        					if(data.flag){
		        						$("#dg").datagrid("reload");
		        					}
		        				}
		        			})
		        		}
		        	}
				},{
					text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
						$("#isave").unbind();
						$("#addcourse").dialog("setTitle","修改课程");
						var row = $("#dg").datagrid("getSelected");
						if(row){
							$("#addcourse").dialog("open");
							//{"courseid":1,"coursename":"java班级","description":
							//"java学习","learntime":188,"money":12000}
							$("#coursename").val(row.coursename);
							$("#description").val(row.description);
							$("#learntime").val(row.learntime);
							$("#money").val(row.money);						
							$("#isave").click(function(){							
								var icoursename = $("#coursename").val();
								var idescription = $("#description").val();
								var ilearntime = $("#learntime").val();
								var imoney = $("#money").val();
								$.ajax({
									url:'course/update.do',
									//coursename = ?,       courseid = ?     where courseid = ?
											//coursename:icoursename,courseid:icourse
									data:{courseid:row.courseid,coursename:icoursename,description:idescription,learntime:ilearntime,money:imoney},
									success:function(data){
										if(data.flag){
											$("#dg").datagrid("reload");
											$("#addcourse").dialog("close");
										}
									}
								})
							});
						}}
				},]
			});   
			$("#addcourse").dialog({
			    title: '增加课程',    
			    closed: true,    
			    cache: false,    
			    modal: true   
			})
		})
</script>
</html>