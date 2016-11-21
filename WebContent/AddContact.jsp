<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.List, com.sar2016.entities.Contact, com.sar2016.entities.Address, com.sar2016.entities.Enterprise, com.sar2016.entities.ContactGroup, com.sar2016.entities.PhoneNumber"
%>

<jsp:include page="Head.jsp" />

 <% Contact contact = (Contact)request.getAttribute("contact"); %>
  <% Address address = (Address)request.getAttribute("contact-address"); %>
  <% boolean enterprise = false; %>
  <% if(contact instanceof Enterprise){ enterprise = true; } %>
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
						        <form id="form" method="post" role="form" action="<%if(contact != null){%>modify-contact<% }else{%>add-contact<%}%>">
						        
						        <%if(contact != null){%><input type="hidden" name="contact_id" value="<% out.println(contact.getId());%>"/><%}%>
						        	
				    				<div class="form-group">
										<input type="text" class="form-control" id="name" name="first_name" placeholder="First name" <%if(contact != null && contact.getFirstName() != null){ out.println("value=\""+contact.getFirstName()+"\""); }%> required>
									</div>
									<div class="form-group contact-spec">
										<input type="text" class="form-control" id="name" name="last_name" placeholder="Last name" <%if(contact != null && contact.getLastName() != null){ out.println("value=\""+contact.getLastName()+"\""); }%>>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="email" name="email" placeholder="Email" <%if(contact != null && contact.getEmail() != null){ out.println("value=\""+contact.getEmail()+"\""); }%> required>
									</div>
									<div class="form-group contact-spec">
										<input type="text" class="form-control" id="nick_name" name="nick_name" placeholder="Nickname" <%if(contact != null && contact.getNickName() != null){ out.println("value=\""+contact.getNickName()+"\""); }%>>
									</div>
									<div class="form-group company-spec" >
										<input type="text" class="form-control" id="siretnum" name="num_siret" placeholder="Siret Number" <%if(contact != null && enterprise && ((Enterprise)contact).getNumSiret() != 0){ out.println("value=\""+((Enterprise)contact).getNumSiret()+"\""); }%>>
									</div>
									
									<div id="phonesList" class="form-group">
										<div class="input-group">
									      <input class="form-control" placeholder="Add a phone number" type="text" disabled>
									      <span class="input-group-btn">
									      	<input id="nbPhones" name="nb_phones" value=0 type="hidden"/>
									        <button id="addPhoneButton" class="btn btn-secondary" type="button"><i class="fa fa-plus"></i></button>
									      </span>
									    </div>
									</div>
									<%if(contact != null && contact.getProfiles() != null){
									    	   Object[] numbers = contact.getProfiles().toArray(); %>
									     <% for(int i =0; i < numbers.length; i ++){ %>
									      <div class="form-group col-md-12 phoneNumberField">
										      <div class="col-md-5">
										     	 <input name="phones[<%out.println(i);%>].phoneKind" class="form-control" placeholder="PhoneKind" type="text" value="<% out.println(((PhoneNumber)numbers[i]).getPhoneKind()); %>">
										      </div>
										      <div class="col-md-5">
										      	<input name="phones[<%out.println(i);%>].phoneNumber" class="form-control col-md-6" placeholder="PhoneNumer" type="text" value="<% out.println(((PhoneNumber)numbers[i]).getPhoneNumber()); %>">
										      </div>
										      <div class="col-md-2">
										      	<button class="btn btn-secondary btn-danger deletePhoneNumber" type="button" onclick="deletePhoneNumber(event)">
										      	<i class="fa fa-times"></i>
										      	</button>
										      </div>
									      </div>
			                                     <%} %>
	                                      <% } %> 
									
									<div id="contactGroups" class="form-group">
									    <label >Assign to a group :</label>
									    <select class="form-control" name="contactGroup">
									    <%List<ContactGroup> contactGroups = (List<ContactGroup>)request.getAttribute("contactGroups"); %>
										<% for(int i =0; i < contactGroups.size(); i++){%>
											<option value=<% out.println(contactGroups.get(i).getId()); %> ><% out.println(contactGroups.get(i).getGroupName()); %></option>
										<%}%>
										</select>
										<label >Or create a new one :</label>
										<input type="text" name="groupName" class="form-control"/>
									</div>
									
									<input type="hidden" id="companyInput" name="companyOrNot" value="false" />
									<input type="hidden" class="address-field" name="PLACE_ID" <%if(address != null && address.getPlaceId() != null){ out.println("value=\""+address.getPlaceId()+"\""); }%>/>
									<input type="hidden" class="address-field" name="ADD_LAT" <%if(address != null && address.getLat() != null){ out.println("value=\""+address.getLat()+"\""); }%>/>
									<input type="hidden" class="address-field" name="ADD_LNG" <%if(address != null && address.getLng() != null){ out.println("value=\""+address.getLng()+"\""); }%>/>
									<input type="hidden" class="address-field" name="ADD_ST_NB" />
									<input type="hidden" class="address-field" name="ADD_STREET" <%if(address != null && address.getStreet() != null){ out.println("value=\""+address.getStreet()+"\""); }%>/>
									<input type="hidden" class="address-field" name="ADD_CITY" <%if(address != null && address.getCity() != null){ out.println("value=\""+address.getCity()+"\""); }%>/>
									<input type="hidden" class="address-field" name="ADD_COUNTRY" <%if(address != null && address.getCountry() != null){ out.println("value=\""+address.getCountry()+"\""); }%>/>
									<input type="hidden" class="address-field" name="ADD_ZIPCODE" <%if(address != null && address.getZip() != null){ out.println("value=\""+address.getZip()+"\""); }%>/>

									</form>
									<div class="form-group">
									<input id="autocomplete" class="form-control" class="controls" type="text"
        placeholder="Enter a location" <%if(address != null){%> style="display: none;" <%} %>>
        							</div>
        							<div class="form-group">
        							<div id="selectedAddress" class="form-group" <% if(address == null){%> style="display: none;"<%} %>>
										<div class="input-group">
									      <input id="selectedAddressInput" class="form-control" placeholder="<% if(address != null){ out.println(address.getStreet()+" "+address.getCity()+" "+address.getZip()+" "+address.getCountry()); }else{ %>Address selected<%} %>" type="text" disabled>
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
 
<jsp:include page="Footer.jsp" />

		<script src="./js/GMapsHelper.js"></script>
		<script>
				$(".company-spec").hide();
				var companySwitch = true;
				$("#nbPhones").val($(".deletePhoneNumber").size());
				console.log($("#nbPhones").val());
				GMapsHelper.init({
					type : "address",
					<%if(address != null){%>
					lat : <%out.println(address.getLat()+",");%>
					lng : <%out.println(address.getLng());%>
					<%}%>
				});
				
				$("#cancelAddress").click(function(){
					$(".address-field").val("");
					$("#autocomplete").show();
					$("#autocomplete").val("");
					$("#selectedAddress").hide();
					GMapsHelper.deleteMarkers();
				});
				
				$("#switchButton").click(function(){
					if(companySwitch){
						 $("#switchButton").html("Contact");
						 $(".contact-spec").hide();
						 $(".company-spec").show();
						 companySwitch = false;
						 $("#companyInput").val(!companySwitch);
					}else{
						 $("#switchButton").html("Company");
						 $(".contact-spec").show();
						 $(".company-spec").hide();
						 companySwitch = true;
						 $("#companyInput").val(!companySwitch);
					}
				});
				
				$("#addPhoneButton").click(function(){
					var nbPhones =  $(".deletePhoneNumber").size();
					if(nbPhones != 0)
						nbPhones + 1;
					$("#phonesList").after('<div class="form-group col-md-12 phoneNumberField" >'
					+'<div class="col-md-5"><input name="phones['+nbPhones+'].phoneKind" class="form-control" placeholder="PhoneKind" type="text" ></div>'
					+'<div class="col-md-5"><input name="phones['+nbPhones+'].phoneNumber" class="form-control col-md-6" placeholder="PhoneNumer" type="text" ></div>'
					+'<div class="col-md-2"><button class="btn btn-secondary btn-danger deletePhoneNumber" type="button" onClick="deletePhoneNumber(event)"><i class="fa fa-times"></i></button></div>'
					+'</div>');
					nbPhones += 1;
					$("#nbPhones").val((nbPhones));
					console.log($("#nbPhones").val());
				});
				
				function deletePhoneNumber( event ){
					$(event.target).parents(".form-group").remove();
					$("#nbPhones").val($(".deletePhoneNumber").size());
					console.log($("#nbPhones").val());
				}
		</script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->

</body></html>