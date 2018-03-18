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
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
/**
 *
 * @author hoang
 */
public class TestDatabaseThriftClient {
    public static void main(String [] args) throws Exception {
        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(server, "/example");
	context.addServlet(ServletHandler.class, "/");
        TTransport transport;
        try {

        transport = new TSocket("localhost", 8080);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(transport);
        skillManager.Iface clientThrift = new skillManager.Client(protocol);
        
        
        server.start();
        
        
        Skill skill1 = clientThrift.findByID(4);
        System.out.println(skill1.name);
//        
//        Skill insertSkill = new Skill(20,"JavaScript");
//        Skill skill2 = clientThrift.insertSkill(insertSkill);
//        System.out.println(skill2.name);
//
//        clientThrift.deleteByID(2);
//        Skill updateSkill = new Skill(10,"Shell");
//        Skill skill3 = clientThrift.updateByID(updateSkill);
//        System.out.println(skill3.name);
//        
//        List<Skill> arrSkills = new ArrayList<Skill>();
//        arrSkills = clientThrift.findAllSkills();
//        for(int i=0;i<arrSkills.size();i++){
//            System.out.println(arrSkills.get(i).id +" "+ arrSkills.get(i).name);
//        }
//        List<Skill> arrSkills = new ArrayList<Skill>();
//        Skill skill1 = new Skill(11,"ABC");
//        Skill skill2 = new Skill(12,"DEF");
//        arrSkills.add(skill1);
//        arrSkills.add(skill2);
//        
//        List<Skill> arrSkillsGet = new ArrayList<Skill>();
//        arrSkillsGet = clientThrift.multiInsert(arrSkills);
//        for(int i=0;i<arrSkills.size();i++)
//            System.out.println(arrSkillsGet.get(i).name);
//
//        clientThrift.multiDelete(arrSkills);
        
        transport.close();
        } catch (TException x) {
            System.out.println(x.getMessage());
        }
    }
}
