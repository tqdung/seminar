/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duong.skill.thrift_connection;

import com.duong.skill.skillManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author hoang
 */
public class SkillClientManagerImpl implements SkillClientProvider {

    private final String host;
    private final int port;

    public SkillClientManagerImpl(String host, int port) {
        if (null == host) {
            throw new NullPointerException();
        }
        if (port < 0) {
            throw new IllegalArgumentException();
        }
        this.host = host;
        this.port = port;
    }

    @Override
    public skillManager.Iface getClient() throws RuntimeException {
        TTransport transport;
        try {

            transport = new TSocket(host, port);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            skillManager.Iface clientThrift = new skillManager.Client(protocol);
            return clientThrift;
        } catch (TTransportException ex) {
            throw new TOpenConnectionException("Cannot established conneciton");
        }

    }

}
