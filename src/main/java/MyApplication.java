package com.jax.rest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/rest")
//The java class declares root resource and provider classes
public class MyApplication extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        // juste un path pour tester l'application
        h.add( com.jax.rest.HelloWorld.class );
        // ajouter la classe pour le service
        h.add( com.jax.rest.ServPublisherMain.class );
        return h;
    }
}