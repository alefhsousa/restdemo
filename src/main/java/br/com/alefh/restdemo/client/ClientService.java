package br.com.alefh.restdemo.client;

import br.com.alefh.restdemo.exception.UnavailableServiceException;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe responsável por fazer requisições a serviços RESTFull e retorna o body dos responses em forma de String
 *
 * @author Alefh Sousa
 * @version 1.0
 * Created by alefh on 6/30/16.
 */
public class ClientService implements ClientHttp {

    @Deprecated
    /**
    * @deprecated CDI eyes only
    */
    public ClientService() {
    }

    /**
     *
     * @param url url do serviço que deseja consultar. Exemplo: https://api.github.com/gists
     * @return String contendo a informação do body da requisição
     * @throws MalformedURLException Se a url do serviço estiver formatada de forma errada
     *
     */
    @Override
    public String get(String url) {

        URL serviceUrl = null;
        try {
            serviceUrl = new URL(url);
            InputStream response = serviceUrl.openStream();
            Reader reader = new InputStreamReader(response);
            return  CharStreams.toString(reader);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Não foi possível acessar a url: " + url, e);
        } catch (IOException e) {
            throw  new UnavailableServiceException(url, e);
        }
    }
}
