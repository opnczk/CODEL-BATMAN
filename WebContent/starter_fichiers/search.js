(function() {
        var morphSearch = document.getElementById( 'morphsearch' ),
                form = document.getElementById('morphsearch-form'),
                formFront = document.getElementById('morphsearch-form-front'),
                input = document.getElementById( 'morphsearch-input' ),
                inputOpen = document.getElementById( 'morphsearch-input-open' ),
                sidebarToggle = document.getElementById('sidebar-toggle'),
                ctrlClose = morphSearch.querySelector( 'span.morphsearch-close' ),
                submitBtn = morphSearch.querySelector('#search-btn'),
                timeOutFn = null,
                isOpen = isAnimating = false,
                keySearchTrigg = 0,
				scrollVal = 0,
                toggleSearch = function(evt) {
                    // return if open and the input gets focused
                    if( evt.type.toLowerCase() === 'focus' && isOpen ) return false;
                    if( evt.type.toLowerCase() === 'submit' ){
                        var searchHtml = '<span class="dummy-media-object"> <span class="img-search-placeholder"><i class="fa fa-spin fa-circle-o-notch"></i></span><h3>Recherche en cours...</h3></span>';
                        document.getElementById('users-results').innerHTML = searchHtml;
                        document.getElementById('companies-results').innerHTML = searchHtml;
                        document.getElementById('orders-results').innerHTML = searchHtml;
                        adminSearchAjax( evt );
                        if( isOpen )return false;
                    }

                    var offsets = morphSearch.getBoundingClientRect();
                    if( isOpen ) {
                        classie.remove( morphSearch, 'open' );
                        classie.remove(sidebarToggle, 'hidden');
						document.body.style.overflowY = "visible";
						document.documentElement.scrollTop = document.body.scrollTop = scrollVal;
                        // trick to hide input text once the search overlay closes
                        // todo: hardcoded times, should be done after transition ends
                        if( input.value !== '' ) {
                            setTimeout(function() {
                                classie.add( morphSearch, 'hideInput' );
                                setTimeout(function() {
                                    classie.remove( morphSearch, 'hideInput' );
                                    input.value = '';
                                    inputOpen = '';
                                }, 300 );
                            }, 500);
                        }

                        input.blur();
                    }
                    else {
                        classie.add( morphSearch, 'open' );
                        classie.add(sidebarToggle, 'hidden');
						document.body.style.overflowY = "hidden";
						scrollVal = document.documentElement.scrollTop || document.body.scrollTop;
						document.documentElement.scrollTop = document.body.scrollTop = 0;
						/*/window.scrollTo(0, 0);*/
                        inputOpen.focus();
                    }
                    isOpen = !isOpen;
                },
                submitFormFn = function(){
                    /*var xhr = getLastAjaxRequest();
                    if(xhr != null)
                        xhr.abort();
                    keySearchTrigg = 0;
                    submitBtn.click();*/
                },
                 searchTrigger = function() {
                    if(keySearchTrigg < 4){
                        if(timeOutFn != null)
                            clearTimeout(timeOutFn);
                        keySearchTrigg ++;
                        timeOutFn = setTimeout(submitFormFn(), 3000);
                    }else{
                        if(timeOutFn != null)
                            clearTimeout(timeOutFn);
                        submitFormFn();
                    }
                 };

        // events
        form.addEventListener( 'submit', toggleSearch );
        formFront.addEventListener( 'submit', toggleSearch );
        ctrlClose.addEventListener( 'click', toggleSearch );
        // esc key closes search overlay
        // keyboard navigation events
        document.addEventListener( 'keydown', function( ev ) {
            var keyCode = ev.keyCode || ev.which;
            if( keyCode === 27 && isOpen ) {
                toggleSearch(ev);
            }
        } );
        document.getElementById('morphsearch-input').addEventListener( 'keydown', function( ev ) {
            document.getElementById('morphsearch-input-open').value = ev.target.value;
            if( !isOpen ) {
                toggleSearch(ev);
            }
            var keyCode = ev.keyCode || ev.which;
            if(keyCode != 8 && keyCode != 46) { // backspace

                searchTrigger();
            }
        } );
        document.getElementById('morphsearch-input-open').addEventListener( 'keydown', function( ev ) {
            var keyCode = ev.keyCode || ev.which;
            $.ajax({
            	  type: "POST",
            	  url: "/GestionContact/ResearchAllServlet",
            	  data: {"param":this.value},
            	  success: function(data){
            		 var json = JSON.parse(data);
            		 var html = "";
            		 
            		 json.contacts.forEach(function(entry) {
            			 var contactHtml = "";
	               		 contactHtml += '<a class="dummy-media-object" href="./profile?id='+entry.id+'">';
	               		 contactHtml += '<img class="round" src="./starter_fichiers/default-user.jpg" alt="">';
	               		 contactHtml +=	'<h3>'+entry.first_name+' '+entry.last_name+'</h3>'
	               		 contactHtml += '<p>'+entry.email+'</p>';
	               		 contactHtml += '</a>';
	               		 html += contactHtml;
					});
            		 $("#users-results").html(html);
            		 
            		 
            		 
            	  }
            	});
            if(keyCode != 8 && keyCode != 46) { // backspace
                searchTrigger();
            }
        } );
        /***** for demo purposes only: don't allow to submit the form *****/
        morphSearch.querySelector( 'button[type="submit"]' ).addEventListener( 'click', function(ev) { ev.preventDefault(); } );
    })();