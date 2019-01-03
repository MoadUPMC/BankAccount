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
<h3>Deposez votre argent</h3>
<form method="POST" action="/depositing" >
    <p>Montant</p> <input  type="number" id="amount" name="amount" required/>
    <p>Provenance</p><input type="text" id="nameOfOperation" name="nameOfOperation" required/>

    <button type="submit">Valider</button>
</form>
</body>
</html>