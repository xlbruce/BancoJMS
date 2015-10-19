package com.br.banco.sessionbeans;

import com.br.banco.entity.Cliente;
import javax.ejb.Local;

/**
 *
 * @author bruce
 */
@Local
public interface SessionManagerLocal {

    boolean sacar(double qtde, Cliente cliente);
    
    public boolean auth(int nroConta, String senha);

    void transferir(Cliente origem, Cliente destino, int qtde);
}
