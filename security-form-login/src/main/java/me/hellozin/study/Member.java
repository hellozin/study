package me.hellozin.study;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String memberId;

    private String password;

    private int age;

    private boolean isMarried;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
