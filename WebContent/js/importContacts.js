var clientId = "128124732452-h8ja44bqta91s95ui0empsgde5nj122i.apps.googleusercontent.com";
          var apiKey = '6qlRPKgljuED0dI2XglPcMAs';
          var scopes = 'https://www.googleapis.com/auth/contacts.readonly';

          $(document).on("click","#importContacts", function(event){
        	event.preventDefault();
            gapi.client.setApiKey(apiKey);
            window.setTimeout(authorize);
          });

          function authorize() {
            gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthorization);
          }

          function handleAuthorization(authorizationResult) {
            if (authorizationResult && !authorizationResult.error) {
              $.get("https://www.google.com/m8/feeds/contacts/default/thin?alt=json&access_token=" + authorizationResult.access_token + "&max-results=500&v=3.0",
                function(response){
            	  console.log(response);
                  //process the response here
                  $("#triggImport").click();
                  var html = "";
                  //$("#import-modal-body").html(JSON.stringify(response, null, 4)); // (Optional) beautiful indented output.);
                  	if(response.feed.entry){
                  		var arr = response.feed.entry;
                  		var count = 0;
                  		for (var i = 0; i < arr.length; i++) {
                  			var contactHtml = "";
                  			var toAdd = true;
                  		    var contact = arr[i];
                  		    //console.log(i);
                  		  contactHtml += "<div class=\"col-md-12\">";
                  		contactHtml += "<div class=\"col-md-6\">";
                  		    if(contact.hasOwnProperty('gd$name')){
                  		    	if(contact.gd$name.hasOwnProperty('gd$givenName')){
                  		    		contactHtml += " "+contact.gd$name.gd$givenName.$t;
                  		    		contactHtml += "<input name=\"contacts["+count+"].first_name\" value=\"";
                  		    		contactHtml += contact.gd$name.gd$givenName.$t;
                  		    		contactHtml += "\" hidden />";
                  		    	}
                  		    	if(contact.gd$name.hasOwnProperty('gd$familyName')){
                  		    		contactHtml += contact.gd$name.gd$familyName.$t;
                  		    		contactHtml += "<input name=\"contacts["+count+"].last_name\" value=\"";
                  		    		contactHtml += " "+contact.gd$name.gd$familyName.$t;
                  		    		contactHtml += "\" hidden />";
                  		    	}
                  		    }else{
                  		    	toAdd = false;
                  		    }
                  		  contactHtml += "</div>";
                  		contactHtml += "<div class=\"col-md-6\">";
	                  		  if(contact.hasOwnProperty('gd$email')){
	                  			contactHtml += contact.gd$email[0].address;
	                  			contactHtml += "<input name=\"contacts["+count+"].email\" value=\"";
	                  			contactHtml += contact.gd$email[0].address;
	                  			contactHtml += "\" hidden />";
	                  		    }else{
	                  		    	toAdd = false;
	                  		    }
	                  		contactHtml += "</div>";
	                  		contactHtml += "</div>";
	                  		if(toAdd){
	                  			html += contactHtml;
	                  			count ++;
	                  		}
                  		}
                  	}
                  	html += "<input name=\"nb_contacts\" value=\""+count+"\" hidden/>"
                  	$("#import-modal-body").html(html);
                });
            }
          }