<%-- 
    Document   : admin-profile
    Created on : Oct 5, 2023, 9:35:12 AM
    Author     : Mai Vu Bao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords"
              content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
        <meta name="description"
              content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
        <meta name="robots" content="noindex,nofollow">
        <title>Admin Dashboard</title>
        <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
        <!-- Custom CSS -->
        <link href="css/style.min.css" rel="stylesheet">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    </head>

    <body>
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Main wrapper - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
             data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
            <!-- ============================================================== -->
            <!-- Topbar header - style you can find in pages.scss -->
            <!-- ============================================================== -->
            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin6">
                        <!-- ============================================================== -->
                        <!-- Logo -->
                        <!-- ============================================================== -->
                        <a class="navbar-brand" href="admin" style="text-decoration: none; font-size: 24px; font-weight: bold; color: #333; font-family: Arial, sans-serif;">Admin Dashboard</a>
                        <!-- ============================================================== -->
                        <!-- End Logo -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- toggle and nav items -->
                        <!-- ============================================================== -->
                        <a class="nav-toggler waves-effect waves-light text-dark d-block d-md-none"
                           href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                    </div>
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                        <ul class="navbar-nav d-none d-md-block d-lg-none">
                            <li class="nav-item">
                                <a class="nav-toggler nav-link waves-effect waves-light text-white"
                                   href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                            </li>
                        </ul>
                        <!-- ============================================================== -->
                        <!-- Right side toggle and nav items -->
                        <!-- ============================================================== -->
                        <ul class="navbar-nav ms-auto d-flex align-items-center">

                            <!-- ============================================================== -->
                            <!-- Search -->
                            <!-- ============================================================== -->
                            <li class=" in">
                                
                            </li>
                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                            <li>
                                <a class="profile-pic" href="admin-profile.jsp">
                                    <img src="${sessionScope.user.getAvatar()}" alt="user-img" width="36"
                                         class="img-circle"></a>
                                <a href="logout" style="padding-right: 20px"><span class="text-white font-medium">Sign Out</span></a>
                            </li>
                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- ============================================================== -->
            <!-- End Topbar header -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <aside class="left-sidebar" data-sidebarbg="skin6">
                <!-- Sidebar scroll-->
                <div class="scroll-sidebar">
                    <!-- Sidebar navigation-->
                    <nav class="sidebar-nav">
                        <ul id="sidebarnav">
                            <!-- User Profile-->
                            <li class="sidebar-item pt-2">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="admin"
                                   aria-expanded="false">
                                    <i class="far fa-clock" aria-hidden="true"></i>
                                    <span class="hide-menu">Statistic</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="admin-profile.jsp"
                                   aria-expanded="false">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    <span class="hide-menu">Profile</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="manageslide"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Slider Management</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="managepost"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Post Management</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="usercontrol"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">User Management</span>
                                </a>
                            </li>                           
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="settingcontrol"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Setting Management</span>
                                </a>    
                            </li>
                        </ul>

                    </nav>
                    <!-- End Sidebar navigation -->
                </div>
                <!-- End Sidebar scroll-->
            </aside>
            <!-- ============================================================== -->
            <!-- End Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Page wrapper  -->
            <!-- ============================================================== -->
            <div class="page-wrapper">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <div class="page-breadcrumb bg-white">
                    <div class="row align-items-center">
                        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                            <h4 class="page-title">Profile</h4>
                        </div>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Container fluid  -->
                <!-- ============================================================== -->
                <div class="container-fluid">
                    <!-- ============================================================== -->
                    <!-- Start Page Content -->
                    <!-- ============================================================== -->
                    <!-- Row -->
                    <div class="row">
                        <!-- Column -->
                        <div class="col-lg-4 col-xlg-3 col-md-12">
                            <div class="white-box">
                                <div class="user-bg"> <img width="100%" alt="user" src="plugins/images/large/img1.jpg">
                                    <div class="overlay-box">
                                        <div class="user-content">
                                            <a href="javascript:void(0)"><img id="avatar" src="${sessionScope.user.getAvatar()}"
                                                                              class="thumb-lg img-circle" alt="img"></a>
                                            <input type="file" id="fileInput" name="imageFile" onchange="uploadImage()" style="display: none;" />
                                        </div>
                                    </div>
                                </div>
                                <div class="user-btm-box mt-5 d-md-flex justify-content-center">
                                    <div class="form-group mb-4">
                                        <div class="col-sm-12">
                                            <button class="btn btn-success" id="fileButton">Update Avatar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Column -->
                        <!-- Column -->
                        <div class="col-lg-8 col-xlg-9 col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <form class="form-horizontal form-material" action="updateuser" enctype="multipart/form-data" method="post">
                                        <input type="hidden" value="${user.getUserID()}" name="id"/>
                                        <input type="hidden" value="admin" name="mode"/>
                                        <input type="hidden" id="imageName" value="${sessionScope.user.getAvatar().replace("images/avatar/","")}" name="imageName"/>
                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0">Username: </label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input type="text" placeholder=""
                                                       class="form-control p-0 border-0"
                                                       oninput="checkLength()" pattern="^(?:[A-Z][a-zA-Z\s]*){1,50}$" 
                                                       required 
                                                       name="username"
                                                       title="Cannot null, Begin word with upper character, length between 0 -50"
                                                       value="${sessionScope.user.getUserName()}"> </div>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="small mb-1" for="inputGender">Gender</label>
                                            <div style="display: flex; justify-content: space-between; margin-top: 20px">
                                                <div style="flex: 1;">
                                                    <input type="radio" id="male" name="gender" value="male" ${(sessionScope.user.getGender() == 1) ? 'checked' : ''}>
                                                    <label for="male">Male</label>
                                                </div>
                                                <div style="flex: 1;">
                                                    <input type="radio" id="female" name="gender" value="female"  ${(sessionScope.user.getGender() == 1) ? '' : 'checked'}> 
                                                    <label for="female">Female</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0">Phone No</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input type="text" placeholder=""
                                                       class="form-control p-0 border-0" 
                                                       pattern="[0-9]{10}" title="Please enter 10 digits with no spaces."
                                                       value="${sessionScope.user.getPhoneNumber()}"
                                                       name="phonenumber">
                                            </div>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label for="example-email" class="col-md-12 p-0">Address</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input type="text" placeholder=""
                                                       class="form-control p-0 border-0" 
                                                       id="example-email" value="${sessionScope.user.getAddress()}" name="address">
                                            </div>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0">Email</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input type="text" placeholder=""
                                                       class="form-control p-0 border-0" name="email" readonly value="${sessionScope.user.getEmail()}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <div class="form-group mb-4">
                                                    <button class="btn btn-success mx-1">Update Profile</button>
                                                    <a href="change-user-password.jsp" class="btn btn-success mx-1">Change Password</a>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- Column -->
                    </div>
                    <!-- Row -->
                    <!-- ============================================================== -->
                    <!-- End PAge Content -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- Right sidebar -->
                    <!-- ============================================================== -->
                    <!-- .right-sidebar -->
                    <!-- ============================================================== -->
                    <!-- End Right sidebar -->
                    <!-- ============================================================== -->
                </div>
                <!-- ============================================================== -->
                <!-- End Container fluid  -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- footer -->
                <!-- ============================================================== -->
                <footer class="footer text-center"> 2023 © Palette Joy Bring Art To You
                </footer>
                <!-- ============================================================== -->
                <!-- End footer -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Page wrapper  -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Wrapper -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- All Jquery -->
        <!-- ============================================================== -->
        <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap tether Core JavaScript -->
        <script src="bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/app-style-switcher.js"></script>
        <!--Wave Effects -->
        <script src="js/waves.js"></script>
        <!--Menu sidebar -->
        <script src="js/sidebarmenu.js"></script>
        <!--Custom JavaScript -->
        <script src="js/custom.js"></script>

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
        </script>
    </body>

</html>
