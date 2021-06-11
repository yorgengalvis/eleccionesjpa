$(document).ready( function () {
    $('#table_id').DataTable();
    
    $('#datetime').change(function(){
    	$("#datetime").datetimepicker({
            format: 'yyyy-mm-dd'
        });
    });
    
    $('#datefechafin').change(function(){
    	$("#datefechafin").datetimepicker({
            format: 'yyyy-mm-dd'
        });
    });
    $('#btn-s').prop('disabled', true);
   
    $('#estamentoSelect').on('change', function (){
      	let idestamento=$('#estamentoSelect').val();
      	$.ajax({
      		type:'GET',
      		url:'estamentos',
      		data:"action=get&id="+idestamento,
      		success:function(data){
      			$("#eleccionSelect").prepend("<option value="+data.id+" selected='selected'>"+data.nombre+"</option>");
      		}
      	});
  	});
    
} );

