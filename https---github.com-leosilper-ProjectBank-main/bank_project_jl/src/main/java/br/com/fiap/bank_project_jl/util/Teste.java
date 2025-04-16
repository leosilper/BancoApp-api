package br.com.fiap.bank_project_jl.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Teste {
    private final String nome;

    public static void main(String[] args) {
        Teste t = new Teste("Leo");
        System.out.println(t.getNome());
    }
}
