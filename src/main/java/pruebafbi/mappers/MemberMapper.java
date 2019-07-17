package pruebafbi.mappers;

import pruebafbi.dtos.DtoFactory;
import pruebafbi.dtos.MemberDto;
import pruebafbi.entities.EntityFactory;
import pruebafbi.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberMapper {

    public static final Member dtoToBasicEntity(MemberDto memberDto)
    {
        final Member member = EntityFactory.instanceMember(memberDto.getId());
        return member;
    }

    public Member dtoToEntity(MemberDto memberDto)
    {
        final Member member = EntityFactory.instanceMember(memberDto.getId());
        member.setFirstname(memberDto.getFirstname());
        member.setLastname(memberDto.getLastname());
        member.setAdmissionDate(memberDto.getAdmissionDate());
        member.setIdNumber(memberDto.getIdNumber());
        return member;
    }

    public static final MemberDto entityToDto(Member member)
    {
        final MemberDto memberDto = new MemberDto(member.getId());
        memberDto.setFirstname(member.getFirstname());
        memberDto.setLastname(member.getLastname());
        memberDto.setAdmissionDate(member.getAdmissionDate());
        memberDto.setIdNumber(member.getIdNumber());
        return memberDto;
    }

    public static final List<MemberDto> entityListToDtoList( List<Member> memberList )
    {
        final List<MemberDto> memberDtoList = new ArrayList<>();
        for (Member m : memberList)
        {
            final MemberDto memberDto = DtoFactory.instanceMemberDto(m.getId());
            memberDto.setFirstname(m.getFirstname());
            memberDto.setLastname(m.getLastname());
            memberDto.setIdNumber(m.getIdNumber());
            memberDto.setAdmissionDate(m.getAdmissionDate());
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

}
