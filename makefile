JC := javac
FLAGS := -sourcepath src -d bin

bin/alessandro/Test.class: test/alessandro/Test.java
	$(JC) $(FLAGS) test/alessandro/Test.java
	touch test/alessandro/Test.java

.PHONY: run
run:
	java -cp bin alessandro.Test
