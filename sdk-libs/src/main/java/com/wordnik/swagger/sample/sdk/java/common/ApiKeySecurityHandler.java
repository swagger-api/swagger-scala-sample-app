package com.wordnik.swagger.sample.sdk.java.common;

import com.wordnik.swagger.common.SecurityHandler;

import java.util.Map;

/**
 * User: ramesh
 * Date: 8/4/11
 * Time: 7:35 AM
 */
public class ApiKeySecurityHandler implements SecurityHandler {

    private String apiKey = "";

    public ApiKeySecurityHandler(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Populate the security infomration in http headers map and/or reqsource URL.
     *
     * Value spopulated in the http headers map will be set as http headers while making the server communication.
     *
     * Depending on the usecase requried information can be added to either of them or both.
     *
     * @param resourceURL
     * @param headers
     */
    public void populateSecurityInfo(String resourceURL, Map<String, String> httpHeaders) {
        resourceURL = resourceURL + "api_key="+apiKey;
    }
}
