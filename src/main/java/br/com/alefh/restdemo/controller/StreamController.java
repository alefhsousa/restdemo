package br.com.alefh.restdemo.controller;

import br.com.alefh.restdemo.exception.ExceptionController;
import br.com.alefh.restdemo.model.StreamVO;
import br.com.alefh.restdemo.response.FirstCharNonRepeted;
import br.com.alefh.restdemo.response.NotFoundFirstCharNonRepeted;
import br.com.alefh.restdemo.response.StreamResponse;
import br.com.alefh.restdemo.stream.DefaultStream;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.view.Results;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * Classe responsável pelo serviço rest de encontrar o primeiro caractere que não se repete dado uma stream qualquer
 *
 * @author Alefh Sousa
 * @version 1.0
 *
 */
@Controller
public class StreamController {

    private final Result result;
    private DefaultStream defaultStream;

    @Deprecated
    /**
    * @deprecated CDI eyes only
    */
    public StreamController() {
        this(null, null);
    }

    @Inject
    public StreamController(Result result, DefaultStream defaultStream) {
        this.result = result;
        this.defaultStream = defaultStream;
    }

    /**
     * Endpoint para analisar o stream e verificar qual o primeiro caractere que não se repete
     *
     * Veja mais informações aqui: {link}
     *
     * @param  streamVO
     *
     */
    @Post
    @Consumes(value = "application/json", options = WithoutRoot.class)
    public void procuraPrimeiroCaractereNaoRepetido(StreamVO streamVO){

        if (!defaultStream.isValid(streamVO.getStream())){
            result.forwardTo(ExceptionController.class)
                    .renderErrorsToJson(Arrays.asList(new SimpleMessage("stream","A stream não pode ser nula ou vazia")));
        }

        Character c = defaultStream.build(streamVO.getStream()).findFirstChar();

        result.use(Results.json()).withoutRoot().from(getMessageOrChar(c)).serialize();
    }

    private StreamResponse getMessageOrChar(Character character){
        if(character.equals(' ')) return new NotFoundFirstCharNonRepeted("Todos os caracteres se repetem ao menos uma vez no input");

        return new FirstCharNonRepeted(character.toString());
    }

}
