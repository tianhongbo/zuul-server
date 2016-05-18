package com.huami.id.zuulserver.postfilter;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.huami.id.zuulserver.util.FilterUtil;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PostFilter4LogRsp extends ZuulFilter {

  private static final Logger log = LoggerFactory.getLogger(PostFilter4LogRsp.class);

  // post filter to log all the origin response
  @Override
  public String filterType() {
    return "post";
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
    // Always enable logging rsp
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();

    Map<String, String> zuulReqHeaders = ctx.getZuulRequestHeaders();
    List<Pair<String, String>> originRspHeaders = ctx.getOriginResponseHeaders();
    int originRspStatusCode = ctx.getResponse().getStatus();
    List<Pair<String, String>> zuulRspHeaders = ctx.getZuulResponseHeaders();
    int zuulRspStatusCode = ctx.getResponseStatusCode();

    log.info("eventType=\"forward request\", " + "host=\"" + ctx.getRouteHost() + "\", "
        + StringUtils.join(FilterUtil.mapToList(zuulReqHeaders), ", "));
    log.info("eventType=\"receive response\", rspStatusCode=" + originRspStatusCode + ", rspContentLen="
        + ctx.getOriginContentLength() + ", " + StringUtils.join(FilterUtil.pairToList(originRspHeaders), ", "));
    log.info("eventType=\"forward response\", rspStatusCode=" + zuulRspStatusCode + ", "
        + StringUtils.join(FilterUtil.pairToList(zuulRspHeaders), ", "));
    return null;
  }

}