<%-- 
    Document   : cadastro
    Created on : Nov 24, 2017, 7:04:51 PM
    Author     : sham
--%>

<%@page import="api.modelo.Usuario"%>
<%@page import="api.modelo.Papel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Cadastro</title>
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
                <input type="submit" value="Cadastro" formaction="${pageContext.request.contextPath}/Cadastro">
                <input type="submit" value="Sair" formaction="${pageContext.request.contextPath}/">
            </form>    
            <h2 id="subtitulo_principal">Criando uma Conta</h2>
            <div id="sub_content">
                </br>
                <% Usuario usuarioCriado = (Usuario) request.getAttribute("usuarioCriado"); %>
                <% if (usuarioCriado == null) {
                    out.println("</br><h1 class='invalid'>Falha ao tentar criar usuario!</h1>");
                } else {%>
                <h2>nome: <%=usuarioCriado.getNome()%></h2></br>
                <h2>Email: <%=usuarioCriado.getEmail()%></h2></br>
                <h2>Nome de Usuario: <%=usuarioCriado.getNomeUsuario()%></h2></br>
                <%for(Papel p : usuarioCriado.getPapeis()){ %>
                    <h3><%=p.getDescricao().toString()%></h3></br>
                <%}%>
                
                </br><h1 style="color: green">Usuario Criado com Sucesso!</h1>
                <%}%>
            </div>

        </div>      
        <br><br><br><br><br><br><br><br><br><br><br>
    </body>
</html>
