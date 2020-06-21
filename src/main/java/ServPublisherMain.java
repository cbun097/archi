package com.jax.rest;
import com.sun.istack.internal.NotNull;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

/** Fichier qui comprend les méthodes main du côté serveur - le service de validation ISBN */
@Path("/validerIsbn")
public class ServPublisherMain {
    boolean isbnValide;
    boolean isbnDisponible;
    String urlInfo;

    /** Méthode qui vérifie si le path /validerisbn fonctionne */
    @GET
    @Produces("text/plain")
    public String getMessage() {
        return "validate";
    }

    /** Méthode getBook qui fait la recherche de l'ISBN dans le service API de Open Library */
    @GET
    @Path("/get")
    public Response getBook(@NotNull @QueryParam("isbn") String isbn){
        Client client = ClientBuilder.newClient();
        Response queryResponse = client.target("http://openlibrary.org/api/books")
                    .queryParam("bibkeys", "ISBN:" + isbn)
                    .request()
                    .accept(MediaType.APPLICATION_JSON).buildGet().invoke();
        if(queryResponse != null) {
            getIsbnDisponible(isbn);
            isbnValide = true;
        }
        return postHtml(queryResponse, isbnValide, getIsbnDisponible(isbn), urlInfo);
    }

    /** Méthode qui vérifie le nombre d'item dans le catalogue
     * True -> trouver dans le catalogue
     * False -> existe pas  */
    public String getIsbnDisponible(String isbn){
        // array du Catalogue
        String[] catalogueArray = {"9780071824552","0451526538","2729893407","0201558025","0385472579","9780980200447",
                "0062937723","1633697223","0486659429", "9780486682525"};
        List<String> list  = Arrays.asList(catalogueArray);
        if(list.contains(isbn)) {
            isbnDisponible = true;
        }
        return String.valueOf(isbnDisponible);
    }

    /** Methode getIsbnValide, vérifie si le livre existe dans le service Open Library
     * False: l'objet revient empty
     * True: l'isbn existe dans le service externe
     */
        public void getIsbnValide(){
//        if(response != null && !response.isEmpty()) {
//            !isbnValide;
//            return "var _OLBookInfo = {}";
//        }
//        return false;
    }

    /** Méthode getUrlInfo, prend la clé url-info du service
     * si existe: urlInfo === url-info du service
     * si existe pas: error message-> Document introuvable selon OpenLibrary */
    public void getUrlInfo() {

    }

    /** retourner la response parse après avoir reçu l'isbn entrée */
    @Consumes("text/plain")
    public Response postHtml(Response resp, Boolean valide, String disponible, String url) {
        return Response.status(200).entity("Valeur ISBN de OpenLibrary: " + resp.readEntity(String.class) + "<br> ISBN Valide: " + valide + "<br> ISBN Disponile: " + disponible + "<br> URL_INFO: " + url).build();
    }
}

