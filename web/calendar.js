



//номер недели
Date.prototype.getWeek = function() {
  var date = new Date(this.getTime());
  date.setHours(0, 0, 0, 0);
  // Thursday in current week decides the year.
  date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
  // January 4 is always in week 1.
  var week1 = new Date(date.getFullYear(), 0, 4);
  // Adjust to Thursday in week 1 and count number of weeks from date to week1.
  return 1 + Math.round(((date.getTime() - week1.getTime()) / 86400000 - 3 + (week1.getDay() + 6) % 7) / 7);
}

// Returns the four-digit year corresponding to the ISO week of the date.
Date.prototype.getWeekYear = function() {
  var date = new Date(this.getTime());
  date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
  return date.getFullYear();
}

Date.prototype.next = function() {
	return this.setDate(this.getDay()+1);
}

Date.prototype.prev = function() {
	return this.setDate(this.getDay()+1);
}

Date.prototype.first = function() {
	if(this.getDay() != 1)
	{return this.setDate(this.getDate() - this.getDay() + (this.getDay() == 0 ? -6:1));}
	else return this;
}

Date.prototype.last = function() {
	if(this.getDay() != 0)
	{return this.setDate(this.getDate() - this.getDay() + 7);}
	else return this;

}

Date.prototype.sql = function() {
	let date = this.getDate().toString();
	let month = (this.getMonth() + 1).toString();
	let year = this.getFullYear().toString();
	
	if (date.length<2) {date = '0'+date;}
	if (month.length<2) {month = '0'+month;}
	switch (year.length) {
	case 1: year = '000' + year;
	case 2: year = '00' + year;
	case 3: year = '0' + year;
	default: break;
	}
	return year+'-'+month+'-'+date;
	
}

$( document ).ready(function() {


def_date = new Date("2019-01-10");
$('#date_from').val(new Date(def_date.first()).sql());
$('#date_to').val(new Date(def_date.last()).sql());

let date_from=window.document.getElementById("date_from");
let date_to=window.document.getElementById("date_to");

let olddate_from=date_from.value;
let olddate_to=date_to.value;

let isChanged_from = function(){
  if(date_from.value!== olddate_from){
    olddate_from=date_from.value;
    return true;
  };
  return false;
};

let isChanged_to = function(){
  if(date_to.value!== olddate_to){
    olddate_to=date_to.value;
    return true;
  };
  return false;
};

date_from.addEventListener("blur", function(){
  if(isChanged_from())
  {$('#date_from').val(new Date(new Date($('#date_from').val()).first()).sql())}
});

date_to.addEventListener("blur", function(){
  if(isChanged_to())
  {$('#date_to').val(new Date(new Date($('#date_to').val()).last()).sql())}
});

//обновить
$('div#editor>div>input#set').click(
			function(){
elems = $('div#editor>div>input#set').parent().parent().children().children();
elems.each(function( index ) {
	$('.pressedTime').children(this.id).html(this.value.substr(0,5));
	$('.pressedTime').attr("change","change");
});
}
		);

});

//выделение
var selection = {
        single: function(el) {
            $('#timeGrid td').not(el).removeClass(this.cl);
            this.ctrl(el);
        },
        shift: function(el) {
            if (typeof this.last !== 'number') {
                return this.single(el);
            }
            var till = $(el).index(this.slcr),
                from = this.last;
            if (from > till) till = [from, from = till][0];
            $('#timeGrid td').not($('#timeGrid td').eq(this.last)).removeClass(this.cl);
            $('#timeGrid td').slice(from, till).add(el).addClass(this.cl);
        },
        ctrl: function(el) {
            $(el).addClass(this.cl);
            this.last = $(el).index(this.slcr);
            console.log(this.last);
        },
        slcr: '#timeGrid td',
        cl: 'pressedTime',
        last: null
    };	

$('#timeGrid td').on('click', function(e) {
		method = !e.shiftKey && !e.ctrlKey ? 'single' : (e.shiftKey ? 'shift' : 'ctrl');
		selection[method](this);
		});

//построение таблицы
//заголовок
function table_content()
{
var table_head = '<tr><th>МЕСЯЦ</th><th>НЕДЕЛЯ</th><th>ПН</th><th>ВТ</th><th>СР</th><th>ЧТ</th><th>ПТ</th><th>СБ</th><th>ВС</th></tr>';
var table = '';
console.log('ajhvb');
table += table_head;
var d_length = result.SQLdata.length;

for (i=0;i < d_length;i+=7){
table +="\n\
<tr>\n\
<th>"+ monthName(i+6) +"</th>\n\
<th>"+(new Date(result.SQLdata[i].DAY)).getWeek()+"</th>\n\
<td date='"+result.SQLdata[i+0].DAY+"'>\n\
<DAY noparse=1>"+result.SQLdata[i+0].DAY.substr(-2)+"</DAY>\n\
"+getTag (i)+
"</td>\n\
<td date='"+result.SQLdata[i+1].DAY+"'>\n\
<DAY noparse=1>"+result.SQLdata[i+1].DAY.substr(-2)+"</DAY>\n\
"+getTag (i+1)+
"</td>\n\
<td date='"+result.SQLdata[i+2].DAY+"'>\n\
<DAY noparse=1>"+result.SQLdata[i+2].DAY.substr(-2)+"</DAY>\n\
"+getTag (i+2)+
"</td>\n\
<td date='"+result.SQLdata[i+3].DAY+"'>\n\
<DAY noparse=1>"+result.SQLdata[i+3].DAY.substr(-2)+"</DAY>\n\
"+getTag (i+3)+
"</td>\n\
<td date='"+result.SQLdata[i+4].DAY+"'>\n\
<DAY noparse=1>"+result.SQLdata[i+4].DAY.substr(-2)+"</DAY>\n\
"+getTag (i+4)+
"</td>\n\
<td date='"+result.SQLdata[i+5].DAY+"'>\n\
<DAY noparse=1>"+result.SQLdata[i+5].DAY.substr(-2)+"</DAY>\n\
"+getTag (i+5)+
"</td>\n\
<td date='"+result.SQLdata[i+6].DAY+"'>\n\
<DAY noparse=1>"+result.SQLdata[i+6].DAY.substr(-2)+"</DAY>\n\
"+getTag (i+6)+
"</td>\n\
</tr>";
}
//console.log(table);
return table;
}

//номер месяца в текст, только для первого числа
var month = ['-','янв','фев','мар','апр','май','июн','июл','авг','сен','окт','ноя','дек'];
function monthName (d)
{
	console.log("monthName - "+d);
	if (result.SQLdata[d].DAY.substr(-2) <= 7)
	{return month[Number(result.SQLdata[d].DAY.substr(5,2))];}
	else return "";
}

//вывод оставшихся тегов
function getTag (i)
{
	var tag = "";
	
	Object.keys(result.SQLdata[i]).forEach(function(entry) {
		if(entry == "DAY"){}
		else if (entry == "HOURS") {tag += "<"+entry +">"+result.SQLdata[i][entry].substr(0,5)+"</"+entry+">";}
		else {tag += "<"+entry +">"+result.SQLdata[i][entry]+"</"+entry+">";}
		console.log(result.SQLdata[i][entry]);
	});
	return tag;
}

function CalendarChangeToData(name) {
    outer = $('[change]');
    var result = "\"";
    for (i = 0; i < outer.length; i++) {
        result += "{";
        result += "\"" + "DAY" + "\"";
        result += ":";
        result += "\"" + $('[change]')[i].getAttribute('date') + "\"";

        var inner = $(outer[i]).children();
        //внутренние теги
        for (j = 0; j < inner.length; j++) {
			if (inner[j].hasAttribute('noparse')) continue;
            result += ",";
            result += "\"" + inner[j].tagName + "\"";
            result += ":";
            result += "\"" + inner[j].innerText + "\"";
        }
        //внутренние теги

        result += "}";
        if (i < elems.length - 1) {
            result += ",";
        }
    }
    result += "\"";
	console.log("\""+name+"\"" + ":" + result);
    return "\""+name+"\"" + ":" + result;
}


function CalendarChangeToData(name) {
    outer = $('[change]');
	
    var result = '[';
    for (i = 0; i < outer.length; i++) {
        result += '{';
        result += '"' + "DAY" + '"';
        result += ':';
        result += '"' + outer[i].getAttribute('date') + '"';

        var inner = $(outer[i]).children();
        //внутренние теги
        for (j = 0; j < inner.length; j++) {
			if (j == 0) {}
			if (inner[j].hasAttribute('noparse')) continue;
            result += ',';
            result += '"' + inner[j].tagName + '"';
            result += ':';
            result += '"' + inner[j].innerText + '"';
        }
        //внутренние теги

        result += '}';
        if (i < outer.length - 1) {
            result += ",";
        }
    }
    result += ']';
	console.log('"'+name+'"' + ':' + result);
    return '"'+name+'[]"' + ':' + result;
}