<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.List, com.sar2016.entities.Contact, com.sar2016.entities.Enterprise"
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
	<div class="col-md-6">
	<div class="box box-danger">
                <div class="box-header with-border">
                  <h3 class="box-title">Contacts</h3>

                  <!-- <div class="box-tools pull-right">
                    <span class="label label-danger">8 New Members</span>
                  </div>-->
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                  <ul class="users-list clearfix">
                  <%List<Contact> contacts = (List<Contact>)request.getAttribute("contacts"); %>
					<% for(int i =0; i < contacts.size(); i++){%>
						<li>
	                      <img src="./starter_fichiers/default-user.jpg" alt="User Image">
	                      <a class="users-list-name" href="./profile?id=<% out.println(contacts.get(i).getId()); %>"><% out.println(contacts.get(i).getFirstName()); %> <% out.println(contacts.get(i).getLastName()); %></a>
	                    </li>
					<%}%>
                  </ul>
                  <!-- /.users-list -->
                </div>
                <!-- /.box-body -->
                <div class="box-footer text-center">
                  
                </div>
                <!-- /.box-footer -->
              </div>
             </div>
             <div class="col-md-6">
	<div class="box box-danger">
                <div class="box-header with-border">
                  <h3 class="box-title">Enterprises</h3>

                  <!-- <div class="box-tools pull-right">
                    <span class="label label-danger">8 New Members</span>
                  </div>-->
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                  <ul class="users-list clearfix">
                  <%List<Enterprise> enterprises = (List<Enterprise>)request.getAttribute("enterprises"); %>
					<% for(int i =0; i < enterprises.size(); i++){%>
						<li>
	                      <img src="./starter_fichiers/default-company.jpg" alt="User Image">
	                      <a class="users-list-name" href="./profile?id=<% out.println(enterprises.get(i).getId()); %>"><% out.println(enterprises.get(i).getFirstName()); %></a>
	                    </li>
					<%}%>
                  </ul>
                  <!-- /.users-list -->
                </div>
                <!-- /.box-body -->
                <div class="box-footer text-center">
                  
                </div>
                <!-- /.box-footer -->
              </div>
             </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
 

<jsp:include page="Footer.jsp" />

</body></html>