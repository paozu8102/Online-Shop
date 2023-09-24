<!DOCTYPE html>
<html lang="en">
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
            background-color: whitesmoke;
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
            background-color: #3498db;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 18px;
            transition: background-color 0.3s ease;
            margin-right: 10px; /* Kho?ng cách gi?a nút "Send Email" và "Cancel" */
        }
        input[type="submit"]:hover {
            background-color: #2980b9;
        }
        .cancel-button {
            padding: 15px 30px;
            background-color: #ccc;
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
</head>

<body>
    <div class="image-container">
        <img src="images/forgot2.jpg" alt="Image Description">
    </div>
    <div class="container">
        <h2>Forgot Password</h2>
        <form method="post" action="forgot-password?service=submitEmail">
            <label for="email">Enter your email:</label>
            <b>${mess}</b>
            <input type="text" id="email" required="" name="email" value="${emailAddress}"><br><br>
            <input type="submit" value="Send Email">
            <a href="login.jsp" class="cancel-button">Cancel</a>
        </form>
    </div>
</body>
</html>
