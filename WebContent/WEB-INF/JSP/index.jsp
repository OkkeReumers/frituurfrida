<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename='resourceBundles.teksten' />
<!doctype html>
<html>
<head>
<fmt:message key='frituurFrida' var='title' />
<c:import url='head.jsp'>
	<c:param name='title' value='${title}' />
</c:import>
</head>
<body>
<vdab:menu/>
	<h1>
		<fmt:message key='vandaagZijnWe${openGesloten}' />
	</h1>
	<fmt:message key='afbeelding${openGesloten}' var='afbeelding' />
	<img src='<c:url value="/images/${afbeelding}.png"/>'
		alt="<fmt:message key='${openGesloten}'/>">
	<h2>
		<fmt:message key='adres' />
	</h2>
	${adres.straat} ${adres.huisNr}
	<br> ${adres.gemeente.postcode} ${adres.gemeente.naam}
</body>
</html>

