# User Microservice

## Pré-requis

Assurez-vous d’avoir bien installé PostgreSQL sur votre machine. Si ce n’est pas
le cas rendez-vous à l’adresse suivante :
https://www.postgresql.org/download/

Egalement, assurez-vous d'avoir une version de Java supérieur ou égal à 8 installé sur votre machine.
Si ce n'est pas le cas : 
https://www.oracle.com/fr/java/technologies/javase/javase-jdk8-downloads.html

Veillez également à avoir installé et configuré maven comme suit :
https://maven.apache.org/install.html

## Configuration

Le fichier de configuration du projet se trouve dans un depot git externe.
Veuillez à avoir configuré et déployé le edge microservice WA-Configuration avant de déployer cette application.

https://github.com/Mr-Nugget/WA_ConfigurationMicroservice

Vous pouvez rajouter vos propre configuration dans le fichier bootstrap.properties du dossier src/main/ressources/

## Installation du projet en local

Faites un clone du projet en téléchargeant l'archive de la branche master ou bien la commande :
```bash
git clone https://github.com/Mr-Nugget/WA-UserMicroservice
```

A la base du projet lancer la commande maven : 
```bash
mvn clean install
```
Ceci va créer un build du projet, vous retrouverez le fichier .jar dans le dossier target de l'application.

## Installation de la base de données

### Création de la base et de l'utilisateur

La base de donnée est gérée avec PostgreSQL.
Commencez par créer un utilisateur postgreSQL en accédant au terminal PSQL ou bien
directement en utilisant l’interface de gestion pgAdmin en faisant clique droit sur
Login/Group Role -> Create -> Login/Group Role.
Nom : wildadventure
Password : WA2020

Script PSQL :
```SQL
CREATE ROLE wildadventure WITH
LOGIN
SUPERUSER
INHERIT
CREATEDB
CREATEROLE
NOREPLICATION
ENCRYPTED PASSWORD 'md548b2014d279ed088f5736d9321159b11;
```
Assurez-vous que l’utilisateur soit un superutilisateur et qu’il puisse créer des bases.

Créer ensuite la base de donnée avec cet utilisateur via la console psql on bien toujours l'interface pgAdmin.

Script PSQL :
```PSQL
CREATE DATABASE wa_account
WITH
OWNER = wildadventure
ENCODING = 'UTF8'
LC_COLLATE = 'French_France.1252'
LC_CTYPE = 'French_France.1252'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;
```

### Création des tables

Au démarrage l'application va automatiquement créer les tables associés grâce à Spring JPA.

### Insertion des données de test

Exécutez ensuite un script maven pour lancer le plugin Flyway DB en créer un job maven depuis votre éditeur de code ou bien à la base du projet : 
```bash
mvn clean flyway:migrate
```

Vous retrouverez les scripts d'insertion de données dans le dossier migration du projet : src/main/ressources/db/migration/

Vous pouvez les exécuter à la main ou bien dans l'interface pgAdmin si vous le souhaitez.

## Déploiement

Lancer le jar généré dans le dossier target de l'application via la commande suivante :
```bash
java -jar monarchive.jar
```
ou bien lancer le projet directement depuis votre éditeur de code en faisant un run de l'application java.

## Dépendances

- Spring Cloud Config
- Swagger 2
- Spring boot
- JUnit
- Log4J
- Flyway DB
- Spring JPA
- Eureka Client
- Spring Security

## Présentation

Microservice de l'application Wild Adventure permettant la gestion des utilisateurs de l'application. Certaines URI sont sécrurisées via une authentification par token JWT gérée et configurée par Spring Security.


Pour plus d'information sur le microservice, après son déploiement en local, rendez-vous sur : 

`http://localhost:10090/swagger-ui.html`

## Version

1.0.0
