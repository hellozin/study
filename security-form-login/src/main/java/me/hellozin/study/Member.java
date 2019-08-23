package me.hellozin.study;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String memberId;

    private String password;

    private Integer age;

    private Boolean isMarried;

    public Member(String memberId, String password, Integer age, Boolean isMarried) {
        this.memberId = memberId;
        this.password = password;
        this.age = age;
        this.isMarried = isMarried;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
