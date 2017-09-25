---
title 		: PDG - Fonctionnalités
author		: Lawrence Stalder
date 		: 19 Septembre 2017
geometry 	: "margin=1in"
algorithm 	: true
fontsize	: 11pt
lang		: true
babel-lang	: "francais"
---


# Designs Patterns
* State (co/deco)

# Fonctionnalités
## Librairies
* Java
* JavaFx
* Maven
* Gson

## Client
* connexion au server -> SSLSocket
* identifiant
* jouer
* notification (pret à jouer sur partie x)
* communication

## Server
* mySQL
* dico
* gestion client
* historique /stats
* stockage profil joueur (ID, etc)

## Implémentation
### Mode Partie
* timer 72h max entre chaque cout

### Mode Tournoi
* timer 24h max entre chaque cout

### Commun
* vérificateur à chaque tuile
* indices
* chat
* jeter les tuiles
* melanger les tuiles (réaffichage dans un autre sens)
* lancer une recherche de partie
* aperçu des tuiles adverses

### Bonus
* parrainage
* notification windows
* pubs

# Règles du jeu
* Former des mots 7 lettres
* chaque joueur possède 7 tuiles random
* pre-séléctionne des tuiles
* les tuiles valent des points
* la partie possède 114 tuiles 
* la victoire revient au joueur avec le plus de points une fois que les tuiles sont épuisées





