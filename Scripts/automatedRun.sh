#!/bin/bash
# Created by Larry Gates


regionPrevFile=""
segmentPrevFile=""
regionDir="ChicagoData/RegionEst"
segmentDir="ChicagoData/SegmentEst"
email="email@email.com"
subject="Script Progress"
counter=0

function curlStatements() 
{
	curl -fs https://data.cityofchicago.org/api/views/t2qc-9pjd/rows.csv?accessType=DOWNLOAD -o "ChicagoData/RegionEst/$1.csv"
	curl -fs https://data.cityofchicago.org/api/views/t2qc-9pjd/rows.json?accessType=DOWNLOAD -o "ChicagoData/RegionEst/$1.json"
	curl -fs https://data.cityofchicago.org/api/views/t2qc-9pjd/rows.xml?accessType=DOWNLOAD -o "ChicagoData/RegionEst/$1.xml"

	curl -fs https://data.cityofchicago.org/api/views/n4j6-wkkf/rows.json?accessType=DOWNLOAD -o "ChicagoData/SegmentEst/$2.json"
	curl -fs https://data.cityofchicago.org/api/views/n4j6-wkkf/rows.xml?accessType=DOWNLOAD -o "ChicagoData/SegmentEst/$2.xml"
	curl -fs https://data.cityofchicago.org/api/views/n4j6-wkkf/rows.csv?accessType=DOWNLOAD  -o "ChicagoData/SegmentEst/$2.csv"
}

echo "Downloading Started"
while [ true ] 
	segmentPrevFile=$(ls ChicagoData/SegmentEst/*csv -t | head -1)
	regionPrevFile=$(ls ChicagoData/RegionEst/*csv -t | head -1)
	now=$(date +"%H_%M_%S-%m_%d_%y")
	echo "Run Segment and Region Download"
	date
	regionFile="regionEst-$now"
	segmentFile="segmentEst-$now"
	do curlStatements $regionFile $segmentFile 
	# echo $regionFile
	# echo $segmentFile
	# echo $regionPrevFile
	# echo $segmentPrevFile
	if cmp --silent "$regionDir/$regionFile.csv" "$regionPrevFile" 
		then
		rm "$regionDir/$regionFile.csv"
		rm "$regionDir/$regionFile.xml"
		rm "$regionDir/$regionFile.json"
		echo "Same region"
	fi
	
	if  cmp --silent "$segmentDir/$segmentFile.csv" "$segmentPrevFile" 
		then
		rm "$segmentDir/$segmentFile.csv"
		rm "$segmentDir/$segmentFile.xml"
		rm "$segmentDir/$segmentFile.json"
		echo "Same segment"
	fi
	
	sleep 5m

	((counter+=5))
	
	echo ""

	if [ $counter -eq "60" ]; then
		DATE=$(date)
		counter=0
		REGION_COUNT=$(ls ChicagoData/RegionEst/*.csv | wc -l)
		SEGMENT_COUNT=$(ls ChicagoData/SegmentEst/*.csv | wc -l)
		STRING=$"Stats: $DATE \nRegion CSV Files: $REGION_COUNT \nSegment CSV Files: $SEGMENT_COUNT\n"
		printf "$STRING" | mail -s "$subject" $email
	fi 
done


