package com.br.banco.sessionbeans;

import com.br.banco.dao.ClienteDAO;
import com.br.banco.entity.Cliente;
import javax.ejb.Stateless;

/**
 *
 * @author bruce
 */
@Stateless
public class SessionManager implements SessionManagerLocal {

    @Override
    public boolean auth(int nroConta, String senha) {
        ClienteDAO dao = new ClienteDAO();
        Cliente encontrado = dao.readById(nroConta);
        if (encontrado != null &&
                encontrado.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

}
