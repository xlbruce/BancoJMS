package com.br.banco.sessionbeans;

import com.br.banco.dao.ClienteDAO;
import com.br.banco.entity.Cliente;
import javax.ejb.Singleton;

/**
 * 
 * @author bruce
 */
@Singleton
public class SessionManager implements SessionManagerLocal {
    
    @Override
    public boolean auth(int nroConta, String senha) {
        ClienteDAO dao = new ClienteDAO();
        Cliente encontrado = dao.readById(nroConta);
        if (encontrado != null
                && encontrado.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean sacar(double qtde, Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        if (cliente.sacar(qtde)) {
            dao.update(cliente);
            return true;
        }
        return false;
    }

    @Override
    public boolean transferir(Cliente origem, Cliente destino, int qtde) {
        ClienteDAO dao = new ClienteDAO();        
        if(origem.sacar(qtde)) {
            destino.depositar(qtde);
            dao.update(origem);
            dao.update(destino);
            return true;
        } 
        return false;
    }

    
}
