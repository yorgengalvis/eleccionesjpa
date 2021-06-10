<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="css/main.css">
<title>Tipos de documento | Admin</title>
</head>
<body>
	<jsp:useBean class="ufps.edu.co.dao.TipoDocumentoDAO" id="tipodocumentoDAO"></jsp:useBean>

	<c:if test="${tipodocumento != null}">
		<%--  <form action="${pageContext.request.contextPath}/ActualizarEmpleado?accion=edita&codigo=${empleado.codigo}"  method="post"> --%>
		<form
			action="tipodocumento?action=actualizar&id=${tipodocumento.id}"
			method="POST">
	</c:if>
	<c:if test="${tipodocumento == null}">
		<%--  <form action="${pageContext.request.contextPath}/AgregarEmpleado?accion=agregar&codigo=${empleado.codigo}"  method="POST"> --%>
		<form action="tipodocumento?action=registrar" method="POST">
	</c:if>
<header>
      <div class="line-roja"></div>
      <div class="informacion col-auto text-center">
         <img class="w-56" src="img/logo_ufps.png">
         <label>VOTACIONES ELECTORALES UFPS 2021</label>
      </div>
   </header>
   <main>
       <div class="container-fluid mt-2 tx-center bg-red">
        Panel Administrativo - Sistema de Elecciones
        </div>
           <div class="container mt-3" >
               <div class="card">
                   <div class="card-header bg-red tx-center">Administración de Tipos de documento</div>
                   <div class="card-body">
                       <div class="card-body">
                        
                        <form>
                              <div class="row">
                                <div class="col-sm-6">
			                         <div class="input-group mb-3">
			                           <div class="input-group-prepend">
			                             <span class="input-group-text bg-red" id="basic-addon1">Descripcion</span>
			                           </div>
			                           <input type="text" class="form-control" name="descripcion" value="${tipodocumento.descripcion}" required>
			                         </div>
                     				</div>
                                      <div class="col-sm-4">
                                        <button type="submit" class="btn bg-red" action=""><i class="fa fa-plus"></i> Añadir</button>
                                      </div>
                              </div>
                        </form>
                       </div>
                        <div class="table-responsive mt-2">
                            <table id="table_id" class="display mt-5">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Descripcion</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                <c:forEach var="tipoDocumento" items="${tipodocumentoDAO.list()}">
                                 <tr>
                                        <td><c:out value="${tipoDocumento.id}"></c:out></td>
                                        <td><c:out value="${tipoDocumento.descripcion}"></c:out></td>
                                        <th><a class="p-1 br-2 bg-red" href="#"><i class="fa fa-edit"></i></a><a class="p-1 br-2 bg-red" href="#"><i class="fa fa-times"></i></a></th>
                                  </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
               </div>
      </div>
   </main>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>