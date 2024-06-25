package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import model.Livro;
import dao.BancoDAO;

public class RemoveBookViewController {

    @FXML
    private TextField tituloRemoverField;

    private MainApp mainApp;
    private Stage dialogStage;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleRemoveBook() {
        String titulo = tituloRemoverField.getText();

        if (titulo == null || titulo.isEmpty()) {
            showAlert("Erro", "Campo de título vazio", "Por favor, insira o título do livro a ser removido.");
        } else {
            Livro livro = BancoDAO.getInstance().buscarLivros().stream()
                    .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                    .findFirst()
                    .orElse(null);

            if (livro != null) {
                BancoDAO.getInstance().removerLivro(livro);
                showAlert("Sucesso", "Livro Removido", "O livro foi removido com sucesso.");
                dialogStage.close();
            } else {
                showAlert("Erro", "Livro Não Encontrado", "Nenhum livro com o título fornecido foi encontrado.");
            }
        }
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
