var validateYear = false;
var validateMonth = true;
var validateDay = true;

window.addBindings = function(){
    $( "#choice" ).buttonset();
    $("#botonMapa").click(
      function() {
    	if (validateYear && validateMonth && validateDay) {
          apagar("loading");
          getInformation('http://localhost:8080/kmlbi/biservlet?q=' + 
            $("#measureSelect").val() + '&fYear=' + 
            $("#fromYear option:selected").text() + '&tYear=' + 
            $("#toYear option:selected").text() + '&fMonth=' + 
            $("#fromMonth option:selected").val() + '&tMonth=' + 
            $("#toMonth option:selected").val() +'&fDay=' + 
            $("#datepickerFrom").val() + '&tDay=' + 
            $("#datepickerTo").val() + '&aggr=' + 
            $("#choice :radio:checked").val());
    	} else {
    		alert('Invalid inputs');
    	}
    });
    
    
    $("#botonGraficas").click(function() {
        // Llamada AJAX para mostrar los graficos
        apagar("loading");
        var request = {
          "measure"  	: $("#measureSelect option:selected").val(),	
          "yearFrom"    : $("#fromYear option:selected").val(),
          "yearTo"      : $("#toYear option:selected").val(),
          "scale"		: $("#scaleSelect option:selected").val(),
          "latitude"	: $("#latitude").val(),
          "longitude"	: $("#longitude").val()
        };
        
        if (checkDataGraphs(request)) {
            $.ajax({
              type: "POST",
              url: "ajaxGraficos",
              data: request,
              dataType: 'json'
            }).done(function(data) {
                if (!data['error']) {
                	showResultsGraphics(data, request);
                }
            }).always(function() {
                encender("loading"); 
            });
        } else {
            encender("loading");
            alert("Invalid inputs");
        }
    });
    
    
    $("#getAnalyzeButton").click(function() {
    	// Llamada AJAX para mostrar los resultados de los calculos
        apagar("loading");
        var request = {
          "surface"   	: $("#surface").val(),
          "generators"	: $("#generators").val(),
          "latitude"	: $("#latitude").val(),
          "longitude"	: $("#longitude").val(),
          "efficiency"	: $("#efficiency option:selected").val()
        };
        
        if (checkDataCalculus(request)) {
            $.ajax({
              type: "POST",
              url: "ajaxCalculos",
              data: request,
              dataType: 'json'
            }).done(function(data) {
                if (!data['error']) {
                    showResultsCalculus(data, request);
                }
            }).always(function() {
                encender("loading"); 
            });
        } else {
            encender("loading");
            alert("Invalid inputs");
        }
    });
    
    
    $("#scaleSelect").change(function() {
    	if ( $("#scaleSelect").val() == 2 ) {
    		apagar("pop");
    	} else {
    		$("#locationButton").hide();
    	}
    });
    
    
    $("#locationButton").click(function() {
    	apagar("pop");
    });
    
    
    $("#acceptPop").click(function() {
    	encender("pop");
    	if ( $("#latitude").val() != "" && $("#longitude").val() != "" ) {
    		$("#saveScale").html('Concrete Place:<br />' + 
    				'Lat: ' + $("#latitude").val() + '<br />' + 
    				'Lon: ' +  $("#longitude").val());
    		$("#locationButton").show();
    	} else {
    		$("#saveScale").html('(National)');
    		$("#scaleSelect option[value=1]").attr("selected", true);
    		$("#locationButton").hide();
    	}
    });
    
    
    $("#selectCoordinates").click(function() {
    	apagar("pop");
    });
    
    
    $("#acceptCoordinates").click(function() {
    	encender("pop");
    	if ( $("#latitude").val() != "" && $("#longitude").val() != "" ) {
    		$("#lat-resume").text($("#latitude").val());
    		$("#lon-resume").text($("#longitude").val());
    	} else {
    		$("#lat-resume").text("");
    		$("#lon-resume").text("");
    	}
    });


    var i;
    for(i = 0; i < 30; i++)
    {  var option = "<option value='"+ (1983+i) +"'>"+ (1983+i) +"</option>"
      $(option).appendTo("#fromYear");
      $(option).appendTo("#toYear");
    }

    $( "#datepickerFrom" ).datepicker();
    $( "#datepickerTo" ).datepicker();

    $( "#accordion" ).accordion();
    if ( $("#pop").length > 0 ) {
    	$("#locationButton").hide();
    }
    
    $("#accordion").find('h3').filter(':contains(Scale)').hide();
    $("#accordion").find('h3').filter(':contains(Scale)').next().hide();
    

    $("<option value='1'> January </option>").appendTo("#fromMonth");
    $("<option value='2'> February </option>").appendTo("#fromMonth");
    $("<option value='3'> March </option>").appendTo("#fromMonth");
    $("<option value='4'> April </option>").appendTo("#fromMonth");
    $("<option value='5'> May </option>").appendTo("#fromMonth");
    $("<option value='6'> June </option>").appendTo("#fromMonth");
    $("<option value='7'> July </option>").appendTo("#fromMonth");
    $("<option value='8'> August </option>").appendTo("#fromMonth");
    $("<option value='9'> September </option>").appendTo("#fromMonth");
    $("<option value='10'> October </option>").appendTo("#fromMonth");
    $("<option value='11'> November </option>").appendTo("#fromMonth");
    $("<option value='12'> December </option>").appendTo("#fromMonth");

    $("<option value='1'> January </option>").appendTo("#toMonth");
    $("<option value='2'> February </option>").appendTo("#toMonth");
    $("<option value='3'> March </option>").appendTo("#toMonth");
    $("<option value='4'> April </option>").appendTo("#toMonth");
    $("<option value='5'> May </option>").appendTo("#toMonth");
    $("<option value='6'> June </option>").appendTo("#toMonth");
    $("<option value='7'> July </option>").appendTo("#toMonth");
    $("<option value='8'> August </option>").appendTo("#toMonth");
    $("<option value='9'> September </option>").appendTo("#toMonth");
    $("<option value='10'> October </option>").appendTo("#toMonth");
    $("<option value='11'> November </option>").appendTo("#toMonth");
    $("<option value='12'> December </option>").appendTo("#toMonth");

}

window.tabs = function (){
    $('.tab').on('click', function(e){
    	// Panel derecho
        e.preventDefault();
        var id = $(this).attr('href');
        $('.contenido').css('display', 'none');
        $('#'+id).css('display', 'block');
        $('.tab').removeClass('active');
        $(this).addClass('active');
        
        // Accordion headers
        var headsMap = [
            $("#accordion").find('h3').filter(':contains(Aggregator)'),
            $("#accordion").find('h3').filter(':contains(Monthly)'),
            $("#accordion").find('h3').filter(':contains(Daily)')
        ];
        
        var headsGraph = [
	        $("#accordion").find('h3').filter(':contains(Scale)')
        ];
        
        // Botones y leyenda
        if ($("#botonMapa").css("display") == "inline" && $(this).attr('id') == "cambiaGraficas") {
        	//------------------------------------------------------
        	// GRAPHS TAB
        	//------------------------------------------------------
        	$("#botonMapa").css({ display: "none" });
        	$("#botonGraficas").css({ display: "inline" });
        	$(".leyenda").css({ display: "none" });
        	for (i=0; i<headsMap.length; i++) {
        		headsMap[i].hide();
        		headsMap[i].next().hide();
        	}
        	for (i=0; i<headsGraph.length; i++) {
        		headsGraph[i].show();
        	}
        	
        	var active = $("#accordion").accordion("option", "active");
        	if (active != 0 && active != 3) {
        		$("#accordion").accordion("option", "active", 0);
        	}
        	$("#pop").hide();
        	
        } else if ($("#botonMapa").css("display") == "none" && $(this).attr('id') == "cambiaMapa") {
        	//------------------------------------------------------
        	// MAP TAB
        	//------------------------------------------------------
        	$("#accordion").find('h3').filter(':contains(Monthly)').show();
        	$("#botonMapa").css({ display: "inline" });
        	$("#botonGraficas").css({ display: "none" });
        	$(".leyenda").css({ display: "block" });
        	for (i=0; i<headsGraph.length; i++) {
        		headsGraph[i].hide();
        		headsGraph[i].next().hide();
        	}
        	for (i=0; i<headsMap.length; i++) {
        		headsMap[i].show();
        	}
        	
        	var active = $("#accordion").accordion("option", "active");
        	if (active == 5) {
        		$("#accordion").accordion("option", "active", 0);
        	}
        	$("#pop").hide();
        }
    });
}


var ge;
var currentKmlObject = null;

 function finished(kmlObject) {
    if (!kmlObject) {
      setTimeout(function() {
        alert('Bad or null KML.');
      }, 0);
      return;
    }else{
      currentKmlObject = kmlObject;
      ge.getFeatures().appendChild(kmlObject);
    }
    encender("loading");
  }

  var map_picker; 
  
window.pick = function(){
    if( $('#map_picker #get').length > 0){
         var mapOptions = {
            zoom: 4,
            center: new google.maps.LatLng(40.4445904, -3.697276800000054),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById('map_picker_map'),
                mapOptions);
        geocoder = new google.maps.Geocoder();
        google.maps.event.addListener(map, "click", function(event) {
                var lat = event.latLng.lat();
                var lng = event.latLng.lng();
                $('#latitude').val(lat);
                $('#longitude').val(lng);
               
            });
        $('#map_picker #get').on('click', function(e){
            e.preventDefault();
            codeAddress();
               
            });
    }
}

  

  function codeAddress() {
    //In this case it gets the address from an element on the page, but obviously you  could just pass it to the method instead
    var address = document.getElementById("address").value;

    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        //In this case it creates a marker, but you can get the lat and lng from the location.LatLng
        map.setCenter(results[0].geometry.location);
        $('#latitude').val(results[0].geometry.location.jb);
        $('#longitude').val(results[0].geometry.location.kb);
            
        var marker = new google.maps.Marker({
            map: map, 
            position: results[0].geometry.location
        });
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }

window.init = function() {
   addBindings();
   tabs();
   //picker coordinates
   pick();
   google.earth.createInstance('map3d', initCallback, failureCallback);
}


window.getInformation = function (url){
  if (currentKmlObject) {
    ge.getFeatures().removeChild(currentKmlObject);
    currentKmlObject = null;
    }
  google.earth.fetchKml(ge, url, finished);
}

function initCallback(instance) {
  ge = instance;
  ge.getWindow().setVisibility(true);

  // add a navigation control
  ge.getNavigationControl().setVisibility(ge.VISIBILITY_AUTO);
}

function failureCallback(errorCode) {
}


var chartData = []; // para graficos

// Muestra los resultados de los graficos de un JSON devuelto tras la llamada AJAX
function showResultsGraphics(response, request) {
	// Si no se ha dibujado antes el contenedor de graficos lo dibujamos
	if ( $("#chartprev").length == 1 ) {
		chart.write("chartdiv");
	}
	
    var chartData = [];
    var y = request.yearFrom;
    
    for (var i=0; i<response.max.length; i++) {
        chartData.push({
            year: y,
            max: response.max[i],
            min: response.min[i],
            avg: response.avg[i]
        }); 
        y++;
    }
    
    chart.dataProvider = chartData;
    chart.validateData();
}


//Muestra los resultados de los calculos de un JSON devuelto tras la llamada AJAX
function showResultsCalculus(response, request) {
	$("#resEnergyGenerated").text(separadorMiles(response["energyGenerated"]/1000));
	$("#resHomeEquivalent").text(separadorMiles(response["homeEquivalent"]));
	$("#resMonetaryEquivalent").text(separadorMiles(response["monetaryEquivalent"]));
	$("#resInstallationCost").text(separadorMiles(response["installationCos"]));
	$("#resTimeRecover").text(separadorMiles(response["timeRecover"]));
}


function separadorMiles(number) {
	n = number.toFixed(2);
	out = String(n).split("").reverse().join("")
    .replace(/(\d{3}\B)/g, "$1,")
    .split("").reverse().join("");
	
	out.replace(".", "@");
	out.replace(",", ".");
	out.replace("@", ",");
	return out;
}


// Comprueba si los años son validos, si la medida es correcta y la escala
function checkDataGraphs(request) {
    year_now = (new Date).getFullYear();
    return ($.isNumeric(request['yearFrom']) && 
    	$.isNumeric(request['yearTo']) &&
    	$.isNumeric(request['scale']) &&
    	request['yearTo'] >= request['yearFrom'] &&
    	request['yearFrom'] >= 1983 && request['yearTo'] <= year_now &&
    	(request['scale'] == 1 || (request['scale'] == 2 && 
    			$.isNumeric(request['latitude']) &&
    			$.isNumeric(request['longitude'])))
       	);
}


// Comprueba si los datos son validos para los calculos
function checkDataCalculus(request) {
	return ($.isNumeric(request['surface']) &&
		$.isNumeric(request['generators']) &&
		$.isNumeric(request['latitude']) &&
		$.isNumeric(request['longitude']) &&
		$.isNumeric(request['efficiency']) &&
		request['efficiency'] >= 0 &&
		request['efficiency'] <= 2
		);
}


// Loading
function getPageSize() {
    var xScroll, yScroll;
    if (window.innerHeight && window.scrollMaxY) {  
        xScroll = window.innerWidth + window.scrollMaxX;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
        xScroll = document.body.scrollWidth;
        yScroll = document.body.scrollHeight;
    } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
        xScroll = document.body.offsetWidth;
        yScroll = document.body.offsetHeight;
    }
    var windowWidth, windowHeight;
    if (self.innerHeight) { // all except Explorer
        if(document.documentElement.clientWidth){
            windowWidth = document.documentElement.clientWidth; 
        } else {
            windowWidth = self.innerWidth;
        }
        windowHeight = self.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
        windowWidth = document.documentElement.clientWidth;
        windowHeight = document.documentElement.clientHeight;
    } else if (document.body) { // other Explorers
        windowWidth = document.body.clientWidth;
        windowHeight = document.body.clientHeight;
    }   
    // for small pages with total height less then height of the viewport
    if(yScroll < windowHeight){
        pageHeight = windowHeight;
    } else { 
        pageHeight = yScroll;
    }
    // for small pages with total width less then width of the viewport
    if(xScroll < windowWidth){  
        pageWidth = xScroll;        
    } else {
        pageWidth = windowWidth;
    }
    arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight);
    return arrayPageSize;
}


// Efecto apagar
function apagar(id) {
    $('#Playerholder, #Player, embed, object')
    .css({ 'visibility' : 'visible' });
    
	/* Show and center loading div */
	$('#' + id).css('display', 'block');
	var top = Math.max($(window).height() / 2 - $("#" + id)[0].offsetHeight / 2, 0);
	var left = Math.max($(window).width() / 2 - $("#" + id)[0].offsetWidth / 2, 0);
	$("#" + id).css('top', top + "px");
	$("#" + id).css('left', left + "px");
    
    $('body')
    .append('<div id="lightsoff-background"></div>');

    var page_size = getPageSize();
    
    $('#lightsoff-background')
    .css({
        backgroundColor:    "#000",
        opacity:            0.9,
        width:              page_size[2],
        height:             page_size[1]
    }).show();
}

// Efecto encender
function encender(id) {
    $('#lightsoff-background')
    .fadeOut(function() { 
        $('#lightsoff-background').remove(); 
    });
    $('#' + id).css('display', 'none');
    return false;
}


$(document).ready(function(){
    init();
    $("#saveMeasure").html('(' + $("#measureSelection option:selected").text() + ')');
    $("#saveAggreg").html('(Total)');
    $("#saveScale").html('(' + $("#scaleSelection option:selected").text() + ')');
});

// Validate
$(function(){
  var auxFromYear;
  var auxToYear;

  var auxFromMonth;
  var auxToMonth;

  
  var auxFromDay;
  var auxToDay;

  var auxMeasure;

  var auxAggreg;
  
  var auxScale;

  var validate = true;
 
  
  var setAuxFromToYears = function(){
   auxFromYear = ( $("#fromYear option:selected").text().length == 0) ? 'Select one'  :  parseInt($("#fromYear option:selected").text());    
   auxToYear = ( $("#toYear option:selected").text().length == 0) ? 'Select one' :  parseInt($("#toYear option:selected").text());    
   $("#saveYears").html("(" +auxFromYear+ "-" + auxToYear + ")");
    validar(auxFromYear, auxToYear, 1);
  }
  $("#fromYear").change(function() {
    setAuxFromToYears();
  });
  $("#toYear").change(function() {
    setAuxFromToYears();  
  });
    
  var setAuxFromToMonths = function(){
    auxFromMonth = ( $("#fromMonth option:selected").text().length == 0) ? 'Select one' : $("#fromMonth option:selected").text();    
    auxToMonth = ( $("#toMonth option:selected").text().length == 0) ? 'Select one' : $("#toMonth option:selected").text();    
    
    $("#saveMonths").html("(" + auxFromMonth + "-" + auxToMonth + ")");
    validar($("#fromMonth option:selected").val(), $("#toMonth option:selected").val(), 2);
  }
  $("#fromMonth").change(function() {
    setAuxFromToMonths();    
  });
  $("#toMonth").change(function() {
   setAuxFromToMonths();  
  });
    
    
   var setAuxFromToDays = function(){
    auxFromDay = ( $("#datepickerFrom").val().length == 0) ? 'Select one' :$("#datepickerFrom").val();    
    auxToDay = ( $("#datepickerTo").val().length == 0) ? 'Select one' : $("#datepickerTo").val();    
    
    
    $("#saveDays").html("(" + auxFromDay + "-" + auxToDay + ")");
    validar(date2int(auxFromDay), date2int(auxToDay), 3);
  }
  $("#datepickerFrom").change(function() {
     setAuxFromToDays(); 
   
  });
  $("#datepickerTo").change(function() {
    setAuxFromToDays(); 
        
  });

  $("#measureSelection").change(function() {
    auxMeasure = ($("#measureSelection option:selected").text());
    $("#saveMeasure").html('(' + auxMeasure + ')');
  });
  
  $("#scaleSelection").change(function() {
	  auxScale = ($("#scaleSelection option:selected").text());
    $("#saveScale").html('(' + auxScale + ')');
  });

  $("input[name='repeat']").change(function() {
    if(this.checked) {
      switch (parseInt(this.value)) {
        case 1:
        auxAggreg = "Total";
        break;
        case 2:
        auxAggreg = "Average";
        break;
        case 3:
        auxAggreg = "Maximum";
        break;
        case 4:
        auxAggreg = "Minimum";
        break;
      }
      $("#saveAggreg").html('(' + auxAggreg + ')');
    }
  });
});

function validar(from, to, type) {
  switch (type) {
    case 1:
    if ((to-from)<0)
      validateYear = false;
    else
      validateYear = true;
    break;
    case 2:
    if ((to-from)<0)
      validateMonth = false;
    else
      validateMonth = true;
    break;
    case 3:
    if ((to-from)<0)
      validateDay = false;
    else
      validateDay = true;
    break;
  }
}

function date2int(date) {
  if (date != undefined) {
    var n=date.split("/");
    return parseInt(n[0])*12 + parseInt(n[1]) + parseInt(n[2])*365;
  }
}
