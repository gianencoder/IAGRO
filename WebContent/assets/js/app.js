
$(document).ready(function () {
	formulario.rol();
	$(".selectRol").val("4");
});

const formulario = {

	rol : function() {
		$('.selectRol').change(function (){
			
			let rs = $(this).val();
			
			$('.contProfesion').addClass('d-none');
			$('.contInstituto').addClass('d-none');
			$('.contCedula').addClass('d-none');			
			
			 if(rs == '3'){
			$('.contProfesion').removeClass('d-none');
			$('.contCedula').removeClass('d-none');
			
			
				
			}else if(rs == '1'){
			$('.contInstituto').removeClass('d-none');
			$('.contCedula').removeClass('d-none');
		
		
			}
		})
	}
	
}