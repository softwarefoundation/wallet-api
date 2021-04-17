package com.softwarefoundation.walletapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    @Length(min = 3, max = 50, message = "O campo nome deve conter entre 3 e 50 caracteres")
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    @NonNull
    @Length(min = 6, message = "O campo senha deve conter no mínimo 6 caracteres")
    private String senha;
}
