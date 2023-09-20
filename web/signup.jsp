<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700|Raleway:300,600" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1"><link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'><link rel="stylesheet" href="./style.css">

    </head>
    <body>
        <!-- partial:index.partial.html -->
        <div class="container">
            <section id="formHolder">

                <div class="row">

                    <!-- Brand Box -->
                    <div class="col-sm-6 brand">
                        <a href="#" class="logo">This is</a>

                        <div class="heading">
                            <h2>Palette Joy</h2>
                            <p>Your Right Choice</p>
                        </div>

                        <div class="success-msg">
                            <p>Cool ! You are one of our members now ! Please login to view page</p>
                            <a href="login.jsp" class="profile">CLICK TO LOGIN</a>
                        </div>
                    </div>


                    <!-- Form Box -->
                    <div class="col-sm-6 form">


                        <div class="signup form-peice">
                            <form class="signup-form" action="signup" method="post">
                                <h3>${error}</h3>

                                <div class="form-group">
                                    <label for="name">Full Name</label>
                                    <input type="text" name="username" id="name" class="name">
                                    <span class="error"></span>
                                </div>

                                <div class="form-group">
                                    <label for="email">Email Adderss</label>
                                    <input type="email" name="email" id="email" class="email">
                                    <span class="error"></span>
                                </div>

                                <div class="form-group">
                                    <label for="phone">Phone Number - <small>Optional</small></label>
                                    <input type="text" name="phonenumber" id="phone">
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" name="password" id="password" class="pass">
                                    <span class="error"></span>
                                </div>

                                <div class="form-group">
                                    <label for="passwordCon">Confirm Password</label>
                                    <input type="password" name="passwordCon" id="passwordCon" class="passConfirm">
                                    <span class="error"></span>
                                </div>

                                <div class="CTA">
                                    	<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
                                    <a href="login.jsp">I have an account</a>
                                </div>
                            </form>
                        </div> 
                    </div>
                </div>

            </section>


            <!-- <footer>
                  <p>
                     Form made by: <a href="http://mohmdhasan.tk" target="_blank">Mohmdhasan.tk</a>
                  </p>
               </footer>
            -->

        </div>
        <!-- partial -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script><script  src="./script.js"></script>

    </body>
</html>
