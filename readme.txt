Night Vision source files
-------------------------

Night Vision may be examined and built with the source files included
in this directory.

Required for building NV is the Java Development Kit (minimum
version 8):
  https://www.oracle.com/java/technologies/downloads/
and the Apache Ant build tool:
  https://ant.apache.org/bindownload.cgi

With Java and Ant installed, all that is needed to compile and run NV
is to issue the following command from this directory:

  ant run

This will utilize the compile and build instructions within the
build.xml file included in this directory.


NV began as a program for the OS/2 operating system.  Around the
year 2000 conversion to Java began.  After conversion was complete
a number of new features have been added, and now (as of November 2023)
NV comprises 69 Java source files.  Some comments on the Java source:
- There were many problems encountered in the Java compilers at
  the time the earliest NV source files were written.  Many of the
  work-arounds are still there, as focus has always been in adding
  new features rather than re-writing existing (and working) code.
- Documentation is hopefully adequate in most cases, but can and
  should be enhanced.  Most of the documentation was written before
  the transition to open source.
- Some incompatibilities between Java 1.3 and 1.4 had to be dealt
  with previously by compiling some files with 1.3 and the rest
  with 1.4.  This allowed NV to run in both environments.  Now
  that NV requires a minimum of Java 8 this mixed-compile is
  no longer done, and hopefully all of the code adjustments for
  this have been removed, but it is possible some vestiges remain.
- Some of the smaller source files should be re-examined and
  perhaps removed or consolidated with other files.

