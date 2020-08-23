package repository;

import domain.docs.Genre;

import java.util.List;
import java.util.Set;

public interface GenreRepository {

    void add(String genreTitle, String docId, String documentText);

    void remove(String genreTitle, String docId);

    List<String> getDocumentsInGenre(String genreTitle);

    Set<Genre> getGenres();

}
