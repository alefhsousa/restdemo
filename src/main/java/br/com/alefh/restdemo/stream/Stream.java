package br.com.alefh.restdemo.stream;

/***
 * Classe responsável pelo contrato de uma stream
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public interface Stream {

    public char getNext();
    public boolean hasNext();

}
