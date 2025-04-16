package br.com.fiap.bank_project_jl.dto;

import br.com.fiap.bank_project_jl.domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String nome;
    private String email;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .nome(user.getName())
                .email(user.getEmail())
                .build();
    }
}
