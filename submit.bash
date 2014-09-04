#! /usr/bin/bash

make clean
make rsubmit
cd sabeysir
make
echo 
echo "---- Testing 1 2 3----"
make test
echo "---- All checked ----"
echo 
make clean
rm -rf test
cd ..
echo 
echo "---- Submission is ready to land ----"
read -p "What's the project step #: " p

while true; do
  read -p "turnin -c EE468 -p step$p -v sabeysir ? " yn
  case $yn in
    [Yy]* ) turnin -c EE468 -p step$p -v sabeysir; echo; exit;;
    [Nn]* ) exit;;
    * ) echo "Please answer (y)es or (n)o.";;
  esac
done
