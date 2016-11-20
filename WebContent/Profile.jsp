<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.List, com.sar2016.entities.Contact, com.sar2016.entities.ContactGroup, com.sar2016.entities.Enterprise, com.sar2016.entities.Address,com.sar2016.entities.PhoneNumber, java.util.Set"
%>

<jsp:include page="Head.jsp" />
  
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
                                <img class="img-circle" src="./starter_fichiers/default-user.jpg" alt="User Pic">
                            </div>
                            <div class="col-xs-2 col-sm-2 hidden-md hidden-lg">
                                <img class="img-circle" src="./starter_fichiers/default-user.jpg" alt="User Pic">
                            </div>
                            <%Contact contact = (Contact)request.getAttribute("contact"); %>
						
                            <div class=" col-md-9 col-lg-9 hidden-xs hidden-sm">
                                <strong><% out.println(contact.getFirstName()); %> 
						                  <% out.println(contact.getLastName()); %></strong><br>
                                <table class="table table-user-information">
                                    <tbody>
                                    <tr>
                                        <td>Email :</td>
                                        <td><% out.println(contact.getEmail()); %></td>
                                    </tr>
                                    <tr>
                                    <td>Phone numbers :</td>
                                    </tr>
                                     <% Object[] numbers = contact.getProfiles().toArray(); %>
                                    <% for(int i =0; i < numbers.length; i ++){ %>
                                    <tr>
                                        <td><% out.println(((PhoneNumber)numbers[i]).getPhoneKind()); %></td>
                                        <td><% out.println(((PhoneNumber)numbers[i]).getPhoneNumber()); %></td>
                                    </tr>
                                     <%} %>
						            <tr>
                                    <td>Groups :</td>
                                   
                                     <% Object[] books = contact.getBooks().toArray(); %>
                                    <% for(int i =0; i < books.length; i ++){ %>
                                 
                                        <td><% out.println(((ContactGroup)books[i]).getGroupName()); %></td>
                                    </tr>
                                     <%} %>
                                     <%Address address = (Address)request.getAttribute("contact-address"); %>
						                   <%if(address != null){ %>
						            <tr>
						            <td>Address :</td>
						           
						            <td>
						            <% out.print(address.getStreet()); %>
						            <% out.print(address.getCity()); %>
						            <% out.print(address.getZip()); %>
						            <% out.print(address.getCountry()); %>
						            </td>
						            </tr>
                                    <tr> 
                                       <td id="map" style="height: 375px" colspan="2">
											</td>
                                    </tr>
                                     <%} %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                       <button class="btn btn-sm btn-warning" type="button" data-toggle="tooltip" data-original-title="Edit this user"><i class="glyphicon glyphicon-edit"></i></button>
                       <a href="./remove-contact?id=<% out.print(contact.getId());%>"><button class="btn btn-sm btn-danger" type="button" data-toggle="tooltip" data-original-title="Remove this user"><i class="glyphicon glyphicon-remove"></i></button></a>
               		</div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
<jsp:include page="Footer.jsp" />

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
<script src="./js/GMapsHelper.js"></script>
<%if(address != null){ %>               	                  
		<script>
				GMapsHelper.init({
					marker : "true",
					lat : <% out.print(address.getLat()); %>,
					lng: <% out.print(address.getLng()); %>
				});
		</script>
 <%} %>

</body></html>