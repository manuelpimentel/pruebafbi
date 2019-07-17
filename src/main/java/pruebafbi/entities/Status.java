package pruebafbi.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "status")
@SequenceGenerator(name = "status_seq", sequenceName = "status_seq", allocationSize = 1)
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "status_seq")
    @Column(name = "id")
    private Long _id;
    @Column(name = "name")
    private String _name;
    @OneToMany(mappedBy = "_status")
    private List<MemberStatusLog> _memberStatusLog;

    public Status() {
    }

    public Status(Long id) {
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

    public List<MemberStatusLog> getMemberStatusLog() {
        return _memberStatusLog;
    }

    public void setMemberStatusLog(List<MemberStatusLog> memberStatusLog) {
        _memberStatusLog = memberStatusLog;
    }

    public enum status
    {
        Active("ACTIVE"),
        Incarcerated("INCARCERATED"),
        Dead("DEAD");

        private final String status;

        private status( String name )
        {
            status= name;
        }

        public String getStatus()
        {
            return this.status;
        }
    }
}
