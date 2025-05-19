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
importation de la base de données 
```` psql -U postgres -d restaurant -f restaurant.sql````
les tables de la base de données seront genérées automatiquement par hibernate
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

## l'ajout et l'edition et la suppression de menu ,plat ,boisson sont implémentée dans les sevices et gerées par les api
## Authentification – Login API
### POST /api/auth/login
Permettre à un utilisateur de se connecter avec son email et mot de passe, et de recevoir un JWT token + rôles en réponse.

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


### création de compte 
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
### réservations
ajouter une réservation avec l'envoie automatique de mail :http://localhost:8080/api/reservations 
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

2. Simuler un paiement par carte
   POST http://localhost:8080/api/paiement/carte?commandeId={id}
exemple :POST http://localhost:8080/api/paiement/carte?commandeId=23
   Résultat attendu (JSON) :
   {
   "message": "Paiement validé avec succès",
   "commandeId": 23,
   "statut": "payée",
   "factureUrl": "/api/paiement/facture/23"
   }
3. Télécharger la facture PDF après paiement

GET http://localhost:8080/api/paiement/facture/{id_commande}
GET http://localhost:8080/api/paiement/facture/23

Logique métier – Paiement et Facture
Lorsqu’un client valide sa commande, elle est d'abord enregistrée avec le statut "en attente".
Une fois le client choisit le paiement par carte, une API /api/paiement/carte?commandeId=... simule un paiement sécurisé (aucune vraie transaction). Si le paiement est validé, le backend change le statut de la commande à "payée", ajoute le montant au chiffre d’affaires et génère automatiquement une facture PDF. Le frontend peut ensuite afficher un message de confirmation ainsi qu’un lien de téléchargement vers la facture via /api/paiement/facture/{id}.


