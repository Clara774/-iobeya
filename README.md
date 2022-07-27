# Documentation du test Iobeya

L'application est développée en Java, Spring Boot, MySQL, Spring Security, JWT, JPA, Rest API

## Démarrage

**1. Récupérer l'application**

```bash
git clone https://github.com/Clara774/iobeya.git
```

**2. Création de la database Mysql et alimentation**
- script de création de la base et des tables `src/main/resources/script/iobeya.sql`
```bash
CALL create_categories(nbCatategoryWithoutParent,nbCategoryMax)
```
- execution des scripts pour alimenter la table categories `src/main/resources/script/categories.sql`

**3. Identifiant connexion à la base de données**

+ fichier : `src/main/resources/application.properties`
+ valeurs : `spring.datasource.username` and `spring.datasource.password`

**4. Commande pour lancer en local l'application**

```bash
mvn clean install spring-boot:run
```
URL : <http://localhost:8080>

## Présentation des Apis

### Authentification

| Method | Url | Decription | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/auth/signin | Log in | [JSON](#signin) |

### Categories

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/categories/{id} | Récupérer une catégorie par son identifiant | [JSON](#getCategorie) |
| GET    | /api/categories | Récupérer tous les catégories par pagination | |
| GET    | /api/categories/{name} | Récupérer les catégories par leurs noms | |
| DELETE | /api/categories/{id} | Supprimer une catégorie par son identifiant | |
| POST   | /api/categories/ | Ajout d'une catégorie | [JSON](#bodyCategorieAdd) |
| PUT    | /api/categories/ | Modification d'une catégorie | [JSON](#bodyCategorieUpdate) |

Il est nécessaire d'avoir un rôle user pour accéder aux apis. Le rôle est données grace à la connexion

### TestsPostman

+ fichier : `iObeya.postman_collection.json`

1- Récupérer un token via le test "Login User"

2- Coller le token dans chaque test à exécuter au niveau de "Authorization/Type-> Bearer token"

3- Exécuter les tests

## Sample Valid JSON Request Bodys

##### <a id="signup">Sign Up -> /api/auth/signin</a>
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY1ODc1ODg5OCwiZXhwIjoxNjU4ODQ1Mjk4fQ.3cK6itmaFHHMFazyNUuLewFj2JlJQZfsh-qSXHL6Osf-iibdGLZaV0N6wIzZqb8GR1TGfQyanvWvbUDaEBY3BA",
  "id": 1,
  "username": "admin",
  "roles": [
    "ROLE_USER"
  ]
}

```

##### <a id="getCategorie">Récupérer une catégorie -> /api/categories/{id}</a>
```json
{
  "id": 23,
  "name": "category.23",
  "parent": null
}
```

##### <a id="bodyCategorieUpdate">Body d'update categorie -> /api/categories/</a>
```JSON
{
  "id":2,
  "name":"category.2",
  "parent": 8
}
```

##### <a id="bodyCategorieAdd">Body de création categorie -> /api/categories</a>
```JSON
{
  "name":"category",
  "parent":19
}
```

## Accès au Swagger

URL : <http://localhost:8080/v2/api-docs>

Cela permet d'accéder aux apis au format JSON


## Accès à l'interface web

URL : <http://localhost:8080/swagger-ui/index.html#/>

Cela permet d'accéder au api via une interface web en HTML