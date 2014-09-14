#! /usr/bin/env python
import os
import subprocess

errors = False
testdir = './test/'
for fname in os.listdir(testdir):
    if(fname.endswith('.micro')):
        micro = testdir + fname
        myout = testdir + fname.replace('.micro', '.myout')
        trout = testdir + fname.replace('.micro', '.out')

        connector = ";"
        if os.name == "posix": connector = ":"
        excommand = 'java -cp lib/antlr.jar' + connector + 'classes/ Micro '+ micro + ' > ' + myout
        dfcommand = 'diff -b -B ' + myout + ' ' + trout

        print "Testing file:", fname
        exout = subprocess.check_output(excommand, shell=True);
        try:
            dfout = subprocess.check_output(dfcommand, shell=True);
        except:
            errors = True
            print "--- Failed testcase for ", fname

if(errors): exit(1)
