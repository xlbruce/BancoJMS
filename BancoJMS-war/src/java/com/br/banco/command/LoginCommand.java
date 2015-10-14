package com.br.banco.command;

import com.br.banco.dao.ClienteDAO;
import com.br.banco.entity.Cliente;
import com.br.banco.log.LogProducerLocal;
import com.br.banco.sessionbeans.SessionManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
public class LoginCommand implements Command {

    LogProducerLocal logProducer = lookupLogProducerLocal();
    SessionManagerLocal sessionManager = lookupSessionManagerLocal();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        /**
         * Como o banco de dados está dando erro, vou deixar um usuário default
         * para testes
         */
        /*int nroConta = Integer.parseInt(request.getParameter("nro_conta"));
         Cliente cliente = new Cliente(nroConta);
         cliente.setSenha(request.getParameter("senha"));
         ClienteDAO dao = new ClienteDAO();
         Cliente encontrado = dao.readById(nroConta);
        
         if (encontrado != null
         && encontrado.getSenha().equals(cliente.getSenha())) {
         //Seta um objeto cliente para manter a sessão ativa
         request.getSession().setAttribute("clienteLogado", encontrado);
         //Seta um cookie para teste
         Cookie cookie = new Cookie("usuarioLogado",
         encontrado.getNroConta().toString());
        
         cookie.setMaxAge(-1);
        
         response.addCookie(cookie);
         //Faz o log
         String logMessage = "Cliente " + encontrado.getNome();
         logMessage += ", conta (" + encontrado.getNroConta()
         + ") logado";
        
         logProducer.log(logMessage);
        
         try {
         request.getRequestDispatcher("index.jsp").forward(request, response);
         } catch (ServletException ex) {
         System.err.println("ServletException");
         System.err.println(ex.getMessage());
         } catch (IOException ex) {
         System.err.println("IOException");
         System.err.println(ex.getMessage());
         }
         }*/

        //--INICIO--
        int nroConta = Integer.parseInt(request.getParameter("nro_conta"));
        String senha = request.getParameter("senha");

        if (sessionManager.auth(nroConta, senha)) {
            ClienteDAO dao = new ClienteDAO();
            Cliente clienteLogado = dao.readById(nroConta);
            request.getSession().setAttribute("clienteLogado", clienteLogado);

            String logMessage = "Cliente " + clienteLogado.getNroConta() + " logado";
            logProducer.log(logMessage);
            try {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                System.err.println("Erro ao redirecionar");
                System.err.println(ex.getMessage());
            }

        } else {
            try (PrintWriter out = response.getWriter()) {
                String logMessage = "Tentativa de login com a conta " + nroConta;
                logProducer.log(logMessage);
                out.print("<script>alert('Usuário ou senha inválidos');</script>");
                out.print("<meta http-equiv=\"refresh\" content=0;url=\"login.html\">");
            } catch (IOException ex) {
                Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
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
