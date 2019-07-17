package pruebafbi.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "member_rank_log")
@SequenceGenerator(name = "member_rank_seq", sequenceName = "member_rank_seq", allocationSize = 1)
public class MemberRankLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_rank_seq")
    @Column(name = "id")
    private Long _id;
    @Column(name = "date")
    private Date _date;
    @ManyToOne
    @JoinColumn(name="fk_member")
    private Member _member;
    @ManyToOne
    @JoinColumn(name="fk_rank")
    private Rank _rank;

    public MemberRankLog() {
    }

    public MemberRankLog(Long id) {
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

    public Member getMember() {
        return _member;
    }

    public void setMember(Member member) {
        _member = member;
    }

    public Rank getRank() {
        return _rank;
    }

    public void setRank(Rank rank) {
        _rank = rank;
    }
}
