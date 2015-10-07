package net.apispark.webapi.client;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

public class AbstractClientResource {

    public void addQueryParameter(ClientResource client, String name, String value) {
        if (value != null) {
            client.addQueryParameter(name, value);
        }
    }

    public void addQueryParameter(ClientResource client, String name, Integer value) {
        if (value != null) {
            client.addQueryParameter(name, Integer.toString(value));
        }
    }

    public void addQueryParameter(ClientResource client, String name, Long value) {
        if (value != null) {
            client.addQueryParameter(name, Long.toString(value));
        }
    }

    public void addQueryParameter(ClientResource client, String name, Double value) {
        if (value != null) {
            client.addQueryParameter(name, Double.toString(value));
        }
    }
    
    public void addQueryParameter(ClientResource client, String name, Float value) {
        if (value != null) {
            client.addQueryParameter(name, Float.toString(value));
        }
    }
    
    public void addQueryParameter(ClientResource client, String name, Boolean value) {
        if (value != null) {
            client.addQueryParameter(name, Boolean.toString(value));
        }
    }

}
