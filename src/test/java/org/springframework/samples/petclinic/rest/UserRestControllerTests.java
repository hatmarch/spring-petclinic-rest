package org.springframework.samples.petclinic.rest;


import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;

@QuarkusTest
@TestHTTPEndpoint(UserRestController.class)
public class UserRestControllerTests {


    @Test
    @TestSecurity(roles="ADMIN")
    public void testCreateUserSuccess() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEnabled(true);
        user.addRole( "OWNER_ADMIN" );
        ObjectMapper mapper = new ObjectMapper();
        String newVetAsJSON = mapper.writeValueAsString(user);
        RestAssured.with().contentType(MediaType.APPLICATION_JSON_VALUE).body(newVetAsJSON).post()
            .then().statusCode(HttpResponseStatus.CREATED.code());
    }

    @Test
    @TestSecurity(roles="ADMIN")
    public void testCreateUserError() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEnabled(true);
        ObjectMapper mapper = new ObjectMapper();
        String newVetAsJSON = mapper.writeValueAsString(user);
        RestAssured.with().contentType(MediaType.APPLICATION_JSON_VALUE).body(newVetAsJSON).post()
            .then().statusCode(HttpResponseStatus.BAD_REQUEST.code());
    }
}
