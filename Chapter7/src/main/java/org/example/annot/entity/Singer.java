package org.example.annot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name="Singer.findById",
                query = "select distinct s from Singer s "
                        + "left join fetch s.albums а "
                        + "left join fetch s.instruments i "
                        + "where s.id = :id"),
        @NamedQuery(name = "Singer.findAllWithAlbum",
                query = "select distinct s from Singer s "
                        + "left join fetch s.albums а "
                        + "left join fetch s.instruments i")
})
public class Singer implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int version;
    private Set<Album> albums = new HashSet<>();
    private Set<Instrument> instruments = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Album> getAlbums() {
        return albums;
    }

    public boolean addAlbum(Album album) {
        album.setSinger(this);
        return getAlbums().add(album);
    }

    public void removeAlbum(Album album) {
        getAlbums().remove(album);
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @ManyToMany   // (fetch = FetchType.EAGER) для принудительного заполнения связанными данными
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "SINGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }


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
}
