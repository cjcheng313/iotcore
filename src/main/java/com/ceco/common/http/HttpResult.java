package com.ccj.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResult {
    // 响应码
    private Integer code;

    // 响应体
    private String body;
}
