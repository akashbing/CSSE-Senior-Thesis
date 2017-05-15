-- Handling Regions
regionRecords = LOAD '/tmp/ChicagoData/RegionEst' USING PigStorage(',') AS (region:chararray, region_id:int, west:double, east:double, south:double, north:double, description:chararray, speed:double, time:chararray);
regionTopRowRemove = FILTER regionRecords BY region != 'REGION';
regionClean1 = FOREACH regionTopRowRemove GENERATE region, speed, ToDate(time, 'yyyy-MM-dd HH:mm:ss.S') AS TimeStamp;
regionClean2 = FOREACH regionClean1 GENERATE region, speed, ToMilliSeconds(TimeStamp), TimeStamp, CONCAT(CONCAT(CONCAT(CONCAT((chararray)GetYear(TimeStamp), '-'), (chararray)GetDay(TimeStamp)), '-'), (chararray) GetMonth(TimeStamp)) AS StringTime;
regionClean3 = DISTINCT regionClean2;
regionClean4 = FILTER regionClean3 BY (long) MonthsBetween(CurrentTime(), TimeStamp) < (long) 8L;
STORE regionClean4 INTO '/tmp/ChicagoData_Result/Region/' USING org.apache.pig.piggybank.storage.MultiStorage('/tmp/ChicagoData_Result/Region/', '4', 'none', ',');


-- Handling Segments

segmentRecords = LOAD '/tmp/ChicagoData/SegmentEst' USING PigStorage(',') AS (segment_id:int, street:chararray, direction:chararray, from_street:chararray, to_street:chararray, length:double, street_heading:chararray, comments:chararray, start_longitude:long, start_latitude:long, end_longitude:long, end_latitude:long, speed:double, time:chararray);
segmentTopRowRemove = FILTER segmentRecords BY street != 'STREET';
segmentClean1 = FOREACH segmentTopRowRemove GENERATE segment_id, speed, ToDate(time, 'yyyy-MM-dd HH:mm:ss.S') AS TimeStamp;
segmentClean2 = FOREACH segmentClean1 GENERATE segment_id, speed, ToMilliSeconds(TimeStamp), TimeStamp, CONCAT(CONCAT(CONCAT(CONCAT((chararray)GetYear(TimeStamp), '-'), (chararray)GetDay(TimeStamp)), '-'), (chararray) GetMonth(TimeStamp)) AS StringTime;
segmentClean3 = DISTINCT segmentClean2;
segmentClean4 = FILTER segmentClean3 BY (long) MonthsBetween(CurrentTime(), TimeStamp) < (long) 8L;
STORE segmentClean4 INTO '/tmp/ChicagoData_Result/Segments/' USING org.apache.pig.piggybank.storage.MultiStorage('/tmp/ChicagoData_Result/Segments/', '4', 'none', ',');
