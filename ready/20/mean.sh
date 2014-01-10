#!/bin/sh

for alg in *
do
	if [ ! -d $alg ]
	then
		continue;
	fi
	echo $alg
	for info in $alg/*.info
	do
		sum=0; 
		for i in `cat $info`
		do 
			sum=$(echo $sum + $i | bc);
		done; 
		echo -n "$info:\t" 
		total=$(wc -w $info | awk '{print $1}')
		echo $sum/$total | bc -l;
	done;
done;
