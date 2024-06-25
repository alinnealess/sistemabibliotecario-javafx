package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.MainApp;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import dao.BancoDAO;

import java.util.List;

public class ReturnBookViewController {

    @FXML
    private TextField cpfField;
    @FXML
    private TextField tituloField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleReturnBook() {
        String cpf = cpfField.getText();
        String titulo = tituloField.getText();

        if (cpf == null || cpf.isEmpty() || titulo == null || titulo.isEmpty()) {
            showAlert("Erro", "Campos vazios", "Por favor, preencha todos os campos.");
            return;
        }

        Usuario usuario = null;
        for (Usuario u : BancoDAO.getInstance().buscarUsuarios()) {
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
        for (Livro l : BancoDAO.getInstance().buscarLivros()) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                livro = l;
                break;
            }
        }

        if (livro == null) {
            showAlert("Erro", "Livro não encontrado", "Nenhum livro com o título fornecido foi encontrado.");
            return;
        }

        List<Emprestimo> emprestimos = BancoDAO.getInstance().buscarEmprestimosAtivos();
        Emprestimo emprestimo = null;

        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().equals(usuario) && e.getLivro().equals(livro)) {
                emprestimo = e;
                break;
            }
        }

        if (emprestimo == null) {
            showAlert("Erro", "Empréstimo não encontrado", "Nenhum empréstimo ativo para o usuário e livro fornecidos foi encontrado.");
            return;
        }

        BancoDAO.getInstance().removerEmprestimo(emprestimo);
        livro.setQtdEstoque(livro.getQtdEstoque() + 1);
        BancoDAO.getInstance().salvarDados();

        showAlert("Sucesso", "Devolução realizada", "A devolução foi realizada com sucesso.");
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
