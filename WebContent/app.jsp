<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="index.css">
<title>Insert title here</title>
</head>
<body>
	<header>
				<div id="hDiv">
					<a href="#"><span id="span1">Cours</span><span id="span2">aline</span></a>
					<ul  id="hUl">
						<li>Accueil</li>
						<li><a href="#" id="categorie">Categories &#9660</a></li>
						<li><a href="#">Modules &#9660</a></li>
						<li>A propos</li>
						<li>Contacs</li>
					</ul>
					<input type="text" name="recherche" placeholder="Rechercher" class="recherche">
					<img src="images/lo.png" id="loopImg">

					<a href="login.jsp"><button id="bt1">Se connecter</button></a>
					<a href="register.jsp"><button>S'inscrire</button></a>
				</div>
				<div>
				<h1>Intelegent E-learning Platform</h1>
				<button id="com">Commancer</button>
				</div>

		</header>
		<footer>
			<div class="contact">

	            <h4 >Contact</h4>
	            <input type="text" class="inputt" name="nom" placeholder="Nom et prénom"><br><br>
	            <input type="text" class="inputt" name="email" placeholder="exemple@exemple.com"><br><br>
	            <input type="text" class="inputt" name="sujet" placeholder="sujet"><br><br>
	            <textarea class="textarea" placeholder="message"></textarea><br><br>
	            <button type="button" class="buttonn">Envoyer</button>

          	</div>
          	<div class="about">
            	<h4 >À propos</h4>
	            <p >Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.  </p>
	        </div>
	        <div id="rs">
	        	<h4 >Suivez nous : </h4>
				<ul>
					<li id="fb"></li>
					<li id="twitter"></li>
					<li id="youtube"></li>
					<li id="google"></li>

				</ul>

			</div>
		</footer>

</body>
</html>