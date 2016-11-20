 <!-- Main Footer -->
  <footer class="main-footer" style="min-height: 51px">
    <!--<strong><i class="fa fa-heart"></i></strong>-->
  </footer>
  
  <div class="overlay"></div>
</div>
<!-- ./wrapper -->
<!-- REQUIRED JS SCRIPTS -->
<!-- Button trigger modal -->
<button id="triggImport" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="display:none;">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <form action="./import-contacts" method="POST">
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
        <button type="submit" class="btn btn-primary">Import Contacts</button>
      </div>
      </form>
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
<script src="./starter_fichiers/classie.js"></script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->