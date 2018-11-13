<%-- 
    Document   : cadastro
    Created on : Nov 24, 2017, 7:04:51 PM
    Author     : sham
--%>

<%@page import="api.dao.UsuarioDAO"%>
<%@page import="core.dao.UsuarioDAOMariaDB"%>
<%@page import="api.modelo.Usuario"%>
<%@page import="api.modelo.Papel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Excluir Contas</title>
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
            <h2 id="subtitulo_principal">Excluir Contas</h2>
            <div id="sub_content">
                </br>
                <% Usuario logado = (Usuario) request.getAttribute("usuarioLogado"); %>
                <% List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios"); %>
                <lu>
                    <% if (usuarios != null) {
                            int count = 0;
                            for (Usuario u : usuarios) {
                                if (u.getId() != logado.getId()) {
                    %>
                    <h1><%=count%></h1>
                    <li id="menu">                     

                        <b>Nome: <%=u.getNome()%></b>
                        <p>Usuario: <%=u.getNomeUsuario()%><p>
                        <p>Email: <%=u.getEmail()%><p>
                        <form method="post">
                            <input name="<%=count%>" value="<%=u.getId()%>" hidden="true">
                            <input name="excluir" type="submit" value="Excluir" formaction="${pageContext.request.contextPath}/Excluir">
                        </form>
                    </li>
                    </br>

                    <%count++;
                                }
                            }
                        }%>
                </lu>
            </div>

        </div>      
        <br><br><br><br><br><br><br><br><br><br><br>
    </body>
</html>
