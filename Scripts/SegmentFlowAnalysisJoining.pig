segmentAds = LOAD '/tmp/BillBoardByDay_Segment/segmentAds.csv' USING PigStorage(',') AS (segment:int, date:chararray, theme:chararray, rating:int);
speedFiles = LOAD '/tmp/Segments_ChicagoData_Congestions/*' USING PigStorage('\t') AS (segment:chararray, datacount:int, heavyFlowCount:int, mediumFlowCount:int, freeFlowCount:int, startHeavyTime:int, endHeavyTime:int, avgSpeed:float, negativeSpeed:int);
segmentComb = FOREACH segmentAds GENERATE CONCAT(CONCAT((chararray)segment, '*****'), date) AS segmentID, theme, rating;
joinSegments = JOIN speedFiles BY segment, segmentComb BY segmentID;
cleanedJoin = FOREACH joinSegments GENERATE $0, datacount, heavyFlowCount, mediumFlowCount, freeFlowCount, startHeavyTime, endHeavyTime, avgSpeed, negativeSpeed, theme, rating;
STORE cleanedJoin INTO '/tmp/JoinedTrafficBillBoard/Segments/';
