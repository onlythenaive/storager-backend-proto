$(function () {
	$('.dropdown-button').dropdown({
		belowOrigin: true, // Displays dropdown below the button
		constrain_width: true
	});
	$('select').material_select();
	$('.datepicker').pickadate({
		selectMonths: true, // Creates a dropdown to control month
		selectYears: 15 // Creates a dropdown of 15 years to control year
	});
	$('.collapsible').collapsible({
		accordion: true
	});
	 $('.modal').modal();

	if ($('#provider1').length > 0)
		$('#provider1').modal('open');// and open them all

	if ($('#patch1').length > 0)
		$('#patch1').modal('open');// and open them all

	$('input.autocomplete').autocomplete({
		data: {
			"СПб ГУП СПб ИАЦ": null,
			"Какая-то другая организация": null,
			"Организация 1": null,
			"Организация 27": null
		}
	});
});
