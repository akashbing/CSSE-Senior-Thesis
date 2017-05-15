regionAds = LOAD '/tmp/BillBoardByDay_Region/regionAds.csv' USING PigStorage(',') AS (region:chararray, date:chararray, theme:chararray, rating:int);
speedFiles = LOAD '/tmp/Region_ChicagoData_Congestions/*' USING PigStorage('\t') AS (region:chararray, datacount:int, heavyFlowCount:int, mediumHeavyFlowCount:int, mediumFlowCount:int, lightFlowCount:int, freeFlowCount:int, startHeavyTime:int, endHeavyTime:int, avgSpeed:float);
regionComb = FOREACH regionAds GENERATE CONCAT(CONCAT((chararray) region, '***'), date) AS regionID, theme, rating;
joinRegion = JOIN speedFiles BY region, regionComb BY regionID;
cleanedJoin = FOREACH joinRegion GENERATE $0, datacount, heavyFlowCount, mediumHeavyFlowCount, mediumFlowCount, lightFlowCount, freeFlowCount, startHeavyTime, endHeavyTime, avgSpeed, theme, rating;
STORE cleanedJoin INTO '/tmp/JoinedTrafficBillBoard/Regions/';
