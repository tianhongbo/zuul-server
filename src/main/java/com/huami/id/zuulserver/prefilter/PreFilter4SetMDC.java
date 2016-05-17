package com.huami.id.zuulserver.prefilter;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.huami.id.zuulserver.util.FilterUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PreFilter4SetMDC extends ZuulFilter {

  private static final Logger log = LoggerFactory.getLogger(PreFilter4SetMDC.class);

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
    // HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
    MDC.clear();
    MDC.put("correlation_id", UUID.randomUUID().toString().replaceAll("-", ""));
    return null;
  }
}