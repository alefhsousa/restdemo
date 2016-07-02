package br.com.alefh.restdemo.exception;

/**
 * Classe para informar que não foi possível encontrar um Endereço
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class EnderecoNotFoundException extends Exception {
    public EnderecoNotFoundException(String message) {
        super("Não foi encontrado endereço com id: " + message);
    }
}
