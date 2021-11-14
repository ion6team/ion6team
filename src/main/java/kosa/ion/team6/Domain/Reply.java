package kosa.ion.team6.Domain;

import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //getter, setter 만들어주기
@NoArgsConstructor //생성자 (참조받는게 없는)
@AllArgsConstructor //생성자
@Table
@Entity
@Builder
public class Reply {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)//여러개의 댓글은 하나의게시글에 사용할 수 있다
    @JoinColumn(name="board_id" )
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER) //여러개의 댓글은 하나의게시글에 사용할 수 있다 //대댓은 many가 될수도?
    @JoinColumn(name="member_id")
    private Member member;

    @CreationTimestamp //오라클에서 sysdate로 써도 되지만 @CreationTimestamp으로 현재시간받아도 된다
    private Timestamp createDate;


}
