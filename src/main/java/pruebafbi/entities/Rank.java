package pruebafbi.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "rank")
@SequenceGenerator(name = "rank_seq", sequenceName = "rank_seq", allocationSize = 1)
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "rank_seq")
    @Column(name = "id")
    private Long _id;
    @Column(name = "name")
    private String _name;
    @OneToMany(mappedBy = "_rank", orphanRemoval = true)
    private List<MemberRankLog> _memberRankLogList;

    public Rank() {
    }

    public Rank(Long id) {
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

    public List<MemberRankLog> getMemberList() {
        return _memberRankLogList;
    }

    public void setMemberList(List<MemberRankLog> memberList) {
        _memberRankLogList = memberList;
    }

    public enum rank
    {
        Boss("BOSS"),
        Subordinated("SUBORDINATED");

        private final String rank;

        private rank( String name )
        {
            rank= name;
        }

        public String getRank()
        {
            return this.rank;
        }

    }
}
