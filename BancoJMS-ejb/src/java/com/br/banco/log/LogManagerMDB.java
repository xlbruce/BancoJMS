package com.br.banco.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author bruce
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/LogBanco"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class LogManagerMDB implements MessageListener {
    
    private final File ARQUIVOLOG
            = new File("/home/bruce/NetBeansProjects/BancoJMS/log_banco.txt");
    
    public LogManagerMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            log(tm.getText());
        } catch (JMSException ex) {
            System.err.println("JMSException");
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("IOException");
            System.err.println(ex.getMessage());
        }
    }
    
    public void log(String msg) throws IOException {
        validaArquivo(ARQUIVOLOG);
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("[dd/MM/YYYY HH:mm:ss] ");
        String saida = df.format(c.getTime());
        saida += msg + "\n";
        Files.write(Paths.get(ARQUIVOLOG.getPath()), saida.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * Verifica se o arquivo de log existe. Se não existir, cria um novo.
     *
     * @param f
     * @return
     */
    private void validaArquivo(File f) throws IOException {
        if (!f.isFile()) {
            f.createNewFile();
        }
    }
}
