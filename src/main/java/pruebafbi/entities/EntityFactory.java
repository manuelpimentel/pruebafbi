package pruebafbi.entities;

public final class EntityFactory {

    public static final Member instanceMember()
    {
        return new Member();
    }

    public static final Member instanceMember(Long id)
    {
        return new Member(id);
    }

    public static final MemberBossActivity instanceMemberBossActivity()
    {
        return new MemberBossActivity();
    }

    public static final MemberBossActivity instanceMemberBossActivity(Long id)
    {
        return new MemberBossActivity(id);
    }

    public static final Status instanceStatus()
    {
        return new Status();
    }

    public static final Status instanceStatus(Long id)
    {
        return new Status(id);
    }

    public static final Rank instanceRank()
    {
        return new Rank();
    }

    public static final Rank instanceRank(Long id)
    {
        return new Rank(id);
    }

    public static final MemberRankLog instanceMemberRankLog()
    {
        return new MemberRankLog();
    }

    public static final MemberRankLog instanceMemberRankLog(Long id)
    {
        return new MemberRankLog(id);
    }

    public static final MemberStatusLog instanceMemberStatusLog()
    {
        return new MemberStatusLog();
    }

    public static final MemberStatusLog instanceMemberStatusLog(Long id)
    {
        return new MemberStatusLog(id);
    }

}
