<%-- 
    Document   : info_img
    Created on : Dec 8, 2017, 6:49:31 PM
    Author     : shan
--%>

<%@page import="api.modelo.Imagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dados da Imagem</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/recimg/static/css/style.css">
        <script src="${pageContext.request.contextPath}/static/js/functions.js" type="text/javascript"></script>
    </head>
    <body>
        <h1 id="titulo_principal">Reconhecimento de Imagens</h1>
        <div class="content">
            <form id="menu" method="post">
                <input type="submit" value="Perfil" formaction="/recimg/Perfil">
                <input type="submit" value="Minhas Imagens" formaction="${pageContext.request.contextPath}/MinhasImagens">
                <input type="submit" value="Sair" formaction="${pageContext.request.contextPath}/">
            </form>
            <%Imagem i = (Imagem) request.getAttribute("imagem");%>
            <h2 id="subtitulo_principal">Dados da Imagem</h2>
            <div id="sub_content">
                <img src="<%=i.getStringBase64()%>" title="Imagem Selecionada" ></br>
                <div id="info_imagem">
                    <p>Nome: </p>
                    <b id="dado_nome"><%=i.getNome()%></b>
                    </br>                    
                    <p>Tamanho: </p>
                    <p id="dado_tamanho"><%=i.getTamanho()%></p>
                    </br>
                    <p>Tipo: </p>
                    <p id="dado_tipo"><%=i.getTipo()%></p>
                    </br>
                    </br>
                    <h3>Padrões Reconhecidos:</h3>
                    <lu id="info_resultado" class="container linhaLista">
                        <script>criarResultadoInfo("Padrao Estrela", "0", "1.0");</script>
                        <script>criarResultadoInfo("Padrao Sham", "95.0", "1.0");</script>
                    </lu>
                    </br>
                    </br>
                    <form method="post" action="${pageContext.request.contextPath}/Reconhecimento">
                        <input value="<%=i.getId()%>" name="id" hidden="true">
                        <input class="formInput" type="submit" value="Reconhecimento">
                    </form>
                </div>
            </div>
    </body>
</html>