# Makefile for compiling and running the Java program

# Specify the Java compiler
JAVAC = javac

# Specify the Java runtime
JAVA = java

# Specify the main class
MAIN_CLASS = Main

# Specify the source files
SOURCES = Main.java Dataset.java Observer.java Player.java Viewer.java \
          Playable.java NonPlayable.java Image.java Audio.java Video.java Text.java

# Specify the classpath and output directory
CLASSPATH = .
OUTPUT_DIR = bin

# Default target
all: compile run

# Target for compiling the Java code
compile:
	mkdir -p $(OUTPUT_DIR)
	$(JAVAC) -d $(OUTPUT_DIR) -cp $(CLASSPATH) $(SOURCES)

# Target for running the program
run:
	$(JAVA) -cp $(OUTPUT_DIR) $(MAIN_CLASS)

# Target for cleaning the compiled files
clean:
	rm -rf $(OUTPUT_DIR)

.PHONY: all compile run clean

