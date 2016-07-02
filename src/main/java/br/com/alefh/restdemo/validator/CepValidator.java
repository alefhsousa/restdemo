package br.com.alefh.restdemo.validator;

import br.com.alefh.restdemo.model.Cep;
import br.com.caelum.vraptor.validator.DefaultValidator;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

import javax.enterprise.inject.Specializes;

/***
 * Classe responsável pela validação de um objeto cep
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class CepValidator  {


    /**
     * Método faz todas as verifições do cep: vazio, com formatação, inválido
     * @param cep objeto para analisar
     * @param validator validador para armaneza as mensagens de validação
     * @return
     */
    public static boolean validate(Cep cep, Validator validator){
        if ("".equals(cep.getCep())) {
            validator.add(new SimpleMessage("cep","não pode ser branco"));
            return false;
        }

        if ("00000000".equals(cep.getCep())) {
            validator.add(new SimpleMessage("cep","CEP INVÁLIDO"));
            return false;
        }

        if (cep.getCep().contains("-")) {
            validator.add(new SimpleMessage("cep","CEP INVÁLIDO"));
            return false;
        }

        if (!cep.getCep().matches("\\d{8}")) {
            validator.add(new SimpleMessage("cep","CEP INVÁLIDO"));
            return false;
        }
        return true;
    }

}
