package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name=Singer.FIND_ALL,
                query="select s from Singer s"),
        @NamedQuery(name = Singer.FIND_SINGER_BY_ID,
                query = "select distinct s from Singer s "
                        + "left join fetch s.albums а "
                        + "left join fetch s.instruments i "
                        + "where s.id = :id"),
        @NamedQuery(name = Singer.FIND_ALL_WITH_ALBUM,
                query = "select distinct s from Singer s "
                        + "left join fetch s.albums а "
                        + "left join fetch s.instruments i")
})
@Getter
@Setter
@NoArgsConstructor
public class Singer implements Serializable {

    public static final String FIND_ALL = "Singer.findAll";
    public static final String FIND_SINGER_BY_ID = "Singer.findById";
    public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany   // (fetch = FetchType.EAGER) для принудительного заполнения связанными данными
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "SINGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    private Set<Instrument> instruments = new HashSet<>();

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", version=" + version +
                '}';
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }
}
