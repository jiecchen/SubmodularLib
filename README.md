SubmodularLib
------------
Java library for submodular optimization, designed for distributed/streaming computation

+ Author: Jiecao Chen (jiecchen@indiana.edu)

## What is a submodular function?
Let V be the ground set we consider, a set function (i.e. map a subset of V to a real number) f is submodular if

~~~~
for any A \subseteq B \subset V,  f(A + v) - f(A) >= f(B + v) - f(B) for all v \in V - B.
~~~~

Submodular functions have many nice properties which allow us to develop deep theoretical results. Also submodularity is such
a natural property that it frequently occurs in many real-world problem. 

## Why submodular optimization is important?
A large amount of combinatorial problems can be formulated as submodular optimization under certain constraints. 
For example, maximum set coverage, maximum flow/minimum cut, k-median/k-center clustering, just name a few. Applications 
of submodular optimization have been found in influence maximization, document summarization, active learning, computer vision,
network structure learning and many others.

## Why distributed/streaming?
Nowadays we have to deal will extremely "big" data: they usually can not fit into a single machine. Distributed computing and streaming
processing give promising ways to handle big data. We implement a set of distributed/streaming algorithms for submodular optimization in
this library. 

## Design

## TODO

## References