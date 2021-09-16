# Le nom de votre classe principale
# Renommez si nécessaire
MAINCLASS=com.example.tp1.Main
## Le chemin vers où votre classe compilée est installée
# Renommez si nécessaire
INSTALLDIR=../out/production/TP1

MAINDIR=$(dir $(subst .,/,$(MAINCLASS)))

compile: $(subst .,/,$(MAINCLASS)).class

%.class: %.java
	javac -g  $*.java

clean:
	rm $(MAINDIR)*.class

install:
	cp $(MAINDIR)*.class $(INSTALLDIR)/$(MAINDIR)

cleanInstall:
	rm  $(INSTALLDIR)/$(MAINDIR)*.class



