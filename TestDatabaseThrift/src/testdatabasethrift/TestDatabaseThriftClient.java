/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdatabasethrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import com.duong.skill.TSkillManager;
import com.duong.skill.TSkill;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoang
 */
public class TestDatabaseThriftClient {

    public static void main(String[] args) throws Exception {
        TTransport transport;
        try {

            transport = new TSocket("localhost", 8888);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            TSkillManager.Iface clientThrift = new TSkillManager.Client(protocol);
            transport = new TSocket("localhost", 8888);
            transport.open();
            protocol = new TBinaryProtocol(transport);
            TSkillManager.Iface anotherClientThrift = new TSkillManager.Client(protocol);

            TSkill tSkill = clientThrift.findByID(1);
            System.out.println(tSkill.name);
                    
            List<TSkill> findAllSkills = anotherClientThrift.findAllTSkills();
            System.out.println("findAllSkills = " + findAllSkills);
            
            
//            TSkill insertSkill = new TSkill(20,"JavaScript");
//            TSkill skill2 = clientThrift.insertTSkill(insertSkill);
//            System.out.println(skill2.name);
//            clientThrift.deleteByID(12);
//            TSkill updateSkill = new TSkill(1,"Shell");
//            TSkill skill3 = clientThrift.updateByID(updateSkill);
//            System.out.println(skill3.name);
            //        
            //List<Skill> arrSkills = new ArrayList<Skill>();
            //arrSkills = clientThrift.findAllSkills();
            //for(int i=0;i<arrSkills.size();i++){
            //  System.out.println(arrSkills.get(i).id +" "+ arrSkills.get(i).name);
            //}
//            List<TSkill> arrSkills = new ArrayList<TSkill>();
//            TSkill skill1 = new TSkill(11,"ABC");
//            TSkill skill2 = new TSkill(12,"DEF");
//            arrSkills.add(skill1);
//            arrSkills.add(skill2);
//            arrSkills = clientThrift.multiInsert(arrSkills);
            
            //multi select
//            List<TSkill> arrSkills = new ArrayList<TSkill>();
//            List<Integer> listIDs = new ArrayList<Integer>();
//            listIDs.add(1);
//            listIDs.add(3);
//            listIDs.add(4);
//            arrSkills = clientThrift.multiGetByIDs(listIDs);
//            System.out.println(arrSkills);
//            
//            
//            //multi update
//            TSkill skillU1 = new TSkill(1,"Java");
//            TSkill skillU2 = new TSkill(2,"Python 3");
//            List<TSkill> arrSkillsUpdate = new ArrayList<TSkill>();
//            arrSkillsUpdate.add(skillU1);
//            arrSkillsUpdate.add(skillU2);
//            arrSkills = clientThrift.multiUpdate(arrSkillsUpdate);
//            System.out.println(arrSkills);
//            
            List<Integer> listIds = new ArrayList<Integer>();
            listIds.add(8);
            listIds.add(9);
            clientThrift.multiDelete(listIds);
            transport.close();
        } catch (TException x) {
            System.out.println(x.getMessage());
        }
    }
}
