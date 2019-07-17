package pruebafbi.dtos;

import java.util.Date;
import java.util.List;

public class MemberDto {
    private long _id;
    private String _firstname;
    private String _lastname;
    private String _idNumber;
    private Date _admissionDate;
    private RankDto _rank;
    private StatusDto _status;
    private List<MemberBossActivityDto> _memberActivityList;
    private List<MemberBossActivityDto> _bossActivityList;

    public MemberDto() {
    }

    public MemberDto(long id) {
        this._id = id;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
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
        _idNumber = idNumber;
    }

    public Date getAdmissionDate() {
        return _admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        _admissionDate = admissionDate;
    }

    public RankDto getRank() {
        return _rank;
    }

    public void setRank(RankDto rank) {
        _rank = rank;
    }

    public StatusDto getStatus() {
        return _status;
    }

    public void setStatus(StatusDto status) {
        _status = status;
    }

    public List<MemberBossActivityDto> getMemberActivityList() {
        return _memberActivityList;
    }

    public void setMemberActivityList(List<MemberBossActivityDto> memberActivityList) {
        _memberActivityList = memberActivityList;
    }

    public List<MemberBossActivityDto> getBossActivityList() {
        return _bossActivityList;
    }

    public void setBossActivityList(List<MemberBossActivityDto> bossActivityList) {
        _bossActivityList = bossActivityList;
    }
}
