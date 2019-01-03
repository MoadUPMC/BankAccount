<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<h3>Banque en ligne</h3>
<body>
	<p>Operations possibles : </p>
	<a href="deposit">Depot</a>
	<br>
	<a href="withdrawal">Retrait</a>
	<br>
	<a href="history">Historique</a>
	<br>
	<a href="accountstatement">Releve de compte</a>
	<br>
	<h3>Historique</h3>
	<table style="border: 1px solid black">
		<thead>
			<tr>
				<th>Operation</th>
				<th>Montant</th>
				<th>Nom</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="op" items="${account.operations}">
				<tr>
					<td><c:out value="${op.typeOperation}" /></td>
					<td><c:out value="${op.amount}" /></td>
					<td><c:out value="${op.nameOfOperation}" /></td>
					<td><c:out value="${op.date}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>