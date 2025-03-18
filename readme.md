# Backend Spring Boot - Restaurant

## Prérequis

Java 21 (java -version)

PostgreSQL (psql --version)


## 2. Installation et Configuration
Cloner le projet

```git clone https://github.com/ton-repo/restaurant-backend.git```

cd restaurant-backend

 PostgreSQL
Crée la base de données :

```CREATE DATABASE restaurant;```
importation de la base de donéne 
```` psql -U postgres -d restaurant -f restaurant.sql
````
les tables de base de donnée seron genérée automatiquement par hibernate
## structure de projet

## API REST
   
Voir tous les menus	GET	/api/menus
   
Ajouter un menu	POST	/api/menus

Modifier un menu	PUT	/api/menus/{id}
   
Supprimer un menu	DELETE	/api/menus/{id}

Voir tous les plats	GET	/api/plats
   
Ajouter un plat	POST	/api/plats
   
 Voir toutes les boissons	GET	/api/boissons
   
Ajouter une boisson	POST	/api/boissons
   
Voir les réservations	GET	/api/reservations

Ajouter une réservation	POST	/api/reservations 

## l'ajout et l'edition et la suppression de menu ,plat ,boisson sont implémentée dans les sevice et gerée par les api
