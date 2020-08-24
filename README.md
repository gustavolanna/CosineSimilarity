<h3>Cosine Similarity - Solution</h3>

<h4>Project changes<h4>

Changes on the provided source code:

  - Increase the jetty requestHeaderSize to 1k in order to accept bigger documents
  - Project folder's structure

The following packages were added to the project

```
/domain
  /algorithm
  /doc
/repository
/exception
```

Design

Most important domain classes

   - Genre: class to group up the documents
   - Document: class to represent a document
   - Text: base class for Document and Genre, so the Cosine Similarity algorithm will be able to calculate the similarity between two Documents or the similarity between a Document and a Genre.

Performance

The amount of memory required to load 100 Genres with 10000 documents each is 4G. The CPU usage and the garbage collector are pretty consistent according to the graphs bellow

