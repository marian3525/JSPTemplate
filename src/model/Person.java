package model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity<Integer> {
    private String name;

    // name of the field that holds a reference to the parent in the child
    // EAGER fetching only, LAZY will fail when attempting at access the inner set
    @OneToMany(mappedBy = "taskOwner", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Task> tasks;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    private Company employeeCompany;
}
