<%-- 
    Document   : admin-profile
    Created on : Oct 5, 2023, 9:35:12 AM
    Author     : Mai Vu Bao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <title>Saler Dashboard</title>
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
            #myBtn {
                display: none;
                position: fixed;
                bottom: 20px;
                left: 20px;
                z-index: 99;
                font-size: 18px;
                border: none;
                outline: none;
                background-color: #82AE46;
                color: white;
                cursor: pointer;
                padding: 10px 15px;
                border-radius: 50px;
            }

            #myBtn:hover {
                background-color: #555;
            }


            .slider-container {
                width: 80%;
                margin: 0 auto;
                overflow: hidden;
                position: relative;
                margin-top:  10px;
            }

            .slider {
                width: 100%;
            }

            .slide {
                display: none;
            }

            .slide img {
                max-width: 100%;
                height: auto;
            }

            .thumbnails {
                text-align: center;
                margin-top: 10px;
            }

            .thumbnails img {
                max-width: 80px;
                height: auto;
                margin: 0 10px;
                cursor: pointer;
                transition: opacity 0.3s;
            }

            .thumbnails img:hover {
                opacity: 0.7;
            }

            /* Định dạng nút điều hướng */
            .prev-button,
            .next-button {
                position: absolute;
                top: 50%;
                padding: 5px; /* Kích thước nút nhỏ hơn */

                color: #fff;
                border: none;
                cursor: pointer;
                font-size: 20px; /* Kích thước chữ */
                transition: background-color 0.3s; /* Hiệu ứng hover */
                z-index: 1; /* Đảm bảo nút điều hướng nằm trên ảnh */
            }

            .prev-button {
                left: 10px;
                transform: translateY(-50%);
            }

            .next-button {
                right: 10px;
                transform: translateY(-50%);
            }

            /* Hiệu ứng hover */
            .prev-button:hover,
            .next-button:hover {
                color: white; /* Màu xám */
                opacity: 1; /* Độ mờ tối thiểu */
                background-color: gray;

            }

            /* CSS for increasing spacing between <span> elements */
            .billing-form p span {
                margin-right: 10px; /* You can adjust the value as needed */
            }

            .close-button {
                position: absolute;
                top: 10px;
                right: 10px;
                font-size: 20px;
                cursor: pointer;
                z-index: 1;
                color: white;
                background: rgba(0, 0, 0, 0.5);
                border-radius: 50%;
                padding: 5px;
            }


        </style>
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

        <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
        <!-- Custom CSS -->
        <link href="css/style.min.css" rel="stylesheet">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </head>

    <body>
        <!-- ============================================================== 
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
                <nav class="navbar top-navbar navbar-expand-md navbar-dark" style="background-color: #82ae46;">
                    <div class="navbar-header" data-logobg="skin6">
                        <!-- ============================================================== -->
                        <!-- Logo -->
                        <!-- ============================================================== -->
                        <a class="navbar-brand" href="statistic" style="text-decoration: none; font-size: 19px; font-weight: bold; color: #333; font-family: Arial, sans-serif;">Saler Dashboard</a>
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
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="statistic" aria-expanded="false">
                                    <i class="fa fa-chart-bar" aria-hidden="true"></i>
                                    <span class="hide-menu">Statistic</span>
                                </a>
                            </li>

                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="salerprofile"
                                   aria-expanded="false">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    <span class="hide-menu">Profile</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="manage"
                                   aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Product Management</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="manageorder" aria-expanded="false">
                                    <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                    <span class="hide-menu">Order Management</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="javascript:history.back()" aria-expanded="false">
                                    <i class="fa fa-arrow-left" aria-hidden="true"></i>
                                    <span class="hide-menu">Return</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="home" aria-expanded="false">
                                    <i class="fa fa-home" aria-hidden="true"></i>
                                    <span class="hide-menu">Return Home</span>
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
                            <h4 class="page-title">Product ${detail.productID} Detail</h4>
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
                        <div class="col-lg-3 col-xlg-5 col-md-12 text-center">
                            <div class="white-box">


                                <div class="slider-container">
                                    <div class="slider">
                                        <c:forEach items="${listI}" var="i">
                                            <div class="slide">
                                                <img src="images/${i.imageUrl}">
                                                <a href="javascript:void(0);" class="delete-button"
                                                   data-imageurl="${i.imageUrl}"
                                                   data-productid="${detail.productID}"
                                                   data-imageid="${i.imageID}">
                                                    <span class="close-button">×</span>
                                                </a>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="thumbnails">
                                        <c:forEach items="${listI}" var="i">
                                            <img src="images/${i.imageUrl}">
                                        </c:forEach>
                                    </div>
                                </div>



                                <button class="prev-button">&#9668;</button>
                                <button class="next-button">&#9658;</button>


                            </div>
                            <div style="display: inline-block;">
                                <a href="#addImage" data-toggle="modal">
                                    <i><img src="https://cdn-icons-png.flaticon.com/512/262/262038.png" alt="+" width="20" height="20"></i>
                                    Add Image
                                </a>
                            </div>
                        </div>
                        <!-- Column -->
                        <!-- Column -->
                        <div class="col-lg-8 col-xlg-7 col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="editproduct" method="POST" class="form-horizontal form-material">
                                        <input type="hidden" value="${detail.productID}" name="id"/>

                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0">Product Name</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input class="form-control" name="name"  required type="text"  value="${detail.productName}">   
                                            </div>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0">Price</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input class="form-control" name="price"  required type="text"  value="${detail.price}"> 
                                            </div>
                                        </div>

                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0">Description</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <textarea class="form-control" name="description"  required >${detail.description}</textarea>
                                            </div>
                                        </div>

                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0" >Height</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input class="form-control" name="height"  required type="text"  value="${detail.height}"> 
                                            </div>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0">Width</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input class="form-control" name="width"  required type="text"  value="${detail.width}"> 

                                            </div>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label class="col-md-12 p-0">Quantity</label>
                                            <div class="col-md-12 border-bottom p-0">
                                                <input class="form-control" name="quantity"  required type="text"  value="${detail.quantity}"> 
                                                <div class="form-group mb-4">
                                                    <label class="col-md-12 p-0">Discount</label>
                                                    <div class="col-md-12 border-bottom p-0">
                                                        <input class="form-control" name="discount"  required type="text"  value="${detail.discount}"> 

                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="small mb-1" for="inputGender">Status</label>
                                                    <div style="display: flex; justify-content: space-between;">
                                                        <div style="flex: 1;">
                                                            <input type="radio" name="status" value="1" ${(detail.status == 1) ? 'checked' : ''}>
                                                            <label for="active">Active</label>
                                                        </div>
                                                        <div style="flex: 1;">
                                                            <input type="radio"  name="status" value="0"  ${(detail.status == 1) ? '' : 'checked'}> 
                                                            <label for="inactive">Inactive</label>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <button class="btn btn-primary" type="submit" data-toggle="tooltip" data-placement="top" title="Save all">Save changes</button>
                                    </form>
                                </div>
                            </div>
                        </div>




                        <!-- ============================================================== -->
                        <!-- Start Page Content -->
                        <!-- ============================================================== -->
                        <!-- Row -->


                    </div>

                    <!-- Column -->
                </div>

                <div id="addImage" class="modal fade" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form id="productForm" action="editproduct?addimage=1" method="post" enctype="multipart/form-data" >


                                <div class="modal-header">						
                                    <h4 class="modal-title">Add Image</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>

                                <div class="modal-body">	
                                    <input type="hidden" value="${detail.productID}" name="id"/>
                                    <div class="form-group1">
                                        <label>Image</label>
                                        <div id="imagePreview" class="image-container"></div>
                                        <input type="file" name="image" id="fileInput" multiple required>
                                    </div>
                                    <button type="button" class="btn btn-success" id="addImageButton" style="margin-top: 10px;">More Image</button>

                                    <script>
                                        document.getElementById('addImageButton').addEventListener('click', function () {
                                            var input = document.createElement('input');
                                            input.type = 'file';
                                            input.name = 'image';
                                            input.multiple = true;
                                            input.style.marginTop = '10px';
                                            input.style.marginLeft = '45px';
                                            input.required = true;
                                            // Tạo nút xóa tương ứng với mỗi input
                                            var deleteButton = document.createElement('button');
                                            deleteButton.type = 'button';
                                            deleteButton.textContent = 'Delete';
                                            deleteButton.addEventListener('click', function () {
                                                // Xóa input và nút xóa khi nút xóa được nhấn
                                                input.remove();
                                                deleteButton.remove();
                                            });

                                            var formGroup1 = document.querySelector('.form-group1'); // Lấy phần tử .form-group1
                                            formGroup1.appendChild(input); // Thêm input vào .form-group1
                                            formGroup1.appendChild(deleteButton); // Thêm nút xóa vào .form-group1
                                        });
                                    </script>





                                    <style>
                                        .image-container {
                                            position: relative;
                                            display: inline-block;
                                        }

                                        .image-container img {
                                            max-width: 150px;
                                            max-height: 150px;
                                        }

                                        .image-container .delete-icon {
                                            position: absolute;
                                            top: 5px;
                                            right: 5px;
                                            color: white;
                                            background-color: black; /* Màu nền đen của biểu tượng "X" */
                                            border: none; /* Loại bỏ viền */
                                            border-radius: 50%; /* Để biểu tượng "X" trở thành hình tròn */
                                            cursor: pointer;
                                            width: 25px;
                                            height: 25px;
                                            text-align: center;
                                            line-height: 25px;
                                        }
                                    </style>



                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-success" value="Add">
                                </div>
                        </div>
                        </form>
                    </div>
                </div>



            </div><!-- comment -->
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
<script>

    document.addEventListener("DOMContentLoaded", function () {
        const slides = document.querySelectorAll(".slide");
        const thumbnails = document.querySelectorAll(".thumbnails img");
        const prevButton = document.querySelector(".prev-button");
        const nextButton = document.querySelector(".next-button");

        let currentSlide = 0;

// Hiển thị slide đầu tiên
        slides[currentSlide].style.display = "block";

// Xử lý khi nhấp vào các ảnh nhỏ
        thumbnails.forEach(function (thumbnail, index) {
            thumbnail.addEventListener("click", function () {
                slides[currentSlide].style.display = "none";
                currentSlide = index;
                slides[currentSlide].style.display = "block";

// Tắt tất cả các ảnh nhỏ khỏi trạng thái tối màu
                thumbnails.forEach(function (thumb) {
                    thumb.style.opacity = 1;
                });

// Đặt ảnh nhỏ tương ứng vào trạng thái tối màu
                thumbnail.style.opacity = 0.7;
            });
        });

// Xử lý nút "Previous"
        prevButton.addEventListener("click", function () {
            slides[currentSlide].style.display = "none";
            currentSlide = (currentSlide - 1 + slides.length) % slides.length;
            slides[currentSlide].style.display = "block";

// Tắt tất cả các ảnh nhỏ khỏi trạng thái tối màu
            thumbnails.forEach(function (thumb) {
                thumb.style.opacity = 1;
            });

// Đặt ảnh nhỏ tương ứng vào trạng thái tối màu
            thumbnails[currentSlide].style.opacity = 0.7;
        });

// Xử lý nút "Next"
        nextButton.addEventListener("click", function () {
            slides[currentSlide].style.display = "none";
            currentSlide = (currentSlide + 1) % slides.length;
            slides[currentSlide].style.display = "block";

// Tắt tất cả các ảnh nhỏ khỏi trạng thái tối màu
            thumbnails.forEach(function (thumb) {
                thumb.style.opacity = 1;
            });

// Đặt ảnh nhỏ tương ứng vào trạng thái tối màu
            thumbnails[currentSlide].style.opacity = 0.7;
        });

    });



</script>
<script>
    $('#addImage').on('show.bs.modal', function () {
        $('body').addClass('modal-open');
    });

    // When the modal is hidden, remove the 'modal-open' class from the body
    $('#addImage').on('hidden.bs.modal', function () {
        $('body').removeClass('modal-open');
    });
</script>
<script>
    $(document).ready(function () {
        // When an image is clicked, handle the selection
        $(".image-item").click(function () {
            // Remove the "selected" class from all images
            $(".image-item").removeClass("selected");

            // Add the "selected" class to the clicked image
            $(this).addClass("selected");

            // Get the index of the selected image
            var selectedIndex = $(this).data("index");

            // You can now use the selectedIndex for any further actions, e.g., displaying additional information, etc.
            // Example:
            console.log("Selected image index: " + selectedIndex);
        });
    });
</script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const deleteButtons = document.querySelectorAll(".delete-button");
        deleteButtons.forEach(button => {
            button.addEventListener("click", function () {
                const imageUrl = this.getAttribute("data-imageurl");
                const productID = this.getAttribute("data-productid");
                const imageID = this.getAttribute("data-imageid");

                const confirmDelete = confirm("Are you sure you want to delete this image?");
                if (confirmDelete) {
                    // If the user confirms, navigate to the delete URL.
                    window.location.href = 'deleteimage?url=' + imageUrl + '&pid=' + productID + '&id=' + imageID;
                }
            });
        });
    });
</script>




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
</body>

</html>
