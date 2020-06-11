import com.sun.istack.internal.NotNull;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

/** Fichier qui comprend la methode main de cote serveur - le service de validation*/
@Path("/validate")
public class ServPublisherMain {
//    boolean isbnValide;
//    boolean isbnDisponible;
//    String urlInfo;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
//    @Produces("text/plain")
    public String getMessage() {
        // Return some cliched textual content
        return "validate";
    }

    @GET
    @Path("/{isbn}")
    public Response getBook(@NotNull @QueryParam("isbn") String isbn){
        Client client = ClientBuilder.newClient();
        return (Response) client.target("http://openlibrary.org/api/books")
                .queryParam("bibkeys", "ISBN:" + isbn)
                .queryParam("format", "json")
                .queryParam("jscmd", "data")
                .request()
                .accept(MediaType.APPLICATION_JSON).buildGet().invoke();
    }
}

