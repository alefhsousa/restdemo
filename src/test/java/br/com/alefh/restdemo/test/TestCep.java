package br.com.alefh.restdemo.test;

import br.com.alefh.restdemo.model.Cep;
import com.google.gson.Gson;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by alefh on 6/29/16.
 */
public class TestCep {

    public TestCep() {
        baseURI = "http://localhost:9090/cep/";
    }

    @Test
    public void testCpfValido(){
        Cep cep = new Cep("03377050");
        String json = new Gson().toJson(cep);
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(200)
                .body("cep", equalTo(cep.getCep()));
    }

    @Test
    public void testCepInexistente(){
        Cep cep = new Cep("03172666");
        String json = new Gson().toJson(cep);
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(404)
                .body("message", equalTo("CEP NÃO ENCONTRADO"));
    }

    @Test
    public void testCepPorAproximacao(){

        Cep cep = new Cep("03377666");
        String json = new Gson().toJson(cep);
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(200)
                .body("cep", equalTo("03377000"));
    }
    @Test
    public void testCepComMenosDe8Caracteres(){
        String cepInvalido = "033";
        Cep cep = new Cep("033");
        String json = new Gson().toJson(cep);
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCepComMascara(){
        Cep cep = new Cep("03377-040");
        String json = new Gson().toJson(cep);
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCepSomenteComZeros() {
        Cep cep = new Cep("00000000");
        String json = new Gson().toJson(cep);
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(400);
    }


}
