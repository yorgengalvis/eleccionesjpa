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
<title>Elecciones | Votaciones</title>
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
							<c:if test="${eleccion==null}">
							Agregar nueva eleccion
							</c:if>
							<c:if test="${eleccion!=null}">
							Editar eleccion
							</c:if>
						</div>
						<div class="card-body">
						<c:if test="${eleccion==null}">
						<form action="elecciones?action=add" method="POST">
						</c:if>
						<c:if test="${eleccion!=null}">
						<form action="elecciones?action=update&id=${eleccion.id}" method="POST">
                        
						</c:if>				
							
                  <div class="row">
                     <div class="col-sm-10">
                         <div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Nombre</span>
                           </div>
                           <c:if test="${eleccion==null}">
                           <input type="text" class="form-control" name="nombre" required>
                           </c:if>
                           <c:if test="${eleccion!=null}">
                           <input type="text" class="form-control" name="nombre" value="${eleccion.nombre}" required>
                           </c:if>
                         </div>
                         <div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Fecha Inicio</span>
                           </div>
                           <c:if test="${eleccion==null}">
                            <input size="16" type="text" class="form-control" name="fechainicio" id="datetime">  
                         	</c:if>
                         	<c:if test="${eleccion!=null}">
                            <input size="16" type="text" class="form-control" name="fechainicio" value="${eleccion.fechaInicio}" id="datetime">  
                         	</c:if>
                           </div>

                     </div>
                     <div class="col-sm-10">

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                              <span class="input-group-text bg-red" id="basic-addon1">Fecha Fin</span>
                            </div>
                             <c:if test="${eleccion==null}">
                            <input size="16" type="text" class="form-control" name="fechafin" id="datefechafin">  
                         	</c:if>
                         	<c:if test="${eleccion!=null}">
                            <input size="16" type="text" class="form-control" name="fechafin" value="${eleccion.fechaFin}" id="datefechafin">  
                         	</c:if>
                         </div>
                         
                         <div class="input-group mb-3">
                           <div class="input-group-prepend">
                             <span class="input-group-text bg-red" id="basic-addon1">Cargo</span>
                           </div>
                           <c:if test="${eleccion==null}">
                           <input type="text" class="form-control"  name="cargo" required>
                         	</c:if>
                         	<c:if test="${eleccion!=null}">
                         	<input type="text" class="form-control"  name="cargo" value="${eleccion.cargo}" required>
                         	
                         	</c:if>
                         </div>

                     </div>
                     
                     <c:if test="${eleccion==null}">
                     <button type="submit" class="form-control btn bg-red">Registrar Eleccion</button>
                     </c:if>
                      <c:if test="${eleccion!=null}">
                     <button type="submit" class="form-control btn bg-red">Update</button>
                     </c:if>
                     </div>
               </form>
						</div>
					</div>



               	  </div>
               	  <div class="col-sm-8">
               	  <div class="card">
                 <div class="card-header bg-red tx-center">Listado de elecciones</div>
                   <div class="card-body">
                        <div class="table-responsive mt-2">
                            <table id="table_id" class="display mt-5">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Fecha Inicio</th>
                                        <th>Fecha Fin</th>
                                        <th>Cargo</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                <c:forEach var="eleccion" items="${listadoElecciones}">
                                 
                                 <tr>
                                        <td><c:out value="${eleccion.id}"></c:out></td>
                                        <td><c:out value="${eleccion.nombre}"></c:out></td>
                                        <td><c:out value="${eleccion.fechaInicio}"></c:out></td>
                                        <td><c:out value="${eleccion.fechaFin}"></c:out></td>
                                        <td><c:out value="${eleccion.cargo}"></c:out></td>
                                        <th><a class="p-1 br-2 bg-red" href="elecciones?action=edit&id=${eleccion.id}"><i class="fa fa-edit"></i></a><a class="p-1 br-2 bg-red" href="elecciones?action=delete&id=${eleccion.id}"><i class="fa fa-times"></i></a></th>
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
    <script>
        $("#datetime").datetimepicker({
            format: 'yyyy-mm-dd'
            });
        $("#datefechafin").datetimepicker({
            format: 'yyyy-mm-dd'
        });
    </script>
</body>
</html>