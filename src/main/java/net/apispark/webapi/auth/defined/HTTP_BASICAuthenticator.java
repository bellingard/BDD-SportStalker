package net.apispark.webapi.auth.defined;

import net.apispark.webapi.auth.generic.HttpBasicAuthenticator;

public class HTTP_BASICAuthenticator extends HttpBasicAuthenticator {

    public HTTP_BASICAuthenticator(String userId, String password) {
        super(userId, password);
    }

}
