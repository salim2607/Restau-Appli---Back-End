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

# API Documentation

## Allergènes
- **GET** `/api/allergenes` - Récupérer la liste de tous les allergènes  
- **POST** `/api/allergenes` - Ajouter un nouvel allergène  

## Boissons
- **GET** `/api/boissons` - Récupérer toutes les boissons  

## Catégories
- **GET** `/api/categories` - Récupérer toutes les catégories  
- **POST** `/api/categories` - Ajouter une nouvelle catégorie  

## Commandes
- **GET** `/api/commandes` - Récupérer toutes les commandes  
- **POST** `/api/commandes` - Ajouter une nouvelle commande  

## Menus
- **GET** `/api/menus` - Récupérer tous les menus  
- **POST** `/api/menus` - Ajouter un nouveau menu  

## Plats
- **GET** `/api/plats` - Récupérer tous les plats  
- **POST** `/api/plats` - Ajouter un nouveau plat  

## Réservations
- **GET** `/api/reservations` - Récupérer toutes les réservations  
- **POST** `/api/reservations` - Créer une nouvelle réservation  
- **PUT** `/api/reservations/{id}` - Modifier une réservation existante  
- **DELETE** `/api/reservations/{id}` - Supprimer une réservation  
### 🍰 API - Desserts

#### 🔍 GET `/api/desserts`
- **Description :** Récupère tous les desserts disponibles.
- **Méthode :** GET
- **Exemple de réponse :**

## l'ajout et l'edition et la suppression de menu ,plat ,boisson sont implémentée dans les sevice et gerée par les api
