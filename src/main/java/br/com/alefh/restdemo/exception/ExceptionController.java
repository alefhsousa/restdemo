package br.com.alefh.restdemo.exception;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.view.Results;

import javax.inject.Inject;
import java.util.List;

/**
 * Classe responsável por tratar todas as Exceptions que ocorre em volta do negócio da api e retorna um objeto Json de forma amigável
 * para o usuário
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class ExceptionController {

    Result result;

    public ExceptionController() {
    }

    @Inject
    public ExceptionController(Result result) {
        this.result = result;
    }

    /**
     * Método que retorna um objeto json com todos erros que aconteceram na operação e informando que é uma Bad Request
     * @param validationExceptions
     */
    public void renderErrorsToJson(List<Message> validationExceptions) {
        result.use(Results.http()).setStatusCode(400);
        result.use(Results.json()).withoutRoot().from(validationExceptions).exclude("category").serialize();
    }

    /**
     * Método que retorna um objeto json informando que o recurso solicitado não foi encontrado Not Found Request
     * @param validationExceptions
     */
    public void render404ToJson(Message validationExceptions){
        result.use(Results.http()).setStatusCode(404);
        result.use(Results.json()).withoutRoot().from(validationExceptions).exclude("category").serialize();
    }

}
