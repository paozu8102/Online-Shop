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
        <!-- Include Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

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
            #avatar {
                border-radius: 50%;
                width: 10rem;
                height: 10rem;
                object-fit: cover;
            }

        </style>
        <%@include file="template/header.jsp" %>
    </head>
 <c:if test="${sessionScope.acc.roleID==null}">
    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item"><a href="shop" class="nav-link">Shop</a></li>

    <li class="nav-item"><a href="Posts" class="nav-link">Post</a></li>

    <li class="nav-item"><a href="login" class="nav-link">Sign In</a></li>

</c:if>
<c:if test="${sessionScope.acc.roleID==3}">
    <li class="nav-item"><a href="home" class="nav-link">Home</a></li>

    <li class="nav-item"><a href="shop" class="nav-link">Shop</a></li>


    <li class="nav-item"><a href="Posts" class="nav-link">Post</a></li>

    <li class="nav-item active"><a href="UserProfile" class="nav-link">Profile</a></li>
    <li class="nav-item"><a href="myorder" class="nav-link">My Order</a></li>
    <li class="nav-item"><a href="logout" class="nav-link">Sign Out</a></li> 

</c:if>

<li class="nav-item cta cta-colored"><a href="cart" class="nav-link"><span class="icon-shopping_cart"></span>[${cartlist.size()}]</a></li>
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
                    <button class="btn btn-primary" type="button" id="fileButton" data-toggle="tooltip" data-placement="top" title="Upload your image">Upload new image</button>
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
                            <input class="form-control" id="inputUsername" name="username" type="text" oninput="checkLength()" pattern="^(?:[A-Z][a-zA-Z\s]*){1,50}$" required title="Cannot null, Begin word with upper character, length between 0 -50" placeholder="Enter your username" value="${user.getUserName()}">
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
                            <input class="form-control" name="phonenumber" id="inputLastName" type="text" required="" pattern="[0-9]{10}" title="Please enter 10 digits with no spaces." placeholder="Enter your Phone Number" placeholder="Enter your Phone Number" value="${user.getPhoneNumber()}">
                        </div>
                        <!-- Form Group (organization name)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputOrgName">Address</label>
                            <input class="form-control" name="address" id="inputOrgName" required  type="text" placeholder="Enter your Address" value="${user.getAddress()}">
                        </div>
                        <!-- Form Group (email address)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputEmailAddress">Email address</label>
                            <input class="form-control" name="email" id="inputEmailAddress" readonly type="email" placeholder="Enter your email address" value="${user.getEmail()}">
                        </div>                           
                        <!-- Save changes button-->
                        <button class="btn btn-primary" type="submit" data-toggle="tooltip" data-placement="top" title="Save all">Save changes</button>
                        <a class="btn btn-primary" href="change-user-password.jsp" data-toggle="tooltip" data-placement="top" title="Change Password">Change Password</a>
                        <a class="btn btn-primary" href="<%=path%>/PostCreate" data-toggle="tooltip" data-placement="top" title="Create Post">Create A Post</a>
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

    // Function to handle image upload
    function uploadImage() {
        if (fileInput.files.length !== 0) {
            formData.append('imageFile', fileInput.files[0]);
            
            // Validate the selected image here
            const selectedFile = fileInput.files[0];
            // Check if the selected file is an image (JPG or PNG)
            if (!/\.(jpg|png)$/i.test(selectedFile.name)) {
                alert('Please select a valid image file (JPG or PNG).');
                return;
            }
            // Check if the file size is within the allowed limit (5MB)
            const maxSize = 5 * 1024 * 1024; // 5MB in bytes
            if (selectedFile.size > maxSize) {
                alert('The selected image file is too large. Please choose a smaller image.');
                return;
            }
            // Perform the image upload if it passes validation
            fetch(`/SWP391_SE1729_Group3/changeavatar`, {
                method: 'POST',
                body: formData
            });

            setTimeout(function () {
                if (formData.get('imageFile').name !== 'undefined') {
                    avatarImg.src = `images/avatar/` + formData.get('imageFile').name;
                    imageUpdateName.value = formData.get('imageFile').name;
                    formData.delete('imageFile');
                }
            }, 2000);
        }
    }

    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
</body>
</html>
