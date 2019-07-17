package pruebafbi.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "member_status_log")
@SequenceGenerator(name = "member_status_seq", sequenceName = "member_status_seq", allocationSize = 1)
public class MemberStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_status_seq")
    @Column(name = "id")
    private Long _id;
    @Column(name = "date")
    private Date _date;
    @ManyToOne
    @JoinColumn(name="fk_member")
    private Member _member;
    @ManyToOne
    @JoinColumn(name="fk_rank")
    private Status _status;

    public MemberStatusLog() {
    }

    public MemberStatusLog(Long id) {
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

    public Status getStatus() {
        return _status;
    }

    public void setStatus(Status status) {
        _status = status;
    }
}
