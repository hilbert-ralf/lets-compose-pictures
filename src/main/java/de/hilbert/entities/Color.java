package de.hilbert.entities;


import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 * @author hilbert
 * @since 31.10.14
 */
@NodeEntity
public class Color {
    
    @GraphId
    private Long id;

    public Color() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
