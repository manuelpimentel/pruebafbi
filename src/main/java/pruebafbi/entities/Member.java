package pruebafbi.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "member")
@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_seq")
    @Column(name = "id")
    private Long _id;
    @Column(name = "firstname")
    private String _firstname;
    @Column(name = "lastname")
    private String _lastname;
    @Column(name = "id_number")
    private String _idNumber;
    @Column(name = "admission_date")
    private Date _admissionDate;
    @OneToMany(mappedBy = "_member", orphanRemoval = true)
    private List<MemberBossActivity> _memberActivityList;
    @OneToMany(mappedBy = "_boss", orphanRemoval = true)
    private List<MemberBossActivity> _bossActivityList;
    @OneToMany(mappedBy = "_member", orphanRemoval = true)
    private List<MemberStatusLog> _memberStatusLogList;
    @OneToMany(mappedBy = "_member", orphanRemoval = true)
    private List<MemberRankLog> _memberRankLogList;

    public Member() {
    }

    public Member(Long id) {
        _id = id;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getFirstname() {
        return _firstname;
    }

    public void setFirstname(String firstname) {
        _firstname = firstname;
    }

    public String getLastname() {
        return _lastname;
    }

    public void setLastname(String lastname) {
        _lastname = lastname;
    }

    public String getIdNumber() {
        return _idNumber;
    }

    public void setIdNumber(String idNumber) {
        this._idNumber = idNumber;
    }

    public Date getAdmissionDate() {
        return _admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        _admissionDate = admissionDate;
    }

    public List<MemberBossActivity> getMemberActivityList() {
        return _memberActivityList;
    }

    public void setMemberActivityList(List<MemberBossActivity> memberActivityList) {
        _memberActivityList = memberActivityList;
    }

    public List<MemberBossActivity> getBossActivityList() {
        return _bossActivityList;
    }

    public void setBossActivityList(List<MemberBossActivity> bossActivityList) {
        _bossActivityList = bossActivityList;
    }

    public List<MemberStatusLog> getMemberStatusLogList() {
        return _memberStatusLogList;
    }

    public void setMemberStatusLogList(List<MemberStatusLog> memberStatusLogList) {
        _memberStatusLogList = memberStatusLogList;
    }

    public List<MemberRankLog> getMemberRankLogList() {
        return _memberRankLogList;
    }

    public void setMemberRankLogList(List<MemberRankLog> memberRankLogList) {
        _memberRankLogList = memberRankLogList;
    }
}
