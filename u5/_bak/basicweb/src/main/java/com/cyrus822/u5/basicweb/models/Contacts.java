package com.cyrus822.u5.basicweb.models;

import java.io.Serializable;

import org.hibernate.annotations.Generated;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Contacts implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;    

    @NotBlank(message = "Please pro")
    @Size(min = 2, max=100, message = "safasf")
    @Column(nullable = false)
    private String name;
    
    private String phone;
    @Email(message = "pleae")
    private String email;
}
