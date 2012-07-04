/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.freedomotic.webserver;

import it.freedomotic.api.EventTemplate;
import it.freedomotic.api.Protocol;
import it.freedomotic.app.Freedomotic;
import it.freedomotic.exceptions.UnableToExecuteException;
import it.freedomotic.reactions.Command;
import it.freedomotic.util.Info;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author gpt
 */
public class ApplicationServer extends Protocol{
    //TODO: read from config file
    public static final int PORT = 8080;
    public static final String WEBAPP_DIR = "/webapps/gwt_client";
    public static final String WEBAPP_CTX = "/";

    public static Server server;
    
    public ApplicationServer() {
        super("ApplicationServer", "/es.gpulido.webserver/applicationserver-manifest.xml");
    }
    
    @Override
    public void onStart(){
         try {
        String dir = Info.getApplicationPath() + "/plugins/devices/es.gpulido.webserver/data"+WEBAPP_DIR;              
        server = new Server(PORT);               
//        WebAppContext context = new WebAppContext();        
//        context.setDescriptor(dir+"/WEB-INF/web.xml");
//        context.setResourceBase(dir);
//        context.setContextPath(WEBAPP_CTX);
//        context.setParentLoaderPriority(true);  
//        server.setHandler(context);
        
        
         WebAppContext webapp = new WebAppContext();
        webapp.setContextPath(WEBAPP_CTX);
        webapp.setWar(dir+"/Freedomotic_gwt_client.war");
        server.setHandler(webapp);
        
        
       
            server.start();            
        } catch (Exception ex) {
            Logger.getLogger(ApplicationServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    @Override
    public void onStop(){
        try {
            server.stop();
        } catch (Exception ex) {
            Logger.getLogger(ApplicationServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    @Override
    protected void onRun() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onCommand(Command c) throws IOException, UnableToExecuteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean canExecute(Command c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onEvent(EventTemplate event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}