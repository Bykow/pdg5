package pdg5.server.persistent;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * parent class of any data who wants to be store in the database
 */
public class AbstractData {

    /**
     * unique data of the data
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Integer id;

    /**
     * return the unique id of the data
     *
     * @return the unique id of the data
     */
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    /**
     * change the unique id of the data
     *
     * @param id the new unique id of the data
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
