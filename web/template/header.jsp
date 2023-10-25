<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="Model.Product" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  <%
    String path = request.getContextPath();
  %>
  <script>
    //from 11-31: ThanhNX
    // Function to set the scroll position in session storage
        function saveScrollPosition() {
            sessionStorage.setItem('scrollPositionHeader', window.scrollY);
        }

        // Function to retrieve and restore the scroll position
        function restoreScrollPosition() {
            var scrollPosition = sessionStorage.getItem('scrollPositionHeader');
            if (scrollPosition) {
                window.scrollTo(0, scrollPosition);
            }
        }

        // Execute this function when the page is loaded
        window.addEventListener('load', function() {
            restoreScrollPosition();
        });

        // Execute this function when navigating to other pages
        function navigateToOtherPage() {
            // Save the current scroll position before navigating
            saveScrollPosition();
        }
    </script>
<style>
    
body {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 20px;
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


.icon-addcart {
            color: #82ae46;
            display: block;
            margin-bottom: 40px;
        }
        
        .icon-addcart span {
            width: 200px;
            height: 200px;
            display: table;
            margin: 0 auto;
              border-radius: 50%;
              border: 2px solid #82ae46;
            background: lighten(#000, 98%);
            
          
        }
        
        .icon-addcart i {
            display: table-cell;
            vertical-align: middle;
            font-size: 100px;
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
  </head>
  <body class="goto-here">
      <button onclick="topFunction()" id="myBtn" title="Go to top">&#8679;</button>
      <script>
//from 91-107 ThanhNX
let mybutton = document.getElementById("myBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "block";
  } else {
    mybutton.style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}
</script>
		<div class="py-1 bg-primary">
    	<div class="container">
    		<div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
	    		<div class="col-lg-12 d-block">
		    		<div class="row d-flex">
		    			<div class="col-md pr-4 d-flex topper align-items-center">
					    	<div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
						    <span class="text">+0368686868</span>
					    </div>
					    <div class="col-md pr-4 d-flex topper align-items-center">
					    	<div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
						    <span class="text">Palettejoy@gmail.com</span>
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
	      <a class="navbar-brand" href="home">Palette Joy</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">