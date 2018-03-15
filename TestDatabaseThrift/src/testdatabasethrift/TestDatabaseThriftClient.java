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
import com.duong.skill.skillManager;
import com.duong.skill.Skill;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hoang
 */
public class TestDatabaseThriftClient {
    public static void main(String [] args) {
        TTransport transport;
        try {

        transport = new TSocket("localhost", 8080);
        transport.open();

        TProtocol protocol = new  TBinaryProtocol(transport);
        skillManager.Iface clientThrift = new skillManager.Client(protocol);     
         
        
//        Skill skill1 = client.findByID(4);
//        System.out.println(skill1.name);
        
//        Skill insertSkill = new Skill(9,"C#");
//        Skill skill2 = client.insertSkill(insertSkill);
//        System.out.println(skill2.name);

//        client.deleteByID(9);
//        Skill updateSkill = new Skill(9,"F#");
//        Skill skill3 = client.updateByID(updateSkill);
//        System.out.println(skill3.name);
        
        List<Skill> arrSkills = new ArrayList<Skill>();
        arrSkills = clientThrift.findAllSkills();
        for(int i=0;i<arrSkills.size();i++){
            System.out.println(arrSkills.get(i).id +" "+ arrSkills.get(i).name);
        }
        
        transport.close();
        } catch (TException x) {
            System.out.println(x.getMessage());
        }
    }
}
