           
SelStates = new Array();
var i = 0; 

function Stateis(stateis) 
{ 
 
  flag = true;
  
  var m1,m2,m3,m; 
  
  m1 = "Sorry!  "
  m3 = " State is already selected. " +  "\n" + "If you want to DESELECT,  please press 'RESET' Buton and start again";

  if (document.stateform.States.value == "")
  {
    document.stateform.States.value = stateis ;
    SelStates[0] = stateis;  
  }
  else
  {
  
    for (i = 0 ; i < SelStates.length ; i++)
    {
       if (SelStates[i] == stateis)
       {
         flag = false;  
         m2 = "'" + SelStates[i] + "'";
       }  
    }
    if (flag == false)
    {
      m = m1 + m2 + m3;
      alert(m);
    }  
    else
    {
         document.stateform.States.value += "," + stateis;  
         SelStates[SelStates.length] = stateis;  
    }
     
  } 
  
}
 