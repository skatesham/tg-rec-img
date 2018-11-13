<%-- 
    Document   : newjsp
    Created on : Jul 27, 2018, 10:45:01 PM
    Author     : shan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

                    <div class="col-md-6 text-center">
                        <h1>Resultado da Consulta </h1>
                        <% for(String string : textos){
                            %>
                            <h2><%=string%></h2>
                            %<
                        }
                        %>
                        <%if ((boolean) request.getAttribute("reconhecimento")) {%>   
                        <div class="row">
                            <div class="col-md-5">
                                <form method="post" action="${pageContext.request.contextPath}/Reconhecimento">
                                    <input class="btn btn-success" type="submit" value="Voltar">
                                </form>
                            </div>                            
                            <div class="col-md-5">
                                <form method="post" action="${pageContext.request.contextPath}/Info">
                                    <input class="btn btn-warning" type="submit" value="Salvar Resultado">
                                    <input name="isSalvar" value="true" hidden="true">
                                    <input name="imagem_id" value="<%=img.getId()%>" hidden="true">                            
                                </form>
                            </div>
                        </div>
                        <%} else {%>
                        <form method="post" action="${pageContext.request.contextPath}/Info">
                            <input class="btn btn-success" type="submit" value="Voltar">
                            <input name="imagem_id" value="<%=img.getId()%>" hidden="true">
                        </form>
                        <%}%>
                    </div>

                    <div class="col-md-3">
                        <figure class="imghvr-fade">
                            <img src="<%=padrao.getStringBase64()%>" height="200" width="200" class="img-responsive  img-thumbnail">
                            <figcaption>
                                <b>Amostra:</b>
                                <p><%=padrao.getNome()%></p>
                                <p>Tamanho: <%=padrao.getTamanho()%></p>
                            </figcaption>
                        </figure>
                    </div>
        
        
        
        
        
    </body>
</html>
