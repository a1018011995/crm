<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="/WEB-INF/jsp/lib.jsp"></jsp:include>
<div>
	<label>班级姓名</label>
	<input id="qclazzname" type="text">
	<input type="button" id="queryClazz" value="查询">
</div>
<table id="dg"></table>
<div id="addclazz">
	<label>班级名称</label>
	<input id="iclazzname" ><br>	
	<label>课程名称</label>
		<select id="course">			
		</select><br>
	<label>课程学时</label>
	<input id="learntime" type="text" disabled="disabled" ></input><br>
	<label>课程简介</label>
	<input id="description" type="text" disabled="disabled" ></input><br>
	<label>收费标准</label>
	<input id="money" type="text" disabled="disabled" ></input><br>
	<input type="button" id= 'isave' value="保存">
</div>
<html>
<script type="text/javascript">
$(function(){
	$("#queryClazz").click(function(){
		var clazzname = $("#qclazzname").val();
		$("#dg").datagrid({
			queryParams: {
				clazzname:clazzname
			}
		})
	})
	$.ajax({
		url:'course/list.do',
		data:{rows:100},
		success:function(data){
			if(data.rows){
				$("#course").empty();
				$(data.rows).each(function(){
					$("#course").append('<option value='+this.courseid+'>'+this.coursename+'</option>')
				})
			}
		}
	})
	$("#course").change(function(){
		var courseid = $("#course").val();
		$.ajax({
			url:'course/get.do',
			data:{id:courseid},
			success:function(data){
				$("#learntime").empty();
				$("#description").empty();
				$("#money").empty();
				//{"courseid":1,"coursename":"java班级","description":"
				//java学习","learntime":188,"money":12000}
				$("#learntime").val(data.learntime);
				$("#description").val(data.description);
				$("#money").val(data.money);
			}
		})
	})
			$("#dg").datagrid({
				url:'clazz/list.do',
				//[{"learntime":188,"description":"java学习","money":12000,
				//"clazzid":4,"clazzname":"01班","courseid":1,"coursename":"java班级"},
				columns:[[
				          {field:'clazzname',title:'班级名称',width:100},
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
		        		//[{"learntime":188,"description":"java学习","money":12000,
						//"clazzid":4,"clazzname":"01班","courseid":1,"coursename":"java班级"},
		        		$("#addclazz").dialog("open");
						$("#isave").unbind();
						$("#addclazz").dialog("setTitle","增加班级");
						$("#isave").click(function(){
							var iclazzname = $("#iclazzname").val();
							var icourse = $("#course").val();
							$.ajax({
								url:'clazz/add.do',
								data:{clazzname:iclazzname,courseid:icourse},
								success:function(data){
									if(data.flag){
										$("#dg").datagrid("reload");
										$("#addclazz").dialog("close");
									}
								}
							})
							
						});
		        	}
				},{
					text:'删除',
		        	iconCls:'icon-remove',
		        	//[{"learntime":188,"description":"java学习","money":12000,
					//"clazzid":4,"clazzname":"01班","courseid":1,"coursename":"java班级"},
		        	handler:function(){
		        		var row = $("#dg").datagrid("getSelected");
		        		if(row){
		        			$.ajax({
		        				url:'clazz/del.do',
		        				data:{id:row.clazzid},
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
						$("#addclazz").dialog("setTitle","修改班级");
						var row = $("#dg").datagrid("getSelected");
						if(row){
							$("#addclazz").dialog("open");
				        	//[{"learntime":188,"description":"java学习","money":12000,
							//"clazzid":4,"clazzname":"01班","courseid":1,"coursename":"java班级"},
							var iclazzname = $("#iclazzname").val(row.clazzname);
							var icourseid = $("#course").val(row.courseid);
							$.ajax({
								url:'course/get.do',
								data:{id:row.courseid},
								success:function(data){
									$("#learntime").empty();
									$("#description").empty();
									$("#money").empty();
									//{"courseid":1,"coursename":"java班级","description":"
									//java学习","learntime":188,"money":12000}
									$("#learntime").val(data.learntime);
									$("#description").val(data.description);
									$("#money").val(data.money);
								}
							})

							$("#isave").click(function(){	
								var iclazzname = $("#iclazzname").val();
								var icourseid = $("#course").val();
								//alert(iclazzname);
								//alert(icourseid);
								//var oclazzname = document.getElementById('iclazzname').value;
								//var ocourseid = document.getElementById('course').value;
								$.ajax({
									url:'clazz/update.do',
									//clazzname = ?,       courseid = ?     where clazzid = ?
											//clazzname:iclazzname,courseid:icourse
									data:{clazzid:row.clazzid,clazzname:iclazzname,courseid:icourseid},
									success:function(data){
										if(data.flag){
											$("#dg").datagrid("reload");
											$("#addclazz").dialog("close");
										}
									}
								})
							});
						}}
				},]
			});   
			$("#addclazz").dialog({
			    title: '增加班级',    
			    closed: true,    
			    cache: false,    
			    modal: true   
			})
		})
</script>
</html>