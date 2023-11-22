package org.example.annot.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name = AdvancedSinger.FIND_SINGER_BY_ID,
                query = "select distinct s from Singer s "
                        + "left join fetch s.albums а "
                        + "left join fetch s.instruments i "
                        + "where s.id = :id"),
        @NamedQuery(name = AdvancedSinger.FIND_ALL_WITH_ALBUM,
                query = "select distinct s from Singer s "
                        + "left join fetch s.albums а "
                        + "left join fetch s.instruments i")
})
public class AdvancedSinger extends AbstractEntity {

    public static final String FIND_SINGER_BY_ID = "AdvancedSinger.findById";
    public static final String FIND_ALL_WITH_ALBUM = "AdvancedSinger.findAllWithAlbum";

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


}
