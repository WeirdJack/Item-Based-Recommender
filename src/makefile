JFLAGS = -g
JC = javac
JVM= java
FILE = src/train.txt

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Driver.java \
        FileProcessor.java \
        IResults.java \
        MetricsCalculator.java \
        Recommender.java \
        Results.java

#
# MAIN is a variable with the name of the file containing the main method
#

MAIN = Driver

default: classes

classes: $(CLASSES:.java=.class)

input_file: $(FILE)
run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class output.txt
