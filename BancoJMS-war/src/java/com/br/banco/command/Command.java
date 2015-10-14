package com.br.banco.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bruce
 */
public interface Command {
    
    public void execute (HttpServletRequest request, HttpServletResponse response);
    
}
