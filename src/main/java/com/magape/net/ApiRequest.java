package com.magape.net;


/**
 * Encapsulation request
 */
public class ApiRequest {

    /**
     * POST、GET
     */
    RequestMethod method;
    /**
     * url path
     */
    String path;
    /**
     * JSON
     */
    ContentType contentType;
    /**
     * unique request id
     */
    String requestId;
    /**
     * param
     */
    Object param;

    public ApiRequest(RequestMethod method, String path, ContentType contentType, String requestId, Object param) {
        this.method = method;
        this.path = path;
        this.contentType = contentType;
        this.requestId = requestId;
        this.param = param;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
