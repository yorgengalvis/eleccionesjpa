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
<title> Votar | Sistema Votaciones</title>
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
    <div class="row p-2">
        <div class="col-sm-3 tx-center">${votante.nombre}</div>
        <div class="col-sm-4 tx-center">${votante.email}</div>
        <div class="col-sm-4 tx-center"><a class="btn bg-red w-50" href="/EleccionesUFPS2021">Cerrar sesion</a></div>
    </div>
      <div class="container mt-3" >
         <div class="card card-header bg-red tx-center">
            Selecci?n de candidato
            </div>
            <div class="card card-body">
                <p class="tx-center">Rectoria UFPS 2021 <br>
                    <label class="name-estamento">Docente</label>
                </p>
                <p>Debes seleccionar el candidato de preferencia y confirmar tu voto.</p>
                
                <div class="input-group justify-content-center">
                    <div class="row justify-content-center">
                    
                    	<c:forEach var="candidato" items="${candidatos}">
                    		<c:if test="${voto.estamento.eleccion.id == candidato.eleccion.id}">
                    			<div class="col-sm-5 mb-3">
                            <div class="card">
                                <a href="votar?action=selected&candidate=${candidato.id}">
                                    <div class="card-header bg-red ">
                                    <p class="tx-center">
                                    <div class="candidate bg-red p-2">
                                    <label class="tx-center"><c:out value="${candidato.numero}"></c:out></label>
                                    </div>
                                    <p class="tx-center"><c:out value="${candidato.nombre}"></c:out><c:out value="${candidato.apellido}"></c:out></p>
                                	</div>
                                </a>
                            </div>
                        </div>
                    		</c:if>
                    		
                    	</c:forEach>
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
</body>
</html>