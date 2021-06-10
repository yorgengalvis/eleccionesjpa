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
    <link rel="stylesheet" href="css/main.css">
<title>Dashboard ADMIN | Votaciones</title>
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
       <div class="container-fluid mt-2 tx-center bg-red ">
        Panel Administrativo - Sistema de Elecciones
        </div>
           <div class="container mt-3" >
            <div class="card card-body mb-5 justify-content-between">
                <div class="row mb-3 ">
                    <div class="col-sm-3">
                        <a href="votantes"><div class="card">
                            <div class="card-header tx-center bg-red">
                                Votantes
                            </div>
                        </div></a>
                    </div>
                    <div class="col-sm-3">
                        <a href="candidatos"><div class="card">
                         <div class="card-header tx-center bg-red">
                             Candidatos
                         </div>
                     </div></a>
                    </div>
                    <div class="col-sm-3">
                        <a href="tipodocumento"><div class="card">
                            <div class="card-header tx-center bg-red">
                                Tipos de documentos
                            </div>
                        </div></a>
                    </div>
                </div>
                <div class="row">
                   
                   <div class="col-sm-3">
                    <a href="estamentos"><div class="card">
                        <div class="card-header tx-center bg-red">
                            Estamentos
                        </div>
                    </div></a>
                   </div>
                   <div class="col-sm-3">
                    <a href="elecciones"><div class="card">
                        <div class="card-header tx-center bg-red">
                            Elecciones
                        </div>
                    </div></a>
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