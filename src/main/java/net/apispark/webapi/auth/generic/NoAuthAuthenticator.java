package net.apispark.webapi.auth.generic;

import net.apispark.webapi.auth.Authenticator;

import org.restlet.resource.ClientResource;

/**
 * Authenticator that does not provide authentication.
 */
public class NoAuthAuthenticator implements Authenticator {

    @Override
    public void configure(ClientResource clientResource) {
        // Nothing
    }

}