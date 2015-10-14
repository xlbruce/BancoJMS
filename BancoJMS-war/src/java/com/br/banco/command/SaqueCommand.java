package com.br.banco.command;

import com.br.banco.entity.Cliente;
import com.br.banco.log.LogProducerLocal;
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

    LogProducerLocal logProducer = lookupLogProducerLocal();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente = (Cliente) request.getSession().getAttribute("clienteLogado");
        int valorSaque = Integer.parseInt(request.getParameter("valor_saque"));
        try (PrintWriter out = response.getWriter()) {
            if (cliente.sacar(valorSaque)) {
                out.print("<script>alert('Saque realizado com sucesso');</script>");

                String logMessage = "Cliente " + cliente.getNroConta()
                        + " sacou R$" + valorSaque;
                logProducer.log(logMessage);
            } else {
                out.print("<script>alert('Saldo insuficiente!');</script>");
                String logMessage = "Cliente " + cliente.getNroConta()
                        + " tentou sacar R$" + valorSaque;
                logProducer.log(logMessage);
            }
            
            out.print("<meta http-equiv=\"refresh\" content=0;url=\"saque.jsp\">");
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

}
