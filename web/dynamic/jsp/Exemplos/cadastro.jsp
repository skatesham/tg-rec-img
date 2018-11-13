<%-- 
    Document   : cadastro
    Created on : Nov 24, 2017, 7:04:51 PM
    Author     : sham
--%>

<%@page import="api.modelo.Papel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
        <script src="${pageContext.request.contextPath}/static/js/functions.js" type="text/javascript"></script>
    </head>
    <body>
        <h1 id="titulo_principal">Reconhecimento de Imagens</h1>
        <div class="content">
            <form id="menu" method="post">
                <input type="submit" value="Perfil" formaction="${pageContext.request.contextPath}/Perfil">
                <input type="submit" value="Sair" formaction="${pageContext.request.contextPath}/">
            </form>    
            <h2 id="subtitulo_principal">Criando uma Conta</h2>

            <%List<Papel> papeis = (List<Papel>) request.getAttribute("papeis");%>
            <form method="post" action="${pageContext.request.contextPath}/AutenticaCadastro" onsubmit="return validar('senha', 'check');">
                <label class="texto" for="nome">Nome e sobrenome</label>
                <input class="formInput formTextInput" id="nome" type="text" name="nome" title="Insira nome e sobrenome"  pattern="([A-z0-9À-ž\s]){3,}" required></br>

                <label class="texto"  for="mail">Email</label>
                <input name="mail" id="mail" type="email" class="formInput formTextInput" required></br>

                <label class="texto"  for="username">Nome de usuario</label>
                <input class="formInput formTextInput" id="username" type="text" name="nomeUsuario" title="Insira um nome de usiario não existente no sistema"  pattern="([A-z0-9À-ž\s]){3,}" required></br>
                <%if (!papeis.isEmpty()) {%>

                <label class="texto"  for="papeis">Selecione os Papeis</label></br>
                <select size="3" id="papeis" name="papel" multiple required>
                    <%for (Papel p : papeis) {%>
                    <option  name="papel" value="<%=p.getDescricao().name()%>"><%=p.getDescricao()%></option>
                    <%}%>
                </select></br>
                <%}%>
                </br>

                <label class="texto"  for="psw">Senha</label>
                <input class="formInput formTextInput" id="senha"  type="password" title="Senha precisa ser maior que 6" pattern="([A-z0-9À-ž\s]){3,}" required></br>

                <label class="texto"  for="psw2">Confirme Senha</label>
                <input class="formInput formTextInput" id="check"  type="password" title="Senha precisa ser maior que" pattern="([A-z0-9À-ž\s]){3,}" required></br>
                <%-- if ((boolean) request.getAttribute("falhaCriarUsuario")) {
                        out.println("<p class='invalid'> Dados Incorretos</p>");
                    } %>
                <% if ((boolean) request.getAttribute("usuarioCriado")) { %>
                <script>sucesso();</script>
                <%}--%>
                <br>
                <input class="formInput submitButton" type="submit" value="Cadastrar">    
            </form>
        </div>      
        <br><br><br><br><br><br><br><br><br><br><br>
    </body>
</html>
