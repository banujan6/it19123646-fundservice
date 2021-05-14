$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
		url : "FundsAPI", 
		type : type, 
		data : $("#formFund").serialize(), 
		dataType : "text", 
		complete : function(response, status) 
		{ 
			onProductSaveComplete(response.responseText, status); 
		} 
	}); 
});

function onProductSaveComplete(response, status)
{ 
if (status == "success") 
 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
		 $("#alertSuccess").text("Successfully saved."); 
		 $("#alertSuccess").show();
		 
		 $("#divfundsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
	 } 
 } else if (status == "error") 
 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
 } else
 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
 }

	$("#hidFundIDSave").val(""); 
	$("#formFund")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidFundIDSave").val($(this).closest("tr").find('#hidFundIDUpdate').val()); 
 $("#fundCode").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#fundName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#fundAmount").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#fundDesc").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 

//REMOVE=======================================================

$(document).on("click", ".btnRemove", function(event)
{ 
	$.ajax( 
	{ 
		 url : "FundsAPI", 
		 type : "DELETE", 
		 data : "fundID=" + $(this).data("fundid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
			 onProductDeleteComplete(response.responseText, status); 
		 } 
	}); 
});

function onProductDeleteComplete(response, status)
{ 
	if (status == "success") 
	{ 
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{ 
		 $("#alertSuccess").text("Successfully deleted."); 
		 $("#alertSuccess").show(); 
		 
		 $("#divfundsGrid").html(resultSet.data); 
		} else if (resultSet.status.trim() == "error") 
		{ 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		} 
		
 } else if (status == "error") 
 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
 } else
 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 

}

// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#fundCode").val().trim() == "") 
 { 
 return "Insert Fund Code."; 
 } 
// NAME
if ($("#fundName").val().trim() == "") 
 { 
 return "Insert Project Name."; 
 }

//PRICE-------------------------------
if ($("#fundAmount").val().trim() == "") 
 { 
 return "Insert Fund Amount."; 
 } 
// is numerical value
var tmpPrice = $("#fundAmount").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for Fund Amount."; 
 } 
// convert to decimal price
 $("#fundAmount").val(parseFloat(tmpPrice).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#fundDesc").val().trim() == "") 
 { 
 return "Insert Fund Description."; 
 } 
return true; 
}