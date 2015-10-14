package com.br.banco.log;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;

/**
 *
 * @author bruce
 */
@Stateless
public class LogProducer implements LogProducerLocal {
    @Resource(mappedName = "jms/LogBanco")
    private Queue logBanco;
    @Inject
    @JMSConnectionFactory("jms/LogBancoFactory")
    private JMSContext context;

    @Override
    public void log(String msg) {
        sendJMSMessageToLogBanco(msg);
    }

    private void sendJMSMessageToLogBanco(String messageData) {
        context.createProducer().send(logBanco, messageData);
    }

    
}
