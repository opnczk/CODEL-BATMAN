<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.List, com.sar2016.entities.Contact, com.sar2016.entities.Enterprise"
%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html class=" js cssanimations"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Agenda Codel</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="./starter_fichiers/bootstrap.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="./starter_fichiers/d2c8d49ad6.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="./starter_fichiers/ionicons.css">
  <!-- chat css -->
  <link rel="stylesheet" href="./starter_fichiers/chat.css">
  <link rel="stylesheet" href="./starter_fichiers/search.css">
  <link rel="stylesheet" href="./starter_fichiers/spec.css">
  <link rel="stylesheet" type="text/css" href="./starter_fichiers/icons.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="./starter_fichiers/AdminLTE.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
  <link rel="stylesheet" href="./starter_fichiers/skin-blue.css">
  <link rel="stylesheet" type="text/css" href="./starter_fichiers/normalize.css">
  <link rel="stylesheet" type="text/css" href="./starter_fichiers/grid.css">
  <link rel="stylesheet" type="text/css" href="./starter_fichiers/loading.css">
  <script src="./starter_fichiers/modernizr.js"></script>
  <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body style="overflow-y: visible;" class="skin-blue fixed">
 <script type="text/javascript">
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
                  		for (var i = 0; i < arr.length; i++) {
                  			var contactHtml = "";
                  			var toAdd = true;
                  		    var contact = arr[i];
                  		    //console.log(i);
                  		  contactHtml += "<div class=\"col-md-12\">";
                  		contactHtml += "<div class=\"col-md-6\">";
                  		    if(contact.hasOwnProperty('gd$name')){
                  		    	contactHtml += contact.gd$name.gd$fullName.$t;
                  		    }else{
                  		    	toAdd = false;
                  		    }
                  		  contactHtml += "</div>";
                  		contactHtml += "<div class=\"col-md-6\">";
	                  		  if(contact.hasOwnProperty('gd$email')){
	                  			contactHtml += contact.gd$email[0].address;
	                  		    }else{
	                  		    	toAdd = false;
	                  		    }
	                  		contactHtml += "</div>";
	                  		contactHtml += "</div>";
	                  		if(toAdd){
	                  			html += contactHtml;
	                  		}
                  		}
                  	}
                  	$("#import-modal-body").html(html);
                });
            }
          }
        </script>
        <script src="https://apis.google.com/js/client.js"></script>
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">
  
    <!-- Logo -->
    <a href="#" class="logo" style="background: #3c8dbc">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>Agenda </b><i class="fa fa-book find-a-paw-logo-icon"></i></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Agenda </b><i class="fa fa-book find-a-paw-logo-icon"></i></span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
	 <!-- Sidebar toggle button-->
	  <a href="#" id="sidebar-toggle" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
	
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="./starter_fichiers/user2-160x160.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">John Doe</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="./starter_fichiers/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <div style="position: relative; overflow: hidden; width: auto; height: 893px;" class="slimScrollDiv"><section style="height: 893px; overflow: hidden; width: auto;" class="sidebar">
      <!-- search form (Optional) -->
		<form id="morphsearch-form" action="#" method="post" class="sidebar-form morphsearch-form">
			<div class="input-group">
				<input id="morphsearch-input" name="SEARCH_TERMS" class="form-control " placeholder="Recherche..." type="text">
		  <span class="input-group-btn">
			<button type="submit" name="search" id="search-btn" class="btn btn-flat morphsearch-submit"><i class="fa fa-search"></i>
			</button>
		  </span>
			</div>
		</form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <!-- Optionally, you can add icons to the links -->
        <!-- <li><a href="ResearchContact.jsp"><i class="fa fa-user"></i>Search Contact</a></li>-->
        <li><a href="./home"><i class="fa fa-home"></i> <span>Home</span></a></li>
        <li><a href="AddContact.jsp"><i class="fa fa-user-plus"></i> <span>Add Contact</span></a></li>
        <li><a href="AddContactGroup.jsp"><i class="fa fa-users"></i> <span>Add Contact Group</span></a></li>
        <li><a id="importContacts" href="./authRequest"><i class="fa fa-google"></i> <span>Import Contacts From Google</span></a></li>
        <!--  <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span> <i class="fa fa-angle-left pull-right"></i></a>
          <ul class="treeview-menu">
            <li><a href="#">Link in level 2</a></li>
            <li><a href="#">Link in level 2</a></li>
          </ul>
        </li>-->
        
      </ul>
      <!-- /.sidebar-menu -->
    </section><div style="background: rgba(0, 0, 0, 0.2) none repeat scroll 0% 0%; width: 3px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 893px;" class="slimScrollBar"></div><div style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;" class="slimScrollRail"></div></div>
    <!-- /.sidebar -->
  </aside>

  <div id="morphsearch" class="morphsearch">

    <div class="morphsearch-content">
        <form id="morphsearch-form-front" action="{{ url($prefix[0].'/test/') }}" method="post" class="morphsearch-form" data-ajaxsuccesshandler="adminSearchSuccessHandler" data-ajaxerrorhandler="adminSearchErrorHandler">
            <div class="input-group">
                <input id="morphsearch-input-open" name="SEARCH_TERMS" class="form-control " placeholder="Search..." autocomplete="off" type="text">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat morphsearch-submit"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
            <div class="dummy-column">
                <h2>Utilisateurs</h2>
                            <span id="users-results">
                                <span class="dummy-media-object">
                                    <span class="img-search-placeholder">
                                        <i class="fa fa-spin fa-circle-o-notch"></i>
                                    </span>
                                    <h3>Recherche en cours...</h3>
                                </span>
                            </span>
            </div>
            <div class="dummy-column">
                <h2>Entreprises</h2>
                            <span id="companies-results">
                                <span class="dummy-media-object">
                                    <span class="img-search-placeholder">
                                        <i class="fa fa-spin fa-circle-o-notch"></i>
                                    </span>
                                    <h3>Recherche en cours...</h3>
                                </span>
                            </span>
            </div>
            <div class="dummy-column">
                <h2>Commandes</h2>
                            <span id="orders-results">
                                <span class="dummy-media-object">
                                    <span class="img-search-placeholder">
                                        <i class="fa fa-spin fa-circle-o-notch"></i>
                                    </span>
                                    <h3>Recherche en cours...</h3>
                                </span>
                            </span>
            </div>
        </div>
        <span class="morphsearch-close"></span>

    </div>
  
  <!-- Content Wrapper. Contains page content -->
  <div style="min-height: 892px;" class="content-wrapper">
    <!-- Content Header (Page header) -->
   <!-- <section class="content-header">
      <h1>
        Page Header
        <small>Optional description</small>
      </h1>
    </section>-->

    <!-- Main content -->
    <section class="content scrolledContent">
	<!-- Your content here -->
	<div class="col-md-12 center">
	<div style="display: block;" class="row user-infos user2">
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">User information</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-3 col-lg-3 hidden-xs hidden-sm">
                                <img class="img-circle" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100" alt="User Pic">
                            </div>
                            <div class="col-xs-2 col-sm-2 hidden-md hidden-lg">
                                <img class="img-circle" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=50" alt="User Pic">
                            </div>
                            <div class="col-xs-10 col-sm-10 hidden-md hidden-lg">
                                <strong>Cyruxx</strong><br>
                                <dl>
                                    <dt>User level:</dt>
                                    <dd>Administrator</dd>
                                    <dt>Registered since:</dt>
                                    <dd>11/12/2013</dd>
                                    <dt>Topics</dt>
                                    <dd>15</dd>
                                    <dt>Warnings</dt>
                                    <dd>0</dd>
                                </dl>
                            </div>
                            <div class=" col-md-9 col-lg-9 hidden-xs hidden-sm">
                                <strong>Cyruxx</strong><br>
                                <table class="table table-user-information">
                                    <tbody>
                                    <tr>
                                         <%Contact contact = (Contact)request.getAttribute("contact"); %>
						
						                  <% out.println(contact.getFirstName()); %>
						                  <% out.println(contact.getLastName()); %>
						                  <% out.println(contact.getEmail()); %>
                                        <td>User level:</td>
                                        <td>Administrator</td>
                                    </tr>
                                    <tr>
                                        <td>Registered since:</td>
                                        <td>11/12/2013</td>
                                    </tr>
                                    <tr>
                                        <td>Topics</td>
                                        <td>15</td>
                                    </tr>
                                    <tr>
                                        <td>Warnings</td>
                                        <td>0</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <button class="btn btn-sm btn-primary" type="button" data-toggle="tooltip" data-original-title="Send message to user"><i class="glyphicon glyphicon-envelope"></i></button>
                        <span class="pull-right">
                            <button class="btn btn-sm btn-warning" type="button" data-toggle="tooltip" data-original-title="Edit this user"><i class="glyphicon glyphicon-edit"></i></button>
                            <a href="./remove-contact?id=<% out.print(contact.getId());%>"><button class="btn btn-sm btn-danger" type="button" data-toggle="tooltip" data-original-title="Remove this user"><i class="glyphicon glyphicon-remove"></i></button></a>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <!-- Main Footer -->
  <footer class="main-footer" style="min-height: 51px">
    <!--<strong><i class="fa fa-heart"></i></strong>-->
  </footer>
  
  <div class="overlay"></div>
</div>
<!-- ./wrapper -->
<!-- REQUIRED JS SCRIPTS -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel"><i class="fa fa-google"></i> Import Contacts from Google.</h4>
      </div>
      <div id="import-modal-body" class="modal-body">
       	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<!-- jQuery 2.2.0 -->
<script src="./starter_fichiers/jQuery-2.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="./starter_fichiers/bootstrap.js"></script>
<!-- AdminLTE App -->
<script src="./starter_fichiers/app.js"></script>
<script src="./starter_fichiers/jquery.js"></script>
<script src="./starter_fichiers/classie.js"></script>
<script src="./starter_fichiers/search.js"></script>
<script src="./starter_fichiers/masonry.js"></script>
<script src="./starter_fichiers/imagesloaded.js"></script>
<script src="./starter_fichiers/classie.js"></script>
<script src="./starter_fichiers/colorfinder-1.js"></script>
<script src="./starter_fichiers/gridScrollFx.js"></script>
<script>
	var scrolledToHalf = false;
	var scrolledToLastThird = false;

	new GridScrollFx( document.getElementById( 'grid' ), {
		viewportFactor : 0.4
	} );
	$("#grid img").each(function() {
	
		var image = new Image();
		image.src = $(this).attr("src");
		// Calculate aspect ratio and store it in HTML data- attribute
		var aspectRatio = image.naturalWidth / image.naturalHeight;
		$(this).data("aspect-ratio", aspectRatio);
		// Conditional statement
		if(aspectRatio > 1) {
			// Image is landscape
			$(this).css({
				width: "100%",
				height: "auto"
			});
		} else if (aspectRatio < 1) {
			// Image is portrait
			$(this).css({
				maxHeight: "100%",
				width : "auto"
			});
		} else {
			// Image is square
			$(this).css({
				maxWidth: "100%",
				height: "auto"
			});            
		}
	});
	$(window).scroll(function() {
		if($(window).scrollTop() == ($(document).height() - $(window).height())) {
			   // ajax call get data from server and append to the div
			   console.log('Scrolled to bottom '+($(document).height() - $(window).height()));
		}
		if($(window).scrollTop() > ($(document).height() - $(window).height())/2 && !scrolledToHalf) {
			   // ajax call get data from server and append to the div
			   scrolledToHalf = true;
			   console.log('Scrolled to half '+($(document).height() - $(window).height())/2 );
		}
		if($(window).scrollTop() > (($(document).height() - $(window).height()) - ($(document).height() - $(window).height())/3) && !scrolledToLastThird) {
			   // ajax call get data from server and append to the div
			   scrolledToLastThird = true;
			   console.log('Scrolled to last third');
			   
			   var loadingHtml = '<div id="fountainG"><div id="fountainG_1" class="fountainG"></div><div id="fountainG_2" class="fountainG"></div><div id="fountainG_3" class="fountainG"></div><div id="fountainG_4" class="fountainG"></div><div id="fountainG_5" class="fountainG"></div><div id="fountainG_6" class="fountainG"></div><div id="fountainG_7" class="fountainG"></div><div id="fountainG_8" class="fountainG"></div></div>';
				$('.grid-wrap').append(loadingHtml);
		}
	});
</script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->



</body></html>