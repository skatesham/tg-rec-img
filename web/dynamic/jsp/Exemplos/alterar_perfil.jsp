<%-- 
    Document   : alterar_perfil
    Created on : Nov 23, 2017, 7:15:32 PM
    Author     : sham
--%>

<%@page import="api.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Alterar Perfil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/functions.js"></script>
    </head>
    <body>
        <h1 id="titulo_principal">Reconhecimento de Imagens</h1>
        <div class="content">
            <form id="menu" method="post">
                <input type="submit" value="Perfil" formaction="${pageContext.request.contextPath}/Perfil">
                <input type="submit" value="Minhas Imagens" formaction="${pageContext.request.contextPath}/MinhasImagens">
                <input type="submit" value="Sair" formaction="${pageContext.request.contextPath}/" >
            </form>      
            <h2 id="subtitulo_principal">Alterar Perfil</h2>
            <div id="sub_content">
                <form action="">
                    <img id="fotoPerfil"src="${pageContext.request.contextPath}/static/img/me.jpg" title="Sham Vinicius Fiorin"></br>
                    <input type="submit" value="Alterar Foto" formmethod="post" formaction="${pageContext.request.contextPath}/Perfil"></br>
                    <% Usuario u = (Usuario) request.getAttribute("usuarioLogado");%>
                </form>
                <p>Nome:</p> 
                <%if(u != null) { %>
                <h3 id="nome"> <%= u.getNome()%> </h3>
                <p>E-mail:</p>
                <h3> <%= u.getEmail()%> </h3>
                <%}%>
                <form method="post" action="${pageContext.request.contextPath}/AtualizacaoPerfil" onsubmit="return validar('senha', 'check');">
                    <p>Nome</p>
                    <input name="novoNome" class="formInput formTextInput" title="Insira novo nome"  pattern="([A-z0-9À-ž\s]){3,}"></br>
                    <p>Email</p>
                    <input name="novoEmail" class="formInput formTextInput" title="Insira novo Email" type="email" pattern=".{9,}"></br>
                    <p>Senha Atual</p> 
                    <input class="formInput formTextInput" name="senha" type="password" title="Insira sua senha" pattern="([A-z0-9À-ž\s]){3,}"></br>
                    <p>Nova Senha</p>
                    <input id="senha" class="formInput formTextInput" name="senhaCheck" type="password" title="Insira nova senha"  pattern="([A-z0-9À-ž\s]){3,}"></br>
                    <p>Confirme Nova Senha</p>
                    <input id="check" class="formInput formTextInput" name="senhaCheckConfirmation" type="password" title="Insira nova senha"  pattern="([A-z0-9À-ž\s]){3,}"></br>
                    <input type="submit" value="Salvar Alterações">
                </form>
            </div>
        </div>

    </body>
</html>
