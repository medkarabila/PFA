
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Registration Page</title>        
    </head>
    <body>
    <center>
        <div id="mystyle" class="myform">
            <form id="form" name="form" method="post" action="RegisterServlet">
                <h1>Registration</h1>
                <p>Please enter the following information</p>

                <label>First Name
                    <span class="small">Enter your first name</span>
                </label>
                <input type="text" name="firstName" id="firstName" />
                <label>Last Name
                    <span class="small">Enter your last name</span>
                </label>
                <input type="text" name="lastName" id="lastName" />

                <label>Email
                    <span class="small">Ex: med@gmail.com</span>
                </label>
                <input type="text" name="email" id="email" />

                <label>User ID
                    <span class="small">Enter your user ID</span>
                </label>
                <input type="text" name="userId" id="userId" />

                <label>Password
                    <span class="small">Password min. 6 chars</span>
                </label>
                <input type="password" name="password" id="password" />
                
                <label>Status
                    <span class="small">Enter your status</span>
                </label>
                <div class="radioclass">
	                <input class="ra" type="radio" name="gender" value="admin"> Admin<br>
	  			 	<input class="ra" type="radio" name="gender" value="prof"> Prof<br>
	 			 	<input class="ra" type="radio" name="gender" value="etudiant"> Etudiant
				</div>
                <button type="submit">Register</button>
                <div class="spacer"></div>

            </form>
        </div>
    </center>    
</body>
</html>
