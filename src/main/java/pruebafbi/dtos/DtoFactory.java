package pruebafbi.dtos;


public final class DtoFactory {

    public static final MemberDto instanceMemberDto()
    {
        return new MemberDto();
    }

    public static final MemberDto instanceMemberDto(Long id)
    {
        return new MemberDto(id);
    }

    public static final StatusDto instanceStatusDto()
    {
        return new StatusDto();
    }

    public static final StatusDto instanceStatusDto(Long id)
    {
        return new StatusDto(id);
    }

    public static final RankDto instanceRankDto()
    {
        return new RankDto();
    }

    public static final RankDto instanceRankDto(Long id)
    {
        return new RankDto(id);
    }

    public static final CommonResponseDto instanceCommonResponseDto(String code, Object body, String type)
    {
        return new CommonResponseDto(code,body,type);
    }

    public static final CommonResponseDto instanceCommonResponseDto(String code, String type)
    {
        return new CommonResponseDto(code, type);
    }

}
