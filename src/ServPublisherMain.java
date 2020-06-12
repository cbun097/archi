import com.sun.istack.internal.NotNull;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** Fichier qui comprend les méthodes main du côté serveur - le service de validation ISBN */
@Path("/validerIsbn")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServPublisherMain {
//    boolean isbnValide; // True -> trouver dans openLibrary, False -> pas trouver
//    boolean isbnDisponible; // True -> trouver dans le cataogue, False -> existe pas
//    String urlInfo; // === url-info du service, "Document introuvable selon OpenLibrary

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getMessage() {
        // Return some cliched textual content
        return "validate";
    }

    /** Méthode getBook qui fait la recherche de l'ISBN dans le service API de Open Library */
    @GET
    @Path("/{isbn}")
    public Response getBook(@NotNull @QueryParam("isbn") String isbn){
        Client client = ClientBuilder.newClient();
        Response queryResponse = client.target("http://openlibrary.org/api/books")
                .queryParam("bibkeys", "ISBN:" + isbn)
                .queryParam("format", "json")
                .queryParam("jscmd", "data")
                .request()
                .accept(MediaType.APPLICATION_JSON).buildGet().invoke();
        return queryResponse;
    }

    /** Méthode qui vérifie le nombre d'item dans le catalogue */
    public void getIsbnDisponible(){}
}

