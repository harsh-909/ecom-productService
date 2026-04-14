package com.ecom.productService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
 public class UserTokenDto {

    private String token;

    private UserDto userDto;
}

