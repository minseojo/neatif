package com.minseojo.neatif.domain.member;


import com.minseojo.neatif.domain.address.Address;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="members")
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userId;
    private String password;
    private String username;
    private Address address;
    private String tel;
    private String mobile;
    private String email;

    protected Member() {

    }
}
