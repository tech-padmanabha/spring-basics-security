package io.pn.dto;

import java.util.List;

public record RegisterRequest(String username,String password,List<String> roles) {

}
