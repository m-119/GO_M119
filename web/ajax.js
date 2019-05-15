$( document ).ready(function() {
		
		
		
		$("#btn").click(
			function(){
				if ($('#ajax_form > input[name=action]:checked').val() == 'update Calendar'){updateTable("update Calendar", 'DB_Connector', CalendarChangeToData("SQLdata"));}
				else{sendAjaxForm('result_form', 'ajax_form', 'DB_Connector');}
				return false; 
			}
		);
		
		//запросы требуется разделить
	if ($("#ID-DEPARTMENT")[0] != null) { getForm("ID-DEPARTMENT", 'DB_Connector'); }
	if ($("#DEPARTMENT_ID-ID-TITLE")[0] != null) { getForm("DEPARTMENT_ID-ID-TITLE", 'DB_Connector'); }
	if ($("#ID-GRADE")[0] != null) { getForm("ID-GRADE", 'DB_Connector'); }
	if ($("#ID-REGION-TIME_ZONE")[0] != null) { getForm("ID-REGION-TIME_ZONE", 'DB_Connector'); }
});
 
 //сбор данных формы
 function getForm(formid, url) {
    jQuery.ajax({
        url:     url, //url страницы (action_ajax_form.php)
        type:     "POST", //метод отправки
        dataType: "html", //формат данных
		contentType: "application/json; charset=utf-8",
        data: act(formid),  // Сеарилизуем объект
        success: function(response) { //Данные отправлены успешно
        	result = jQuery.parseJSON(response);
			
			formData = {};	//для сбора данных;
			formData[formid] = result;
			
			buildSelect(formid,result);
    	},
    	error: function(response) { // Данные не отправлены
    		document.getElementById(result_form).innerHTML = "Ошибка подключения.";
    	}
 	});
}

//update
 function updateTable(formid, url, data) {
    jQuery.ajax({
        url:     url, //url страницы (action_ajax_form.php)
        type:     "POST", //метод отправки
        dataType: "html", //формат данных
		contentType: "html",
        data: act(formid, data),  // Сеарилизуем объект
        success: function(response) { //Данные отправлены успешно
        	result = jQuery.parseJSON(response);
			
			formData = {};	//для сбора данных;
			formData[formid] = result;
			
    	},
    	error: function(response) { // Данные не отправлены
    		document.getElementById(result_form).innerHTML = "Ошибка подключения.";
    	}
 	});
}

 //сбор данных формы
function sendAjaxForm(result_form, ajax_form, url) {
    jQuery.ajax({
        url:     url, //url страницы (action_ajax_form.php)
        type:     "POST", //метод отправки
        dataType: "html", //формат данных
        data: FormToJSON("#"+ajax_form),  // Сеарилизуем объект
        success: function(response) { //Данные отправлены успешно
        	result = jQuery.parseJSON(response);
			
        	document.getElementById(result_form).innerHTML = result.valueOf().action;
			
			//если есть календарь - заполнение календаря; включение селекта
			if ($("#timeGrid > tbody")[0] != null) {$("#timeGrid > tbody")[0].innerHTML = table_content(); $('#timeGrid td').on('click', function(e) {method = !e.shiftKey && !e.ctrlKey ? 'single' : (e.shiftKey ? 'shift' : 'ctrl');	selection[method](this);});}
    	},
    	error: function(response) { // Данные не отправлены
    		document.getElementById(result_form).innerHTML = "Ошибка подключения.";
    	}
 	});
}

function act (str, data)
{
	console.log("act:"+!!data);
	if (!!data)
	{
	console.log('JSON: {"action" : "'+str+'",' +data+ '}');
	return '{"action" : "'+str+'",' + data + '}';
	}
	else{
	console.log('JSON: {"action" : "'+str+'"}');
	return '{"action" : "'+str+'"}';
	}
}

function buildSelect(elemement,json)
{
	elm = $("#"+elemement+"")[0];
	$(elm).html('<option null="-1"></option>');
	
	for (i = 0; i < formData[elemement]["SQLdata"].length ; i++)
	{
	if (elm.tagName == "SELECT")
	{
		//json[json.length-1][1].hasOwnProperty("DEPARTMENT_ID");
		//json[json.length-1][1].hasOwnProperty("ID");
		//json[json.length-1][1].hasOwnProperty("TITLE");
		line = '<option value= "' + formData[elemement]["SQLdata"][i].valueOf().ID+'" ';
		if (formData[elemement]["SQLdata"][i].hasOwnProperty("DEPARTMENT_ID")) {line += ' DEPARTMENT_ID ="' + formData[elemement]["SQLdata"][i].valueOf().DEPARTMENT_ID +'"';}
		if (formData[elemement]["SQLdata"][i].hasOwnProperty("TIME_ZONE")) {line += ' TIME_ZONE ="' + formData[elemement]["SQLdata"][i].valueOf().TIME_ZONE +'"';}
		line += ' >' + formData[elemement]["SQLdata"][i].valueOf().TEXT + '</option>\n';
		$(elm).append(line);
		console.log("line");
	}
	else if (elm.tagName == "DATALIST")
	{
		//json[json.length-1][1].hasOwnProperty("DEPARTMENT_ID");
		//json[json.length-1][1].hasOwnProperty("ID");
		//json[json.length-1][1].hasOwnProperty("TITLE");
		line = '<option value= "' + formData[elemement]["SQLdata"][i].valueOf().TEXT+'" ';
		line += 'id = "' + formData[elemement]["SQLdata"][i].valueOf().ID+'" ';
		if (formData[elemement]["SQLdata"][i].hasOwnProperty("DEPARTMENT_ID")) {line += ' DEPARTMENT_ID ="' + formData[elemement]["SQLdata"][i].valueOf().DEPARTMENT_ID +'"';}
		if (formData[elemement]["SQLdata"][i].hasOwnProperty("TIME_ZONE")) {line += ' TIME_ZONE ="' + formData[elemement]["SQLdata"][i].valueOf().TIME_ZONE +'"';}
		line += ' >' + formData[elemement]["SQLdata"][i].valueOf().TEXT + '</option>\n';
		$(elm).append(line);
		console.log("line");
	}
	
	
	}
		
}


//данные формы в JSON-строку
function FormToJSON (frmSelector)
{
	var obj = $(frmSelector).serializeToJSON();
			console.log(JSON.stringify(obj));
			return JSON.stringify(obj);
}