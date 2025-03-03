package com.bridgelabz.contactsapi.entity;


import jakarta.persistence.*;
import lombok.*;

//UC_1_Using lombok
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}