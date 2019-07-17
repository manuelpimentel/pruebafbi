package pruebafbi.dtos;

import java.util.Date;

public class MemberBossActivityDto {
    private Long _id;
    private Date _date;
    private Boolean _actual;
    private MemberDto _boss;
    private MemberDto _member;

    public MemberBossActivityDto() {
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public Boolean getActual() {
        return _actual;
    }

    public void setActual(Boolean actual) {
        _actual = actual;
    }

    public MemberDto getBoss() {
        return _boss;
    }

    public void setBoss(MemberDto boss) {
        _boss = boss;
    }

    public MemberDto getMember() {
        return _member;
    }

    public void setMember(MemberDto member) {
        _member = member;
    }
}
