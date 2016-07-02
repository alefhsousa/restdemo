package br.com.alefh.restdemo.response;

/**
 * Classe utilizada para realizar o response quando for encontrar um o caractere não repetido em uma Stream
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class FirstCharNonRepeted implements StreamResponse {

    private String charNonRepeted;

    public FirstCharNonRepeted(String charNonRepeted) {
        this.charNonRepeted = charNonRepeted;
    }

    @Override
    public String getResponse() {
        return charNonRepeted;
    }
}
