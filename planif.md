---
title 		: PDG
author		: Lawrence Stalder
date 		: 19 Septembre 2017
geometry 	: "margin=1in"
algorithm 	: true
fontsize	: 11pt
lang		: true
babel-lang	: "francais"
---


#Fonctionnalités

##Divers
* Maven
* JavaFx
* Gson


##Client
* connexion au server -> SSLSocket
* identifiant
* jouer
* notification (pret à jouer sur partie x)
* communication

##Server
* mySQL
* dico
* gestion client
* historique /stats
* stockage profil joueur (ID, etc)


##Les petits plus
* verificateur à chaque tuile
* indices
* chat
* jeter les tuiles
* melanger les tuiles (réaffichage dans un autre sens)
* timer 72h
* aperçu des tuiles adverses
* parrainage
* notification windows
* pubs


#Règles du jeu
* Former des mots 7 lettres
* chaque joueur pocède 7 tuiles random
* pre-selectionne des tuiles
* les tuiles valent des points
* la partie pocède 114 tuiles 
* la victoire revient au joueur avec le plus de points une fois que les tuiles sont épuisées


#Design Pattern
* State (co/deco)


#Planification
1 :
Découverte du projet, constitution des groupes.

2 :
Plannification
Liste des fonctionnalités
Recherche d'outils et ressources (dico, librairies, langages, ...)

3 :
Liste des fonctionnalités finale (+ règles)
Modèle de base de données
Diagramme de classes
Protocole initial

4 :
Préparation de l'UI pour les fonctionnalités de base
Serveur TCP (gestion des connexions aux clients)
Base de données
Début de rédaction des requêtes SQL

5 :
Finition et implémentation du protocole
Création de compte
Login

6 :
Lancement d'une partie contre un utilisateur connu

7 :
Lancement d'une partie contre un utilisateur aléatoire (en attente de partie)
Déroulement d'une partie

8 :
Présentation intermédiaire (14.11, imposé)
Déroulement d'une partie

9 :
Déroulement d'une partie

10:
Mode tournoi

11:
Mode tournoi
Finitions

12:
Finitions

13:
Rapport

14:
Rapport

15:
Rendu final du projet (16.01, imposé)

16:
Défense orale (23.01, imposé)







