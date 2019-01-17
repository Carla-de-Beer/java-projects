# Travelling Salesman

This project demonstrates how a genetic algorithm can be used to find an optimised solution to the Travelling Salesman Problem.
The city data is dynamically read from a file (using the JSON Simple library) and calculates the shortest distance it can find, linking all cities.
Specifiable parameters: crossover rate, mutation rate, population size, max. no. iterations, elitism generation gap. The strategy Design Pattern was used to allow for various selection strategies (ransom, rank roulette, tournament) to be hot-swapped from within the client program.

Resources:
* City data obtained from: https://gist.github.com/Miserlou/c5cd8364bf9b2420bb29
* The crossover strategy makes use of Modified Order Crossover (MOX), as described in:
http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.91.9167&rep=rep1&type=pdf
