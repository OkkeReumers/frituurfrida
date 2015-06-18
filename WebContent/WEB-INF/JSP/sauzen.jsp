<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!doctype html>
<html lang='nl'>
	<head>
	<c:import url='head.jsp'>
		<c:param name='title' value="Sauzen" />
	</c:import>
	</head>
	<body>
	<vdab:menu/>
	<h1>Sauzen</h1>
		<form method="post" action="<c:url value='/sauzen/verwijderen.htm'/>">
			<c:forEach var='saus' items='${sauzen}'>
				<h2>
					<label>
						<input type="checkbox" name="nummer" value="${saus.nummer}">${saus.naam}
					</label>
				</h2>
				<img src='<c:url value="/images/${saus.naam}.png"/>' alt="${saus.naam }"> ingrediënten:
					<c:forEach var="ingredient" items="${saus.ingredienten }"
						varStatus="status">
						${ingredient }<c:if test="${not status.last }">, </c:if> 
					</c:forEach>
			</c:forEach>
			<div><input type="submit" value="Aangevinkte sauzen verwijderen"></div>
		</form>		
	</body>
</html>