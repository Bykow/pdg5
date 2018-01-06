package pdg5.server.persistent;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created on 14.11.17 by Bykow
 */
public class AbstractData {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }
}
