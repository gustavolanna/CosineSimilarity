package repository;

import domain.docs.Genre;
import exception.ObjectNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryGenreRepository implements GenreRepository {

    private Map<String, Genre> repository = new ConcurrentHashMap<>();

    public void add(String genreTitle, String docId, String documentText) {
        Genre genre = repository.get(genreTitle);
        if (genre == null) {
            genre = new Genre(genreTitle);
            repository.put(genreTitle, genre);
        }
        genre.add(docId, documentText);
    }

    public void remove(String genreTitle, String docId) {
        Genre genre = repository.get(genreTitle);
        if (genre != null) {
            genre.remove(docId);
        }
        throw new ObjectNotFoundException("Genre not found " + genreTitle);
    }

    public List<String> getDocumentsInGenre(String genreTitle) {
        Genre genre = repository.get(genreTitle);
        if (genre != null) {
            return genre.getDocuments();
        }
        throw new ObjectNotFoundException("Genre not found " + genreTitle);
    }

    public Set<Genre> getGenres() {
        return repository.values()
                .stream()
                .collect(Collectors.toSet());
    }

}
