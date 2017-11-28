package pdg5.server.persistent;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created on 14.11.17 by Bykow
 */
public class AbstractData {
    public Integer id;

    @Id
    @GeneratedValue(strategy = IDENTITY)

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }
}