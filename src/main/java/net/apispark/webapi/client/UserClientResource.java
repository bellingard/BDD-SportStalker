package net.apispark.webapi.client;


public class UserClientResource extends AbstractClientResource {

    private final net.apispark.webapi.auth.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String userid;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param userid
     *            Identifier of the User
     */
    public UserClientResource(net.apispark.webapi.Config config, java.lang.String userid) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.userid = userid;
        this.absolutePath = config.getBasePath() + "/users/{userid}";
    }

    /**
     * Loads a User.
     * 
     * @return {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.User getUser() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("userid", this.userid);

        securityRuntimeConfigurator.accept(net.apispark.webapi.auth.defined.HTTP_BASICAuthenticator.class).configure(client);

        return client.wrap(net.apispark.webapi.resource.UserResource.class).getUser();
    }

    /**
     * Stores a User.
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.User putUser(net.apispark.webapi.representation.User bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("userid", this.userid);

        securityRuntimeConfigurator.accept(net.apispark.webapi.auth.defined.HTTP_BASICAuthenticator.class).configure(client);

        return client.wrap(net.apispark.webapi.resource.UserResource.class).putUser(bean);
    }

    /**
     * Deletes a User.
     * 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void deleteUser() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("userid", this.userid);

        securityRuntimeConfigurator.accept(net.apispark.webapi.auth.defined.HTTP_BASICAuthenticator.class).configure(client);

        client.wrap(net.apispark.webapi.resource.UserResource.class).deleteUser();
    }

}