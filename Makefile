JC     = javac
EXE    = LFSR_Test
JFLAGS = -sourcepath src -classpath obj -d obj -target 7 -source 7

default: all

all: $(CLASSES)
	javac $(JFLAGS) src/*.java
	cd obj && jar cvmf ../Manifest.txt ../$(EXE).jar *.class
	chmod +x ./$(EXE).jar

clean:
	find . -name "*.class" -exec rm {} \;
	rm $(EXE).jar
