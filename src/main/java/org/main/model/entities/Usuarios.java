package org.main.model.entities;

import java.time.Year;

public class Usuarios {
    private int id;
    private String nome;
    private String matricula;
    private String senha;
    private String telefone;
    private TipoUsuario tipoUsuario; // ADMIN ou ALUNO

    public Usuarios(){}

    public Usuarios(int id, String nome,String matricula, String senha, String telefone, TipoUsuario tipoUsuario) {
        this.id = id;
        setSenha(senha);
        setNome(nome);
        this.matricula = matricula;

        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuarios(int id,String nome, String senha, TipoUsuario tipoUsuario) {
        this.id = id;
        setSenha(senha);
        setNome(nome);

        this.telefone = "Não Definido";
        this.tipoUsuario = tipoUsuario;
    }

    public Usuarios(String nome, String senha, String telefone, TipoUsuario tipoUsuario) {
        setSenha(senha);
        setNome(nome);

        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuarios(String nome, String senha, TipoUsuario tipoUsuario) {
        setSenha(senha);
        setNome(nome);

        this.telefone = "Não Definido";
        this.tipoUsuario = tipoUsuario;
    }

    //Construtor usado para atualizar cadastro com o parâmetro de telefone
    public Usuarios(int id,String nome, String senha, String telefone) {
        this.id = id;
        setSenha(senha);
        setNome(nome);

        this.telefone = telefone;
    }

    //Construtor usado para atualizar cadastro sem o parâmetro de telefone
    public Usuarios(int id,String nome, String senha) {
        this.id = id;
        setSenha(senha);
        setNome(nome);

        this.telefone = "Não Definido";
    }

    //
    public Usuarios(int id, String nome, String matricula, String telefone,TipoUsuario tipoUsuario ) {
        this.id = id;
        setNome(nome);
        this.matricula = matricula;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome não pode estar vazio.");
        }

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setSenha(String senha) {
        if ( senha == null || senha.length() < 6) {
            throw new IllegalArgumentException(
                    "A senha deve conter pelo menos de 6 caracteres.");
        }

        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public static String getMatricula(int id) {
        int anoAtual = Year.now().getValue();
        return anoAtual + String.format("%04d", id);
    }

    public String exibirPerfil() {
        return """
           ==================
           PERFIL
           ==================
           Nome: %s
           Matrícula: %s
           Telefone: %s
           Tipo: %s
           """.formatted(
                nome,
                getMatricula(id),
                telefone,
                tipoUsuario);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + getMatricula(id) + '\'' +
                '}';
    }
}
