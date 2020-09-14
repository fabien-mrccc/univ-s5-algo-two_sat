# Cible qui pour compiler
compile:

# Cible qui explique comment executer
exec:

# Demarre automatiquement une demonstration de votre programme
# Il faut que cette demo soit convaincante
demo:

# Executer automatiquent les test
# On s'attend (d'habitude) que pour claque classe MaClasse il y ait une
# classe TestMaClasse qui vorifie le bon comportment de chaque methode de la classe
# sur au moins une entrée
test:

# Pour la cible suivante, on vous laisse faire
clean:

# Voici une cible complète, pour créer son rendu de tp 
zip:
	moi=$$(whoami) ; zip -r $${moi}_renduTP1.zip *
