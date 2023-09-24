<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Change Password</title>
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700|Raleway:300,600" rel="stylesheet">
 <meta name="viewport" content="width=device-width, initial-scale=1"><link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'><link rel="stylesheet" href="./style.css">
<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css2/style.css">
<style>
.form-group.form-button {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.return-link {
    /* Add your styles for the Return to Home link here */
    font-size: 14px;
    text-decoration: none;
    color: black; /* Adjust text color */
    border: 2px solid black; /* Add border */
    padding: 10px 20px; /* Adjust padding as needed to align with the Change button */
    border-radius: 5px; /* Add border-radius for rounded corners */
}

.return-link:hover {
    background-color: black; /* Change background color on hover */
    color: white; /* Change text color on hover */
}

</style>
</head>
<body>

	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">CHANGE PASS</h2>
					
						<form method="post" action="ChangePassword" class="register-form"
							id="register-form">
						
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label>                                    
                                                                <input readonly type="email" name="email" id="email" class="email" value="${user.getEmail()}
                                                                <span class=""></span>
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
                                                                    <input type="password" name="pass" id="password" placeholder="Old Password" class="pass">
                                                                <span class="error"></span>
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                                                <input type="password" name="newPassword" id="passwordCon" placeholder="New Password" class="passConfirm">
                                                                <span class=""></span>
							</div>
                                                                <div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                                                <input type="password" name="confirmPassword" id="passwordCon" placeholder="Confirm Pass" class="passConfirm">
                                                                <span class=""></span>
							</div>
                                                                                                            <% if (request.getAttribute("passwordChanged") != null && (boolean) request.getAttribute("passwordChanged")) { %>
                                            <div class="message-container text-center mt-4">
                                                <p class="message-text">Password Changed!</p>
                                            </div>
                                            <% } %>
                                            <div class="d-block mx-4 mb-3 mb-lg-4  text-danger"><b>${mess}</b></div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="return-link" value="Change" />
                                                               <a href="user-profile.jsp" class="return-link">Cancel</a>
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/hoa.jpg" alt="sing up image">
						</figure>
						
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script><script  src="./script.js"></script>



</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>