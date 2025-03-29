#! /bin/bash
rm -rf andoria
rm -rf birdOfPrey
rm -rf enterprise
rm -rf jasonDeps
tar -zxvf MAS.tar.gz 
jason andoria/andoria.mas2j &
jason birdOfPrey/birdOfPrey.mas2j &
jason enterprise/enterprise.mas2j; pkill java
#clear
