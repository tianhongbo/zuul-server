package com.huami.id.zuulserver.util;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.netflix.util.Pair;

public class FilterUtil {

  public static List<String> mapToList(Map<String, String> map) {
    List<String> headers = new LinkedList<String>();

    for (String name : map.keySet()) {
      headers.add(name + "=\"" + map.get(name) + "\"");
    }

    // join all the key-value pairs like: "name=Bob, age=18"
    return headers;
  }

  public static List<String> pairToList(List<Pair<String, String>> pairs) {
    List<String> headers = new LinkedList<String>();

    for (Pair<String, String> pair : pairs) {
      headers.add(pair.first() + "=\"" + pair.second() + "\"");
    }

    // join all the key-value pairs like: "name=Bob, age=18"
    return headers;
  }

  public static List<String> getHTTPReqHeader(HttpServletRequest request) {
    List<String> headers = new LinkedList<String>();
    // add url. e.g: "http://localhost:8080/v1/client/login"
    headers.add("url=\"" + request.getRequestURL().toString() + "\"");

    // add uri. e.g: "/v1/client/login"
    headers.add("uri=\"" + request.getRequestURI() + "\"");

    // add headers
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = (String) headerNames.nextElement();
      headers.add(headerName + "=\"" + request.getHeader(headerName) + "\"");
    }

    // join all the key-value pairs like: "name=Bob, age=18"
    return headers;
  }
}