package br.com.alefh.restdemo.util;

import br.com.alefh.restdemo.stream.Stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Classe utilitária  para analisar  uma stream
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class FindFirstCharNonRepeted {

    /**
     * Método responsável por analisar a stream e retorna o primeiro caractere que não se repete
     * @param stream
     * @return
     */
    public static char firstChar(Stream stream) {
        List<Character> aux = new ArrayList<Character>();
        Set<Character> set = new HashSet<Character>();

        while (stream.hasNext()) {
            Character actual = stream.getNext();
           // System.out.println(actual);
            if (!set.add(actual)) {
                aux.remove(actual);
            } else {
                aux.add(actual);
            }
        }
        return aux.isEmpty() ? ' ' : aux.get(0);
    }
}
