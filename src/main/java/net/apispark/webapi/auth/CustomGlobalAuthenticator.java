package net.apispark.webapi.auth;

import net.apispark.webapi.auth.generic.NoAuthAuthenticator;

/**
 * A marker class that serves as the id of the Authenticator that can be configured through
 * {@link SecurityConfig#configureCustomGlobalAuth}.
 */
public class CustomGlobalAuthenticator extends NoAuthAuthenticator {
}