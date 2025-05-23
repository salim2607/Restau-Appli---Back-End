# 🍕 Spring Boot Restaurant API - Docker Ready

Ce projet Spring Boot simule un système de gestion de restaurant avec commandes, paiement, factures PDF, rôles utilisateurs et plus.

---

## 🚀 Lancer le projet via Docker

### 🛠️ Pré-requis
Assurez-vous d'avoir installé :
- [Docker](https://www.docker.com/products/docker-desktop)
- (Docker Compose est inclus)

---

### 📁 Fichiers inclus

- `Dockerfile` : build et exécution de l'app Spring Boot
- `docker-compose.yml` : backend + base PostgreSQL
- `application.properties` : configuration Spring Boot avec variables Docker

---

### ▶️ Commandes

```bash
# Lancer l'application (API + BDD)
docker-compose up --build
```

L'API sera accessible sur :  
📍 `http://localhost:8080`

---

## 🔁 Cycles de test API

### 🔹 Créer une commande
```http
POST /api/commandes/pdf
```

### 🔹 Simuler un paiement
```http
POST /api/paiement/carte?commandeId=1
```

### 🔹 Voir la facture PDF
```http
GET /api/paiement/facture/1
```

---

## 🗃️ Base de données

- Nom : `mydb`
- User : `postgres`
- MDP : `postgres`
- Port : `5432`

---

## 📦 Exemple d'utilisation

```bash
curl -X POST http://localhost:8080/api/test/public
```

---

## 🤝 Pour contribuer

- Cloner le repo
- Lancer Docker
- Tester les endpoints Postman