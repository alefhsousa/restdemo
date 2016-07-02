package br.com.alefh.restdemo.model;

/**
 * Classe para representar um Stream
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class StreamVO {

    private String stream;
    private char  charNonRepeted;

    public StreamVO() {
    }

    public StreamVO(String s) {
        this.stream = s;
    }


    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public char getCharNonRepeted() {
        return charNonRepeted;
    }

    public void setCharNonRepeted(char charNonRepeted) {
        this.charNonRepeted = charNonRepeted;
    }

}
