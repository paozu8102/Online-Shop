<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up To Join WIth Us</title>
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700|Raleway:300,600" rel="stylesheet">
 <meta name="viewport" content="width=device-width, initial-scale=1"><link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'><link rel="stylesheet" href="./style.css">
<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css2/style.css">
<style>
.error-message {
    display: none;
    color: red;
    font-weight: bold;
    white-space: nowrap; /* Prevent the text from wrapping */
    overflow: hidden; /* Hide any overflowed text */
}

.agree-term:invalid + .label-agree-term + .error-message {
    display: inline-block; /* Display inline-block to enable sliding */
    animation: slide 8s linear forwards; /* Sliding animation */
}

@keyframes slide {
    from {
        transform: translateX(100%);
    }
    to {
        transform: translateX(0);
    }
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
						<h2 class="form-title">Sign Up</h2>
					
						<form method="post" action="signupwithemail" class="register-form"
							id="register-form">
                                                    <h3>${error}</h3>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label>                                    
                                                                <input type="email" name="email" id="email" placeholder="Your Email" class="email">
                                                                <span class="error"></span>
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
                                                                    <input type="password" name="password" id="password" placeholder="Password" class="pass">
                                                                <span class="error"></span>
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                                                <input type="password" name="passwordCon" id="passwordCon" placeholder="Confirm Pass" class="passConfirm">
                                                                <span class="error"></span>
							</div>
							<div class="form-group">
    <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" required />
    <label for="agree-term" class="label-agree-term">
        <span><span></span></span>I agree with all statements <a href="term.jsp" class="term-service">Terms of service</a>
    </label>
    <span class="error-message">Please select the Terms of service.</span>
</div>
                                                        
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
                                                                <span><a href="signupforsaler.jsp">You're an artist?</a></span>
                                                                <span><a href="login.jsp">Login</a></span>
							</div>
						</form>
                                                                                    <div class="social-login">
                            </div>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/RSU.jpg" alt="sing up image">
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