## Voici un Makefile
## Pour plus d'information sut un Makefile voir
## https://makefiletutorial.com/

MAINCLASS=Main
PRODUCTIONPATH=out/production/TP1

all: compile install exec

# Cible (target, en anglais)  pour compiler
compile:
	cd src ; make compile

install:
	cd src ; make install

clean:
	cd src ; make clean ; make cleanInstall
	rm *.zip

# Cible pour executer 
exec:
	java -classpath $(PRODUCTIONPATH) $(MAINCLASS)

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
