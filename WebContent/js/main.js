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
} );

