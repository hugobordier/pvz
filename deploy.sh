#!/bin/bash

set -e

echo "➡️  Nettoyage et compilation du projet..."
mvn clean package

WAR_FILE="target/CoursEpfBack.war"
if [ ! -f "$WAR_FILE" ]; then
    echo "❌ Fichier WAR introuvable ! Assurez-vous que la compilation s'est bien passée."
    exit 1
fi

echo "➡️  Déploiement du fichier WAR..."
sudo mv "$WAR_FILE" /opt/tomcat/webapps/

echo "✅ Déploiement terminé avec succès !"
