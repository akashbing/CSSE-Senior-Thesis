#!/bin/bash
REGION_COUNT=$(ls RegionEst/*.csv | wc -l)
SEGMENT_COUNT=$(ls SegmentEst/*.csv | wc -l)
DATE=$(date)
echo ""
echo "Stats: $DATE"
echo "Region CSV Files: $REGION_COUNT"
echo "Segment CSV Files: $SEGMENT_COUNT"