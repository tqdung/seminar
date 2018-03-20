namespace java com.duong.skill
struct Skill{
    1: i32 id,
    2: string name

}
service skillManager{
    list<Skill> findAllSkills();
    Skill findByID(1: i32 id);
    void deleteByID(1: i32 id);
    Skill updateByID(1: Skill skill);
    Skill insertSkill(1: Skill skill);
    list<Skill> multiUpdate(1: list<Skill> skills);
    void multiDelete(1: list<Skill> skills);
    list<Skill> multiInsert(1: list<Skill> skills);
    list<Skill> multiGetByIDs(1: list<i32> ids); 
}