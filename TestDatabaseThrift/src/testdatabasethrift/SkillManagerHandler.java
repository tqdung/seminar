/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdatabasethrift;

import com.duong.domain.Skill;
import com.duong.mapper.SkillMapper;
import com.duong.skill.TSkill;
import com.duong.skill.TSkillManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import org.apache.thrift.TException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author hoang
 */
public class SkillManagerHandler implements TSkillManager.Iface {

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
    public List<TSkill> findAllTSkills() throws TException {
        List<TSkill> arTSkills = new ArrayList<>();

//        try (Connection conn = poolCnn.getConnection()) {
//            try (Statement stm = conn.createStatement()) {
//                ResultSet rs = stm.executeQuery("select * from skills");
//                while (rs.next()) {
//                    TSkill skill = new TSkill();
//                    skill.id = rs.getInt("id");
//                    skill.name = rs.getString("name");
//                    arTSkills.add(skill);
//                }
//                rs.close();
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//            System.out.println(e.getMessage());
//        }
        Session session = SessionProvider.getSession();
        session.beginTransaction();

        String sql = "SELECT id, name FROM skills";

        List<Skill> listSkillDomain = session.createNativeQuery(sql, Skill.class).getResultList();
        session.getTransaction().commit();
        session.close();
        SkillMapper skillMapper = new SkillMapper();
        arTSkills = skillMapper.entities2Dtos(listSkillDomain);

        return arTSkills;
    }

    @Override
    public TSkill findByID(int id) throws TException {
        TSkill tSkill = new TSkill();
//        String sql = "select * from skills where id = ?";
//        try (Connection conn = poolCnn.getConnection()) {
//            try (PreparedStatement stm = conn.prepareStatement(sql)) {
//                stm.setInt(1, id);
//                stm.execute();
//                ResultSet rs = stm.getResultSet();
//                rs.first();
//                skill.id = rs.getInt("id");
//                skill.name = rs.getString("name");
//                rs.close();
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        Session session = SessionProvider.getSession();

        session.beginTransaction();
        Skill skill = session.find(Skill.class, id);
        session.getTransaction().commit();
        session.close();
        SkillMapper skillMapper = new SkillMapper();
        tSkill = skillMapper.entity2Dto(skill);

        return tSkill;

    }

    @Override
    public void deleteByID(int id) throws TException {
//        String sql = "delete from skills where id = ?";
//        try (Connection conn = poolCnn.getConnection()) {
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setInt(1, id);
//            pstm.executeUpdate();
//            pstm.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public TSkill updateByID(TSkill skill) throws TException {
//        String sql = "update skills set name = ? where id = ?";
//        try (Connection conn = poolCnn.getConnection()) {
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setString(2, skill.name);
//            pstm.setInt(1, skill.id);
//            pstm.executeUpdate();
//            pstm.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return skill;
        Session session = SessionProvider.getSession();

        session.beginTransaction();
        Skill skill = session.find(Skill.class, id);
        session.remove(skill);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TSkill insertTSkill(TSkill tSkill) throws TException {
//        if (skill.id < 0) {
//            return null;
//        }
//        String sql = "insert into skills values(?, ?)";
//        try (Connection conn = poolCnn.getConnection()) {
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setInt(1, skill.id);
//            pstm.setString(2, skill.name);
//            pstm.executeUpdate();
//            pstm.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return skill;
        Session session = SessionProvider.getSession();

        session.beginTransaction();

        Skill skill = new Skill(tSkill.getName());
        session.persist(skill);
        session.getTransaction().commit();
        session.close();
        return tSkill;
    }

    @Override
    public List<TSkill> multiUpdate(List<TSkill> skills) throws TException {
//        String sql = "update skills set name = ? where id = ?";
//        try (Connection conn = poolCnn.getConnection()) {
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            for (int i = 0; i < skills.size(); i++) {
//                pstm.setString(1, skills.get(i).name);
//                pstm.setInt(2, skills.get(i).id);
//                pstm.addBatch();
//            }
//            pstm.executeBatch();
//            pstm.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = session.find(Skill.class, skills.get(i).getId());
            skill.setSkillName(skills.get(i).getName());
            session.persist(skill);
        }
        session.getTransaction().commit();
        session.close();
        return skills;
    }

    @Override
    public void multiDelete(List<Integer> ids) throws TException {
//        String sql = "delete from skills where id = ?";
//        try (Connection conn = poolCnn.getConnection()) {
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            for (int i = 0; i < ids.size(); i++) {
//                pstm.setInt(1, ids.get(i));
//                pstm.addBatch();
//            }
//            pstm.executeBatch();
//            pstm.close();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        for(int i=0;i<ids.size();i++){
            Skill skill = session.find(Skill.class, ids.get(i));
            session.remove(skill);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<TSkill> multiInsert(List<TSkill> skills) throws TException {
//        String sql = "insert into skills values(?,?)";
//        try (Connection conn = poolCnn.getConnection()) {
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            for (int i = 0; i < skills.size(); i++) {
//                pstm.setInt(1, skills.get(i).id);
//                pstm.setString(2, skills.get(i).name);
//                pstm.addBatch();
//            }
//            pstm.executeBatch();
//            pstm.close();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

        Session session = SessionProvider.getSession();
        session.beginTransaction();
        
        SkillMapper skillMapper = new SkillMapper();
        List<Skill> listSkill = skillMapper.dtos2Entities(skills);
        
        for(int i=0;i<listSkill.size();i++){
            session.persist(listSkill.get(i));
        }
        
        session.getTransaction().commit();
        session.close();
        return skills;
    }

    @Override
    public List<TSkill> multiGetByIDs(List<Integer> ids) throws TException {
//        String sql = "select * from skills where id = ?";
//        try (Connection conn = poolCnn.getConnection()) {
//            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
//                for (int i = 0; i < ids.size(); i++) {
//                    pstm.setInt(1, ids.get(i));
//                    pstm.execute();
//                    ResultSet rs = pstm.getResultSet();
//                    rs.first();
//                    TSkill skill = new TSkill();
//                    skill.id = rs.getInt("id");
//                    skill.name = rs.getString("name");
//                    listTSkills.add(skill);
//                }
//                pstm.close();
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        List<TSkill> listTSkills = new ArrayList<TSkill>();
        Session session = SessionProvider.getSession();
        List<Skill> listSkills = new ArrayList<Skill>();
        session.beginTransaction();
        SkillMapper skillMapper = new SkillMapper();
        for (int i = 0; i < ids.size(); i++) {
            Skill skill = session.find(Skill.class, ids.get(i));
            listSkills.add(skill);
        }
        listTSkills = skillMapper.entities2Dtos(listSkills);
        session.getTransaction().commit();
        session.close();
        return listTSkills;
    }

    @Override
    public TSkill updateByID(TSkill tSkill) throws TException {
        Session session = SessionProvider.getSession();

        session.beginTransaction();
        Skill skill = session.find(Skill.class, tSkill.getId());
        skill.setSkillName(tSkill.getName());
        session.persist(skill);
        session.getTransaction().commit();
        session.close();
        return tSkill;
    }

}
