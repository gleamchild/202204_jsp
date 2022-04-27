<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
	//파라미터 메세지 창
	if('${param.msg}' != ''){
		alert('${param.msg}');
	}
	//requestScope 메세지 창
	if('${msg}' != ''){
		alert('${msg}');
	}
</script>