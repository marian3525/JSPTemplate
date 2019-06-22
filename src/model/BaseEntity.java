package model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<ID_T extends Serializable> implements Serializable {
    @Id
    @GeneratedValue
    private ID_T id;

    public BaseEntity() {

    }
}

/*
    Parents MUST have Cascade.PERSIST or ALL on @OneToMany so that the inner list elements
    are persisted too when saving the parent
 */