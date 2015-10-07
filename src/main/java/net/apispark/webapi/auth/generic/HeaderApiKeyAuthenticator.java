package net.apispark.webapi.auth.generic;

import net.apispark.webapi.auth.Authenticator;

import org.restlet.resource.ClientResource;

/**
 * Configures authentication based on the value of a specific header.
 */
public class HeaderApiKeyAuthenticator implements Authenticator {
    /** The name of the authentication header. */
    private final String headerName;

    /** The value of the header. */
    private final String token;

    /**
     * Constructor.
     * 
     * @param headerName
     *            The name of the authentication header.
     * @param token
     *            The value of the header.
     */
    public HeaderApiKeyAuthenticator(String headerName, String token) {
        this.headerName = headerName;
        this.token = token;
    }

    @Override
    public void configure(ClientResource clientResource) {
        clientResource.getRequest().getHeaders().set(headerName, token);
    }
}