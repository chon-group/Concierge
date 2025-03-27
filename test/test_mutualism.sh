#! /bin/bash
rm mas01/src/agt/spock.asl 
cp mutualism/asl/spock.asl.bkp mutualism/asl/spock.asl
xterm -geometry 120x50 -e "jason mas01/mas01.mas2j" &
xterm -geometry 120x50 -e "jason mutualism/mutualism.mas2j"
