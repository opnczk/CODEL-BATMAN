var GMapsHelper = {};

(function(win, doc){

    GMapsHelper.map = null;
    GMapsHelper.markers = [];
    GMapsHelper.default_lat = 48.8281026;
    GMapsHelper.default_lng = 2.350302899999974;
    GMapsHelper.default_zoom = 10;
    GMapsHelper.default_selector = 'map';
    GMapsHelper.APIKey = 'AIzaSyBnvYWNnpiWaC7jGS7yMakh6gffoambtGs';
    GMapsHelper.defer = false;
    GMapsHelper.async = false;
    GMapsHelper.callback = true;
    GMapsHelper.autocomplete = false;
    GMapsHelper.context = {};

    GMapsHelper.refTab = {
        "postal_code" : 'ADD_ZIPCODE',
        "country" : 'ADD_COUNTRY',
        "locality" : 'ADD_CITY',
        "street_number" : 'ADD_ST_NB',
        "route" : "ADD_STREET",
    };

    GMapsHelper.init = function (context) {

    	GMapsHelper.context = context;
    	
        if(context.type == 'address')
            GMapsHelper.autocomplete = true;

        if(context.init) {
            if (context.init.async)
                GMapsHelper.async = true;
            if (context.init.defer)
                GMapsHelper.defer = true;
            if(context.init.APIKey)
                GMapsHelper.APIKey = context.init.APIKey;
        }

        GMapsHelper.callback = 'GMapsHelper.genericCallBack';
        GMapsHelper.generateAPICall();
    };
        
    GMapsHelper.generateAPICall = function (){
		/*This is meant to compare existing gmaps calls and adapt to them, in case there's multiple GMapsHelper.init calls with different parameters*/
        /*var scriptEl = document.querySelector("script[src*='https://maps.googleapis.com/maps/api/js']");
        console.log(scriptEl.getAttribute('src'));
		var curQueryElChunks = scriptEl.getAttribute('src').split("?")[1].split('&');
		var curQueryEl = [];
		for(k in curQueryElChunks){
			var elDef = curQueryElChunks[k].split('=');
			curQueryEl[elDef[0]] = elDef[1];
		}*/
		
		var scriptElement = document.createElement('script');

        var queryEl = [];
        var srcStr = "https://maps.googleapis.com/maps/api/js?key="+GMapsHelper.APIKey;
        if(GMapsHelper.autocomplete)
            queryEl['libraries'] = "places";
            //srcStr += "&libraries=places";
        queryEl['callback'] = GMapsHelper.callback
            //srcStr += "&callback="+GMapsHelper.callback;

        for(k in queryEl)
            srcStr += '&'+k+'='+queryEl[k];

        scriptElement.setAttribute('src', srcStr);
        document.body.appendChild(scriptElement);
    }

    GMapsHelper.genericCallBack = function () {
    	var context = GMapsHelper.context;
        var mapOptions = {
            zoom: (context.zoom || GMapsHelper.default_zoom),
            center: new google.maps.LatLng(
                (context.lat || GMapsHelper.default_lat),
                (context.lng || GMapsHelper.default_lng)
            ),
        };

        GMapsHelper.map = new google.maps.Map(document.getElementById((context.selector || GMapsHelper.default_selector)), mapOptions);

        if(context.type == 'address'){
            GMapsHelper.initAddressSearch();
        }else {
            if (context.marker){
                GMapsHelper.addMarker(context.lat, context.lng);
                
            }

            if (context.type == "markedMap" && context.start && context.end) {
                GMapsHelper.markedMap(context);
            }
        }
    }
        
    /*AddressMap Initiliaze*/
    GMapsHelper.initAddressSearch = function () {
        var autocomplete = new google.maps.places.Autocomplete(
            (document.getElementById('autocomplete')), {
                types: ['geocode']
            });

        autocomplete.addListener('place_changed', function() {
            var place = autocomplete.getPlace();

            if (!place.geometry) {
                return;
            }

            var placeId = place.place_id;
            var lat = place.geometry.location.lat();
            var lng = place.geometry.location.lng();
            var formattedAddress = place.formatted_address;

            $('.address-field[name="PLACE_ID"]').val(placeId);
            $('.address-field[name="ADD_LAT"]').val(lat);
            $('.address-field[name="ADD_LNG"]').val(lng);

            var str_nb = null;
            var route = null;

            for(idxadd in place.address_components)
            {
                var tabElem =  place.address_components[idxadd];
                for(idxtypes in tabElem.types) {
                    var typeElem = tabElem.types[idxtypes];
                    var inputName = GMapsHelper.refTab[typeElem];

                    if(inputName != null){
                        $('.address-field[name="'+inputName+'"]').val(tabElem.long_name);
                        console.log( $('.address-field[name="'+inputName+'"]').val());
                    }
                    if(typeElem == "street_number")
                    	$('.address-field[name="ADD_ST_NB"]').val(tabElem.long_name);
                    /*if(typeElem == "route")
                        route = tabElem.long_name;*/
                }
            }

            /*$('.address-field[name="ADD_ST_NB"]').val(str_nb);
            /*$('.address-field[name="ADD_LINE_1"]').val(str_nb+' '+route);
            console.log($('.address-field[name="ADD_LINE_1"]').val());*/
            $('#selectedAddress').show();
            $("#selectedAddressInput").val($("#autocomplete").val());
            $('#autocomplete').hide();

            GMapsHelper.initAddressMap(lat, lng);
        });
    }

    GMapsHelper.initAddressMap = function(lat, lng) {
        //$(document.getElementById('address-gmaps-wrapper')).show();
        google.maps.event.trigger(GMapsHelper.map, 'resize');

        GMapsHelper.deleteMarkers();
        GMapsHelper.addMarker(lat, lng);
        GMapsHelper.setCenter(lat, lng);
    }

    GMapsHelper.markedMap = function(context) {
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer({ map: GMapsHelper.map });

        if(context.pickupZone) {
            for(var key in context.pickupZone){
                var zone = context.pickupZone[key];
                GMapsHelper.drawPickupZone(zone.north, zone.south,zone.east,zone.west);
            }
        }
        GMapsHelper.calculateAndDisplayRoute(directionsDisplay, directionsService, context.start, context.end, context.pos_transp);
    }

    GMapsHelper.calculateAndDisplayRoute = function(directionsDisplay, directionsService, start, end, pos_transp)
    {
        if(pos_transp) {
            var waypts = [];
            waypts.push({
                location: pos_transp,
                stopover: false
            });
        }
        // Retrieve the start and end locations and create a DirectionsRequest using
        // WALKING directions.
        var directionOptions = {
            origin: start.add,
            destination: end.add,
            travelMode: google.maps.TravelMode.DRIVING
        }

        if(pos_transp) {
            directionOptions.waypoints = waypts;
            directionOptions.optimizeWaypoints = false;
        }

        directionsService.route(directionOptions, function(response, status) {
            // Route the directions and pass the response to a function to create
            // markers for each step.
            if (status === google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
                    if(pos_transp)
                        GMapsHelper.addMarker(pos_transp.lat, pos_transp.lng, true);
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
    }

    GMapsHelper.drawPickupZone = function (north, south, east, west)
    {
        var rectangle = new google.maps.Rectangle({
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: '#FF0000',
            fillOpacity: 0.35,
            map: GMapsHelper.map,
            bounds: {
                north: north,
                south: south,
                east: east,
                west: west
            }
        });
        return rectangle;
    }

    GMapsHelper.addMarker = function (lat, lng, transp)
    {
        if(!transp) transp = false;

        var LatLng = new google.maps.LatLng(lat,lng);

        if(transp)
            GMapsHelper.setCenter(lat, lng);

        var markerOpts = {
            position: LatLng,
            map: GMapsHelper.map
        };

        if(transp) {
            /*Customize the marker here*/
        }

        var marker = new google.maps.Marker(markerOpts);

        marker.setMap(GMapsHelper.map);

        GMapsHelper.markers.push(marker);
    }

    GMapsHelper.deleteMarkers = function () {
        GMapsHelper.setMapOnAll(null);
        GMapsHelper.markers = [];
    }

    GMapsHelper.setCenter = function(lat, lng){
        var LatLng = new google.maps.LatLng(lat,lng);

        GMapsHelper.map.setCenter(LatLng);
    }

    GMapsHelper.setMapOnAll = function(map) {
        for (var i = 0; i < GMapsHelper.markers.length; i++) {
            GMapsHelper.markers[i].setMap(map);
        }
    }
}
)(window, document);