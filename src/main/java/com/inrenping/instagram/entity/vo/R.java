package com.inrenping.instagram.entity.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {
    private Integer code;
    private String msg;
    private T data;
}