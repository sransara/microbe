#! /usr/bin/env python
import os
import subprocess

# For ecn server to subprocess
def fn_check_output():
    def f(*popenargs, **kwargs):
        if 'stdout' in kwargs:
            raise ValueError('stdout argument not allowed, it will be overridden.')
        process = subprocess.Popen(stdout=subprocess.PIPE, *popenargs, **kwargs)
        output, unused_err = process.communicate()
        retcode = process.poll()
        if retcode:
            cmd = kwargs.get("args")
            if cmd is None:
                cmd = popenargs[0]
            raise subprocess.CalledProcessError(retcode, cmd)
        return output
    return f

if "check_output" not in dir(subprocess): 
    subprocess.check_output = fn_check_output()

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
            dyfcommand = 'diff -y ' + myout + ' ' + trout
            os.system(dyfcommand)
            errors = True
            print "--- Failed testcase for ", fname

if(errors): exit(1)

