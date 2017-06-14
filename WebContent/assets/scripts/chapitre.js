$(document).ready(function(){
	$(".chapitre").hide();
	$(".chapitre:first").show();
	//alert($(".chapitre").length)
	$("#oui").click(function(){
		//alert("oui")
		nextChapitre();
	})
	
})

function nextChapitre(){
	var chapitreVisible=$(".chapitre:visible");
	var chapitreNext=chapitreVisible.next();
	
	chapitreVisible.hide();
	chapitreNext.show();
		
}