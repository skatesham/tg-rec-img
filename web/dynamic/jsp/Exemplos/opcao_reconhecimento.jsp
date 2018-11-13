<%-- 
    Document   : opcao_reconhecimento
    Created on : Nov 23, 2017, 7:14:43 PM
    Author     : sham
--%>

<%@page import="java.util.List"%>
<%@page import="api.modelo.Padrao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Reconhecimento</title>
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
                <input type="submit" value="Minhas Imagens" formaction="${pageContext.request.contextPath}/MinhasImagens">
                <input type="submit" value="Sair" formaction="${pageContext.request.contextPath}/" >
            </form>      
            <h2 id="subtitulo_principal">Opções de Reconhecimento</h2>
            <div id="sub_content">
                <form id="form_reconhecimento" method="post" action="${pageContext.request.contextPath}/Resultado">
                    <input type="text" value="" name="id" hidden="true">
                    <div id="padroes" class="container">
                        <lu>
                            <%List<Padrao> padroes = (List<Padrao>) request.getAttribute("padroes");%>
                            <% for (Padrao p : padroes) {%>
                            <li>

                                <img src="<%=p.getStringBase64()%>"></br>
                                <input type="checkbox" name="checkboxPadrao" value="<%=p.getId()%>">
                                <b><%=p.getNome()%></b>
                                <p><%=p.getTamanho()%></p>

                            </li></br>
                            <%}%>
                        </lu>
                    </div>
                    <input class="formInput" type="submit" value="Reconhecer Imagem"></br>
                </form>
            </div>
        </div>
    </body>
</html>
