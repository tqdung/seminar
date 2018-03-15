/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdatabasethrift;

import com.duong.skill.Skill;
import com.duong.skill.skillManager;
import java.util.List;
import org.apache.thrift.TException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
/**
 *
 * @author hoang
 */
public class SkillManagerHandler implements skillManager.Iface {

    String user = "root";
    String pwd = "root";
    String url = "jdbc:mysql://localhost:3306/mysqljdbc";
    @Override
    public List<Skill> findAllSkills() throws TException {
        List<Skill> arSkills = new ArrayList<Skill>();
        System.out.println("asdfdjasf");
        try{
            Connection conn = DriverManager.getConnection(url, user, pwd);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from skills");
            while(rs.next()){
                Skill skill = new Skill();
                skill.id = rs.getInt("id");
                skill.name = rs.getString("name");
                arSkills.add(skill);
            }
            
        }catch(SQLException e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
        return arSkills;
    }

    @Override
    public Skill findByID(int id) throws TException {
        Skill skill = new Skill();
        String sql = "select * from skills where id =" + id;
        try{
            Connection conn = DriverManager.getConnection(url, user, pwd);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.first();
            skill.id = rs.getInt("id");
            skill.name = rs.getString("name");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return skill;
        
    }

    @Override
    public void deleteByID(int id) throws TException {
        String sql = "delete from skills where id =" + id;
        try{
            Connection conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Skill updateByID(Skill skill) throws TException {
        String sql = "update skills set name ='"+ skill.name+ "' where id =" + skill.id;
        try{
            Connection conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return skill;
    }

    @Override
    public Skill insertSkill(Skill skill) throws TException {
        String sql = "insert into skills values('"+skill.id+"','"+skill.name+"')";
        try{
            Connection conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return skill;
    }

}
