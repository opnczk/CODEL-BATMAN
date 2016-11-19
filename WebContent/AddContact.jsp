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
  <link rel="stylesheet" type="text/css" href="./starter_fichiers/loading.css">
  <script src="./starter_fichiers/modernizr.js"></script>
<script
			  src="https://code.jquery.com/jquery-3.1.1.min.js"
			  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
			  crossorigin="anonymous"></script>
		
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
<body style="overflow-y:visible;" class="skin-blue fixed">
 <script type="text/javascript" src="./js/importContacts.js"></script>
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
        <li class="active"><a href="AddContact.jsp"><i class="fa fa-user-plus"></i> <span>Add Contact</span></a></li>
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
        <form id="morphsearch-form-front" action="#" method="post" class="morphsearch-form" data-ajaxsuccesshandler="adminSearchSuccessHandler" data-ajaxerrorhandler="adminSearchErrorHandler">
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
			
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">User information</h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-12">
						    <div class="form-area">
						        <br style="clear:both">
						        <div class="form-wrapper form-horizontal">
						        <form id="form" method="post" role="form" action="add-contact">
				    				<div class="form-group">
										<input type="text" class="form-control" id="name" name="first_name" placeholder="First name" required>
									</div>
									<div class="form-group contact-spec">
										<input type="text" class="form-control" id="name" name="last_name" placeholder="Last name">
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="email" name="email" placeholder="Email" required>
									</div>
									<div class="form-group contact-spec">
										<input type="text" class="form-control" id="nick_name" name="nick_name" placeholder="Nickname">
									</div>
									<div class="form-group company-spec" style="display: none;">
										<input type="text" class="form-control" id="siretnum" name="siret_num" placeholder="Siret Number">
									</div>
									<div id="phonesList" class="form-group">
										<div class="input-group">
									      <input class="form-control" placeholder="Add a phone number" type="text" disabled>
									      <span class="input-group-btn">
									      	<input id="nbPhones" name="nb_phones" value="0" type="hidden"/>
									        <button id="addPhoneButton" class="btn btn-secondary" type="button"><i class="fa fa-plus"></i></button>
									      </span>
									    </div>
									    
									</div>
									
									<input type="hidden" class="address-field" name="PLACE_ID" />
									<input type="hidden" class="address-field" name="ADD_LAT" />
									<input type="hidden" class="address-field" name="ADD_LNG" />
									<input type="hidden" class="address-field" name="ADD_ST_NB" />
									<input type="hidden" class="address-field" name="ADD_STREET" />
									<input type="hidden" class="address-field" name="ADD_CITY" />
									<input type="hidden" class="address-field" name="ADD_COUNTRY" />
									<input type="hidden" class="address-field" name="ADD_ZIPCODE" />

									</form>
									<div class="form-group">
									<input id="autocomplete" class="form-control" class="controls" type="text"
        placeholder="Enter a location">
        							</div>
        							<div class="form-group">
        							<div id="selectedAddress" class="form-group" style="display: none;">
										<div class="input-group">
									      <input id="selectedAddressInput" class="form-control" placeholder="Address selected" type="text" disabled>
									      <span class="input-group-btn">
									        <button id="cancelAddress" class="btn btn-secondary btn-danger" type="button"><i class="fa fa-times"></i></button>
									      </span>
									    </div>
									</div>
        							<div id="map" style="height: 375px"></div>
									</div>
						    </div>
						</div>
                    </div>
                    <div class="panel-footer">
                     <button type="submit" form="form" id="submit" name="submit" class="btn btn-primary">Submit Form</button>
                     <button id="switchButton" class="pull-right btn btn-primary">Company</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <!-- Main Footer -->
  <!-- <footer class="main-footer">
    <!--<strong><i class="fa fa-heart"></i></strong>
  </footer>
  -->
  <div class="overlay"></div>
  
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
</div>
<!-- ./wrapper -->
<!-- REQUIRED JS SCRIPTS -->
<!-- Modal -->

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
<script src="./starter_fichiers/classie.js"></script>
		<script src="./js/GMapsHelper.js"></script>
		<script>
				$("#nbPhones").val(""+$(".deletePhoneNumber").size());
				console.log($("#nbPhones").val());
				GMapsHelper.init({
					type : "address"
				});
				
				$("#cancelAddress").click(function(){
					$(".address-field").val("");
					$("#autocomplete").show();
					$("#autocomplete").val("");
					$("#selectedAddress").hide();
					GMapsHelper.deleteMarkers();
				});
				
				$("#switchButton").click(function(){
					var value = $("#switchButton").html();
					if(value == "Company"){
						 $("#switchButton").html("Contact");
						 $(".contact-spec").show();
						 $(".company-spec").hide();
					}else{
						 $("#switchButton").html("Company");
						 $(".contact-spec").hide();
						 $(".company-spec").show();
					}
				});
				
				$("#addPhoneButton").click(function(){
					var nbPhones =  $(".deletePhoneNumber").size();
					$("#phonesList").after('<div class="form-group col-md-12 phoneNumberField" >'
					+'<div class="col-md-5"><input name="phones['+nbPhones+'].phoneKind" class="form-control" placeholder="PhoneKind" type="text" ></div>'
					+'<div class="col-md-5"><input name="phones['+nbPhones+'].phoneNumber" class="form-control col-md-6" placeholder="PhoneNumer" type="text" ></div>'
					+'<div class="col-md-2"><button class="btn btn-secondary btn-danger deletePhoneNumber" type="button" onClick="deletePhoneNumber(event)"><i class="fa fa-times"></i></button></div>'
					+'</div>');
					$("#nbPhones").val(""+nbPhones);
					console.log($("#nbPhones").val());
				});
				
				function deletePhoneNumber( event ){
					$(event.target).parents(".form-group").remove();
					$("#nbPhones").val(""+$(".deletePhoneNumber").size());
					console.log($("#nbPhones").val());
				}
		</script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
<script>

</script>


</body></html>