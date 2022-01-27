.PHONY: all run

all:
	javac -sourcepath ./src -d bin src/Main.java

run:
	java -classpath ./bin Main
