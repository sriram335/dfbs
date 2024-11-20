// ALL abbreviations for Vehicle Types are the types in OSS_VEHICLE
//FEB - TK - GROSS
febTKArray = new Array();
febTKArray[0] = ["0-7000","7000"];
febTKArray[1] = ["7001-9000","9000"];
febTKArray[2] = ["9001-10000","10000"];
febTKArray[3] = ["10001-11000","11000"];
//FEB - FT	- GROSS	
febFTArray = new Array();
febFTArray[0] = ["0-3000","3000"];
//MAR - ST - NONE
marSTArray = new Array();
marSTArray[0] = ["All Weights","8000"];
//MAR - WR - GROSS
marWRArray = new Array();
marWRArray[0] = ["0-16000","16000"];
marWRArray[1] = ["16000+","16001"];
//MAR - TK - GROSS
marTKArray = new Array();
marTKArray[0] = ["11001-16000","16000"];
marTKArray[1] = ["16001-20000","20000"];
marTKArray[2] = ["20001-23000","23000"];
marTKArray[3] = ["23001-26000","26000"];
marTKArray[4] = ["26001-30000","30000"];
marTKArray[5] = ["30001-36000","36000"];
marTKArray[6] = ["36001-42000","42000"];
marTKArray[7] = ["42001-48000","48000"];
marTKArray[8] = ["48001-54000","54000"];
marTKArray[9] = ["54001-60000","60000"];
marTKArray[10] = ["60001-66000","66000"];
marTKArray[11] = ["66000+","66001"];

//MAR - TR - RT - CGW
marTRArray = new Array();
marTRArray[0] = ["0-20000","20000"];
marTRArray[1] = ["20001-26000","26000"];
marTRArray[2] = ["26001-30000","30000"];
marTRArray[3] = ["30001-36000","36000"];
marTRArray[4] = ["36001-42000","42000"];
marTRArray[5] = ["42001-48000","48000"];
marTRArray[6] = ["48001-54000","54000"];
marTRArray[7] = ["54001-60000","60000"];
marTRArray[8] = ["60001-66000","66000"];
marTRArray[9] = ["66001-72000","72000"];
marTRArray[10] = ["72001-76000","76000"];
marTRArray[11] = ["76001-78000","78000"];
marTRArray[12] = ["78000+","78001"];
//MAR - FT - GROSS
marFTArray = new Array();
marFTArray[0] = ["3001-5000","5000"];
marFTArray[1] = ["5001-7000","7000"];
marFTArray[2] = ["7001-9000","9000"];
marFTArray[3] = ["9001-12000","12000"];
marFTArray[4] = ["12001-16000","16000"];
marFTArray[5] = ["16001-22000","22000"];
marFTArray[6] = ["22000+","22001"];
function getWeightRange(regMonth,vehType){
	if(regMonth == 1){
		if(vehType == 'TK'){
			return febTKArray;
		}else if(vehType == 'FT'){
			return febFTArray;
		}
	}else if(regMonth == 2){
		if(vehType == 'WR'){
			return marWRArray;
		}else if(vehType == 'TK'){
			return marTKArray;
		}else if(vehType == 'TR' || vehType == 'RT'){
			return marTRArray;
		}else if(vehType == 'FT'){
			return marFTArray;
		}
	}

}