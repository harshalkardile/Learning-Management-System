<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<!-- jQuery -->
<title>webdamn.com : Demo Bootstrap Modal Form Submit with jQuery</title>
<script type="text/javascript" src="js/addBookModel.js"></script>
<link rel="icon" type="image/png" href="http://webdamn.com/wp-content/themes/v2/webdamn.png">
</head>
<body class="">

	
	<div class="container" style="min-height:500px;">
	
<div class="container">
	
	<br>
	<div id="addBookModel">
        <button type="button" class="btn btn-info btn" data-toggle="modal" data-target="#addBookModel-modal">Show Contact Form</button></div>
					<div id="addBookModel-modal" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<a class="close" data-dismiss="modal">×</a>
									<h3>Contact Form</h3>
								</div>
								<form id="contactForm" name="addBookModel" role="form">
									<div class="modal-body">				
										<div class="form-group">
											<label for="name">Name</label>
											<input type="text" name="name" class="form-control">
										</div>
										<div class="form-group">
											<label for="email">Email</label>
											<input type="email" name="email" class="form-control">
										</div>
										<div class="form-group">
											<label for="message">Message</label>
											<textarea name="message" class="form-control"></textarea>
										</div>					
									</div>
									<div class="modal-footer">					
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										<input type="submit" class="btn btn-success" id="submit">
									</div>
								</form>
							</div>
						</div>
		</div>			
	
</div>	
<div class="insert-post-ads1" style="margin-top:20px;">

			</div>
</div>
</body></html>
