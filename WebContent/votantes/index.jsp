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
						<form action="votantes?action=update&id=${votante.id}" method="POST">
                        
						</c:if>				
							
                  <div class="row">
                  <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <label class="input-group-text bg-red" for="inputGroupSelect01">Estamento: </label>
                           </div>
                           <c:if test="${votante==null}">
	                           	<select class="custom-select" name="estamento"  id="estamentoSelect">
	                             <c:forEach var="estamento" items="${listadoEstamentos}">
	                             <option value="${estamento.id}">${estamento.descripcion}</option>
	                             </c:forEach>
	                           </select>
                           </c:if>
                           <c:if test="${votante!=null}">
                           <select class="custom-select" name="estamento" id="estamentoSelect">
	                             <c:forEach var="estamento" items="${listadoEstamentos}">
	                             	<c:if test="${estamento.eleccion.id==votante.eleccion.id}">
	                             	<option selected value="${estamento.id}">${estamento.descripcion}</option>
	                             	</c:if>
	                             <c:if test="${estamento.eleccion.id!=votante.eleccion.id}">
	                             	<option value="${estamento.id}">${estamento.descripcion}</option>
	                             	</c:if>
	                             </c:forEach>
	                           </select>
                           </c:if>
                        </div>
                    </div>
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <label class="input-group-text bg-red" for="inputGroupSelect01">Tipo Documento: </label>
                           </div>
                           <c:if test="${votante==null}">
	                           	<select class="custom-select" name="tipodocumento" id="inputGroupSelect01">
	                             <c:forEach var="TipoDocumento" items="${tiposDocumentos}">
	                             <option value="${TipoDocumento.id}">${TipoDocumento.descripcion}</option>
	                             </c:forEach>
	                           </select>
                           </c:if>
                           <c:if test="${votante!=null}">
                           <select class="custom-select" name="tipodocumento" id="inputGroupSelect01">
	                             <c:forEach var="TipoDocumento" items="${tiposDocumentos}">
	                             	<c:if test="${TipoDocumento.id==votante.tipoDocumento.id}">
	                             	<option selected value="${TipoDocumento.id}">${TipoDocumento.descripcion}</option>
	                             	</c:if>
	                             <c:if test="${TipoDocumento.id!=votante.tipoDocumento.id}">
	                             	<option value="${TipoDocumento.id}">${TipoDocumento.descripcion}</option>
	                             	</c:if>
	                             </c:forEach>
	                           </select>
                           </c:if>
                        </div>
                    </div>
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Documento</span>
                           </div>
                           <c:if test="${votante==null}">
                           <input type="number" class="form-control" name="documento" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           <c:if test="${votante!=null}">
                           <input type="number" class="form-control" value="${votante.documento}" name="documento" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           
                           </div>
                    </div>
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Nombres</span>
                           </div>
                           <c:if test="${votante==null}">
                           <input type="text" class="form-control" name="nombre" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           <c:if test="${votante!=null}">
                           <input type="text" class="form-control" value="${votante.nombre}" name="nombre" required aria-describedby="basic-addon1">
                           </c:if>
                           
                           </div>
                    </div>
                    <div class="col-sm-10">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Email</span>
                           </div>
                           <c:if test="${votante==null}">
                           <input type="email" class="form-control" name="email" required aria-describedby="basic-addon1">
                         
                           </c:if>
                           <c:if test="${votante!=null}">
                           <input type="email" class="form-control" value="${votante.email}" name="email" required aria-describedby="basic-addon1">
                           </c:if>
                           
                           </div>
                    </div>
                    
                    <div class="col-sm-10 w-100">
                    	<div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <label class="input-group-text bg-red" for="inputGroupSelect01">Proceso </label>
                           </div>
	                           	<select class="custom-select" name="eleccion" id="eleccionSelect" disabled>
	                           </select>
                        </div>
                    </div>
                    
                     
                     <c:if test="${votante==null}">
                     <button type="submit" class="form-control btn bg-red">Registrar</button>
                     </c:if>
                      <c:if test="${votante!=null}">
                     <button type="submit" class="form-control btn bg-red">Update</button>
                     </c:if>
                     </div>
               			</form>
						</div>
					</div>



               	  </div>
               	  <div class="col-sm-8">
               	  <div class="card">
                 <div class="card-header bg-red tx-center">Listado de Votantes</div>
                   <div class="card-body">
                        <div class="table-responsive mt-2">
                            <table id="table_id" class="display mt-5">
                                <thead>
                                     <tr>
                                        <th>ID</th>
                                        <th>Tipo D</th>
                                        <th>Documento</th>
                                        <th>Nombre</th>
                                        <th>Email</th>
                                        <th>Eleccion</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="votante" items="${listado}">
                                 	<tr>
                                 		<td><c:out value="${votante.id}"></c:out></td>
                                 		<td><c:out value="${votante.tipoDocumento.descripcion}"></c:out></td>
                                 		<td><c:out value="${votante.documento}"></c:out></td>
                                 		<td><c:out value="${votante.nombre}"></c:out></td>
                                 		<td><c:out value="${votante.email}"></c:out></td>
                                 		<td><c:out value="${votante.eleccion.nombre}"></c:out></td>
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







<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <script src="js/main.js"></script>
    
    <c:if test="${votante!=null}">
    <script>
    let idestamento=$('#estamentoSelect').val();
  	$.ajax({
  		type:'GET',
  		url:'estamentos',
  		data:"action=get&id="+idestamento,
  		success:function(data){
  			$("#eleccionSelect").prepend("<option value="+data.id+" selected='selected'>"+data.nombre+"</option>");
  		}
  	});
    </script>
    </c:if>
</body>
</html>