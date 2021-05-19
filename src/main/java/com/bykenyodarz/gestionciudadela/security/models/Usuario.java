package com.bykenyodarz.gestionciudadela.security.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String idUsuario;

    @NotBlank
    @Size(max = 60)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String usuario;

    @NotBlank
    @Size(max = 80)
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Role> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String idUsuario, String nombre, String usuario, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
    }
}
