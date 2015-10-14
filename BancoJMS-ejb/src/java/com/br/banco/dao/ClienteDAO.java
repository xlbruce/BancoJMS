package com.br.banco.dao;

import com.br.banco.entity.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Essa classe sempre irá retornar objetos Pré-definidos, pois o banco não está funcionando.
 * Trechos comentados representam a implementação original
 * @author bruce
 */
public class ClienteDAO implements GenericDAO<Cliente> {
    private ArrayList<Cliente> clientes;

    private EntityManager em;
    private EntityManagerFactory emf;
    
    public ClienteDAO() {
        /*
        emf= Persistence.createEntityManagerFactory("BancoJMS-ejbPU");
        em = emf.createEntityManager();*/
        clientes = new ArrayList<>();
        clientes.add(new Cliente(1010, "Gilson", 1000, "flash05"));
        clientes.add(new Cliente(1011, "José", 10000, "jose"));
        clientes.add(new Cliente(1012, "Samantha", 3700000, "sam"));
        
    }

    @Override
    public void insert(Cliente t) {
        /*em.getTransaction().begin();
        em.persist(t);
        em.close();*/
    }

    @Override
    public Cliente readById(int id) {
        /*Cliente c = em.find(Cliente.class, id);
        return c;*/
        for (Cliente cliente : clientes) {
            if(cliente.getNroConta() == id) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> read() {
        return clientes;
    }
    
}

