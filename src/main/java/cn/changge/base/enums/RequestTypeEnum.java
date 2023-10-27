package cn.changge.base.enums;

import org.springframework.web.bind.annotation.*;

public enum RequestTypeEnum {

    GET(GetMapping.class),
    POST(PostMapping.class),
    DELETE(DeleteMapping.class),
    PUT(PutMapping.class),
    PATCH(PatchMapping.class);

    private Class requestType;

    RequestTypeEnum(Class requestType) {
        this.requestType = requestType;
    }

    public Class getRequestType() {
        return requestType;
    }

    public void setRequestType(Class requestType) {
        this.requestType = requestType;
    }
}