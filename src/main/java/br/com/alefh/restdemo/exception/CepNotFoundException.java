package br.com.alefh.restdemo.exception;

/**
 * Classe para informar que não foi possível encontrar um cep
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class CepNotFoundException extends Exception {
    public CepNotFoundException(String url){
        super("Não foi encontrado o cep: " + url);
    }
}
