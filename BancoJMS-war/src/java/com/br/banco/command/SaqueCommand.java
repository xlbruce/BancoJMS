package com.br.banco.command;

import com.br.banco.entity.Cliente;
import com.br.banco.log.LogProducerLocal;
import com.br.banco.sessionbeans.SessionManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bruce
 */
public class SaqueCommand implements Command {
    SessionManagerLocal sessionManager = lookupSessionManagerLocal();

    LogProducerLocal logProducer = lookupLogProducerLocal();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente = (Cliente) request.getSession().getAttribute("clienteLogado");
        int valorSaque = Integer.parseInt(request.getParameter("valor_saque"));
        try (PrintWriter out = response.getWriter()) {
            if (sessionManager.sacar(valorSaque, cliente)) {
                //out.print("<script>alert('Saque realizado com sucesso');</script>");
                String logMessage = "Cliente " + cliente.getNroConta()
                        + " sacou R$" + valorSaque;
                logProducer.log(logMessage);
                out.print("<meta http-equiv=\"refresh\" content=0;url=\"saque.jsp?result=sacado\">");
            } else {
                //out.print("<script>alert('Saldo insuficiente!');</script>");
                request.getSession().setAttribute("sacado", false);                
                request.setAttribute("saldo_insuficiente", true);
                String logMessage = "Cliente " + cliente.getNroConta()
                        + " tentou sacar R$" + valorSaque;
                logProducer.log(logMessage);
                out.print("<meta http-equiv=\"refresh\" content=0;url=\"saque.jsp?result=saldo_insuficiente\">");
            }            
            
        } catch (IOException ex) {
            System.err.println("IOException");
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

    private SessionManagerLocal lookupSessionManagerLocal() {
        try {
            Context c = new InitialContext();
            return (SessionManagerLocal) c.lookup("java:global/BancoJMS/BancoJMS-ejb/SessionManager!com.br.banco.sessionbeans.SessionManagerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
