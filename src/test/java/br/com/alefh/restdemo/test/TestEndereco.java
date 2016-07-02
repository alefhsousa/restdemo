package br.com.alefh.restdemo.test;

import br.com.alefh.restdemo.model.Cep;
import br.com.alefh.restdemo.model.Endereco;
import com.google.gson.Gson;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


/**
 * Created by alefh on 6/30/16.
 */

public class TestEndereco {

    public TestEndereco() {
        baseURI = "http://localhost:9090/rest/endereco";
    }

    @Test
    public void testInsereEndereco(){
        Endereco endereco = new Endereco();
        endereco.setCep("03377000");
        endereco.setNumero(32);
        endereco.setRua("Avenida Renata");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/cria")
                .then()
                .statusCode(201);
    }

    @Test
    public void testBuscaEndereco(){
        Endereco endereco = new Endereco(1);
        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(200);
    }

    @Test
    public void testBuscaEnderecoSemId(){
        Endereco endereco = new Endereco();
        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(400);
    }

    @Test
    public void testBuscaEnderecoQueNaoExiste(){
        Endereco endereco = new Endereco(123);
        String json = new Gson().toJson(endereco);
        given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("/consulta")
                .then()
                .statusCode(404);
    }


    @Test
    public void testAtualizaEndereco(){
        Endereco endereco = new Endereco(1);
        endereco.setCep("03377000");
        endereco.setNumero(12);
        endereco.setRua("Avenida Renata");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .put("/atualiza")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAtualizaEnderecoSemId(){
        Endereco endereco = new Endereco();
        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .put("/atualiza")
                .then()
                .statusCode(400);
    }

    @Test
    public void testAtualizaEnderecoQueNaoExiste(){
        Endereco endereco = new Endereco(321);
        endereco.setCep("03377000");
        endereco.setNumero(32);
        endereco.setRua("Avenida Renata");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .put("/atualiza")
                .then()
                .statusCode(404);
    }

    @Test
    public void testAtualizaEnderecoComRuaNull(){
        Endereco endereco = new Endereco();
        endereco.setCep("03377000");
        endereco.setNumero(32);
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .put("/atualiza")
                .then()
                .statusCode(400);
    }

    @Test
    public void testAtualizaEnderecoComNumeroNull(){
        Endereco endereco = new Endereco();
        endereco.setCep("03377000");
        endereco.setRua("Avenida Renata");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .put("/atualiza")
                .then()
                .statusCode(400);
    }

    @Test
    public void testAtualizaEnderecoComCepInvalido(){
        Endereco endereco = new Endereco();
        endereco.setCep("03300");
        endereco.setRua("Avenida Renata");
        endereco.setCidade("São Paulo");
        endereco.setNumero(98);
        endereco.setEstado("SP");

        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .put("/atualiza")
                .then()
                .statusCode(400);
    }

    @Test
    public void testDeletarEndereco(){
        Endereco endereco = new Endereco(1);
        endereco.setCep("03377000");
        endereco.setNumero(32);
        endereco.setRua("Avenida Renata");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .delete("/exclui")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeletarEnderecoQueNaoExiste(){
        Endereco endereco = new Endereco(321);
        endereco.setCep("03377000");
        endereco.setNumero(32);
        endereco.setRua("Avenida Renata");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        String json = new Gson().toJson(endereco);

        given()
                .contentType("application/json")
                .body(json)
                .when()
                .delete("/exclui")
                .then()
                .statusCode(404);
    }
}

