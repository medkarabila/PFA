<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>etudiant</title>
</head>
<script src="assets/scripts/jquery.js"></script>
<script src="assets/scripts/chapitre.js"></script>
<body>
	<section>
		<c:set var="matiere" value="true"/>
		<c:forEach items="${listeChapitre }"  var="chapitre">
			<c:if test="${matiere==true }">
				<h1>Pour accéder a la matiere ${chapitre.matiere } veuillez repondre au questions suivantes:</h1>
				<c:set var="matiere" value="false"/>
			</c:if>
			<div class="chapitre">
				<h2>${chapitre.chapitre }</h2>
				<p><strong>${chapitre.question }?</strong></p>
				<button id="oui">Oui</button>
				<a href="images/${chapitre.fichier }">Non</a>
			</div>
		</c:forEach>
	</section>
	
	<a href="logout.jsp">Logout</a>
</body>
</html>