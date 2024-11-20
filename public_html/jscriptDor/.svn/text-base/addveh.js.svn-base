<SCRIPT LANGUAGE="JavaScript">
<!-- Original:  Richard Gorremans (RichardG@spiritwolfx.com) -->
<!-- Web Site:  http://www.spiritwolfx.com -->

<!-- This script and many more are available free online at -->
<!-- The JavaScript Source!! http://javascript.internet.com -->

<!-- Begin
// Check browser version
var isNav4 = false, isNav5 = false, isIE4 = false
var strSeperator = "/"; 
// If you are using any Java validation on the back side you will want to use the / because 
// Java date validations do not recognize the dash as a valid date separator.
var vDateType = 3; // Global value for type of date format
//                1 = mm/dd/yyyy
//                2 = yyyy/dd/mm  (Unable to do date check at this time)
//                3 = dd/mm/yyyy
var vYearType = 4; //Set to 2 or 4 for number of digits in the year for Netscape
var vYearLength = 2; // Set to 4 if you want to force the user to enter 4 digits for the year before validating.
var err = 0; // Set the error code to a default of zero
if(navigator.appName == "Netscape") {
if (navigator.appVersion < "5") {
isNav4 = true;
isNav5 = false;
}
else
if (navigator.appVersion > "4") {
isNav4 = false;
isNav5 = true;
   }
}
else {
isIE4 = true;
}
function DateFormat(vDateName, vDateValue, e, dateCheck, dateType) {
vDateType = dateType;
// vDateName = object name
// vDateValue = value in the field being checked
// e = event
// dateCheck 
// True  = Verify that the vDateValue is a valid date
// False = Format values being entered into vDateValue only
// vDateType
// 1 = mm/dd/yyyy
// 2 = yyyy/mm/dd
// 3 = dd/mm/yyyy
//Enter a tilde sign for the first number and you can check the variable information.
if (vDateValue == "~") {
alert("AppVersion = "+navigator.appVersion+" \nNav. 4 Version = "+isNav4+" \nNav. 5 Version = "+isNav5+" \nIE Version = "+isIE4+" \nYear Type = "+vYearType+" \nDate Type = "+vDateType+" \nSeparator = "+strSeperator);
vDateName.value = "";
vDateName.focus();
return true;
}
var whichCode = (window.Event) ? e.which : e.keyCode;
// Check to see if a seperator is already present.
// bypass the date if a seperator is present and the length greater than 8
if (vDateValue.length > 8 && isNav4) {
if ((vDateValue.indexOf("-") >= 1) || (vDateValue.indexOf("/") >= 1))
return true;
}
//Eliminate all the ASCII codes that are not valid
var alphaCheck = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/-";
if (alphaCheck.indexOf(vDateValue) >= 1) {
if (isNav4) {
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
else {
vDateName.value = vDateName.value.substr(0, (vDateValue.length-1));
return false;
   }
}
if (whichCode == 8) //Ignore the Netscape value for backspace. IE has no value
return false;
else {
//Create numeric string values for 0123456789/
//The codes provided include both keyboard and keypad values
var strCheck = '47,48,49,50,51,52,53,54,55,56,57,58,59,95,96,97,98,99,100,101,102,103,104,105';
if (strCheck.indexOf(whichCode) != -1) {
if (isNav4) {
if (((vDateValue.length < 6 && dateCheck) || (vDateValue.length == 7 && dateCheck)) && (vDateValue.length >=1)) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
if (vDateValue.length == 6 && dateCheck) {
var mDay = vDateName.value.substr(2,2);
var mMonth = vDateName.value.substr(0,2);
var mYear = vDateName.value.substr(4,4)
//Turn a two digit year into a 4 digit year
if (mYear.length == 2 && vYearType == 4) {
var mToday = new Date();
//If the year is greater than 30 years from now use 19, otherwise use 20
var checkYear = mToday.getFullYear() + 30; 
var mCheckYear = '20' + mYear;
if (mCheckYear >= checkYear)
mYear = '19' + mYear;
else
mYear = '20' + mYear;
}
var vDateValueCheck = mMonth+strSeperator+mDay+strSeperator+mYear;
if (!dateValid(vDateValueCheck)) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
return true;
}
else {
// Reformat the date for validation and set date type to a 1
if (vDateValue.length >= 8  && dateCheck) {
if (vDateType == 1) // mmddyyyy
{
var mDay = vDateName.value.substr(2,2);
var mMonth = vDateName.value.substr(0,2);
var mYear = vDateName.value.substr(4,4)
vDateName.value = mMonth+strSeperator+mDay+strSeperator+mYear;
}
if (vDateType == 2) // yyyymmdd
{
var mYear = vDateName.value.substr(0,4)
var mMonth = vDateName.value.substr(4,2);
var mDay = vDateName.value.substr(6,2);
vDateName.value = mYear+strSeperator+mMonth+strSeperator+mDay;
}
if (vDateType == 3) // ddmmyyyy
{
var mMonth = vDateName.value.substr(2,2);
var mDay = vDateName.value.substr(0,2);
var mYear = vDateName.value.substr(4,4)
vDateName.value = mDay+strSeperator+mMonth+strSeperator+mYear;
}
//Create a temporary variable for storing the DateType and change
//the DateType to a 1 for validation.
var vDateTypeTemp = vDateType;
vDateType = 1;
var vDateValueCheck = mMonth+strSeperator+mDay+strSeperator+mYear;
if (!dateValid(vDateValueCheck)) {
alert("Invalid Date\nPlease Re-Enter");
vDateType = vDateTypeTemp;
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
vDateType = vDateTypeTemp;
return true;
}
else {
if (((vDateValue.length < 8 && dateCheck) || (vDateValue.length == 9 && dateCheck)) && (vDateValue.length >=1)) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
         }
      }
   }
}
else {
// Non isNav Check
if (((vDateValue.length < 8 && dateCheck) || (vDateValue.length == 9 && dateCheck)) && (vDateValue.length >=1)) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
return true;
}
// Reformat date to format that can be validated. mm/dd/yyyy
if (vDateValue.length >= 8 && dateCheck) {
// Additional date formats can be entered here and parsed out to
// a valid date format that the validation routine will recognize.
if (vDateType == 1) // mm/dd/yyyy
{
var mMonth = vDateName.value.substr(0,2);
var mDay = vDateName.value.substr(3,2);
var mYear = vDateName.value.substr(6,4)
}
if (vDateType == 2) // yyyy/mm/dd
{
var mYear = vDateName.value.substr(0,4)
var mMonth = vDateName.value.substr(5,2);
var mDay = vDateName.value.substr(8,2);
}
if (vDateType == 3) // dd/mm/yyyy
{
var mDay = vDateName.value.substr(0,2);
var mMonth = vDateName.value.substr(3,2);
var mYear = vDateName.value.substr(6,4)
}
if (vYearLength == 4) {
if (mYear.length < 4) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
return true;
   }
}
// Create temp. variable for storing the current vDateType
var vDateTypeTemp = vDateType;
// Change vDateType to a 1 for standard date format for validation
// Type will be changed back when validation is completed.
vDateType = 1;
// Store reformatted date to new variable for validation.
var vDateValueCheck = mMonth+strSeperator+mDay+strSeperator+mYear;
if (mYear.length == 2 && vYearType == 4 && dateCheck) {
//Turn a two digit year into a 4 digit year
var mToday = new Date();
//If the year is greater than 30 years from now use 19, otherwise use 20
var checkYear = mToday.getFullYear() + 30; 
var mCheckYear = '20' + mYear;
if (mCheckYear >= checkYear)
mYear = '19' + mYear;
else
mYear = '20' + mYear;
vDateValueCheck = mMonth+strSeperator+mDay+strSeperator+mYear;
// Store the new value back to the field.  This function will
// not work with date type of 2 since the year is entered first.
if (vDateTypeTemp == 1) // mm/dd/yyyy
vDateName.value = mMonth+strSeperator+mDay+strSeperator+mYear;
if (vDateTypeTemp == 3) // dd/mm/yyyy
vDateName.value = mDay+strSeperator+mMonth+strSeperator+mYear;
} 
if (!dateValid(vDateValueCheck)) {
alert("Invalid Date\nPlease Re-Enter");
vDateType = vDateTypeTemp;
vDateName.value = "";
vDateName.focus();
return true;
}
vDateType = vDateTypeTemp;
return true;
}
else {
if (vDateType == 1) {
if (vDateValue.length == 2) {
vDateName.value = vDateValue+strSeperator;
}
if (vDateValue.length == 5) {
vDateName.value = vDateValue+strSeperator;
   }
}
if (vDateType == 2) {
if (vDateValue.length == 4) {
vDateName.value = vDateValue+strSeperator;
}
if (vDateValue.length == 7) {
vDateName.value = vDateValue+strSeperator;
   }
} 
if (vDateType == 3) {
if (vDateValue.length == 2) {
vDateName.value = vDateValue+strSeperator;
}
if (vDateValue.length == 5) {
vDateName.value = vDateValue+strSeperator;
   }
}
return true;
   }
}
if (vDateValue.length == 10&& dateCheck) {
if (!dateValid(vDateName)) {
// Un-comment the next line of code for debugging the dateValid() function error messages
//alert(err);  
alert("Invalid Date\nPlease Re-Enter");
vDateName.focus();
vDateName.select();
   }
}
return false;
}
else {
// If the value is not in the string return the string minus the last
// key entered.
if (isNav4) {
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
else
{
vDateName.value = vDateName.value.substr(0, (vDateValue.length-1));
return false;
         }
      }
   }
}
function dateValid(objName) {
var strDate;
var strDateArray;
var strDay;
var strMonth;
var strYear;
var intday;
var intMonth;
var intYear;
var booFound = false;
var datefield = objName;
var strSeparatorArray = new Array("-"," ","/",".");
var intElementNr;
// var err = 0;
var strMonthArray = new Array(12);
strMonthArray[0] = "Jan";
strMonthArray[1] = "Feb";
strMonthArray[2] = "Mar";
strMonthArray[3] = "Apr";
strMonthArray[4] = "May";
strMonthArray[5] = "Jun";
strMonthArray[6] = "Jul";
strMonthArray[7] = "Aug";
strMonthArray[8] = "Sep";
strMonthArray[9] = "Oct";
strMonthArray[10] = "Nov";
strMonthArray[11] = "Dec";
//strDate = datefield.value;
strDate = objName;
if (strDate.length < 1) {
return true;
}
for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) {
if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) {
strDateArray = strDate.split(strSeparatorArray[intElementNr]);
if (strDateArray.length != 3) {
err = 1;
return false;
}
else {
strDay = strDateArray[0];
strMonth = strDateArray[1];
strYear = strDateArray[2];
}
booFound = true;
   }
}
if (booFound == false) {
if (strDate.length>5) {
strDay = strDate.substr(0, 2);
strMonth = strDate.substr(2, 2);
strYear = strDate.substr(4);
   }
}
//Adjustment for short years entered
if (strYear.length == 2) {
strYear = '20' + strYear;
}
strTemp = strDay;
strDay = strMonth;
strMonth = strTemp;
intday = parseInt(strDay, 10);
if (isNaN(intday)) {
err = 2;
return false;
}
intMonth = parseInt(strMonth, 10);
if (isNaN(intMonth)) {
for (i = 0;i<12;i++) {
if (strMonth.toUpperCase() == strMonthArray[i].toUpperCase()) {
intMonth = i+1;
strMonth = strMonthArray[i];
i = 12;
   }
}
if (isNaN(intMonth)) {
err = 3;
return false;
   }
}
intYear = parseInt(strYear, 10);
if (isNaN(intYear)) {
err = 4;
return false;
}
if (intMonth>12 || intMonth<1) {
err = 5;
return false;
}
if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) {
err = 6;
return false;
}
if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) {
err = 7;
return false;
}
if (intMonth == 2) {
if (intday < 1) {
err = 8;
return false;
}
if (LeapYear(intYear) == true) {
if (intday > 29) {
err = 9;
return false;
   }
}
else {
if (intday > 28) {
err = 10;
return false;
      }
   }
}
return true;
}
function LeapYear(intYear) {
if (intYear % 100 == 0) {
if (intYear % 400 == 0) { return true; }
}
else {
if ((intYear % 4) == 0) { return true; }
}
return false;
}
//  End -->
</script>

</HEAD>
<BODY>

<SCRIPT LANGUAGE = "JAVASCRIPT"></SCRIPT>
<script language="JavaScript">
<!-----
function oninsert()
{
// CGW SHOULD BE GREATER THAN OR EQUAL TO GROSS WT
if (document.myForm.GROSS_WEIGHT.value > document.myForm.COMBINED_GROSS_WEIGHT.value)
  {
    alert("CGW should be greater than or equal to GROSS WT");
    document.myForm.COMBINED_GROSS_WEIGHT.focus();
	return false;
  }
if (document.myForm.COMBINED_GROSS_WEIGHT.value > 80000)
  {
    alert("CGW should not be greater than 80000");
    document.myForm.COMBINED_GROSS_WEIGHT.focus();
	return false;
  }
if (document.myForm.UNIT_NUMBER.value =="")
  {
    alert("Please enter UNIT NUMBER");
    document.myForm.UNIT_NUMBER.focus();
	return false;
  }

if (document.myForm.VEH_PURCHASE_YEAR.value =="")
  {
    alert("Please enter PURCHASE DATE");
    document.myForm.VEH_PURCHASE_YEAR.focus();
	return false;
  }
   if (document.myForm.INS_COMPANY.value =="")
	  {
    alert("Please enter INSURANCE COMPANY");
    document.myForm.INS_COMPANY.focus();
	return false;
  }
   if (document.myForm.INS_POLICY.value =="")
  {
    alert("Please enter INSURANCE POLICY");
    document.myForm.INS_POLICY.focus();
	return false;
  }

  var dy = document.myForm.VEH_PURCHASE_YEAR.value.substr(3,2);
  var mo = document.myForm.VEH_PURCHASE_YEAR.value.substr(0,2);
  var yr = document.myForm.VEH_PURCHASE_YEAR.value.substr(6,4);
  
  var nDate = new Date();  // current date (local)
//alert(nDate);
  var nowTime = nDate.getTime();  // current time (UTC)
  //alert ("nowtime " + nowTime);
  var thenTime = Date.UTC(yr,mo-1,dy);  // specified time (UTC)
 // alert("thentime" + thenTime);
  var thisYear = nDate.getFullYear();
  var thisMonth = nDate.getMonth();
  var thisDay = nDate.getDate();

  //-----------------Past or present time
if (  thenTime > nowTime) 
	{
	    alert("Purchase Date can not be greater than today's date.");
		document.myForm.VEH_PURCHASE_YEAR.focus();
		return false;
     }

if (!(document.myForm.MODEL_YEAR.value ==""))
	{
	if ((document.myForm.MODEL_YEAR.value) > (thisYear + 1))
		{
		alert("Model Year can not be more than the next year.");
		document.myForm.MODEL_YEAR.focus();
		return false;
		}
	}


if ((document.myForm.P_TYPE.value =="BS") 
	  &&(document.myForm.SEATS.value =="" ))
	{
	  alert("If Vehicle type is BS , SEATS has to be entered");
	  document.myForm.SEATS.focus();
	  return false;
	}

  if ((document.myForm.P_TYPE.value == "BS" || 
	 document.myForm.P_TYPE.value == "RT" )
	&& (document.myForm.P_PLTYPE.value == "WR" ||
		document.myForm.P_PLTYPE.value == "1 ST" ||
		document.myForm.P_PLTYPE.value == "5 ST" ||
		document.myForm.P_PLTYPE.value == "PST"))
	{
		alert("If Vehicle type is BS or FT ,Plate type must be PWR");
		document.myForm.P_PLTYPE.focus();
		return false;
	}
  
  if ((document.myForm.P_TYPE.value == "CG" ||    document.myForm.P_TYPE.value == "FT" ||
	  document.myForm.P_TYPE.value == "ST")
	&& (document.myForm.P_PLTYPE.value == "PWR" ||
	     document.myForm.P_PLTYPE.value == "WR" ))
	{
		alert("If Vehicle type is CG or FT or ST ,Plate type must be 1 ST or 5 ST or PST");
		document.myForm.P_PLTYPE.focus();
		return false;
	}
	
	
	if ((document.myForm.P_TYPE.value == "TK" ||    document.myForm.P_TYPE.value == "TR" ||
	  document.myForm.P_TYPE.value == "TT")
	&& (document.myForm.P_PLTYPE.value == "1 ST" ||
	     document.myForm.P_PLTYPE.value == "5 ST" ||
		document.myForm.P_PLTYPE.value == "PST" ))
	{
	alert("If Vehicle type is TK or TR or TT ,Plate type must be PWR or WR");
		document.myForm.P_PLTYPE.focus();
		return false;
	}

else
 {
    document.myForm.submit();
 }
}
</script>

<script language="JavaScript">
function charcheck(field)
{
	if (field.name == "VEHICLE_MAKE")
	{
		max = 30;
	}
	if (field.name == "UNIT_NUMBER")
	{
		max = 15;
	}

 if (field.name == "VEH_PLATE_NUMBER")
	{
		max = 10;
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

	if (field.name == "REQUESTED_BY")
	{
		max = 50;
	}

	if (field.name == "POSITION")
	{
		max = 50;
	}
	if (field.name == "PERMIT_REASON")
	{
		max = 75;
	}
	if (field.value.length > max)
	{
		alert("Please do not enter more than "+ max+ " charecters ");
		field.focus();
		return false;
	}
	}
function platetype_chk()
{
	if ((document.myForm.P_TYPE.value == "BS" || document.myForm.P_TYPE.value == "RT" )
	&& (document.myForm.P_PLTYPE.value == "WR" ||
		document.myForm.P_PLTYPE.value == "1 ST" ||
		document.myForm.P_PLTYPE.value == "5 ST" ||
		document.myForm.P_PLTYPE.value == "PST"))
	{
		alert("If Vehicle type is BS or FT ,Plate type must be PWR");
		document.myForm.P_PLTYPE.focus();
		return false;
	}
  
  if ((document.myForm.P_TYPE.value == "CG" ||    document.myForm.P_TYPE.value == "FT" ||
	  document.myForm.P_TYPE.value == "ST")
	&& (document.myForm.P_PLTYPE.value == "PWR" ||
	     document.myForm.P_PLTYPE.value == "WR" ))
	{
		alert("If Vehicle type is CG or FT or ST ,Plate type must be 1 ST or 5 ST or PST");
		document.myForm.P_PLTYPE.focus();
		return false;
	}
	
	
	if ((document.myForm.P_TYPE.value == "TK" ||    document.myForm.P_TYPE.value == "TR" ||
	  document.myForm.P_TYPE.value == "TT")
	&& (document.myForm.P_PLTYPE.value == "1 ST" ||
	     document.myForm.P_PLTYPE.value == "5 ST" ||
		document.myForm.P_PLTYPE.value == "PST" ))
	{
		alert("If Vehicle type is TK or TR or TT ,Plate type must be PWR or WR");
		document.myForm.P_PLTYPE.focus();
		return false;
	}
	}
function VIN_VALID()
{
if (document.myForm.VIN.value==""  )
  {
    alert("PLEASE ENTER THE VIN NUMBER");
    document.myForm.VIN.focus();
	return false;
  }
  	{
	  myForm.submit();
	}
  }

  function num_check(field)
   {
   var checkOK = "0123456789";
var checkStr = field.value;
var allValid = true;
var decPoints = 0;
var allNum = "";
for (i = 0;  i < checkStr.length;  i++)
{
ch = checkStr.charAt(i);
for (j = 0;  j < checkOK.length;  j++)
if (ch == checkOK.charAt(j))
break;
if (j == checkOK.length)
{
allValid = false;
break;
}
if (ch != ",")
allNum += ch;
}
if (!allValid)
{
    alert("Please enter only numeric value in the field ");
    field.focus();
    return (false);
}
  }
</script>