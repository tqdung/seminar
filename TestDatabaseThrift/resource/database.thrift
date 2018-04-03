namespace java com.duong.TSkill
struct TSkill{
    1: i32 id,
    2: string name

}
service TSkillManager{
    list<TSkill> findAllTSkills();
    TSkill findByID(1: i32 id);
    void deleteByID(1: i32 id);
    TSkill updateByID(1: TSkill TSkill);
    TSkill insertTSkill(1: TSkill TSkill);
    list<TSkill> multiUpdate(1: list<TSkill> TSkills);
    void multiDelete(1: list<i32> ids);
    list<TSkill> multiInsert(1: list<TSkill> TSkills);
    list<TSkill> multiGetByIDs(1: list<i32> ids); 
}
