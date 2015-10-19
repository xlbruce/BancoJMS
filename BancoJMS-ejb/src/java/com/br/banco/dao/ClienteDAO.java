package com.br.banco.dao;

import com.br.banco.entity.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Classe responsável por fazer o acesso ao banco de dados dos clientes
 * @author bruce
 */
public class ClienteDAO implements GenericDAO<Cliente> {
    //private ArrayList<Cliente> clientes;

    private EntityManager em;
    private EntityManagerFactory emf;
    
    public ClienteDAO() {        
        emf= Persistence.createEntityManagerFactory("BancoJMS-ejbPU");
        em = emf.createEntityManager();        
    }

    @Override
    public void insert(Cliente t) {
        em.getTransaction().begin();
        em.persist(t);
        em.close();
    }

    @Override
    public Cliente readById(int id) {
        Cliente c = em.find(Cliente.class, id);
        return c;
    }

    @Override
    public List<Cliente> read() {
        Query q = em.createNamedQuery("Cliente.findAll");
        return q.getResultList();
    }

    @Override
    public void update(Cliente t) {
        Cliente c = em.find(Cliente.class, t.getNroConta());
        em.getTransaction().begin();
        c.setSaldo(t.getSaldo());
        em.persist(c);
        em.getTransaction().commit();        
    }
    
}

