package br.com.alefh.restdemo.controller;

import br.com.alefh.restdemo.dao.EnderecoDAO;
import br.com.alefh.restdemo.exception.EnderecoNotFoundException;
import br.com.alefh.restdemo.exception.ExceptionController;
import br.com.alefh.restdemo.model.Endereco;
import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Classe responsável pelo CRUD de endereço fazendo a comunicação por HTTP.
 *
 * @author Alefh Sousa
 * @version 1.0
 *
 */
@Controller
public class EnderecoController {

    private  final Result result;
    private final EnderecoDAO dao;
    private final Validator validator;

    @Deprecated
    /**
    * @deprecated CDI eyes only
    */
    public EnderecoController() {
        this(null,null, null);
    }

    @Inject
    public EnderecoController(Result result, EnderecoDAO dao, Validator validator) {
        this.result = result;
        this.dao = dao;
        this.validator = validator;
    }

    /**
     * Método responsável por fazer a criação de um endereço e persistir no banco de dados, dês de que seu objeto seja válido
     *
     * @param endereco objeto json do tipo
     */
    @Post
    @Consumes(value = "application/json", options = WithoutRoot.class)
    public void cria(@NotNull @Valid Endereco endereco){
        System.out.println("### tentando");

        redirectToErro();
        dao.save(endereco);
        result.use(Results.status()).created("/consulta");
    }

    @Put
    @Consumes(value = "application/json", options = WithoutRoot.class)
    public void atualiza(@NotNull @Valid Endereco endereco)throws EnderecoNotFoundException{
        IdIsNullable(endereco);
        redirectToErro();
        renderEnderecoNotFoundToJson();
        dao.update(endereco);

        System.out.println("#### voltou do banco");
        result.use(Results.json()).withoutRoot().from(dao.findById(endereco.getId())).serialize();
    }

    @Post
    @Consumes(value = "application/json", options = WithoutRoot.class)
    public void consulta(@NotNull Endereco endereco) throws EnderecoNotFoundException{
        IdIsNullable(endereco);
        redirectToErro();
        renderEnderecoNotFoundToJson();
        Endereco enderecoOK = dao.findById(endereco.getId());
        result.use(Results.json()).withoutRoot().from(endereco).serialize();
    }

    @Delete
    @Consumes(value = "application/json", options = WithoutRoot.class)
    public void exclui(@NotNull Endereco endereco)throws EnderecoNotFoundException{
        IdIsNullable(endereco);
        redirectToErro();
        renderEnderecoNotFoundToJson();
        dao.delete(endereco);
        result.use(Results.status()).ok();
    }

    /**
     * Retorna uma response 404 informando que não foi possível encontrar o endereço solicitado
     */
    private void renderEnderecoNotFoundToJson() {
        result.on(EnderecoNotFoundException.class).forwardTo(ExceptionController.class).render404ToJson(new SimpleMessage("Endereco", "ENDEREÇO NÃO ENCONTRADO"));
    }

    /**
     * Valida se o objeto de endereço que foi passado, tem o id preenchido.
     * @param endereco
     * @return
     */
    private Validator IdIsNullable(@NotNull @Valid Endereco endereco) {
        return validator.addIf(endereco.getId() == null, new SimpleMessage("id", "O ID deve ser preenchido"));
    }

    /**
     * Redireciona o fluxo de dados para apresentar o erro ao usuário da api
     */
    private void redirectToErro() {
        validator.onErrorForwardTo(ExceptionController.class).renderErrorsToJson(validator.getErrors());
    }
}
