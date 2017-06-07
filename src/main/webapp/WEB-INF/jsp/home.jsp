<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
	<h1>Welcome to Home Page</h1>
	<c:if test="${myMessage!=null }">
		<div>${myMessage.message}</div>
		<div>${myMessage.createTime}</div>
	</c:if>
	<div id="msg"></div>
	<ul>
		<li>（1）<c:out value="普通一行字符串"></c:out></li>
		<li>（2）<c:out value="&lt未使用字符转义&gt" /></li>
		<li>（3）<c:out value="&lt未使用字符转义&gt" escapeXml="false"></c:out></li>
		<li>（4）<c:out value="${null}">使用了默认值</c:out></li>
		<li>（5）<c:out value="${null}"></c:out></li>
	</ul>
	<c:url value="http://www.autohome.com.cn">autohome</c:url>
	<%!private int initVar = 0;
	private int serviceVar = 0;
	private int destroyVar = 0;%>

	<%!public void jspInit() {
		initVar++;
		System.out.println("jspInit(): JSP被初始化了" + initVar + "次");
	}

	public void jspDestroy() {
		destroyVar++;
		System.out.println("jspDestroy(): JSP被销毁了" + destroyVar + "次");
	}%>

	<%
		serviceVar++;
		System.out.println("_jspService(): JSP共响应了" + serviceVar + "次请求");

		String content1 = "初始化次数 : " + initVar;
		String content2 = "响应客户请求次数 : " + serviceVar;
		String content3 = "销毁次数 : " + destroyVar;
	%>
	<script type="text/javascript">
		$(function() {
			var ajax = $.ajax({
				"url" : "/chat/1"

			})

			ajax.done(function(data) {
				$("#msg").text(JSON.stringify(data));
			});
			ajax.fail(function(data) {
				$("#msg").text('request failed.');
			});

		});
	</script>
</body>
</html>