/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duong.skill.thrift_connection;

import com.duong.skill.skillManager;

/**
 *
 * @author hoang
 */
public interface SkillClientProvider {

    skillManager.Iface getClient() throws RuntimeException;
}
