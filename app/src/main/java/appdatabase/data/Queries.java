package appdatabase.data;

public final class Queries {
    
    public static final String LIST_SCREENING = 
    """
    select FILM.Titolo, PROIEZIONE.PostiDisponibili, PROIEZIONI.Data, PROIEZIONI.Ora, PROIEZIONI.NomeSede
    from PROIEZIONI, FILM
    where PROIEZIONI.CodFilm = FILM.CodFilm
    """;

}
