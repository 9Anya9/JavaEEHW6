package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "permission", unique = true)
    private Permission permission;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


}
