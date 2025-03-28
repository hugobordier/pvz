package com.epf.api.response;

public class MapResponse {
    private boolean success;
    private String message;
    private Object data;

    public MapResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static MapResponse of(boolean success, String message, Object data) {
    return new MapResponse(success, message, data);
}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
