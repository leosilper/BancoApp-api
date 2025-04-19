package br.com.fiap.bank_project_jl.util;

public class Teste {
    private final String nome;
    
    // Construtor que substitui @RequiredArgsConstructor
    public Teste(String nome) {
        this.nome = nome;
    }
    
    // Getter que substitui @Data
    public String getNome() {
        return nome;
    }
    
    // Outros métodos de @Data (toString, equals, hashCode)
    @Override
    public String toString() {
        return "Teste{" +
                "nome='" + nome + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Teste teste = (Teste) o;
        
        return nome != null ? nome.equals(teste.nome) : teste.nome == null;
    }
    
    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }
    
    public static void main(String[] args) {
        Teste t = new Teste("Leo");
        System.out.println(t.getNome());
    }
}