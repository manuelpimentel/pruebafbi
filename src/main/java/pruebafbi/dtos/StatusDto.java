package pruebafbi.dtos;

import java.util.List;

public class StatusDto {
    private Long _id;
    private String _name;
    private List<MemberDto> _memberList;

    public StatusDto() {
    }

    public StatusDto(Long id) {
        _id = id;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public List<MemberDto> getMemberList() {
        return _memberList;
    }

    public void setMemberList(List<MemberDto> memberList) {
        _memberList = memberList;
    }
}
