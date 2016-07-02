package br.com.alefh.restdemo.test;

import br.com.alefh.restdemo.model.StreamVO;
import com.google.gson.Gson;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
/**
 * Created by alefh on 7/2/16.
 */
public class TestFirstChar {

    public TestFirstChar() {
        baseURI = "http://localhost:9090/rest/stream/";
    }

    @Test
    public void testComStreamNulo(){
        StreamVO streamVO = new StreamVO();
        String json = new Gson().toJson(streamVO);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/procuraPrimeiroCaractereNaoRepetido")
                .then()
                .statusCode(400);
    }

    @Test
    public void testTodosCharsSeRepetem(){
        StreamVO streamVO = new StreamVO("aqui vou aqui vai vou");
        String json = new Gson().toJson(streamVO);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/procuraPrimeiroCaractereNaoRepetido")
                .then()
                .statusCode(200)
                .body("message", equalTo("Todos os caracteres se repetem ao menos uma vez no input"));
    }

    @Test
    public void testEncontraPrimeiroCharNaoRepetido(){
        StreamVO streamVO = new StreamVO("esse aqui sera o conteudo esperado");
        String json = new Gson().toJson(streamVO);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/procuraPrimeiroCaractereNaoRepetido")
                .then()
                .statusCode(200)
                .body("charNonRepeted", equalTo("q"));
    }
}
