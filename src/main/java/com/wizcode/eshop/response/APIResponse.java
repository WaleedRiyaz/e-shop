package com.wizcode.eshop.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// We can use this class to send data to front end
public class APIResponse {
    private String message;
    private Object data;
}
