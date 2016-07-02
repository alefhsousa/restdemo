package br.com.alefh.restdemo.exception;

/**
 * Classe para informar que não foi possível acessar algum serviço externo que a aplicação utiliza
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class UnavailableServiceException extends RuntimeException {

    public UnavailableServiceException(String url){
        super("Não foi possível acessar o serviço: " + url);
    }

    public UnavailableServiceException(String url, Exception e){
        super("Não foi possível acessar o serviço: " + url, e);
    }

}
