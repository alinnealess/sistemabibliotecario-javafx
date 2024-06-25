package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.MainApp;
import model.Livro;
import dao.BancoDAO;

public class AddBookViewController {

    @FXML
    private TextField tituloField;
    @FXML
    private TextField autorField;
    @FXML
    private TextField assuntoField;
    @FXML
    private TextField anoLancamentoField;
    @FXML
    private TextField qtdEstoqueField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleAddBook() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String assunto = assuntoField.getText();
        String anoLancamento = anoLancamentoField.getText();
        String qtdEstoque = qtdEstoqueField.getText();

        if (titulo.isEmpty() || autor.isEmpty() || assunto.isEmpty() || anoLancamento.isEmpty() || qtdEstoque.isEmpty()) {
            showAlert("Campos obrigatórios", "Por favor, preencha todos os campos.");
        } else {
            try {
                int ano = Integer.parseInt(anoLancamento);
                int qtd = Integer.parseInt(qtdEstoque);
                Livro livro = new Livro(titulo, autor, assunto, ano, qtd);

                // Obtendo a instância singleton de BancoDAO e adicionando o livro
                BancoDAO bancoDAO = BancoDAO.getInstance();
                bancoDAO.adicionarLivro(livro);

                showAlert("Sucesso", "Livro adicionado com sucesso.");
                mainApp.showMainView();
            } catch (NumberFormatException e) {
                showAlert("Erro de formato", "Ano de lançamento e quantidade em estoque devem ser números.");
            } catch (Exception e) {
                showAlert("Erro", "Erro ao adicionar livro: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleReturnToTheMenu() {
        mainApp.showMainView();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
