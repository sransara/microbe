#! /usr/bin/env python
import os
from subprocess import call

testdir = './test/'
for fname in os.listdir(testdir):
    if(fname.endswith('.micro')):
        micro = testdir + fname
        myout = testdir + fname.replace('.micro', '.myout')
        trout = testdir + fname.replace('.micro', '.out')
        excommand = 'java -cp lib/antlr.jar;classes/ Micro '+ micro + ' > ' + myout
        dfcommand = 'diff -b -B ' + myout + ' ' + trout
        print "Testing file:", fname
        os.system(excommand);
        os.system(dfcommand);
