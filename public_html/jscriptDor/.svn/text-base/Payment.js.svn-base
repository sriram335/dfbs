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