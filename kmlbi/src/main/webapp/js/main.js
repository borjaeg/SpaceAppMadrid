var validateYear = false;
var validateMonth = true;
var validateDay = true;

window.addBindings = function(){
    $( "#choice" ).buttonset();
    $("#botonMapa").click(
      function() {
    	if (validateYear && validateMonth && validateDay) {
          apagar();
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
    		alert('Invalid');
    	}
    });
    
    
    $("#botonGraficas").click(function() {
        // Llamada AJAX para mostrar los graficos
        apagar();
        var request = {
          "measure"  	: $("#measureSelect option:selected").val(),	
          "yearBegin"   : $("#fromYear option:selected").val(),
          "yearEnd"     : $("#toYear option:selected").val()
        };
        
        if (checkYears(request.yearBegin, request.yearEnd)) {
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
                encender(); 
            });
        } else {
            encender();
            alert("Invalid years");
        }
    });
    
    
    $("#getAnalyzeButton").click(function() {
    	// Llamada AJAX para mostrar los resultados de los calculos
        apagar();
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
                encender(); 
            });
        } else {
            encender();
            alert("Datos invalidos");
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

    $(function() {
        $( "#accordion" ).accordion();
      });

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
        e.preventDefault();
        var id = $(this).attr('href');
        $('.contenido').css('display', 'none');
        $('#'+id).css('display', 'block');
        $('.tab').removeClass('active');
        $(this).addClass('active');
        
        
        if ($("#botonMapa").css("display") == "inline" && $(this).attr('id') == "cambiaGraficas") {
        	$("#botonMapa").css({ display: "none" });
        	$("#botonGraficas").css({ display: "inline" });
        } else if ($("#botonMapa").css("display") == "none" && $(this).attr('id') == "cambiaMapa") {
        	$("#botonMapa").css({ display: "inline" });
        	$("#botonGraficas").css({ display: "none" });
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
      console.log("LLEGO BIEN");
      currentKmlObject = kmlObject;
      ge.getFeatures().appendChild(kmlObject);
    }
    encender();
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

  // add some layers
  //ge.getLayerRoot().enableLayerById(ge.LAYER_BORDERS, true);
  //ge.getLayerRoot().enableLayerById(ge.LAYER_ROADS, true);

}

function failureCallback(errorCode) {
}


var chartData = []; // para graficos

// Muestra los resultados de los graficos de un JSON devuelto tras la llamada AJAX
function showResultsGraphics(response, request) {
    var chartData = [];
    var y = request.yearBegin;
    
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


// Comprueba si los años son validos
function checkYears(yearBegin, yearEnd) {
    year_now = (new Date).getFullYear();
    return ($.isNumeric(yearBegin) && $.isNumeric(yearEnd) &&
        yearBegin >= 1983 && yearEnd <= year_now &&
        yearEnd >= yearBegin);
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
function apagar() {
    $('#Playerholder, #Player, embed, object')
    .css({ 'visibility' : 'visible' });
    $('#loading').css('display', 'block');
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
function encender() {
    $('#lightsoff-background')
    .fadeOut(function() { 
        $('#lightsoff-background').remove(); 
    });
    $('#loading').css('display', 'none');
    return false;
}


$(document).ready(function(){
    init();
    $("#saveMeasure").html('(' + $("#measureSelection option:selected").text() + ')');
    $("#saveAggreg").html('(Total)');
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

  var validate = true;

  $("#fromYear").change(function() {
    auxFromYear = parseInt($("#fromYear option:selected").text());
    $("#saveYears").html("(" + auxFromYear + "-" + auxToYear + ")");
    validar(auxFromYear, auxToYear, 1);
  });
  $("#toYear").change(function() {
    auxToYear = parseInt($("#toYear option:selected").text());
    $("#saveYears").html("(" + auxFromYear + "-" + auxToYear + ")");
    validar(auxFromYear, auxToYear, 1);
  });

  $("#fromMonth").change(function() {
    auxFromMonth = ($("#fromMonth option:selected").text());
    $("#saveMonths").html("(" + auxFromMonth + "-" + auxToMonth + ")");
    validar($("#fromMonth option:selected").val(), $("#toMonth option:selected").val(), 2);
  });
  $("#toMonth").change(function() {
    auxToMonth = ($("#toMonth option:selected").text());
    $("#saveMonths").html("(" + auxFromMonth + "-" + auxToMonth + ")");
    validar($("#fromMonth option:selected").val(), $("#toMonth option:selected").val(), 2);
  });

  $("#datepickerFrom").change(function() {
    auxFromDay = ($("#datepickerFrom").val());
    $("#saveDays").html("(" + auxFromDay + "-" + auxToDay + ")");
    validar(date2int(auxFromDay), date2int(auxToDay), 3);
  });
  $("#datepickerTo").change(function() {
    auxToDay = ($("#datepickerTo").val());
    $("#saveDays").html("(" + auxFromDay + "-" + auxToDay + ")");
    validar(date2int(auxFromDay), date2int(auxToDay), 3);
  });

  $("#measureSelection").change(function() {
    auxMeasure = ($("#measureSelection option:selected").text());
    $("#saveMeasure").html('(' + auxMeasure + ')');
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
