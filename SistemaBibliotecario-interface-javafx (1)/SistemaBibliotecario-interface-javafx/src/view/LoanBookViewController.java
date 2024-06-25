package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.MainApp;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import dao.BancoDAO;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

public class LoanBookViewController {

    @FXML
    private TextField cpfField;
    @FXML
    private TextField tituloField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleLoanBook() {
        String cpf = cpfField.getText();
        String titulo = tituloField.getText();

        if (cpf == null || cpf.isEmpty() || titulo == null || titulo.isEmpty()) {
            showAlert("Erro", "Campos vazios", "Por favor, preencha todos os campos.");
            return;
        }

        BancoDAO bancoDAO = BancoDAO.getInstance();
        Usuario usuario = null;
        for (Usuario u : bancoDAO.buscarUsuarios()) {
            if (u.getCpf().equals(cpf)) {
                usuario = u;
                break;
            }
        }

        if (usuario == null) {
            showAlert("Erro", "Usuário não encontrado", "Nenhum usuário com o CPF fornecido foi encontrado.");
            return;
        }

        Livro livro = null;
        for (Livro l : bancoDAO.buscarLivros()) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                livro = l;
                break;
            }
        }

        if (livro == null) {
            showAlert("Erro", "Livro não encontrado", "Nenhum livro com o título fornecido foi encontrado.");
            return;
        }

        if (livro.getQtdEstoque() <= 0) {
            showAlert("Erro", "Estoque insuficiente", "Não há exemplares suficientes do livro para empréstimo.");
            return;
        }

        // Verificar quantos empréstimos o usuário já possui
        List<Emprestimo> emprestimosUsuario = bancoDAO.buscarEmprestimosAtivos().stream()
                .filter(e -> e.getUsuario().getCpf().equals(cpf))
                .toList();

        if (emprestimosUsuario.size() >= usuario.getLimiteEmprestimos()) {
            showAlert("Erro", "Limite de empréstimos excedido", "O usuário já atingiu o limite de empréstimos.");
            return;
        }

        Date dataEmprestimo = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataEmprestimo);
        cal.add(Calendar.DAY_OF_MONTH, usuario.getPrazoEmprestimo());
        Date dataDevolucaoPrevista = cal.getTime();

        Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucaoPrevista);
        bancoDAO.adicionarEmprestimo(emprestimo);
        livro.setQtdEstoque(livro.getQtdEstoque() - 1);
        bancoDAO.salvarDados();

        showAlert("Sucesso", "Empréstimo realizado", "O empréstimo foi realizado com sucesso.");
    }

    @FXML
    private void handleReturnToTheMenu() {
        mainApp.showMainView();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
