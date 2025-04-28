## Démarrage du projet (Back-end et Front-end)

### Utilisation avec Docker (recommandé)

Pour lancer l'ensemble du projet avec Docker, exécutez la commande suivante à la racine :

```bash
docker compose up --build
```

### Utilisation en local sans Docker

Si vous souhaitez exécuter le projet en local sans utiliser Docker :

- Modifiez la configuration dans `config/AppConfig` pour utiliser `localhost` à l'endroit indiqué.
- Lancez votre serveur Tomcat.
- Exécutez la commande suivante pour compiler et packager le projet :

```bash
mvn clean package
```

- Déplacez le fichier `.war` généré dans le dossier `webapps` de votre installation Tomcat.

### Accès à la documentation API (Swagger)

Une fois le projet démarré, la documentation Swagger est disponible à l'adresse suivante :

http://localhost:8080/CoursEpfBack/swagger-ui/index.html
