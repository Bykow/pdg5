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

# Domaine
Ce tableau à pour but de répartir quel pièce de logiciel ce trouve sur quelle application. Soit client, soit serveur, soit en commun

| Client            | Commun            | Server |
|    -              |    -              |    -   |
| Graphic interface | Exchange Protocol |   DB   |
| Game              |                   | MultiThreading for each client |
|                   |                   | Game Logic |
|    -              |    -              |  Dico  |

## Implémentation
### Mode Tutoriel
* unique possibilité lors du premier login
*

### Mode Partie
* timer 72h max entre chaque cout
* contre joueur matchmaking

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
* Former des mots de 2 à 7 lettres
* chaque joueur possède 7 tuiles random + 2 optionnelle envoyées par l'ennemi. 
* Au premier tour le joueur qui commence possède les 2 optionnelle
* pre-séléctionne des tuiles
* les tuiles valent des points
* la partie possède 114 tuiles 
* la victoire revient au joueur avec le plus de points une fois que les tuiles sont épuisées et qu'un joueur ne peut plus former de mots
* tour par tour

# Problématiques
* Base de donnée
* Tirage au sort
* Interface graphique
* Communication
* IA (joue contre la machine)
* Notifications
* Emplacements des tuiles avec multiplicateurs  
Envoie d'une string lors d'un nouveau coup  
Exemple : 0030000 pour la 3ème, x3
* Envoi de 0 à 2 tuiles à l'ennemi
* Choisie la lettre lorsqu'on pre-selectionne un Joker

#calcul des points par coup
* (somme(Lettre i * Multiplicteur i) + 10 si 7 lettres ) * 2 si lettres ennemies utilisées
* pas de multiplicateur sur la 7ème case
* toujours 2 W (position d'envoi à l'ennemi)
