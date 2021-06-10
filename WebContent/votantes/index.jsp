<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="css/main.css">
<title> Votantes | Votaciones</title>
</head>
<body>
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
           <div class="container-fluid p-5" >
           <a href="admin" class="btn bg-red m-2">Regresar</a>
               <div class="row">
               <div class="col-sm-4">
               	  
					<div class="card">
						<div class="card-header bg-red">
							<c:if test="${votante==null}">
							Agregar nuevo Votante
							</c:if>
							<c:if test="${votante!=null}">
							Editar Votante
							</c:if>
						</div>
						<div class="card-body">
						<c:if test="${votante==null}">
						<form action="votantes?action=add" method="POST">
						</c:if>
						<c:if test="${votante!=null}">
						<form action="votantes?action=update&id=${candidato.id}" method="POST">
                        
						</c:if>				
							
                  <div class="row">
                    
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Documento</span>
                           </div>
                           <c:if test="${candidato==null}">
                           <input type="number" class="form-control" name="documento" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           <c:if test="${candidato!=null}">
                           <input type="number" class="form-control" value="${candidato.documento}" name="documento" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           
                           </div>
                    </div>
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Nombres</span>
                           </div>
                           <c:if test="${candidato==null}">
                           <input type="text" class="form-control" name="nombre" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           <c:if test="${candidato!=null}">
                           <input type="text" class="form-control" value="${candidato.nombre}" name="nombre" required aria-describedby="basic-addon1">
                           </c:if>
                           
                           </div>
                    </div>
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Apellido</span>
                           </div>
                           <c:if test="${candidato==null}">
                           <input type="text" class="form-control" name="apellido" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           <c:if test="${candidato!=null}">
                           <input type="text" class="form-control" value="${candidato.apellido}" name="apellido" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           
                           </div>
                    </div>
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <label class="input-group-text bg-red" for="inputGroupSelect01">Elección: </label>
                           </div>
                           <c:if test="${estamento==null}">
	                           	<select class="custom-select" name="eleccion" id="inputGroupSelect01">
	                             <c:forEach var="eleccion" items="${listaElecciones}">
	                             <option value="${eleccion.id}">${eleccion.nombre}</option>
	                             </c:forEach>
	                           </select>
                           </c:if>
                           <c:if test="${estamento!=null}">
                           <select class="custom-select" name="eleccion" id="inputGroupSelect01">
	                             <c:forEach var="eleccion" items="${listaElecciones}">
	                             	<c:if test="${eleccion.id==candidato.eleccion.id}">
	                             	<option selected value="${eleccion.id}">${eleccion.nombre}</option>
	                             	</c:if>
	                             <c:if test="${eleccion.id!=candidato.eleccion.id}">
	                             	<option value="${eleccion.id}">${eleccion.nombre}</option>
	                             	</c:if>
	                             </c:forEach>
	                           </select>
                           </c:if>
                        </div>
                    </div>
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Numero</span>
                           </div>
                           <c:if test="${candidato==null}">
                           <input type="number" class="form-control" name="numero"  required aria-describedby="basic-addon1">
                         
                           </c:if>
                           <c:if test="${candidato!=null}">
                           <input type="number" class="form-control" value="${candidato.numero}" name="numero"  required aria-describedby="basic-addon1">
                         
                           </c:if>
                           
                           </div>
                    </div>
                     
                     <c:if test="${candidato==null}">
                     <button type="submit" class="form-control btn bg-red">Registrar</button>
                     </c:if>
                      <c:if test="${candidato!=null}">
                     <button type="submit" class="form-control btn bg-red">Update</button>
                     </c:if>
                     </div>
               	</form>
						</div>
					</div>



               	  </div>
               	  <div class="col-sm-8">
               	  <div class="card">
                 <div class="card-header bg-red tx-center">Listado de Candidatos</div>
                   <div class="card-body">
                        <div class="table-responsive mt-2">
                            <table id="table_id" class="display mt-5">
                                <thead>
                                     <tr>
                                        <th>ID</th>
                                        <th>Tipo D</th>
                                        <th>Documento</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="votante" items="${listado}">
                                 	<tr>
                                 		<td><c:out value="${votante.id}"></c:out></td>
                                 		<td><c:out value="${votante.tipoDocumento.descripcion}"></c:out></td>
                                 		<td><c:out value="${votante.documento}"></c:out></td>
                                 		<th><a class="p-1 br-2 bg-red" href="votantes?action=edit&id=${votante.id}"><i class="fa fa-edit"></i></a><a class="p-1 br-2 bg-red" href="votantes?action=delete&id=${votante.id}"><i class="fa fa-times"></i></a></th>
                                 	</tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
               </div>
               	  </div>
               	  
               </div>
      </div>
   </main>







<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>