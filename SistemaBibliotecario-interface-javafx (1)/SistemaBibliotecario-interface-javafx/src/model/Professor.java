package model;


public class Professor  extends Usuario{
    private String departamento;

    public Professor() {
    }

    public Professor(String nome, String cpf, String matricula, String dataNascimento) {
        super(nome, cpf, matricula, dataNascimento);
    }

    @Override
    public String getTipoUsuario() {
        return "Professor";
    }

    public Professor(String nome, String cpf, String matricula, String dataNascimento, String departamento) {
        super(nome, cpf, matricula, dataNascimento);
        this.departamento = departamento;
    }


    @Override
    public int getLimiteEmprestimos() {
        return 5;
    }

    @Override
    public int getPrazoEmprestimo() {
        return 30;
    }
}
