package com.br.banco.command;

import com.br.banco.entity.Cliente;
import com.br.banco.log.LogProducerLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bruce
 */
public class LogoffCommand implements Command {
    LogProducerLocal logProducer = lookupLogProducerLocal();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("cheguei na classe logoffcommand");
        Cliente cliente = (Cliente) request.getSession().getAttribute("clienteLogado");
        String logMessage = "Cliente " + cliente.getNroConta() +
                " fez logoff";
        logProducer.log(logMessage);
        request.getSession().invalidate();
        System.out.println("passei aqui");
        try {
            request.getRequestDispatcher("login.html").forward(request, response);
        } catch (ServletException | IOException ex) {
            System.err.println("Erro ao redirecionar");
            System.err.println(ex.getMessage());
        }
    }

    private LogProducerLocal lookupLogProducerLocal() {
        try {
            Context c = new InitialContext();
            return (LogProducerLocal) c.lookup("java:global/BancoJMS/BancoJMS-ejb/LogProducer!com.br.banco.log.LogProducerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
