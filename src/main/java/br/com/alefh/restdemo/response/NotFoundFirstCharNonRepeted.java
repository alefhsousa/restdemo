package br.com.alefh.restdemo.response;

/**
 * Classe utilizada para realizar o response quando não foi possível encontrar o caractere não repetido em uma Stream
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class NotFoundFirstCharNonRepeted implements StreamResponse {

    private String message;

    public NotFoundFirstCharNonRepeted(String message) {
        this.message = message;
    }

    @Override
    public String getResponse() {
        return message;
    }
}
