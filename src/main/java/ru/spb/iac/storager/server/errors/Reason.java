package ru.spb.iac.storager.server.errors;

import java.io.Serializable;

public class Reason implements Serializable {

    private String code;
    private Object data;
    private String description;
    private String when;
}
