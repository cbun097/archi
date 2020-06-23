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
        try{
            Client client = ClientBuilder.newClient();
            Response queryResponse = client.target("http://openlibrary.org/api/books")
                    .queryParam("bibkeys", "ISBN:" + isbn)
                    .request()
                    .accept(MediaType.APPLICATION_JSON).buildGet().invoke();
            if (queryResponse != null) {
                getIsbnDisponible(isbn);
            }
            //converti la reponse en string
            String finalResponce = queryResponse.readEntity(String.class);
            if (getIsbnValide(finalResponce))
                return postHtml(finalResponce, getIsbnValide(finalResponce), getIsbnDisponible(isbn), getUrlInfo(finalResponce));
            return Response.status(200).entity("ISBN Valide: False").build();
        }
        catch (Exception e)
        {
            // Message d'erreur
            return Response.status(404).entity("<meta charset=\"UTF-8\">\n" +
                    "<meta http-equiv=\"Content-type\" content=\"text/html; charset=UTF-8\">\n" +
                    "<body>Une erreure c'est produite lorsque vous avez essayé d'utiliser notre service. Veuiller réessayer à un autre moment.</body>").build();
        }
    }

    /** Méthode qui vérifie le nombre d'item dans le catalogue
     * True -> trouver dans le catalogue
     * False -> existe pas  */
    public boolean getIsbnDisponible(String isbn){
        // array du Catalogue
        String[] catalogueArray = {"9780071824552","0451526538","2729893407","0201558025","0385472579","9780980200447",
                "0062937723","1633697223","0486659429", "9780486682525"};
        List<String> list  = Arrays.asList(catalogueArray);
        if(list.contains(isbn)) {
            return true;
        }
        return false;
    }

    /** Methode getIsbnValide, vérifie si le livre existe dans le service Open Library
     * False: l'objet revient empty
     * True: l'isbn existe dans le service externe
     */
    public boolean getIsbnValide(String validate){
        validate = validate.split("\\{")[1].split("}")[0];
        if(validate.isEmpty())
            return false;
        return true;
    }

    /** Méthode getUrlInfo, prend la clé url-info du service
     * si existe: urlInfo === url-info du service
     * si existe pas: error message-> Document introuvable selon OpenLibrary */
    public String getUrlInfo(String url) {
        url = url.split("\"info_url\": \"")[1].split("\"")[0];
        url = url.replace("\\u2019", "?");
        return url;
    }

    /** retourner la response parse après avoir reçu l'isbn entrée */
    @Consumes("text/plain")
    public Response postHtml(String resp, Boolean valide, boolean disponible, String url) {
        return Response.status(200).entity("Valeur ISBN de OpenLibrary: " + resp + "<br> ISBN Valide: " + valide + "<br> ISBN Disponile: " + disponible + "<br> URL_INFO: " + url).build();
    }
}

