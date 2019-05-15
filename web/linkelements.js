$( document ).ready(function() {
	
		$("#ID-DEPARTMENT").on ('change', function() { 

		val = $("#ID-DEPARTMENT option:selected").attr("value");
		
		$("#DEPARTMENT_ID-ID-TITLE>option[department_id]").hide();
		$("#DEPARTMENT_ID-ID-TITLE>option[department_id='"+val+"']").show();
		$("#DEPARTMENT_ID-ID-TITLE>option[value!='-1']").removeAttr("selected");
		$("#DEPARTMENT_ID-ID-TITLE>option[null='-1']").show();
		$("#DEPARTMENT_ID-ID-TITLE>option[null='-1']").attr("selected", "selected");
		//обновление элемента
		$("#DEPARTMENT_ID-ID-TITLE").val("");
		});
		
		$("#region").on ('input', function() { 
		console.log("linkelements");
		console.log("список:" + $("#region").attr('list'));
		console.log("регион:" + $("#region").val());
		$('#time_zone').val($("#"+$("#region").attr('list')+">option[value='"+$("#region").val()+"']").attr("time_zone"))
		
		});
		
});

