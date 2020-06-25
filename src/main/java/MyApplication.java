package com.jax.rest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/** Parent url après le nom de domain */
@ApplicationPath("/rest")
public class MyApplication extends Application{
/** Collection de classe lu par JAX-Rs */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        /**ajouter la classe pour le service */
        h.add( com.jax.rest.ServPublisherMain.class );
        return h;
    }
}