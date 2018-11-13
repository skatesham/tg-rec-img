<%-- 
    Document   : login
    Created on : Nov 17, 2017, 4:55:25 PM
    Author     : sham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login - RecImg</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
        <link href="${pageContext.request.contextPath}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <!--
    you can substitue the span of reauth email for a input with the email and
    include the remember me checkbox
        -->
        <div class="container">
            </br>
            <h2 class="text-center text-uppercase text-secondary mb-0 text-light">Reconhecimento de Padrão em Imagem</h2>
            <div class="card card-container">
                <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
                <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
                <p id="profile-name" class="profile-name-card">Login</p>
                <form method="post" action="${pageContext.request.contextPath}/Autenticar.action" class="form-signin">
                    <span id="reauth-email" class="reauth-email"></span>
                    <input type="text" id="inputEmail" class="form-control" placeholder="Usuario" name="usuario" pattern="([A-z0-9À-ž\s]){3,}" required autofocus>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Senha" name="senha" pattern="([A-z0-9À-ž\s]){3,}" required>
                    <%
                        if ((boolean) request.getAttribute("falhaAutenticacao")) {
                    %>
                    <div class="alert alert-danger text-center"> Usuario e/ou senha incorretos </div>
                    <%
                        }%>

                    <div id="remember" class="checkbox" hidden="true">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Entrar</button>
                </form><!-- /form -->
                <a href="#" class="forgot-password" hidden="true">
                    Forgot the password?
                </a>
                <form method="get" action="${pageContext.request.contextPath}/Registro" class="form-signin">
                    <input hidden="true" value="False" id="registro">
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Cadastrar</button>
                </form>
            </div><!-- /card-container -->

        </div><!-- /container -->

        <script src="${pageContext.request.contextPath}/static/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/js/functions.js" type="text/javascript"></script>
    </body>
</html>

