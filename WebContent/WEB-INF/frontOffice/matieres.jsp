<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>etudiant</title>
</head>
<body>
	<section>
		<div id="modules">
			<ul>
				<c:forEach items="${ listeModules }" var="module">


				<a href="Matiere?module=${module.idModule }"><li><img alt="${module.module }"
					src="images/${module.image }">
				${module.module }</li>
				
				</a>

			</c:forEach>
			</ul>
		</div>
		
		<div id="matieres">
	<c:set var="module" value="true" />
		<c:forEach items="${ listeMatieres }" var="matiere">
<c:if test="${module==true}">
	<h1>Liste des matieres de module ${ matiere.module }</h1>
	<c:set var="module" value="false" />
</c:if>
	<a href="Chapitre?matiere=${matiere.id }">
		<div class="module">
			<img src="images/${matiere.icon }" alt="${ matiere.matiere }">
			<p>${matiere.matiere }</p>
		</div>
	</a>
	<p>
			${matiere.description }
			</p>
	<hr>
			</c:forEach>
	</div>
	</section>
	<a href="logout.jsp">Logout</a>
</body>
</html>