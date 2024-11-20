function charcheck(field)
{
	if (field.name == "VEHICLE_MAKE")
	{
		max = 3;
	}
	if (field.name == "UNIT_NUMBER")
	{
		max = 15;
	}

 if (field.name == "VEH_PLATE_NUMBER")
	{
		max = 6;
	}

 if (field.name == "INS_POLICY")
	{
		max = 20;
	}

	if (field.name == "INS_COMPANY")
	{
		max = 75;
	}
	
	if (field.name == "OWNER_NAME")
	{
		max = 75;
	}
  if (field.value.length > max)
	{
		alert("Please do not enter more than "+ max+ " characters ");
		field.focus();
		return false;
	}
if (field.name == "VEHICLE_MAKE")
	{
	if (field.value.length < max)
	{
		alert("Please do not enter less than "+ max+ " characters for Vehicle Make");
		field.focus();
		return false;
	}
	}
if (field.name == "VEH_PLATE_NUMBER")
	{
	if ((document.myForm.VEH_PLATE_NUMBER.value != "") &&(field.value.length < max))
	{
		alert("Please do not enter less than "+ max+ " characters for Plate Number");
		field.focus();
		return false;
	}
	}
	}
