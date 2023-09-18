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
                            <p>Cool ! You are one of our members now</p>
                            <a href="#" class="profile">Your Profile</a>
                        </div>
                    </div>


                    <!-- Form Box -->
                    <div class="col-sm-6 form">

                        <!-- Login Form -->
                        <div class="login form-peice">
                            <form class="login-form" action="login" method="post">
                                <p class="text-danger">${mess}</p>
                                <div class="form-group">
                                    <label for="loginemail">Email Adderss</label>
                                    <input type="email" name="email" id="loginemail" required>
                                </div>

                                <div class="form-group">
                                    <label for="loginPassword">Password</label>
                                    <input type="password" name="password" id="loginPassword"required>
                                </div>   
                                <div class="CTA">
                                    <input type="submit" value="Login">
                                    <a href="signup.jsp">I'm New</a>
                                </div>
<!--                                <div style="padding-left: 40px;padding-top: 5px">
                            <input type="checkbox" ${(cookie.rem.value eq 'ON')?"checked":""}
                                   name="rem"  value="ON"/>Remember Me<br/>
                        </div>   -->
                            </form>
                        </div><!-- End Login Form -->
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
