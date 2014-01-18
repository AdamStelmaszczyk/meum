#!/bin/sh

if [ $# -lt 1 ]; then
	echo "Usage: $0 DIM" 
	return 65
fi

if [ ! -e pngs ]; then 
	mkdir pngs
fi 

./make_pngs.sh ae_$1 naeu_$1 naep_$1 nael_$1 naek_$1
