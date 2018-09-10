package org.entando.selenium.contracttests;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Provider("UserAddProvider")
@PactFolder("target/pacts")
public class UserAddProviderTest {
    private static String accesToken;
    private static Header sessionId;

    @BeforeAll
    public static  void login() throws IOException {
        HttpPost post = new HttpPost( "http://localhost:8080/entando/OAuth2/access_token");
        post.addHeader("Origin", "http://localhost:5000");
        post.addHeader("Accept-Encoding","gzip, deflate, br");
        post.addHeader("Host","localhost:8080");
        post.addHeader("Accept-Language","en-GB,en-US;q=0.9,en;q=0.8");
        post.addHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        post.addHeader("Referer","http://localhost:5000/");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Accept", "*/*");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username","admin"));
        params.add(new BasicNameValuePair("password","adminadmin"));
        params.add(new BasicNameValuePair("grant_type","password"));
        params.add(new BasicNameValuePair("client_id","true"));
        params.add(new BasicNameValuePair("client_secret","true"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = HttpClients.createDefault().execute(post);

        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        response.getEntity().writeTo(outstream);
        accesToken=new JSONObject(new String(outstream.toByteArray())).getString("access_token");
        sessionId=response.getFirstHeader("Set-Cookie");
        System.out.println();
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void testTemplate(PactVerificationContext context, HttpRequest request) {
        // This will add a header to the request
        request.addHeader("Authorization", "Bearer " + accesToken);
        request.setHeader(sessionId);
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) throws MalformedURLException {
        context.setTarget(HttpTestTarget.fromUrl(new URL("http://localhost:8080")));
    }

}
