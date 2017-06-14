<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>etudiant</title>
</head>
<body>
	<div id="categories">
		<ul>


			<c:forEach items="${ categories }" var="categorie">


				<a href="Front?idCategorie=${categorie.id }"><li><img alt="${categorie.categorie }"
					src="images/${categorie.icon }">
				${categorie.categorie }</li>
				
				</a>

			</c:forEach>

		</ul>
	</div>
	<div id="modules">
	<c:set var="categorie" value="true" />
		<c:forEach items="${ listModule }" var="module">
<c:if test="${categorie==true}">
	<h1>Liste des modules de la categorie ${ module.categorie }</h1>
	<c:set var="categorie" value="false" />
</c:if>
	<a href="Matiere?module=${module.id }">
		<div class="module">
			<img src="images/${module.image }" alt="${ module.module }">
			<p>${module.module }</p>
			
			
			
		</div>
	</a>
	
	<p>
			${module.description }
			</p>
	<hr>
			</c:forEach>
	</div>
	<a href="logout.jsp">Logout</a>
</body>
</html>