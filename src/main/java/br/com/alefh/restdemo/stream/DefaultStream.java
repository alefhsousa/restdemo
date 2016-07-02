package br.com.alefh.restdemo.stream;

import br.com.alefh.restdemo.model.StreamVO;
import br.com.alefh.restdemo.util.FindFirstCharNonRepeted;

/**
 * Classe responsável pela implementação de uma stream
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class DefaultStream implements Stream {
    private int i = 0;
    private char[] chars = null;


    @Deprecated
    /**
    * @deprecated CDI eyes only
    */
    public DefaultStream() {
    }

    public DefaultStream(String str){
        chars = str.toCharArray();
    }

    /**
     * Verifica se ainda é possível percorrer pelas caracteres da stream
     * @return
     */
    public boolean hasNext() {
        return i <= (chars.length-1);
    }

    /**
     * Pega o próximo caractere da stream
     * @return
     */
    public char getNext() {
        return chars[i++];
    }



    @Override
    public String toString() {
        return "[DefaultStream]" + chars.toString();
    }

    /**
     * Válida se a stream que foi passada não esta nula ou vazia
     * @param stream
     * @return
     */
    public boolean isValid(String stream)  {
        if(stream == null || stream.length() <= 0){
            return false;
        }
        return true;
    }

    /**
     * Faz a transformação de uma stream para um array de caracteres
     * @param str
     * @return
     */
    public DefaultStream build(String str) {
        this.chars = str.toCharArray();
        return this;
    }

    /**
     * Análisa a stream e retorna o primeiro caracterece que não se repete
     * @return
     */
    public Character findFirstChar(){
        return FindFirstCharNonRepeted.firstChar(this);
    }
}

