<%-- 
    Document   : resultado_reconhecimento
    Created on : Nov 23, 2017, 7:16:44 PM
    Author     : sham
--%>

<%@page import="api.modelo.Padrao"%>
<%@page import="java.util.List"%>
<%@page import="api.modelo.Imagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Resultados</title>
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
            <h2 id="subtitulo_principal">Resultados do Reconhecimento</h2>
            <div id="sub_content">
                <%List<Padrao> padroes = (List<Padrao>) request.getAttribute("padroes"); %>
                <h3>Padrões Encontrados:</h3>
                <%Imagem img = (Imagem) request.getAttribute("imagem"); %>
                <form id="resultado" method="post" action="${pageContext.request.contextPath}/Info">
                    <input class="formInput" type="submit" value="Salvar Resultado"></br>
                    <ul id="lista_resultado">
                        <%=padroes.size()%>
                        <%for (Padrao p : padroes) {%>
                            <script>criarListaResultado( "<%=p.getStringBase64()%>","<%=p.getNome()%>", 99.5, "<%=img.getStringBase64()%>");</script>
                        <%}%>
                    </ul>
                </form>
            </div>
        </div>
    </body>
</html>
