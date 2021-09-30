## Voici un Makefile
## Pour plus d'information sut un Makefile voir
## https://makefiletutorial.com/

MAINCLASS=Main
INSTALLDIR=out/production/TP1
JARFILE=2SAT

JAR=/usr/bin/jar
JAVA=/usr/bin/java

#JAR=/usr/local/opt/openjdk/bin/jar
#JAVA=/usr/local/opt/openjdk/bin/java


all: compile install exec

# Cible (target, en anglais)  pour compiler
compile:
	cd src ; make compile

install:
	cd src ; make install

jar: compile
	cd $(INSTALLDIR); \
	echo Main-Class: $(subst /,.,$(MAINCLASS)) > manifest.txt ; \
	$(JAR) cvfm $(JARFILE).jar manifest.txt ./
	mv $(INSTALLDIR)/$(JARFILE).jar ./

clean:
	cd src ; make clean ; make cleanInstall
	rm *.zip *.jar manifest.*


# Cible pour executer 
exec:
	$(JAVA) -classpath $(PRODUCTIONPATH) $(MAINCLASS)

execjar: $(JARFILE).jar
	$(JAVA) -jar $(JARFILE).jar

# Executer automatiquent les test
# On s'attend (d'habitude) que pour claque classe MaClasse il y ait une
# classe TestMaClasse qui vorifie le bon comportment de chaque methode de la classe
# sur au moins une entrée
test:

# Cible pour créer son rendu de tp 
zip:
	moi=$$(whoami) ; zip -r $${moi}_renduTP1.zip *


# Cible pour vérifier le contenu de son rendu de tp 
zipVerify:
	moi=$$(whoami) ; unzip -l $${moi}_renduTP1.zip
