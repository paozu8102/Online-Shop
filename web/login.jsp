<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login</title>

        <!-- Font Icon -->
        <link rel="stylesheet"
              href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css2/style.css">
    </head>
    <body>

        <div class="main">

            <!-- Sing in  Form -->
            <section class="sign-in">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure>
                                <img src="images/phongcanh.jpg" alt="sing up image">
                            </figure>
                        </div>

                        <div class="signin-form">
                            <h2 class="form-title">Hello, Welcome to Pallette Joy !</h2>
                            <form method="post" action="login" class="register-form" id="login-form">
    <p class="text-danger">${mess}</p>

    <div class="form-group">
        <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
        <input type="text" name="email" id="email" placeholder="Your Email" />
    </div>

    <div class="form-group">
        <label for="password"><i class="zmdi zmdi-lock"></i></label>
        <input type="password" name="password" id="password" placeholder="Password" />
    </div>

 

    <div class="form-group form-button">
        <input type="submit" name="signin" id="signin" class="form-submit" value="Log in" />
        <a style="text-decoration: none; color: black" href="signup.jsp">New account?</a>
        <a style="text-decoration: none; color: black" href="signupforsaler.jsp">Artist</a>
        <a style="text-decoration: none; color: black" href="forgotPassword.jsp">Forgot</a>
    </div>
</form>

<script>
    // L?y giá tr? c?a "Remember Me" t? cookie và ??t vào radio button khi trang t?i l?i
    window.onload = function() {
        const rememberOnRadio = document.getElementById('remember-on');
        const rememberOffRadio = document.getElementById('remember-off');

        const rememberMeValue = getCookie('rem');

        if (rememberMeValue === 'ON') {
            rememberOnRadio.checked = true;
        } else if (rememberMeValue === 'OFF') {
            rememberOffRadio.checked = true;
        }

        // Thêm x? lý s? ki?n cho radio button
        const radios = document.querySelectorAll('input[name="rem"]');
        for (const radio of radios) {
            radio.addEventListener('change', function() {
                const selectedValue = document.querySelector('input[name="rem"]:checked').value;
                setCookie('rem', selectedValue, 30);  // L?u giá tr? vào cookie
            });
        }
    };

    // Hàm ?? l?u giá tr? vào cookie
    function setCookie(cookieName, cookieValue, daysToExpire) {
        const date = new Date();
        date.setTime(date.getTime() + (daysToExpire * 24 * 60 * 60 * 1000));
        const expires = 'expires=' + date.toUTCString();
        document.cookie = cookieName + '=' + cookieValue + ';' + expires + ';path=/';
    }

    // Hàm ?? l?y giá tr? c?a cookie
    function getCookie(cookieName) {
        const name = cookieName + '=';
        const cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            let cookie = cookies[i].trim();
            if (cookie.indexOf(name) === 0) {
                return cookie.substring(name.length, cookie.length);
            }
        }
        return '';
    }
</script>
<script>
  // Check the value of the "sc" attribute
  var scValue = "${sc}";

  // Check if "sc" is "Successfully" and display an alert
  if (scValue === "Successfully") {
    alert("Sign up successfully! Please login to view our page");
  }
</script>

                            <div class="social-login">
                                <span class="social-label">Or login with</span>
                                <ul class="socials">
                                    <li><a href="#"><i
                                                class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                    <li><a href="#"><i
                                                class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                                    <li><a href="#"><i
                                                class="display-flex-center zmdi zmdi-google"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>

        <!-- JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>
    </body>
    <!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>