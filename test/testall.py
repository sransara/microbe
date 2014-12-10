#! /usr/bin/env python
import os
import sys
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
testdir = '.' + os.sep + 'test' + os.sep
nonsense = 'cmd /C ' if sys.platform.startswith('win') else ''

inputfile = testdir + 'input.txt'
import random
random.seed()
randomnumbers = [random.randint(1,20) for x in xrange(20)]
f = open(inputfile,'w')
for r in randomnumbers:
    f.write(str(r) + '\n')
f.close()

for fname in os.listdir(testdir):
    if(fname.endswith('.micro') and (len(sys.argv) == 1 or fname.startswith(sys.argv[1]))):
        micro = testdir + fname
        ouout = testdir + fname.replace('.micro', '.out')
        myout = testdir + fname.replace('.micro', '.myout')
        mout = testdir + fname.replace('.micro', '.m.myout')
        tout = testdir + fname.replace('.micro', '.t.out')

        connector = ";"
        if os.name == "posix": connector = ":"
        oucommand = 'java -jar '+ testdir +'final.jar ' + micro + ' > ' + ouout
        excommand = 'java -ea -cp lib/antlr.jar' + connector + 'classes/ Micro '+ micro + ' > ' + myout
        t2xcommand = nonsense + testdir + 'tinyR ' + ouout + ' nostats < ' + inputfile + ' > ' + tout
        t1xcommand = nonsense + testdir + 'tinyR ' + myout + ' nostats < ' + inputfile + ' > ' + mout
        dfcommand = 'diff -y -W 150 ' + mout + ' ' + tout

        print "Testing file:", fname
        try:
            print(oucommand)
            subprocess.check_output(oucommand, shell=True);
            print(excommand)
            subprocess.check_output(excommand, shell=True);
            print(t2xcommand)
            subprocess.check_output(t2xcommand, shell=True);
            print(t1xcommand)
            subprocess.check_output(t1xcommand, shell=True);
        except:
            print "--- Run time error"
            exit(1)

        try:
            print(dfcommand)
            subprocess.check_output(dfcommand, shell=True);
            print "--- PASSED TEST"
        except:
            print "--- FAILED TEST"
            os.system(dfcommand)
            print ""
            exit(1)

if(errors): exit(1)

