<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Terms of Service</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            line-height: 1.6;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        h2 {
            font-size: 20px;
            margin-top: 30px;
            margin-bottom: 10px;
        }

        p {
            margin-bottom: 15px;
        }

        ol {
            margin-bottom: 15px;
        }

        .back-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Terms of Service</h1>

        <section>
            <h2>1. Acceptance of Terms</h2>
            <p>
                Welcome to Pallette Joy. These terms and conditions outline the rules and regulations for the use of our website.
            </p>
        </section>

        <section>
            <h2>2. Intellectual Property Rights</h2>
            <p>
                Our website and its original content, features, and functionality are owned by us and are protected by international copyright, trademark, patent, trade secret, and other intellectual property or proprietary rights laws.
            </p>
        </section>

        <section>
            <h2>3. User Accounts and Registration</h2>
            <p>
                If you create an account on our website, you are responsible for maintaining the security of your account and for any activities that occur under your account.
            </p>
        </section>

        <section>
            <h2>4. Limitations</h2>
            <p>
                In no event shall we be liable for any damages arising out of the use or inability to use our website.
            </p>
        </section>

        <section>
            <h2>5. Governing Law</h2>
            <p>
                These terms shall be governed and construed in accordance with the laws of [your country], without regard to its conflict of law provisions.
            </p>
        </section>

        <button class="back-button" onclick="goBack()">Go Back</button>
    </div>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
