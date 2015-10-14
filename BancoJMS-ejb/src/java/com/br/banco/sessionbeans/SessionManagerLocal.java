/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.banco.sessionbeans;

import javax.ejb.Local;

/**
 *
 * @author bruce
 */
@Local
public interface SessionManagerLocal {

    boolean auth(int nroConta, String senha);
    
}
