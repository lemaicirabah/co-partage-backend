# Liste des technologies et logiciels pour le projet

> Notez que les elements ==surlignés== sont très important!

## Logiciels

- ==**IntelliJ IDEA:**== Comme environnement de développement pour facilité la compréhension lors du développement entre nous (oui je sais VScode c’est cool ou autres comme vim/neovim btw, mais on risque d’avoir des différences au niveau de certain truc).
- ==**Postman:**== Pour faire nos requêtes HTTP à nos APIs et vérifier les réponses, les requests body, response body, query parameters, path parameters, code d’erreur ou de succès HTTP, etc. 
  - [Postman download](https://www.postman.com/downloads/)
- **Docker:** Celui-là je suis pas encore certain, mais idéalement pour éviter des problèmes du genre “Oui mais moi sa marchait sur mon ordinateur” kind of stuff… Je dirais qu’il y a de forte chance qu’on l’utilise pour le déploiement. 

## Technologies

### Backend

- ==**Java:**== On maitrise ça.
- ==**Maven:**== Il simplifie le processus de développement en automatisant de nombreuses tâches courantes telles que la compilation, le test, le packaging, le déploiement et la gestion des dépendances.
  - [Mavec docs](https://maven.apache.org/guides/)
- ==**Springboot:**== Simplifie le développement d'applications Java en fournissant une configuration automatique, des dépendances intégrées, un serveur d'application embarqué, et diverses fonctionnalités pour le déploiement, la surveillance et le développement efficace des applications Java modernes.
  - Bien adapté au développement d'applications basées sur une architecture de microservices. Il offre des fonctionnalités telles que la gestion des configurations distribuées, la découverte des services, et l'intégration avec des outils de conteneurisation comme Docker.
  - [Springboot docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#documentation)
- **Swagger (utile, mais facultatif):** Simplifie le processus de conception, de documentation et de consommation d'API RESTful en fournissant une approche standardisée, des outils et des bonnes pratiques. Il favorise la cohérence des API, améliore la productivité des développeurs et renforce l'écosystème global des API.
  - [swagger docs](https://swagger.io/docs/specification/about/)
- ==**HTTP:**==
  - [HTTP codes docs](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)
- ==**MySQL:**== Base de données
  - [MySQL docs](https://dev.mysql.com/doc/)

## Frontend

### Vue3 avec vuetify3

- ==**HTTP:**==
  - [HTTP codes docs](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)
- **HTML:** On maitrise ça, easy.
- **CSS:** On maitrise ça, easy.
- ==**TypeScript:**== Extension de Javascript avec le typage (static typing) on peut le voir comme JS++.
  - [TypeScript Tutorial - W3school](https://www.w3schools.com/typescript/index.php)

- ==**Vue.js version 3:**== Un framework JavaScript open source utilisé pour construire des interfaces utilisateur interactives et réactives. Il est conçu pour être simple à intégrer et à utiliser, tout en offrant des fonctionnalités avancées pour le développement d'applications web modernes. Vue.js est souvent comparé à d'autres frameworks frontend tels que React.js et Angular.js, mais il se distingue par sa simplicité, sa flexibilité et sa performance.
  - [Vuejs docs](https://vuejs.org/guide/introduction.html)
- ==**Vuetify:**== Un framework frontend open source basé sur Vue.js, conçu pour faciliter le développement d'interfaces utilisateur riches et réactives. Il fournit une collection de composants prêts à l'emploi, de directives, d'outils de mise en page et de thèmes personnalisables qui permettent aux développeurs de créer rapidement des applications web esthétiquement attrayantes et fonctionnelles.
  - [Vuetify docs](https://vuetifyjs.com/en/introduction/why-vuetify/)

## Architecture de microservices

[![Top 5 Technologies To Build Microservices Architecture - RV Technologies](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Frvweb.nyc3.digitaloceanspaces.com%2F2022%2F09%2F27131748%2FWhat-is-Microservice-Architecture.jpg&f=1&nofb=1&ipt=b2d8638bd8034a70d84bacdb9e04d8d5d8478ac6e8c38da6c79dbb2f2a136b98&ipo=images)](https://rvweb.nyc3.digitaloceanspaces.com/2022/09/27131748/What-is-Microservice-Architecture.jpg)

- Dans une architecture basée sur les microservices, l'application est décomposée en un ensemble de petits services autonomes, chacun responsable d'une fonctionnalité spécifique.

- Chaque service est développé, déployé et évolué indépendamment des autres, ce qui permet une plus grande flexibilité et une meilleure gestion des mises à jour.

- Favorise la scalabilité horizontale, la résilience et la réutilisation du code, mais peut introduire une complexité opérationnelle supplémentaire.

- Souvent, une base de données par microservice.

- On a le client (frontend) qui communique directement (et uniquement) avec l’API Gateway.

- Fort probable qu’on développe une API pour la sécurité (authentication, access tokens, etc).

- Un orchestration des APIs avec un truc comme *Netflix Eureka Server* avec *Spring Cloud*.

  - > Client-side service discovery allows services to find and communicate with each other without hard-coding the hostname and port

  - [Lien](https://www.baeldung.com/spring-cloud-netflix-eureka) informatif.

