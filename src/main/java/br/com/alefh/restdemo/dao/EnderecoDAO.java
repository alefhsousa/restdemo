package br.com.alefh.restdemo.dao;

import br.com.alefh.restdemo.exception.EnderecoNotFoundException;
import br.com.alefh.restdemo.model.Endereco;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Classe responsável por tratar o acesso ao banco de dados para consultar endereço
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class EnderecoDAO {

    private final EntityManager manager;

    @Deprecated
    /**
    * @deprecated CDI eyes only
    */
    public EnderecoDAO() {
        this(null);
    }

    /**
     * Cria um novo EnderecoDAO.
     *
     * @param manager JPA's EntityManager.
     */
    @Inject
    public EnderecoDAO(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Método que faz a persistência do endereço no banco de dados
     * @param endereco
     */
    public void save(Endereco endereco){
        manager.persist(endereco);
    }

    /**
     * Método que faz o update de um endereço já cadastrado no banco de dados
     * @param endereco
     * @throws EnderecoNotFoundException Caso seja passado um endereço que não exista no banco de dados
     */
    public void update(Endereco endereco) throws EnderecoNotFoundException {
       if(containsById(endereco.getId())){
           System.out.println("#### endereco existe: " + endereco.getId());
           manager.merge(endereco);
       }else {
           throw new EnderecoNotFoundException(endereco.getId().toString());
       }
    }

    /**
     * Método que retorna um endereço pelo Id que foi passado
     * @param id
     * @return
     * @throws EnderecoNotFoundException Caso seja passado um endereço que não exista no banco de dados
     */
    public Endereco findById(Integer id) throws EnderecoNotFoundException{

        if(this.containsById(id)){
            return getEndereco(id);
        }else {
            throw new EnderecoNotFoundException(id.toString());
        }
    }

    /**
     * Método que faz a exclusão de um endereço do banco de dados
     * @param endereco
     * @throws EnderecoNotFoundException Caso seja passado um endereço que não exista no banco de dados
     */
    public void delete(Endereco endereco) throws EnderecoNotFoundException {
        if (containsById(endereco.getId())){
            Endereco enderecoOk=manager.merge(endereco);
            manager.remove(enderecoOk);
        }else {
            throw new EnderecoNotFoundException(endereco.getId().toString());
        }
    }

    /**
     * Método que faz a verificação se existe um endereço já cadastrado com o Id que foi passado
     * @param id
     * @return
     */
    public boolean containsById(Integer id){
        Endereco endereco = getEndereco(id);
        return endereco != null;
    }

    /**
     * Método auxiliar para retornar um endereço pelo id
     * @param id
     * @return
     */

    private Endereco getEndereco(Integer id) {
        return manager.find(Endereco.class, id);
    }
}
