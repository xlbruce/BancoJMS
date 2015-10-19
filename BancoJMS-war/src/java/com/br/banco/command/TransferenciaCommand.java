package com.br.banco.command;

import com.br.banco.dao.ClienteDAO;
import com.br.banco.entity.Cliente;
import com.br.banco.log.LogProducerLocal;
import com.br.banco.sessionbeans.SessionManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
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
public class TransferenciaCommand implements Command {

    SessionManagerLocal sessionManager = lookupSessionManagerLocal();

    LogProducerLocal logProducer = lookupLogProducerLocal();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ClienteDAO dao = new ClienteDAO();
        int nroContaDestinatario = Integer.parseInt(request.getParameter("nro_conta_destinatario"));
        Cliente destinatario = dao.readById(nroContaDestinatario);
        Cliente remetente = (Cliente) request.getSession().getAttribute("clienteLogado");
        int valorTransferencia = Integer.parseInt(request.getParameter("valor_transferencia"));
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String logMessage = "";

        try (PrintWriter out = response.getWriter()) {
            sessionManager.transferir(remetente, destinatario, valorTransferencia);
            logMessage = "Cliente " + remetente.getNroConta()
                    + " transferiu " + nf.format(valorTransferencia)
                    + " para a conta " + destinatario.getNroConta();
            logProducer.log(logMessage);

            out.print("<script>alert('Transferencia efetuada com sucesso');</script>");
            out.print("<meta http-equiv=\"refresh\" content=0;url=\"transferencia.jsp\">");
        } catch (IOException ex) {
            Logger.getLogger(TransferenciaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*try (PrintWriter out = response.getWriter()) {
         if (remetente.sacar(valorTransferencia)) {
         destinatario.depositar(valorTransferencia);
        
         logMessage = "Cliente " + remetente.getNroConta()
         + " transferiu " + nf.format(valorTransferencia)
         + " para a conta " + destinatario.getNroConta();
         logProducer.log(logMessage);
        
         out.print("<script>alert('Transferencia efetuada com sucesso');</script>");
         out.print("<meta http-equiv=\"refresh\" content=0;url=\"transferencia.jsp\">");
         } else {
         logMessage = "Cliente " + remetente.getNroConta()
         + " tentou transferir " + nf.format(valorTransferencia)
         + " para a conta " + destinatario.getNroConta();
         logProducer.log(logMessage);
        
         out.print("<script>alert('Saldo insuficiente!');</script>");
         out.print("<meta http-equiv=\"refresh\" content=0;url=\"transferencia.jsp\">");
         }
        
        
         } catch (IOException ex) {
         Logger.getLogger(TransferenciaCommand.class.getName()).log(Level.SEVERE, null, ex);
         }*/

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
