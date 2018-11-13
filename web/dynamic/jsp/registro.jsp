<%-- 
    Document   : login
    Created on : Nov 17, 2017, 4:55:25 PM
    Author     : sham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Cadastro - Recimg</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
        <link href="${pageContext.request.contextPath}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/static/js/functions.js" type="text/javascript"></script>
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
                <p id="profile-name" class="profile-name-card">Cadastramento</p>
                <form method="post" action="${pageContext.request.contextPath}/Cadastro" class="form-signin" onsubmit="return validar('inputPassword', 'confirmacao')">
                    <span id="reauth-email" class="reauth-email"></span>
                    <input type="text" id="inputNome" class="form-control" placeholder="Nome" name="nome" pattern="([A-z0-9À-ž\s]){3,}" required>
                    <input type="email" id="inputEmail" class="form-control" placeholder="Email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
                    <input type="text" id="inputUsuario" class="form-control" placeholder="Usuario" name="usuario" pattern="([A-z0-9À-ž\s]){3,}" required>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Senha" name="senha" pattern="([A-z0-9À-ž\s]){3,}" required>
                    <input type="password" id="confirmacao" class="form-control" placeholder="Confirmar Senha" name="confirmacaosenha" pattern="([A-z0-9À-ž\s]){3,}" required>
                    <div  hidden="true" id="alertSenha" class="alert alert-danger text-center">Confirmação de Senha Inválida</div>
                    <%
                        if ((boolean) request.getAttribute("falhaAutenticacao")) {
                    %>
                    <div class="alert alert-danger text-center"> Informações inseridas  não disponíveis </div>
                    <%
                        }%>

                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Registrar</button>
                </form><!-- /form -->
                <form method="get" action="${pageContext.request.contextPath}/" class="form-signin">
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Login</button>
                </form>
            </div><!-- /card-container -->

        </div><!-- /container -->
    </body>
</html>

