package model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity<Integer> {
    private String name;

    // infinite recursion unless excluded from hash
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "employeeCompany", cascade = CascadeType.ALL, fetch= FetchType.EAGER) // or persist only
    private Set<Person> employees;
}
