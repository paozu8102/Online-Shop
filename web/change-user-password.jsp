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
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <!-- Include Bootstrap JavaScript dependencies (jQuery, Popper.js, Bootstrap JS) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
            body{
                margin-top:20px;
                background-color:#f2f6fc;
                color:#69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            </style>
            <%@include file="template/header.jsp" %>
        </head>

        <body>
        <li class="nav-item active"><a href="index.jsp" class="nav-link">Home</a></li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
            <div class="dropdown-menu" aria-labelledby="dropdown04">
                <a class="dropdown-item" href="shop.jsp">Shop</a>
                <a class="dropdown-item" href="wishlist.jsp">Wishlist</a>
                <a class="dropdown-item" href="product-single.jsp">Single Product</a>
                <a class="dropdown-item" href="cart.jsp">Cart</a>
                <a class="dropdown-item" href="checkout.jsp">Checkout</a>
            </div>
        </li>
        <li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
        <li class="nav-item"><a href="blog.jsp" class="nav-link">Blog</a></li>
        <li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
        <c:if test="${sessionScope.acc==null}"><li class="nav-item active"><a href="login" class="nav-link">Sign In</a></li></c:if>
        <c:if test="${sessionScope.acc!=null}"><li><a class="getstarted scrollto" href="logout"></a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Manage</a>
                <div class="dropdown-menu" aria-labelledby="dropdown04">
                    <a class="dropdown-item" href="usercontrol">Manage User</a>
                    <a class="dropdown-item" href="settingcontrol">Manage Category</a>
                </div>
            </li>
            <li class="nav-item active"><a href="logout" class="nav-link">Sign Out</a></li> 
            </c:if>
        <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>
    </ul>
</div>
</div>
</nav>
<!-- END nav -->
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
                                            <label class="form-label" for="form3Example1c">
                                                <span style="color: black;
                                                    font-size: 20px;
                                                    font-weight: bold;">
                                                    Email:
                                                </span>
                                                <span style="color: #7ac507;
                                                    font-size: 24px;">
                                                    ${user.getEmail()}
                                                </span>
                                            </label>                                          
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
