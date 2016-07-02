package br.com.alefh.restdemo.controller;

import br.com.alefh.restdemo.exception.CepNotFoundException;
import br.com.alefh.restdemo.exception.ExceptionController;
import br.com.alefh.restdemo.model.Cep;
import br.com.alefh.restdemo.service.CepService;
import br.com.alefh.restdemo.validator.CepValidator;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

import javax.inject.Inject;

/**
 * Classe responsável por tratar requisições HTTP consultar o CEP e retorna em forma de application/json
 * @author Alefh Sousa
 * @version 1.0
 *
 */

@Controller
public class CepController {

    private Result result;
    private CepService cepService;
    private Validator validator;

    @Deprecated
    /**
    * @deprecated CDI eyes only
    */
    public CepController() {
    }

    @Inject
    public CepController(Result result, CepService cepService, Validator validator) {
        this.result = result;
        this.cepService = cepService;
        this.validator = validator;
    }

    /**
     * Método responsável por realizar a consulta de CEP utilizando o serviço da postmon
     * acessível pela url /cep/consulta com o método Post
     *
     * estrutura do json para requisição:
     *
     * {
     *     "cep": "03354790"
     * }
     *
     * @param cep o cep que deseja consultar
     * @return Retorna o cep em formato application/json
     * @throws CepNotFoundException caso o cep que foi passado não seja encontrado
     */
    @Post
    @Consumes(value = "application/json", options = WithoutRoot.class)
    public void consulta(Cep cep) throws CepNotFoundException {

        result.on(Exception.class).forwardTo(ExceptionController.class).render404ToJson(new SimpleMessage("cep", "CEP NÃO ENCONTRADO"));
        CepValidator.validate(cep, validator);
        validator.onErrorForwardTo(ExceptionController.class).renderErrorsToJson(validator.getErrors());
        Cep cepOk = cepService.findCepOnApi(cep.getCep());

        result.use(Results.json()).withoutRoot().from(cepOk).serialize();


    }


}
