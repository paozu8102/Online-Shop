<%-- 
    Document   : post-management
    Created on : Oct 5, 2023, 9:34:49 AM
    Author     : Mai Vu Bao
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

    <head>
        <%
  String path = request.getContextPath();
        %>
        <script>
            //from 11-31: ThanhNX
            // Function to set the scroll position in session storage
            function saveScrollPosition() {
                sessionStorage.setItem('scrollPosition', window.scrollY);
            }

            // Function to retrieve and restore the scroll position
            function restoreScrollPosition() {
                var scrollPosition = sessionStorage.getItem('scrollPosition');
                if (scrollPosition) {
                    window.scrollTo(0, scrollPosition);
                }
            }

            // Execute this function when the page is loaded
            window.addEventListener('load', function () {
                restoreScrollPosition();
            });

            // Execute this function when navigating to other pages
            function navigateToOtherPage() {
                // Save the current scroll position before navigating
                saveScrollPosition();

        </script>
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



            .sorting {
                display: flex; /* Use flexbox to align items in a row */
                align-items: center; /* Vertically center items in the flex container */
                gap: 20px; /* Space between the two forms */
            }

            /* Style the select elements */
            form select {
                padding: 10px;
                border: 1px solid #ccc;
                background-color: #fff;
                border-radius: 5px;
                outline: none;
            }

            /* Style the select arrow */
            form select::after {
                content: "\25BC"; /* Unicode down arrow character */
                position: absolute;
                top: 50%;
                right: 10px;
                transform: translateY(-50%);
                pointer-events: none;
            }
            .url::-webkit-scrollbar {
                display: none;
            }

            .active {
                border-bottom: 1px solid #00aff0;
            }

        </style>
        <title>Palette Joy - Bring art to your home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords"
              content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
        <meta name="description"
              content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
        <meta name="robots" content="noindex,nofollow">
        <title>Manage Product</title>
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
    <script src="js/jquery.min.js"></script>
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
                        <a class="navbar-brand" href="admin" style="text-decoration: none; font-size: 19px; font-weight: bold; color: #333; font-family: Arial, sans-serif;">Admin Dashboard</a>
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
                            <h4 class="page-title">Setting Management</h4>
                        </div>
                        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
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
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="white-box">

                                <ul class="product-category">







                                    <div>
                                        <form action="settingcontrol" method="get" style="display: inline-block; margin-right: 10px;">
                                            <select name="settingtype" id="setting_type" style="color: #82ae46;">
                                                <option value="" style="color: #82ae46;">Select Setting Types</option>
                                                <option value="all" style="color: #82ae46;" ${param.settingtype == 'all' ? 'selected' : ''}>All Setting Types</option>
                                                <option value="category" style="color: #82ae46;" ${param.settingtype == 'category' ? 'selected' : ''}>Product Category</option>
                                                <option value="type" style="color: #82ae46;" ${param.settingtype == 'type' ? 'selected' : ''}>Product Topic</option>
                                                <option value="post" style="color: #82ae46;" ${param.settingtype == 'post' ? 'selected' : ''}>Post</option>
                                            </select>
                                        </form>


                                        <form action="settingcontrol" method="get" style="display: inline-block; margin-right: 10px;">
                                            <select name="settingstatus" id="setting_status" style="color: #82ae46;">
                                                <option value="" style="color: #82ae46;">Select Statuses</option>
                                                <option value="all" style="color: #82ae46;" ${param.settingstatus == 'all' ? 'selected' : ''}>All Statuses</option>
                                                <option value="active" style="color: #82ae46;" ${param.settingstatus == 'active' ? 'selected' : ''}>Active</option>
                                                <option value="inactive" style="color: #82ae46;" ${param.settingstatus == 'inactive' ? 'selected' : ''}>Inactive</option>
                                            </select>
                                        </form>

                                        <form action="settingcontrol" method="post" style="display: inline-block; margin-right: 10px;">
                                            <select name="sortname" id="sortname" style="color: #82ae46;">
                                                <option value="" style="color: #82ae46;">Sort By Name</option>
                                                <option value="asc" style="color: #82ae46;" ${param.sortname == 'asc' ? 'selected' : ''}>A -> Z</option>
                                                <option value="desc" style="color: #82ae46;" ${param.sortname == 'desc' ? 'selected' : ''}>Z -> A</option>
                                            </select>
                                        </form>

                                        <div style="display: inline-block; margin-right: 10px;">
                                            <form action="searchsetting" method="post" class="search-form">
                                                <div class="search-container">
                                                    <input name="txt" type="text" placeholder="Search">
                                                    <button type="submit"><i class="icon-search" style="color: white;"></i></button>
                                                </div>
                                            </form>
                                        </div>

                                        <div style="display: inline-block; margin-left: 400px;">
                                            <a href="#addSetting" data-toggle="modal">
                                                <i><img src="https://cdn-icons-png.flaticon.com/512/262/262038.png" alt="+" width="20" height="20"></i>
                                                Add Setting
                                            </a>
                                        </div>
                                    </div>

                                </ul>
                                <div class="table-responsive">
                                    <table class="table text-nowrap">
                                        <thead>
                                            <tr>
                                                <th style="width: 100px;">ID</th>
                                                <th style="width: 300px;">Setting Type</th>
                                                <th style="width: 300px;">Name</th>
                                                <th style="width: 300px;">Status</th>
                                                <th style="width: 300px;">Action</th>

                                            </tr>
                                        </thead>


                                        <c:forEach items="${listC}" var="o" varStatus="loop" >
                                            <tbody>
                                            <form id="settingStatusForm${loop.index}" action="settingstatus" method="post">
                                                <tr class="text-center">

                                                <input type="hidden" name="id" value="${o.id}">
                                                <input type="hidden" name="settingtype" value="${o.settingtype}">
                                                <input type="hidden" name="status" value="${o.status}">
                                                <td class="price"><span>${o.id}</span></td>
                                                <td  class="price">${o.settingtype}</td>
                                                <td class="price">${o.name}</td>
                                                <td  class="price" style="color: ${o.status == 1 ? 'green' : 'red'}">${o.status == 1 ? 'Active' : 'Inactive'}</td>
                                                <td class="total">
                                                    <a href="#" data-toggle="modal" data-target="#editSetting" data-id="${o.id}" data-name="${o.name}" data-type="${o.settingtype}" data-status="${o.status}" data-description="${o.description}">
                                                        <img src="https://media.istockphoto.com/id/1161405325/vi/vec-to/bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-b%C3%BAt-ch%C3%AC-%C4%91%C6%B0%E1%BB%A3c-c%C3%B4-l%E1%BA%ADp-theo-phong-c%C3%A1ch-ph%E1%BA%B3ng.jpg?s=612x612&w=0&k=20&c=f4xtI6U0w47PmSzYr12a9DflXkhTXr1qFCuJz-GMegk="
                                                             alt="Mô tả ảnh" width="20" height="20">
                                                    </a>


                                                    <button type="button" style="background-color: white !important; color: ${o.status == 0 ? 'green' : 'red'} !important;" onclick="confirmSettingStatus(${loop.index})">
                                                        ${o.status == 0 ? 'Active' : 'Deactive'}
                                                    </button>


                                                </td>


                                                </tr>

                                            </form>
                                            </tbody>
                                        </c:forEach>

                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row mt-5">
                        <div class="col text-center">
                            <div class="block-27">
                                <ul>
                                    <c:if test="${tag > 1}">
                                        <li><a href="settingcontrol?index=${tag-1}"><i class="ion-ios-arrow-back"></i></a></li>
                                            </c:if>

                                    <c:forEach begin="1" end="${endP}" var="i">
                                        <li class="${tag == i ? 'active' : ''}">
                                            <a href="settingcontrol?index=${i}">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${tag < endP}">
                                        <li><a href="settingcontrol?index=${tag+1}"><i class="ion-ios-arrow-forward"></i></a></li>
                                            </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <div id="addSetting" class="modal fade" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="addsetting" method="post">
                                    <div class="modal-header">						
                                        <h4 class="modal-title">Add Setting</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">					
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input name="name" type="text" class="form-control" required>
                                        </div>
                                        <div class="form-group" >

                                            <label>Setting Type</label>

                                            <select name="settingtype" class="form-select" aria-label="Default select example" required>
                                                <option value="">-Select-</option>

                                                <option value="3">Product Topic</option>
                                                <option value="1">Product Material</option>
                                                <option value="2">Post</option>
                                            </select>
                                            <div class="form-group">
                                                <label>Description</label>
                                                <textarea name="description" class="form-control" required></textarea>
                                            </div>
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


                    <div id="editSetting" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="editsetting" method="post">
                                    <div class="modal-header">						
                                        <h4 class="modal-title">Setting Detail</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">	
                                        <div class="form-group">
                                            <label>ID</label>
                                            <input name="id" type="text" class="form-control" required readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input name="name" type="text" class="form-control" required readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>Setting Type</label>
                                            <input name="settingtype" type="text" class="form-control" required readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>Status</label>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="status" id="Active" value="Active" >
                                                <label class="form-check-label" for="Active">
                                                    Active
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="status" id="Inactive" value="Inactive" >
                                                <label class="form-check-label" for="Inactive">
                                                    Inactive
                                                </label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea name="description" class="form-control" required></textarea>
                                        </div>
                                        <div class="modal-footer">
                                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                            <input type="submit" class="btn btn-success" value="Edit">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


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



        <script>

                document.getElementById("setting_type").onchange = function () {
                    this.form.submit();
                };

                document.getElementById("setting_status").onchange = function () {
                    this.form.submit();
                };

                document.getElementById("sortname").onchange = function () {
                    this.form.submit();
                }
        </script>
        <script>
                // Sử dụng jQuery để xử lý sự kiện click trên liên kết
                $('a[data-target="#editSetting"]').click(function () {
                    // Lấy giá trị từ thuộc tính data
                    var id = $(this).data('id');
                    var name = $(this).data('name');
                    var type = $(this).data('type');
                    var status = $(this).data('status');
                    var description = $(this).data('description');
                    // Đặt giá trị vào các trường input trong form
                    $('#editSetting input[name="id"]').val(id);
                    $('#editSetting input[name="name"]').val(name);
                    $('#editSetting input[name="settingtype"]').val(type);
                    $('#editSetting textarea[name="description"]').val(description);
                    // Thiết lập giá trị của radio buttons "Active" hoặc "Inactive" dựa trên giá trị của status
                    if (status === 1) {
                        $('#Active').prop('checked', true);
                        $('#Inactive').prop('checked', false);
                    } else {
                        $('#Active').prop('checked', false);
                        $('#Inactive').prop('checked', true);
                    }
                });
        </script>

  
        
        <script>
                // When the modal is shown, add the 'modal-open' class to the body
                $('#editSetting').on('show.bs.modal', function () {
                    $('body').addClass('modal-open');
                });

                // When the modal is hidden, remove the 'modal-open' class from the body
                $('#editSetting').on('hidden.bs.modal', function () {
                    $('body').removeClass('modal-open');
                });

                $('#addSetting').on('show.bs.modal', function () {
                    $('body').addClass('modal-open');
                });

                // When the modal is hidden, remove the 'modal-open' class from the body
                $('#addSetting').on('hidden.bs.modal', function () {
                    $('body').removeClass('modal-open');
                }
                );

        </script>
        
   <script>
    function confirmSettingStatus(index) {
        var result = confirm("Are you sure you want to block/unblock this Category?");
        if (!result) {
            return false;  // Cancel the form submission if the user clicks 'Cancel'
        }

        document.getElementById("settingStatusForm" + index).submit();
    }
</script>


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
