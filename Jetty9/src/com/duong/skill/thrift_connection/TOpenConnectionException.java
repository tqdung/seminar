/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duong.skill.thrift_connection;

/**
 *
 * @author hoang
 */
public class TOpenConnectionException extends RuntimeException {

    private final String msg;

    public TOpenConnectionException(String msg) {
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;

    }

}
