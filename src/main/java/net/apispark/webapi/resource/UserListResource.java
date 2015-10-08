package net.apispark.webapi.resource;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface UserListResource {

    /**
     * Loads a list of User.
     *
     * @return {@link net.apispark.webapi.representation.UserList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @Get
    net.apispark.webapi.representation.UserList getUserList();

    /**
     * Adds a User.
     *
     * @return {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @Post
    net.apispark.webapi.representation.User postUserList(net.apispark.webapi.representation.User bean);

}