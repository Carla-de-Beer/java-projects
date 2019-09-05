# Pet List API

This is a simple Spring Boot project that makes use of locally stored mock data as well as a MongoDB connection in order to provide a list of pets.

Based the online tutorial "REST API: Java Spring Boot and MongoDB": https://www.codementor.io/gtommee97/rest-api-java-spring-boot-and-mongodb-j7nluip8d

The project makes use of Java 8 and Maven.

## Instructions

Execute the following commands in order to create the MongoDB database:

`use pet_store;`

`db.createCollection("pet");`

```db.pet.insertMany([
  {
    "name" : "Fluffy",
    "species" : "cat",
    "breed" : "siamese"
  },
  {
    "name" : "Henry",
    "species" : "tortoise",
    "breed" : "leopard tortoise"
  }
]);```
