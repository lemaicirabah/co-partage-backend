# projet-INM5151

## Équipe

> Développement web fullstack.

## Idées

- Recherche de partenaires pour les travaux universitaire.

- Plateforme de Partage de Compétences entre Étudiants :

  1. **Gestion des Utilisateurs** :
     - Créer un système d'inscription et de gestion des utilisateurs permettant aux étudiants de s'inscrire, de créer des profils, de définir leurs compétences et leurs intérêts, et de rechercher d'autres utilisateurs avec lesquels partager leurs connaissances.
  2. **Publication de Demandes et d'Offres** :
     - Mettre en place une fonctionnalité permettant aux utilisateurs de publier des demandes de tutorat ou d'aide dans des matières spécifiques, ainsi que des offres pour partager leurs compétences dans des domaines où ils excellent.
  3. **Recherche et Filtrage** :
     - Développer un moteur de recherche avancé qui permet aux utilisateurs de trouver des partenaires d'apprentissage en fonction de critères tels que la matière, le niveau d'expertise, la localisation géographique et les disponibilités.
  4. **Messagerie et Planning** :
     - Intégrer un système de messagerie instantanée pour faciliter la communication entre les utilisateurs et leur permettre de planifier des sessions d'apprentissage en ligne ou en personne.
  5. **Gestion des Sessions d'Apprentissage** :
     - Créer un outil de planification des sessions d'apprentissage qui permet aux utilisateurs de fixer des rendez-vous, de définir des objectifs d'apprentissage et de fournir des commentaires sur leurs expériences.
  6. **Evaluation et Feedback** :
     - Mettre en place un système d'évaluation permettant aux utilisateurs de noter et de laisser des commentaires sur les sessions d'apprentissage, afin d'aider les autres étudiants à choisir les meilleurs partenaires d'étude.
  7. **Intégration avec les Réseaux Sociaux** :
     - Intégrer la plateforme avec les réseaux sociaux populaires pour permettre aux utilisateurs de partager leurs expériences d'apprentissage, d'inviter leurs amis à rejoindre la communauté et de promouvoir la plateforme auprès d'un public plus large.
  8. **Protection des Données et Sécurité** :
     - Garantir la sécurité et la confidentialité des données des utilisateurs en mettant en œuvre des mesures de protection des données conformes aux réglementations en vigueur, telles que le RGPD.
  9. **Analyse des Données** :
     - Utiliser l'analyse des données pour suivre l'utilisation de la plateforme, identifier les tendances en matière d'apprentissage et améliorer les fonctionnalités et l'expérience utilisateur en fonction des commentaires des utilisateurs.
  10. **Tests et Déploiement** :
      - Effectuer des tests approfondis pour garantir la stabilité et la convivialité de la plateforme, puis la déployer dans un environnement cloud sécurisé pour assurer sa disponibilité continue.

  Ce projet offrirait aux étudiants une plateforme dynamique pour partager leurs connaissances, acquérir de nouvelles compétences et se connecter avec d'autres apprenants partageant les mêmes intérêts, favorisant ainsi l'apprentissage collaboratif et la croissance personnelle.

  ### Plateforme de Partage de Compétences entre Étudiants - Architecture de Microservices :

  1. **Service d'Authentification** :
     - Ce service gère l'inscription des utilisateurs, l'authentification et la gestion des sessions utilisateur.
  2. **Service de Profilage Utilisateur** :
     - Ce service permet aux utilisateurs de créer et de gérer leur profil, y compris leurs compétences, leurs intérêts et leurs préférences de communication.
  3. **Service de Publication** :
     - Ce service gère la publication, la recherche et la gestion des demandes et des offres de partage de compétences entre utilisateurs.
  4. **Service de Messagerie** :
     - Ce service gère la messagerie instantanée entre utilisateurs, y compris l'envoi de messages, la création de conversations et la notification des nouveaux messages.
  5. **Service de Planification** :
     - Ce service permet aux utilisateurs de planifier des sessions d'apprentissage, de fixer des rendez-vous et de synchroniser leur emploi du temps avec d'autres utilisateurs.
  6. **Service d'Evaluation** :
     - Ce service gère l'évaluation des sessions d'apprentissage, la collecte des commentaires des utilisateurs et la génération de scores de satisfaction.
  7. **Service d'Analyse** :
     - Ce service recueille et analyse les données sur l'utilisation de la plateforme, les interactions entre les utilisateurs et les performances des services afin d'optimiser l'expérience utilisateur et de prendre des décisions basées sur les données.
  8. **Service de Notification** :
     - Ce service gère l'envoi de notifications aux utilisateurs pour les informer des nouvelles demandes, des réponses aux messages et des rappels de sessions planifiées.
  9. **Service de Sécurité** :
     - Ce service garantit la sécurité des données des utilisateurs en mettant en œuvre des mesures de protection des données, telles que le chiffrement et l'authentification multi-facteurs.
  10. **Service de Gestion des Ressources** :
      - Ce service gère les ressources partagées telles que les images, les documents et les vidéos utilisés dans les sessions d'apprentissage.

  Chaque service serait développé indépendamment, utilisant les technologies et les frameworks les mieux adaptés à ses besoins spécifiques, et serait déployé dans des conteneurs Docker pour une gestion et une évolutivité faciles. L'ensemble du système serait orchestré à l'aide d'une plateforme de gestion de conteneurs telle que Kubernetes pour assurer une haute disponibilité et une scalabilité efficace.

## Architecture

### Architecture basée sur les Microservices✅

- Dans une architecture basée sur les microservices, l'application est décomposée en un ensemble de petits services autonomes, chacun responsable d'une fonctionnalité spécifique.
- Chaque service est développé, déployé et évolué indépendamment des autres, ce qui permet une plus grande flexibilité et une meilleure gestion des mises à jour.
- Favorise la scalabilité horizontale, la résilience et la réutilisation du code, mais peut introduire une complexité opérationnelle supplémentaire.

## Remise de l’OpsCon

Nous devons rédiger un document OpsCon respectant la norme ISO 29148. Le document doit être remis en format PDF par Moodle. 

> Voir le Moodle du cours pour la date et l’heure de la remise.
>
> - ==remise à la semaine 6==

Chaque section de l’annexe A de la norme doit être représentée dans le document.

**Pondération:**

- 80% sur la pertinence et la qualité du contenu du document.
- 20% sur la présentation et la qualité de la langue.

## Méthodologie agile SCRUM

1. **Constituer une équipe Scrum** :
   - L'équipe Scrum est composée de membres multifonctionnels, y compris un Product Owner, un Scrum Master et des membres de l'équipe de développement.
2. **Créer un Product Backlog** :
   - Le Product Owner est chargé de définir les exigences et les fonctionnalités du produit dans le Product Backlog, qui est une liste priorisée des éléments à livrer.
3. **Planification de la Release** :
   - L'équipe Scrum, en collaboration avec le Product Owner, planifie la release en déterminant les objectifs et le contenu de la prochaine itération (sprint).
4. **Planification du Sprint** :
   - Lors de la planification du sprint, l'équipe sélectionne un ensemble d'éléments du Product Backlog à livrer pendant le sprint et les décompose en tâches plus petites si nécessaire.
5. **Sprint** :
   - Le sprint est une période de temps fixe, généralement de 2 à 4 semaines, pendant laquelle l'équipe développe, teste et livre les fonctionnalités définies dans le sprint backlog.
     - ==Sprint-1 termine à la semaine 9.==
     - ==Sprint-2 termine à la semaine 12.==
6. **Daily Scrum (Stand-up)** :
   - Chaque jour pendant le sprint, l'équipe se réunit pour une réunion quotidienne de synchronisation appelée Daily Scrum, où chaque membre de l'équipe partage ce qu'il a accompli depuis la dernière réunion, ce qu'il prévoit de faire aujourd'hui et s'il rencontre des obstacles.
7. **Développement itératif et collaboratif** :
   - Pendant le sprint, l'équipe travaille de manière itérative pour développer des fonctionnalités potentiellement livrables, en se concentrant sur les objectifs du sprint et en collaborant étroitement avec le Product Owner pour s'assurer que les besoins du client sont satisfaits.
8. **Revues de Sprint** :
   - À la fin de chaque sprint, l'équipe démontre les fonctionnalités développées lors d'une revue de sprint avec le Product Owner et les parties prenantes pour obtenir des commentaires et des suggestions.
9. **Rétrospective de Sprint** :
   - Après la revue de sprint, l'équipe tient une rétrospective de sprint pour réfléchir aux succès et aux défis du sprint, identifier les opportunités d'amélioration et proposer des actions correctives pour les prochains sprints.
10. **Itérations** :
    - Le processus de sprint se répète de manière itérative, avec une planification de sprint suivie d'un sprint, des revues de sprint et des rétrospectives de sprint, jusqu'à ce que toutes les fonctionnalités requises du Product Backlog soient développées et livrées.
11. **Amélioration continue** :
    - L'équipe Scrum s'engage dans une culture d'amélioration continue, en utilisant les leçons apprises des rétrospectives de sprint pour améliorer constamment ses processus et sa performance.

## Détermination du chef d’équipe

- Fred

