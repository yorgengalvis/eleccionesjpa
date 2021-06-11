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
<title> Confirmacion datos | Votaciones</title>
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
      <div class="container mt-5" >
         <div class="card card-header bg-red tx-center">
           			Confirmaciòn datos de votante
            </div>
            <div class="card card-body">
               <form action="votar?action=confirmData&id=${votante.id}" method="POST">
                        <p class="tx-center">Rectoria UFPS 2021 <br>
                            <label class="name-estamento">Docente</label>
                        </p>
                        <div class="mx-auto w-100">
                            <div class="input-group mb-3 w-50 mx-auto">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-red">Nombres:</span>
                                </div>
                                <input type="text" class="form-control" value="${votante.nombre}" disabled>
                            </div>
                            <div class="input-group mb-3 w-50 mx-auto">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-red">Email:</span>
                                </div>
                                <input type="text" class="form-control" value="${votante.email}" disabled>
                            </div>
                            <div class="input-group mb-3 w-50 mx-auto">
                           <div class="input-group-prepend">
                             <label class="input-group-text bg-red" for="inputGroupSelect01">Tipo Documento: </label>
                           </div>
	                           	<select class="custom-select" name="tipodocumento" id="inputGroupSelect01">
	                             <c:forEach var="TipoDocumento" items="${tiposDocumentos}">
	                             <option value="${TipoDocumento.id}">${TipoDocumento.descripcion}</option>
	                             </c:forEach>
	                           </select>
                        </div>

                              <div class="input-group mb-3 w-50 mx-auto">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-red">Documento</span>
                                </div>
                                <input type="number" class="form-control" name="documento" required>
                            </div>
                            <div class="input-group mb-3 w-50 mx-auto">
                              <div class="input-group-prepend">
                                  <span class="input-group-text bg-red">Contraseña de Acceso</span>
                              </div>
                              <input type="text" class="form-control" name="token" required>
                          </div>
                            <div class="input-group ">
                                <button type="submit" class="btn bg-red w-50 mb-3 tx-center mx-auto">Confirmo mis datos</button>
                            </div>
                           
                       </div>
                    
               </form>
            </div>
      </div>
   </main>
   
   <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>