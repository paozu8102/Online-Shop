


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">



        <!-- Include Bootstrap CSS and JavaScript -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">


        <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <style>


            .search-container input [type=text] {
                padding: 6px;
                margin-top: 8px;
                font-size: 10px;

            }

            .search-container button {
                float: right;
                padding: 1px 10px;
                margin-right: 16px;
                background: #82ae46;
                font-size: 17px;
                border: none;
                cursor: pointer;
            }

            .search-container button:hover {
                background: #82ae46;
            }


        </style>
    </head>
    <body class="goto-here">
        <div class="py-1 bg-primary">
            <div class="container">
                <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
                    <div class="col-lg-12 d-block">
                        <div class="row d-flex">
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
                                <span class="text">+ 1235 2355 98</span>
                            </div>
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                                <span class="text">youremail@email.com</span>
                            </div>
                            <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                                <span class="text">3-5 Business days delivery &amp; Free Returns</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">Pallette Joy</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a href="index.jsp" class="nav-link">Home</a></li>
                        <li class="nav-item active dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
                            <div class="dropdown-menu" aria-labelledby="dropdown04">
                                <a class="dropdown-item" href="shop.jsp">Shop</a>
                                <a class="dropdown-item" href="wishlist.jsp">Wishlist</a>
                                <a class="dropdown-item" href="product-single.jsp">Single Product</a>
                                <a class="dropdown-item" href="cart.jsp">Cart</a>
                                <a class="dropdown-item" href="checkout.jsp">Checkout</a>
                            </div>
                        </li>
                                               <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Manage</a>
              <div class="dropdown-menu" aria-labelledby="dropdown04">
              	<a class="dropdown-item" href="settingcontrol">Manage Category</a>
              </div>
            </li>
                        <li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
                        <li class="nav-item"><a href="blog.jsp" class="nav-link">Blog</a></li>
                        <li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
                        <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>

                    </ul>
                </div>
            </div>
        </nav>
        <!-- END nav -->

        <div style="margin-top: 50px" class="container">



            <div style="display: inline-block; margin-left: 10px" >
                <form action="usercontrol" method="post">
                    <select name="user-role" id="user-role" >
                        <option value="">Select Roles</option>
                        <option value="all">All Roles</option>
                        <option value="Admin">Admin</option>
                        <option value="Artist">Artist</option>
                        <option value="Customer">Customer</option>
                    </select>
                </form>

            </div>

            <div style="display: inline-block; margin-left: 10px">
                <form action="usercontrol" method="post">
                    <select name="user-status" id="user-status" >
                        <option value="">Select Statuses</option>
                        <option value="all">All Statuses</option>
                        <option value="active">Active</option>
                        <option value="blocked">Blocked</option>

                    </select>
                </form>
            </div>


            <div style="display: inline-block; margin-left: 10px">
                <form action="searchuser" method="post" class="search-form">
                    <div class="search-container">
                        <input name="txt" type="text"  placeholder="Search">
                        <button type="submit"><i class="icon-search" style="color: white;"></i></button>
                    </div>
                </form>
            </div>

            <div style="display: inline-block; text-align: right; margin-left: 450px;">
                <a href="#addUser" data-toggle="modal" >
                    <i><img src="https://cdn-icons-png.flaticon.com/512/262/262038.png" alt="+" width="20" height="20"></i>
                    Add User
                </a>
            </div>



            <section class="ftco-section ftco-cart">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 ftco-animate">
                            <div class="cart-list">
                                <table class="table">
                                    <thead class="thead-primary">
                                        <tr class="text-center">
                                            <th style="width: 100px;">ID</th>
                                            <th style="width: 300px;">Name</th>
                                            <th style="width: 300px;">Email</th>
                                            <th style="width: 300px;">Mobile</th>
                                            <th style="width: 300px;">Role</th>
                                            <th style="width: 300px;">Status</th>
                                            <th style="width: 300px;">Action</th>

                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${listU}" var="o">

                                            <tr class="text-center">



                                                <td class="price"><span>${o.userID}</span></td>
                                                <td  class="price">${o.userName}</td>
                                                <td class="price">${o.email}</td>
                                                <td class="price">${o.phoneNumber}</td>
                                                <td class="price">
                                                    ${o.roleID == 1 ? 'Admin' : (o.roleID == 2 ? 'Artist' : (o.roleID == 3 ? 'Customer' : 'Unknown'))}
                                                </td>

                                                <td  class="price" style="color: ${o.status == 1 ? 'green' : 'red'}">${o.status == 1 ? 'Active' : 'Blocked'}</td>
                                                <td class="total">
                                                    <a href="#" data-toggle="modal" data-target="#editSetting">
                                                        <img src="https://media.istockphoto.com/id/1161405325/vi/vec-to/bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-b%C3%BAt-ch%C3%AC-%C4%91%C6%B0%E1%BB%A3c-c%C3%B4-l%E1%BA%ADp-theo-phong-c%C3%A1ch-ph%E1%BA%B3ng.jpg?s=612x612&w=0&k=20&c=f4xtI6U0w47PmSzYr12a9DflXkhTXr1qFCuJz-GMegk="
                                                             alt="Mô tả ảnh" width="20" height="20">
                                                    </a>


                                                    <button type="submit" style="background-color: white !important; color: ${o.status == 0 ? 'green' : 'red'} !important;">
                                                        ${o.status == 0 ? 'Active' : 'Block'}
                                                    </button>


                                                </td>


                                            </tr>



                                        </c:forEach>

                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </section>


<div id="addUser" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="adduser" method="post" onsubmit="return validateForm()">
                <div class="modal-header">						
                    <h4 class="modal-title">Add User</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">					
                    <div class="form-group">
                        <label>Name</label>
                        <input name="name" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input name="email" id="email" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input name="pass" id="pass" type="password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Confirm Password</label>
                        <input name="repass" id="repass" type="password" class="form-control" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Phone number</label>
                        <input name="phone" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Role</label>
                        <select name="role" class="form-select" aria-label="Default select example" required>
                            <option value="">-Select-</option>
                            <c:forEach items="${listR}" var="r">
                                        <option value="${r.id}">${r.name}</option>
                                    </c:forEach>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Add">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
function validateForm() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("pass").value;
    var confirmPassword = document.getElementById("repass").value;

    // Check if email is valid using a simple regular expression
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }

    // Check if the password length is greater than or equal to 8 characters
    if (password.length < 8) {
        alert("Password must be at least 8 characters long.");
        return false;
    }

    // Check if the password and confirm password match
    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }



    return true; // Form submission will proceed if all checks pass
}
</script>
<script>
 var emailExists = <%= session.getAttribute("emailExists") %>;

if (emailExists === true) {
    alert("Email already exists.");
   
}
</script>
<%-- Include this code in your JSP or HTML page --%>



            <section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">
                <div class="container py-4">
                    <div class="row d-flex justify-content-center py-5">
                        <div class="col-md-6">
                            <h2 style="font-size: 22px;" class="mb-0">Subcribe to our Newsletter</h2>
                            <span>Get e-mail updates about our latest shops and special offers</span>
                        </div>
                        <div class="col-md-6 d-flex align-items-center">
                            <form action="#" class="subscribe-form">
                                <div class="form-group d-flex">
                                    <input type="text" class="form-control" placeholder="Enter email address">
                                    <input type="submit" value="Subscribe" class="submit px-3">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
            <footer class="ftco-footer ftco-section">
                <div class="container">
                    <div class="row">
                        <div class="mouse">
                            <a href="#" class="mouse-icon">
                                <div class="mouse-wheel"><span class="ion-ios-arrow-up"></span></div>
                            </a>
                        </div>
                    </div>
                    <div class="row mb-5">
                        <div class="col-md">
                            <div class="ftco-footer-widget mb-4">
                                <h2 class="ftco-heading-2">Pallette Joy</h2>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia.</p>
                                <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                                    <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                                    <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                                    <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="ftco-footer-widget mb-4 ml-md-5">
                                <h2 class="ftco-heading-2">Menu</h2>
                                <ul class="list-unstyled">
                                    <li><a href="#" class="py-2 d-block">Shop</a></li>
                                    <li><a href="#" class="py-2 d-block">About</a></li>
                                    <li><a href="#" class="py-2 d-block">Journal</a></li>
                                    <li><a href="#" class="py-2 d-block">Contact Us</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="ftco-footer-widget mb-4">
                                <h2 class="ftco-heading-2">Help</h2>
                                <div class="d-flex">
                                    <ul class="list-unstyled mr-l-5 pr-l-3 mr-4">
                                        <li><a href="#" class="py-2 d-block">Shipping Information</a></li>
                                        <li><a href="#" class="py-2 d-block">Returns &amp; Exchange</a></li>
                                        <li><a href="#" class="py-2 d-block">Terms &amp; Conditions</a></li>
                                        <li><a href="#" class="py-2 d-block">Privacy Policy</a></li>
                                    </ul>
                                    <ul class="list-unstyled">
                                        <li><a href="#" class="py-2 d-block">FAQs</a></li>
                                        <li><a href="#" class="py-2 d-block">Contact</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="ftco-footer-widget mb-4">
                                <h2 class="ftco-heading-2">Have a Questions?</h2>
                                <div class="block-23 mb-3">
                                    <ul>
                                        <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
                                        <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
                                        <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 text-center">

                            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            </p>
                        </div>
                    </div>
                </div>
            </footer>



            <!-- loader -->
            <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
            <script>

                document.getElementById("user-role").onchange = function () {
                    this.form.submit();
                };

                document.getElementById("user-status").onchange = function () {
                    this.form.submit();
                };
            </script>



            <script src="js/jquery.min.js"></script>
            <script src="js/jquery-migrate-3.0.1.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.easing.1.3.js"></script>
            <script src="js/jquery.waypoints.min.js"></script>
            <script src="js/jquery.stellar.min.js"></script>
            <script src="js/owl.carousel.min.js"></script>
            <script src="js/jquery.magnific-popup.min.js"></script>
            <script src="js/aos.js"></script>
            <script src="js/jquery.animateNumber.min.js"></script>
            <script src="js/bootstrap-datepicker.js"></script>
            <script src="js/scrollax.min.js"></script>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
            <script src="js/google-map.js"></script>
            <script src="js/main.js"></script>



    </body>
</html>