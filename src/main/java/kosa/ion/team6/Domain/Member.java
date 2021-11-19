package kosa.ion.team6.Domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Member{
	
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String email;

    @JsonIgnore
    private String password;

    private String address;
    private String address_detail;
    private String phone;
    private String resident1;
    private String resident2;
    
    @ColumnDefault("0")
    private int report_count;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @ColumnDefault("0")
    private String zzim;


}
