<%-- 
    Document   : perfil
    Created on : Nov 17, 2017, 7:24:22 PM
    Author     : sham
--%>

<%@page import="api.modelo.EnumPapeis"%>
<%@page import="api.modelo.Papel"%>
<%@page import="api.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Perfil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
    </head>
    <body>
        <h1 id="titulo_principal">Reconhecimento de Imagens</h1>
        <div class="content">
            <form id="menu" method="post">
                <input type="submit" value="Minhas Imagens" formaction="${pageContext.request.contextPath}/MinhasImagens">
                <% Usuario u = (Usuario) request.getAttribute("usuarioLogado"); %>

                <%
                    boolean isAdmin = false;
                    request.setAttribute("isAdmin", isAdmin);
                    if (u != null) {
                        for (Papel p : u.getPapeis()) {
                            if (p.getDescricao().equals(EnumPapeis.ADMINISTRADOR)) {
                %>       
                                <input type="submit" value="Cadastrar"  formaction="${pageContext.request.contextPath}/Cadastro">
                                <input type="submit" value="Excluir"  formaction="${pageContext.request.contextPath}/Excluir">
                <%              isAdmin = true;
                                request.setAttribute("isAdmin", isAdmin);

                            }
                        }
                    }
                %>
                <input type="submit" value="Sair" formaction="${pageContext.request.contextPath}/" >
                <%if (isAdmin) {
                        out.print("<p style='color: blue'> ADMIN </p>");
                    }%>
            </form>      
            <h2 id="subtitulo_principal">Meu Perfil</h2>
            <div id="sub_content">
                </form>
                <p>Nome:</p> 
                <%if(u != null) { %>
                <h3 id="nome"> <%= u.getNome()%> </h3>
                <p>E-mail:</p>
                <h3> <%= u.getEmail()%> </h3>
                <%}%>
                <form method="post" action="${pageContext.request.contextPath}/AlterarPerfil">
                    <input type="submit" value="Alterar Pefil">
                </form>
            </div>
        </div>
        </br>
        </br> 
        </br>
        </br>
    </body>
</html>