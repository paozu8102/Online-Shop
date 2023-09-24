<%-- 
    Document   : change-user-password
    Created on : Sep 24, 2023, 9:10:12 PM
    Author     : Mai Vu Bao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!--Author: DPV-->
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>QPS</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/q-icon.png" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <style>
            .message-container {
                background-color: #e9ecef;
                padding: 10px;
                border-radius: 5px;
            }
            .message-text {
                font-weight: bold;
            }
        </style>
        
    </head>

    <body>
        
        <section class="vh-100" style="background-color: #eee;">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-12 col-xl-11">
                        <div class="card text-black" style="border-radius: 5px;">
                            <div class="card-body p-md-5">
                                <div class="row justify-content-center">
                                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                                        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Change Password</p>
                                        <form class="mx-1 mx-md-4" action="ChangePassword" method="post">
                                            
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <label class="form-label" for="form3Example1c">Enter Email</label>
                                                    <input type="text" id="inputEmailAddress" class="form-control" name="email" readonly type="email" placeholder="Enter your email address" value="${user.getEmail()}>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <label class="form-label" for="form3Example1c">Enter Old Password</label>
                                                    <input type="password" id="form3Example1c" class="form-control" required name="pass"/>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <label class="form-label" for="form3Example1c">New Password</label>
                                                    <input type="password" id="form3Example1c" class="form-control" required name="newPassword"/>
                                                </div>
                                            </div>                                           
                                            </c:if>
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <label class="form-label" for="form3Example3c">Confirm Password</label>
                                                    <input type="password" id="form3Example3c" class="form-control" required name="confirmPassword"/>
                                                </div>
                                            </div>
                                            <% if (request.getAttribute("passwordChanged") != null && (boolean) request.getAttribute("passwordChanged")) { %>
                                            <div class="message-container text-center mt-4">
                                                <p class="message-text">Password Changed!</p>
                                            </div>
                                            <% } %>
                                            <div class="d-block mx-4 mb-3 mb-lg-4  text-danger"><b>${mess}</b></div>
                                            <div class="d-block mx-4 mb-3 mb-lg-4 text-center">
                                                <button class="btn btn-primary btn-block px-5 w-100" type="submit">Confirm</button>
                                            </div>
                                            <div class="text-center mt-4">
                                                <a href="home" class="btn btn-primary btn-block">Back to Home</a>
                                            </div>

                                        </form>
                                    </div>
                                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                             class="img-fluid" alt="Sample image">
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
