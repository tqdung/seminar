/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdatabasethrift;

import com.duong.skill.skillManager;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author hoang
 */
public class TestDatabaseThriftServer {

    public static skillManager.Processor processor;
    public static SkillManagerHandler handler;

    public static void main(String[] args) throws TTransportException, PropertyVetoException {
        handler = new SkillManagerHandler();
        processor = new skillManager.Processor(handler);
        Runnable run = new Runnable() {
            public void run() {
                try {
                    TServerTransport serverTransport = new TServerSocket(8888);
                    TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
                            .processor(processor));
                    server.serve();
                } catch (TTransportException ex) {
                    Logger.getLogger(TestDatabaseThriftServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        new Thread(run).start();
    }

    // define interface ->viet  write implemnt
}
