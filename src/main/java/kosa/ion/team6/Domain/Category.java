package kosa.ion.team6.Domain;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
	@Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
	private String name;
	private String description;
}
