package br.com.alefh.restdemo.client;


import br.com.alefh.restdemo.exception.UnavailableServiceException;

/**
 *  Interface responsável por estabelecer um contrato com as classes que irão fazer implementação de um client http
 *
 * @author  Alefh Sousa
 * @version 1.0
 */
public interface ClientHttp {

    /**
     *
     * @param url url do serviço que deseja consultar. Exemplo: https://api.github.com/gists
     * @return String Retorna o body da requisição em forma de string
     * @throws UnavailableServiceException caso o servidor esteja fora do ar
     */
    String get(String url) throws UnavailableServiceException;

}
