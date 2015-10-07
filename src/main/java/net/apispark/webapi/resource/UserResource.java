package net.apispark.webapi.resource;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.Delete;

public interface UserResource {

    /**
     * Loads a User.
     *
     * @return  {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @Get
    net.apispark.webapi.representation.User getUser();

    /**
     * Stores a User.
     *
     * @return  {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @Put
    net.apispark.webapi.representation.User putUser(net.apispark.webapi.representation.User bean);

    /**
     * Deletes a User.
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @Delete
    void deleteUser();

}