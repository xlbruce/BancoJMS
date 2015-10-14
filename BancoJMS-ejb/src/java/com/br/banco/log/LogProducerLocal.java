/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.banco.log;

import javax.ejb.Local;

/**
 *
 * @author bruce
 */
@Local
public interface LogProducerLocal {

    void log(String msg);
    
}
