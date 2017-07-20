package com.springrestclientexample;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class Test{
    public static void main( String[] args )  {
   String url="http://localhost:8082/SpringRestServerEx/getUserInfo/{userId}";
    RestTemplate restTemplate=new RestTemplate();
    Map<String,Object> map=new HashMap<String, Object>();
    map.put("userId",101);
    String jsonUser=restTemplate.getForObject(url,String.class,map);
    System.out.println(jsonUser);
    }
}
