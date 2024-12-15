package jpa.member;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    protected Member() {
    }

    public Member(
            final String name
    ) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
