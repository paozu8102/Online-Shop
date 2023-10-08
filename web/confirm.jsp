<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>CAPCHA PAGE</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/q-icon.png" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/stylesFogetPassword.css" rel="stylesheet" />
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
    <script type="text/javascript">
        (function () {
            emailjs.init("QQ6aqdQzML6B_pxqM");
        })();
    </script>
    <script>
        let param = {
            to: '${emailAddress}',
            link: 'http://localhost:${port}/SWP391_SE1729_Group3/forgot-password?service=changePassword&&email=${emailAddress}&&createTime=${milis}'
        }

        emailjs.send('service_w9tl3fh', 'template_b6skueg', param)
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 400px;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
            background-color: #f5f5f5;
            text-align: center;
            position: relative;
            z-index: 1; /* ??m b?o form n?m phía trên ?nh */
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }
        input[type="text"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 3px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        input[type="submit"] {
            padding: 15px 30px;
            background-color: #fff;
            color: black;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s ease;
            margin-right: 10px; /* Kho?ng cách gi?a nút "Send Email" và "Cancel" */
        }
        input[type="submit"]:hover {
            background-color: #ccc;
        }
        .cancel-button {
            padding: 15px 30px;
            background-color: #333;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s ease;
        }
        .cancel-button:hover {
            background-color: #999;
        }
        .image-container {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 0; /* ?nh n?m phía sau form */
        }
        .image-container img {
            width: 100%;
            height: 100%;
            object-fit: cover; /* ??m b?o hình ?nh không b? méo */
        }
    </style>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>

<body>
    <div class="image-container">
        <img src="images/cf.jpg" alt="Image Description">
    </div>
    <div class="container">
        <h2>CAPCHA</h2>
        <span style="color: red">${mess}</span>
        <c:set var="cf" scope="session" value="${sessionScope.confirm}"/>
        <c:if test="${cf == 'sigup'}">
        <form method="post" action="signupwithemail">
            </c:if>
            <label for="email">Enter your capcha:</label>
            <input type="text" name="capchain" placeholder="Your capcha" value="${param.capchain}" required="">
            <input type="submit" value="Confirm">
            <a href="signupWithEmail.jsp" class="cancel-button">Cancel</a>
        </form>
    </div>
</body>
</html>
