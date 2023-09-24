<%-- 
    Document   : UserProfile
    Created on : Sep 19, 2023, 4:29:15 PM
    Author     : Mai Vu Bao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <!-- Include Bootstrap JavaScript dependencies (jQuery, Popper.js, Bootstrap JS) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
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
            }
        </style>
        <%@include file="template/header.jsp" %>
    </head>

    <body>
        <ul>
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
            <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>

        </ul>
    </div>
</div>
</nav>
<!-- END nav -->
<div class="container-xl px-4 mt-4">
    <!-- Account page navigation-->
    <nav class="nav nav-borders">
        <div class="card-header" target="__blank">My Profile</div>
    </nav>
    <hr class="mt-0 mb-4">
    <div class="row">
        <div class="col-xl-4">
            <!-- Profile picture card-->
            <div class="card mb-4 mb-xl-0">
                <div class="card-header">Profile Picture</div>
                <div class="card-body text-center">
                    <!-- Profile picture image-->
                    <img id="avatar" class="img-account-profile rounded-circle mb-2" src="${user.getAvatar()}" alt="">
                    <!-- Profile picture help block-->
                    <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                    <!-- Profile picture upload button-->
                    <button class="btn btn-primary" type="button" id="fileButton">Upload new image</button>
                    <input type="file" id="fileInput" name="imageFile" onchange="uploadImage()" style="display: none;" />
                </div>
            </div>
        </div>
        <div class="col-xl-8">
            <!-- Account details card-->
            <div class="card mb-4">
                <div class="card-header">Account Details</div>
                <div class="card-body">
                    <form action="updateuser" enctype="multipart/form-data">
                        <input type="hidden" value="${user.getUserID()}" name="id"/>
                        <input type="hidden" id="imageName" value="${user.getAvatar().replace("images/avatar/","")}" name="imageName"/>
                        <!-- Form Group (username)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputUsername">Username</label>
                            <input class="form-control" id="inputUsername" name="username" type="text" placeholder="Enter your username" value="${user.getUserName()}">
                        </div>
                        <!-- Form Row-->
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (first name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="inputGender">Gender</label>
                                <div style="display: flex; justify-content: space-between;">
                                    <div style="flex: 1;">
                                        <input type="radio" id="male" name="gender" value="male" ${(user.getGender() == 1) ? 'checked' : ''}>
                                        <label for="male">Male</label>
                                    </div>
                                    <div style="flex: 1;">
                                        <input type="radio" id="female" name="gender" value="female"  ${(user.getGender() == 1) ? '' : 'checked'}> 
                                        <label for="female">Female</label>
                                    </div>
                                </div>
                            </div>
                            <!-- Form Group (last name)-->                               
                        </div>
                        <!-- Form Row        -->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputLastName">Phone Number</label>
                            <input class="form-control" name="phonenumber" id="inputLastName" type="text" placeholder="Enter your Phone Number" value="${user.getPhoneNumber()}">
                        </div>
                        <!-- Form Group (organization name)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputOrgName">Address</label>
                            <input class="form-control" name="address" id="inputOrgName" type="text" placeholder="Enter your Address" value="${user.getAddress()}">
                        </div>
                        <!-- Form Group (email address)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputEmailAddress">Email address</label>
                            <input class="form-control" name="email" id="inputEmailAddress" readonly type="email" placeholder="Enter your email address" value="${user.getEmail()}">
                        </div>                           
                        <!-- Save changes button-->
                        <button class="btn btn-primary" type="submit">Save changes</button>
                        <button class="btn btn-primary" type="button">Change Password</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    // Get references to the button and file input elements
    const fileButton = document.getElementById('fileButton');
    const fileInput = document.getElementById('fileInput');
    const avatarImg = document.getElementById('avatar');
    const formData = new FormData();
    const imageUpdateName = document.getElementById('imageName');
    // Add a click event listener to the button
    fileButton.addEventListener('click', function () {
        // Trigger a click event on the file input when the button is clicked
        fileInput.click();
    });
    function uploadImage() {
        if (fileInput.files.length !== 0) {
            formData.append('imageFile', fileInput.files[0]);
            fetch(`/SWP391_SE1729_Group3/changeavatar`, {
                method: 'POST',
                body: formData});
            setTimeout(function () {
                if (formData.get('imageFile').name !== 'undefined') {
                    
                    avatarImg.src = `images/avatar/` + formData.get('imageFile').name;
                    imageUpdateName.value = formData.get('imageFile').name;
                    formData.delete('imageFile');
                }
            }, 2000);
        }
    }
</script>
</body>
</html>
