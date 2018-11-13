<%-- 
    Document   : blank
    Created on : Feb 18, 2018, 5:18:18 PM
    Author     : shan
--%>



<%@page import="api.modelo.Imagem"%>
<%@page import="api.modelo.Galeria"%>
<%@page import="api.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <script>
        var PROJETO_NOME = "RECIMG";
        var PROJETO_DESCRICAO = "Reconhecimento de Imagem";
    </script> 

    <%Galeria galeria = (Galeria) request.getAttribute("galeria");%>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Reconhecimento de Imagem">
        <meta name="author" content="Sham Vinicius Fiorin">
        <title>RECIMG - Reconhecimento de Imagem</title>
        <!-- Bootstrap core CSS-->
        <link href="${pageContext.request.contextPath}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/static/css/sb-admin.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">

    </head>

    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
            <form method="post" action="${pageContext.request.contextPath}/Principal">
                <button  class="navbar-brand btn btn-link" type="submit">RECIMG  
                </button>
            </form>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
                        <form method="post" action="${pageContext.request.contextPath}/Principal">
                            <button  class="nav-item btn btn-link" type="submit">
                                <i class="fa fa-fw fa-dashboard"></i>
                                <span class="nav-link-text">Dashboard</span>        
                            </button>
                        </form>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Galeria">
                        <form method="post" action="${pageContext.request.contextPath}/Galeria">
                            <button  class="nav-item btn btn-link" type="submit">
                                <i class="fa fa-fw fa-table"></i>
                                <span class="nav-link-text">Galeria</span>        

                            </button>
                        </form>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Enviar Imagem">
                        <form method="post" action="${pageContext.request.contextPath}/EnviarImagem">
                            <button  class="nav-item btn btn-link" type="submit">
                                <i class="fa fa-fw fa-circle"></i>
                                <span class="nav-link-text">Enviar Imagem</span>        
                            </button>

                        </form>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables" hidden="true">
                        <a class="nav-link" href="tables.html">
                            <i class="fa fa-fw fa-table"></i>
                            <span class="nav-link-text">Tables</span>
                        </a>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components" hidden="true">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-wrench"></i>
                            <span class="nav-link-text">Components</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseComponents">
                            <li>
                                <a href="navbar.html">Navbar</a>
                            </li>
                            <li>
                                <a href="cards.html">Cards</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages" hidden="true">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseExamplePages" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-file"></i>
                            <span class="nav-link-text">Example Pages</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseExamplePages">
                            <li>
                                <a href="login.html">Login Page</a>
                            </li>
                            <li>
                                <a href="register.html">Registration Page</a>
                            </li>
                            <li>
                                <a href="forgot-password.html">Forgot Password Page</a>
                            </li>
                            <li>
                                <a href="blank.html">Blank Page</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu Levels" hidden="true">
                        <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti" data-parent="#exampleAccordion">
                            <i class="fa fa-fw fa-sitemap"></i>
                            <span class="nav-link-text">Menu Levels</span>
                        </a>
                        <ul class="sidenav-second-level collapse" id="collapseMulti">
                            <li>
                                <a href="#">Second Level Item</a>
                            </li>
                            <li>
                                <a href="#">Second Level Item</a>
                            </li>
                            <li>
                                <a href="#">Second Level Item</a>
                            </li>
                            <li>
                                <a class="nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti2">Third Level</a>
                                <ul class="sidenav-third-level collapse" id="collapseMulti2">
                                    <li>
                                        <a href="#">Third Level Item</a>
                                    </li>
                                    <li>
                                        <a href="#">Third Level Item</a>
                                    </li>
                                    <li>
                                        <a href="#">Third Level Item</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Link" hidden="true">
                        <a class="nav-link" href="#">
                            <i class="fa fa-fw fa-link"></i>
                            <span class="nav-link-text">Link</span>
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav sidenav-toggler">
                    <li class="nav-item">
                        <a class="nav-link text-center" id="sidenavToggler">
                            <i class="fa fa-fw fa-angle-left"></i>
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown" hidden="true">
                        <a class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-fw fa-envelope"></i>
                            <span class="d-lg-none">Messages
                                <span class="badge badge-pill badge-primary">12 New</span>
                            </span>
                            <span class="indicator text-primary d-none d-lg-block">
                                <i class="fa fa-fw fa-circle"></i>
                            </span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="messagesDropdown" hidden="true">
                            <h6 class="dropdown-header">New Messages:</h6>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">
                                <strong>David Miller</strong>
                                <span class="small float-right text-muted">11:21 AM</span>
                                <div class="dropdown-message small">Hey there! This new version of SB Admin is pretty awesome! These messages clip off when they reach the end of the box so they don't overflow over to the sides!</div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">
                                <strong>Jane Smith</strong>
                                <span class="small float-right text-muted">11:21 AM</span>
                                <div class="dropdown-message small">I was wondering if you could meet for an appointment at 3:00 instead of 4:00. Thanks!</div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">
                                <strong>John Doe</strong>
                                <span class="small float-right text-muted">11:21 AM</span>
                                <div class="dropdown-message small">I've sent the final files over to you for review. When you're able to sign off of them let me know and we can discuss distribution.</div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item small" href="#">View all messages</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown" hidden="true">
                        <a class="nav-link dropdown-toggle mr-lg-2" id="alertsDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-fw fa-bell"></i>
                            <span class="d-lg-none">Alerts
                                <span class="badge badge-pill badge-warning">6 New</span>
                            </span>
                            <span class="indicator text-warning d-none d-lg-block">
                                <i class="fa fa-fw fa-circle"></i>
                            </span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="alertsDropdown">
                            <h6 class="dropdown-header">New Alerts:</h6>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">
                                <span class="text-success">
                                    <strong>
                                        <i class="fa fa-long-arrow-up fa-fw"></i>Status Update</strong>
                                </span>
                                <span class="small float-right text-muted">11:21 AM</span>
                                <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">
                                <span class="text-danger">
                                    <strong>
                                        <i class="fa fa-long-arrow-down fa-fw"></i>Status Update</strong>
                                </span>
                                <span class="small float-right text-muted">11:21 AM</span>
                                <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">
                                <span class="text-success">
                                    <strong>
                                        <i class="fa fa-long-arrow-up fa-fw"></i>Status Update</strong>
                                </span>
                                <span class="small float-right text-muted">11:21 AM</span>
                                <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item small" href="#">View all alerts</a>
                        </div>
                    </li>
                    <li class="nav-item" hidden="true">
                        <form class="form-inline my-2 my-lg-0 mr-lg-2">
                            <div class="input-group">
                                <input class="form-control" type="text" placeholder="Search for...">
                                <span class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                        </form>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="content-wrapper">
            <div class="container-fluid">
                <!-- Breadcrumbs-->
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <form method="post" action="${pageContext.request.contextPath}/Principal">
                            <button  class="btn btn-link" type="submit">Dashboard 
                            </button>
                        </form>
                    </li>
                    <li class="breadcrumb-item active"><button disabled="true" class="btn btn-link text-muted">Galeria</button></li>
                </ol>
                <div class="container">
                    <div class="row text-center">
                        <h1 class="text-dark w-100">Galeria de Imagens</h1>
                    </div>
                    <div class="row">

                        <%int count = 0;%>
                        <%for (Imagem img : galeria) {%>


                        <div class="col-md-3 text-center">
                            <div class="w-100">
                                <figure class="imghvr-fade">
                                    <img src="<%=img.getStringBase64()%>" height="200" width="200" class="img-responsive img-thumbnail">
                                    <figcaption>
                                        <!--h1>Hello world</h1-->
                                        <p><%=img.getNome()%></p>
                                        <div class="btn-group">
                                            <form method="post" action="${pageContext.request.contextPath}/Info">
                                                <input type="submit" value="Selecionar" class="btn btn-success">
                                                <input name="imagem_id" value="<%=img.getId()%>" hidden="true">
                                            </form>
                                            <!--button type="button" class="btn btn-success w-100" >Mais Informação</button-->
                                            <!--button type="button" class="btn btn-default">Samsung</button-->
                                        </div>
                                    </figcaption>
                                </figure>

                                <form method="post" action="${pageContext.request.contextPath}/Info">
                                    <input type="submit" value="Selecionar" class="btn btn-success">
                                    <input name="imagem_id" value="<%=img.getId()%>" hidden="true">
                                </form>
                            </div>
                        </div>
                        <%count++;%>
                        <%}%>

                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid-->
        <!-- /.content-wrapper-->
        <footer class="sticky-footer">
            <div class="container">
                <div class="text-center">
                    <small>Copyright © Recimg 2018</small>
                </div>
            </div>
        </footer>
        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fa fa-angle-up"></i>
        </a>
        <!-- Logout Modal-->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Quer Sair?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Para sair selecione logout.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                        <form action="${pageContext.request.contextPath}/"><input  class="brn brn-primary" type="submit" value="Logout"/></form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <script src="${pageContext.request.contextPath}/static/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/static/vendor/jquery-easing/jquery.easing.min.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/static/js/sb-admin.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/js/functions.js" type="text/javascript"></script>
    </div>
</body>
</html>
