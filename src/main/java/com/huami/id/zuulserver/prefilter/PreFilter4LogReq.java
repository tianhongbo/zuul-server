package com.huami.id.zuulserver.prefilter;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PreFilter4LogReq extends ZuulFilter {

  private static final Logger log = LoggerFactory.getLogger(PreFilter4LogReq.class);

  // pre filter to log all the req
  @Override
  public String filterType() {
    return "pre";
  }

  /**
   * Small number means to be executed firstly. E.g: 1 is earlier than 2.
   * It can be same filterOrder if precedence is not important for a filter.
   */
  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    // Always enable logging req
    return true;
  }

  @Override
  public Object run() {
    HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
    log.info(getHTTPReqHeader(req));
    return null;
  }
  
  private String getHTTPReqHeader(HttpServletRequest request ) {
    List<String> headers = new LinkedList<String>();
    
    Enumeration<String> headerNames = request.getHeaderNames();
    while(headerNames.hasMoreElements()) {
      String headerName = (String)headerNames.nextElement();
      headers.add(headerName + "=\"" + request.getHeader(headerName) + "\"");
    }
    
    // join all the key-value pairs like: "name=Bob, age=18"
    return StringUtils.join(headers, ", ");
  }
}