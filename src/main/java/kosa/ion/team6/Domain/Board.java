package kosa.ion.team6.Domain;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.util.Set;

@Data  //getter, setter 만들어주기
@NoArgsConstructor //생성자 (참조받는게 없는)
@AllArgsConstructor //생성자
@Builder  //빌더 패턴
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String price;
    private String hope_address;
    private boolean onslae;
    private Date create_date;
    private Date update_date;
    private String contents;
    @ColumnDefault("0")
    private int hit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
