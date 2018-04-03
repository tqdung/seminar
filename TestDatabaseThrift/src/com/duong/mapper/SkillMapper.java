/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duong.mapper;

import com.duong.domain.Skill;
import com.duong.skill.TSkill;

/**
 *
 * @author hoang
 */
public class SkillMapper implements BaseMapper<Skill, TSkill> {

    @Override
    public TSkill entity2Dto(Skill t) {
        TSkill tSkill = new TSkill();
        tSkill.setId(t.getId());
        tSkill.setName(t.getSkillName());
        return tSkill;
    }

    @Override
    public TSkill entity2Dto(Skill t, TSkill r) {
        t.setId(r.getId());
        t.setSkillName(r.getName());
        return r;
    }

    @Override
    public Skill dto2Entity(TSkill r) {
        Skill t = new Skill(r.getName());
        return t;
    }

    @Override
    public Skill dto2Entity(TSkill r, Skill t) {
        t.setId(r.getId());
        t.setSkillName(r.getName());
        return t;
    }

}
