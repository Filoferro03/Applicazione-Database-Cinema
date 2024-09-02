package appdatabase.data;

public final class Queries {
    
    public static final String LIST_SCREENING = 
    """
    select PROIEZIONI.codProiezione, FILM.titolo, PROIEZIONI.data, PROIEZIONI.ora, PROIEZIONI.nomeSede
    from PROIEZIONI, FILM
    where PROIEZIONI.codFilm = FILM.codFilm
    """;

}
