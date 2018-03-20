/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdatabasethrift;

import com.duong.skill.Skill;
import com.duong.skill.skillManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoang
 */
public class SkillManagerHandler implements skillManager.Iface {

    String user = "root";
    String pwd = "root";
    String url = "jdbc:mysql://localhost:3306/mysqljdbc";
    private ComboPooledDataSource poolCnn;

    public SkillManagerHandler() throws PropertyVetoException {
        this.poolCnn = new ComboPooledDataSource();
        this.poolCnn.setDriverClass("com.mysql.jdbc.Driver");
        this.poolCnn.setJdbcUrl(url);
        this.poolCnn.setUser(user);
        this.poolCnn.setPassword(pwd);
    }
    
    @Override
    public List<Skill> findAllSkills() throws TException {
        List<Skill> arSkills = new ArrayList<Skill>();

        try (Connection conn = poolCnn.getConnection()) {
            try (Statement stm = conn.createStatement()) {
                ResultSet rs = stm.executeQuery("select * from skills");
                while (rs.next()) {
                    Skill skill = new Skill();
                    skill.id = rs.getInt("id");
                    skill.name = rs.getString("name");
                    arSkills.add(skill);
                }
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(e.getMessage());
        }
        return arSkills;
    }

    @Override
    public Skill findByID(int id) throws TException {
        Skill skill = new Skill();
        String sql = "select * from skills where id = ?";
        try (Connection conn = poolCnn.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setInt(1, id);
                stm.execute();
                ResultSet rs = stm.getResultSet();
                rs.first();
                skill.id = rs.getInt("id");
                skill.name = rs.getString("name");
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return skill;

    }

    @Override
    public void deleteByID(int id) throws TException {
        String sql = "delete from skills where id = ?";
        try (Connection conn = poolCnn.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Skill updateByID(Skill skill) throws TException {
        String sql = "update skills set name = ? where id = ?";
        try (Connection conn = poolCnn.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(2,skill.name);
            pstm.setInt(1, skill.id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return skill;
    }

    @Override
    public Skill insertSkill(Skill skill) throws TException {
        if (skill.id < 0) {
            return null;
        }
        String sql = "insert into skills values(?, ?)";
        try (Connection conn = poolCnn.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, skill.id);
            pstm.setString(2,skill.name);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return skill;
    }

    @Override
    public List<Skill> multiUpdate(List<Skill> skills) throws TException {
        String sql = "update skills set name = ? where id = ?";
        try (Connection conn = poolCnn.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            for(int i=0;i<skills.size();i++){
                pstm.setString(1, skills.get(i).name);
                pstm.setInt(2, skills.get(i).id);
                pstm.addBatch();
            }
            pstm.executeBatch();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return skills;
    }

    @Override
    public void multiDelete(List<Skill> skills) throws TException {
        String sql = "delete from skills where id = ?";
        try (Connection conn = poolCnn.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            for(int i=0;i<skills.size();i++){
                pstm.setInt(1, skills.get(i).id);
                pstm.addBatch();
            }
            pstm.executeBatch();
            pstm.close();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Skill> multiInsert(List<Skill> skills) throws TException {
        String sql = "insert into skills values(?,?)";
        try (Connection conn = poolCnn.getConnection()){
            PreparedStatement pstm = conn.prepareStatement(sql);
            for(int i=0;i<skills.size();i++){
                pstm.setInt(1, skills.get(i).id);
                pstm.setString(2, skills.get(i).name);
                pstm.addBatch();
            }
            pstm.executeBatch();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return skills;
    }

    @Override
    public List<Skill> multiGetByIDs(List<Integer> ids) throws TException {
        String sql = "select * from skills where id = ?";
        List<Skill> listSkills = new ArrayList<Skill>();
        try (Connection conn = poolCnn.getConnection()) {
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                for(int i=0;i<ids.size();i++){
                    pstm.setInt(1, ids.get(i));
                    pstm.execute();
                    ResultSet rs = pstm.getResultSet();
                    rs.first();
                    Skill skill = new Skill();
                    skill.id = rs.getInt("id");
                    skill.name = rs.getString("name");
                    listSkills.add(skill);
                }
                pstm.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listSkills;
    }

}
