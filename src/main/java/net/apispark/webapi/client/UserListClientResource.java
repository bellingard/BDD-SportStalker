package net.apispark.webapi.client;


public class UserListClientResource extends AbstractClientResource {

    private final net.apispark.webapi.auth.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public UserListClientResource(net.apispark.webapi.Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/users/";
    }

    /**
     * Loads a list of User.
     * 
     * @param size
     *            Size of the page to retrieve. Integer value
     * @param id
     *            Allows to filter the collections of result by the value of field id
     * @param sort
     *            Order in which to retrieve the results. Multiple sort criteria can be passed. Example: sort=age ASC,height DESC
     * @param longitude
     *            Allows to filter the collections of result by the value of field longitude
     * @param latitude
     *            Allows to filter the collections of result by the value of field latitude
     * @param page
     *            Number of the page to retrieve. Integer value.
     * @param username
     *            Allows to filter the collections of result by the value of field username
     * @return {@link net.apispark.webapi.representation.UserList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.UserList getUserList(java.lang.String size, java.lang.String id, java.lang.String sort, java.lang.String longitude, java.lang.String latitude, java.lang.String page, java.lang.String username) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        addQueryParameter(client, "$size", size);
        addQueryParameter(client, "id", id);
        addQueryParameter(client, "$sort", sort);
        addQueryParameter(client, "longitude", longitude);
        addQueryParameter(client, "latitude", latitude);
        addQueryParameter(client, "$page", page);
        addQueryParameter(client, "username", username);

        securityRuntimeConfigurator.accept(net.apispark.webapi.auth.defined.HTTP_BASICAuthenticator.class).configure(client);

        return client.wrap(net.apispark.webapi.resource.UserListResource.class).getUserList();
    }

    /**
     * Adds a User.
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.User postUserList(net.apispark.webapi.representation.User bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);

        securityRuntimeConfigurator.accept(net.apispark.webapi.auth.defined.HTTP_BASICAuthenticator.class).configure(client);

        return client.wrap(net.apispark.webapi.resource.UserListResource.class).postUserList(bean);
    }

}