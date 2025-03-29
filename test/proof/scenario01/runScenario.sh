#! /bin/bash
rm -rf andoria
rm -rf birdOfPrey
rm -rf enterprise
tar -zxvf MAS.tar.gz 
cd andoria/
jasonEmbedded andoria.mas2j  -gui &
cd ..
cd birdOfPrey/
jasonEmbedded birdOfPrey.mas2j  -gui &
cd ..
cd enterprise/
jasonEmbedded enterprise.mas2j -gui ; pkill java
cd ..
