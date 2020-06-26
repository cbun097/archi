# Devoir #2 - INF1603

> Création du service pour valider l'ISBN d'un livre en JAX-RS - Jersey et Glassfish.

## Démonstration

## Installation

- [IntelliJ Ultimate](https://www.jetbrains.com/community/education/#students)
- [Install Glassfish 4.1.2](https://javaee.github.io/glassfish/download)

```
Install Glassfish
Open the project in IntelliJ
Configure and run the project
Open in your browser http://localhost:8080/devoir_glassfish_war_explode
```

## Docker

```
Install Glassfish
./exploded_to_war.ps1
docker build -t devoir02 .
docker run -p 8080:8080 devoir02
Open in your browser http://localhost:8080/devoir02
```

## Docs

- [How the project was generated](https://www.jetbrains.com/help/idea/creating-and-running-your-first-restful-web-service.html)
- [OpenLibrary.org](https://openlibrary.org/dev/docs/api/books)
- [Jax-Rs - Jersey](https://www.javatpoint.com/jax-rs-annotations-example)
