function ValidateFields(regMonth)
{
if (regMonth == "1" ){
	 if(document.myForm.VEHICLE_TYPE.value =="TK" && 
		 (document.myForm.COMBINED_GROSS_WEIGHT.value != "7000"&&
		 document.myForm.COMBINED_GROSS_WEIGHT.value != "9000"&&
		 document.myForm.COMBINED_GROSS_WEIGHT.value != "10000" &&
		 document.myForm.COMBINED_GROSS_WEIGHT.value != "11000" ) ){
		 alert("You have to enter either 7000/9000/10000/11000 for this Group");
                 document.myForm.COMBINED_GROSS_WEIGHT.focus();
	         }
		else  if(document.myForm.VEHICLE_TYPE.value =="FT" && 
		 parseInt(document.myForm.COMBINED_GROSS_WEIGHT.value) >= 3000){
          alert("You have to enter 3000lbs or Less for this Group");
 document.myForm.COMBINED_GROSS_WEIGHT.focus();
	         }
       else  if(document.myForm.VEHICLE_TYPE.value =="WR" && 
		 parseInt(document.myForm.COMBINED_GROSS_WEIGHT.value) >= 15999) 
		 {
          alert("You have to enter 15999lbs or Less for this Group");
              document.myForm.COMBINED_GROSS_WEIGHT.focus();

	         }
       else
            {  
		   myForm.submitted = true;	
	       myForm.submit();
            }
}
else if (regMonth == "2" ){
       if(document.myForm.VEHICLE_TYPE.value =="FT" && 
		 parseInt(document.myForm.COMBINED_GROSS_WEIGHT.value) <= 3000){
          alert("You have to enter 3000lbs or Higher for this Group");
             document.myForm.COMBINED_GROSS_WEIGHT.focus();
	         }
	    else  if(document.myForm.VEHICLE_TYPE.value =="WR" && 
		 parseInt(document.myForm.COMBINED_GROSS_WEIGHT.value) <= 15999){
          alert("You have to enter 15999lbs or Higher for this Group");
              document.myForm.COMBINED_GROSS_WEIGHT.focus();
	         }
       else
            {  
		   myForm.submitted = true;	
	       myForm.submit();
            }
    }

}