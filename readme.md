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
```
```
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
## Authentification – Login API
### POST /api/auth/login
Permet à un utilisateur de se connecter avec son email et mot de passe, et de recevoir un JWT token + rôles en réponse.

Requête

### POST http://localhost:8080/api/auth/login
Content-Type: application/json
Body (JSON)

{
  "email": "admin@resto.com",
  "password": "adminpass"
}
 Réponse (200 OK)

Modifier
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1p...",
  "email": "admin@resto.com",
  "roles": [
    "ROLE_ADMIN"
  ]
}


### création un compte 
Post :http://localhost:8080/api/auth/register
```{
  "nom": "Yass",
  "prenom": "Ali",
  "email": "yass.al@resto.com",
  "telephone": "2564853",
  "password": "yass123",
  "roles": ["CLIENT"]
}
```
### reservations
ajouter une reservation avec l'envoie automatique de mail :http://localhost:8080/api/reservations 
```{
 {
  "nomClient": "Mohamed",
  "email": "29114298yassine@gmail.com",
  "dateHeure": "2025-04-30T19:00:00",
  "nombrePersonnes": 4,
  "numeroTable": 11
}
}
```
## commande :
affichage des commandes :
Get :http://localhost:8080/api/commandes
résultat :
```{
  "statut": "en attente",
  "lignesCommande": [
    {
      "quantite": 1,
      "plat": { "id": 3 }
    },
    {
      "quantite": 2,
      "boisson": { "id": 1 }
    },
    {
      "quantite": 1,
      "dessert": { "id": 2 }
    }
  ]
}``` 
````````
création de commande :
Post :http://localhost:8080/api/commandes/pdf
````````
```{
"statut": "payée",
"lignesCommande": [
{
"quantite": 1,
"plat": { "id": 1 }
},
{
"quantite": 2,
"boisson": { "id": 3 }
}
]
}```
````````
Notes techniques pour les devs
Si un ID est incorrect ou inexistant, une exception RuntimeException est levée (à améliorer avec @ControllerAdvice pour gestion propre).

Le champ prix doit exister et être non null dans la base pour tous les produits (plat, boisson, etc.).

Le PDF est retourné inline avec :

Content-Disposition: inline; filename=ticket_23.pdf

