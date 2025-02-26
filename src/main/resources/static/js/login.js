/**
 * 
 */

//$(document).ready(function() {
document.addEventListener('DOMContentLoaded', () => {

	//alert("login js");
	
	let usernames = $("a.dropdown-item");
	let labelUsername = $("#username");
	
	
	usernames.each(function() {
		console.log($(this).text());
		$(this).on("click", function() {
			//alert($(this).find("p.langEN").text());
			labelUsername.val($(this).find("p.langEN").text());
		});
	});
	
});