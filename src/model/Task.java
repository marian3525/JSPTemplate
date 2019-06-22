package model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity<Integer> {
    private int duration;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person taskOwner;
}
