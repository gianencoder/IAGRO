$(document).ready(function () {
	formulario.rol();
});

const formulario = {


	rol : function() {
		$('.selectRol').change(function (){
			
			let rs = $(this).val();
			
					if(rs == 'Común'){
				$('.contCedula').addClass('d-none');
				$('.contInstituto').addClass('d-none');
				$('.contProfesion').addClass('d-none');
			
	
			}else if(rs == 'Experto'){
			$('.contInstituto').addClass('d-none');
			$('.contProfesion').removeClass('d-none');
			$('.contInstituto').removeClass('d-none');
	

				
		}
			else {
			$('.contProfesion').addClass('d-none');
			$('.contInstituto').removeClass('d-none');
			$('.contCedula').removeClass('d-none');
		
			
		
			}
			
	
		})
	}
	
}