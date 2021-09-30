## Voici un Makefile
## Pour plus d'information sut un Makefile voir
## https://makefiletutorial.com/

INSTALLDIR=out/production/TP1
MAINCLASS=Main
JARFILE=2SAT


#JREBIN=/usr/bin
#JREBIN=/usr/local/opt/openjdk/bin
ECLIPSE_LOC=/Applications/Eclipse\ Java.app
JREBIN=$(ECLIPSE_LOC)/Contents/Eclipse/plugins/org.eclipse.justj.openjdk.hotspot.jre.full.macosx.x86_64_16.0.2.v20210721-1149/jre/bin

JAVAC=$(JREBIN)/javac
JAR=$(JREBIN)/jar
JAVA=$(JREBIN)/java

all: compile jar execjar

# Cible (target, en anglais)  pour compiler
compile:
	cd src ; make JAVAC="$(JAVAC)" INSTALLDIR="$(INSTALLDIR)" compile

install:
	cd src ; make install

jar: compile
	cd $(INSTALLDIR); \
	echo Main-Class: $(subst /,.,$(MAINCLASS)) > manifest.txt ; \
	$(JAR) cvfm $(JARFILE).jar manifest.txt ./
	mv $(INSTALLDIR)/$(JARFILE).jar ./

clean:
	cd src ; make clean ; make INSTALLDIR="$(INSTALLDIR)" cleanInstall
	rm *.zip *.jar manifest.*


# Cible pour executer 
exec:
	$(JAVA) -classpath $(INSTALLDIR) $(MAINCLASS)

execjar: $(JARFILE).jar
	$(JAVA) -jar $(JARFILE).jar

version:
	$(JAVA) --version

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

#  LocalWords:  SAT
