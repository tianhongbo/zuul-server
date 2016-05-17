package com.huami.id.zuulserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class Service2001It extends TestCase {

  String serverHost;

  @Before
  public void setUp() throws Exception {
    this.serverHost = "https://account-mi-trans-scott.amazfit.com";
    // serverHost = "http://prod-id-pre-test-1480804328.cn-north-1.elb.amazonaws.com.cn";
    // serverHost = "https://account.amazfit.com";
    // serverHost = "https://prod-id-transfer-1938633162.cn-north-1.elb.amazonaws.com.cn";
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testCommon() {
    String url = serverHost + "/v1/service/verify_app_token";

    long start = System.currentTimeMillis();
    try {
      for (int i = 0; i < 1; i++) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("app_token",
            "TAQABAAAAAPUBC3bFlv5BJY8-LaDdHcTmY_YvuEVqsVSQ24HsYspC90JDuEKq3WQmWhHaddWl-VaftLBwBEsEWfSrnYLVogdilQTCVTd8EI4Akvsg2hIAILqUmvHuxeOvMglArr064oIKW_RWrWmT_Lqm2bUoyt2jkj4FFVVRk76Bg1TIyT66_BkjYFfRwl7B7uA8YJsjCdCanyn-338fNDUMRPMj8fNkod1b-l--vc7vdz4KXa9M"));
        urlParameters.add(new BasicNameValuePair("app_name", "com.huami.shushan"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
          result.append(line);
        }
      }

      long end = System.currentTimeMillis();
      System.out.println("delay time = " + (end - start));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
