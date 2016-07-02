package br.com.alefh.restdemo.service;

import br.com.alefh.restdemo.client.ClientHttp;
import br.com.alefh.restdemo.client.ClientService;
import br.com.alefh.restdemo.exception.CepNotFoundException;
import br.com.alefh.restdemo.exception.UnavailableServiceException;
import br.com.alefh.restdemo.model.Cep;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Classe utilizada para realizar as consultas de CEP
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class CepService {

    public final String wsCep = "http://api.postmon.com.br/v1/cep/";
    private ClientHttp clientHttp;

    @Deprecated
    /**
    * @deprecated CDI eyes only
    */
    public CepService() {
    }

    @Inject
    public CepService(ClientHttp clientHttp) {
        this.clientHttp = clientHttp;
    }

    /**
     * Método que faz a busca de um cep na api do postmon
     * @param cep
     * @return retorna um objeto Cep com suas informações populadas
     * @throws CepNotFoundException Caso um cep não seja encontrado
     */
    public Cep findCepOnApi(String cep) throws CepNotFoundException{

        Cep cepOK = tryFindCep(cep);

        return cepOK;
    }

    /**
     * Método que realiza tentativas de procurar um CEP por aproximação, caso ele não encontrar na primeira tentativa irá acrescentar
     * 0 da direita para esquerda 03377666 ficará 03377660, 03377600 e assim por diante
     * @param cep
     * @return retorna um objeto Cep
     * @throws CepNotFoundException Caso todas as tentativas de cep falharem
     */
    private Cep  tryFindCep(String cep) throws CepNotFoundException {
        int len = cep.length();
        StringBuilder cepBuilder = new StringBuilder(cep);
        while (len > 0){

            try{
                String response =  this.clientHttp.get(wsCep + cepBuilder.toString());
                return new Gson().fromJson(response, Cep.class);
            }catch (UnavailableServiceException e){
                cepBuilder.setCharAt(--len, '0');
            }
        }

        throw new CepNotFoundException(cep);
    }
}
