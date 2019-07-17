package pruebafbi.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "member_boss_activity")
@SequenceGenerator(name = "member_boss_activity_seq", sequenceName = "member_boss_activity_seq", allocationSize = 1)
public class MemberBossActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_boss_activity_seq")
    @Column(name = "id")
    private Long _id;
    @Column(name = "date")
    private Date _date;
    @Column(name = "actual")
    private Boolean _actual;
    @ManyToOne
    @JoinColumn(name="fk_member_boss")
    private Member _boss;
    @ManyToOne
    @JoinColumn(name="fk_member")
    private Member _member;

    public MemberBossActivity() {
    }

    public MemberBossActivity(Long id) {
        _id = id;
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

    public Member getBoss() {
        return _boss;
    }

    public void setBoss(Member boss) {
        _boss = boss;
    }

    public Member getMember() {
        return _member;
    }

    public void setMember(Member member) {
        _member = member;
    }
}
