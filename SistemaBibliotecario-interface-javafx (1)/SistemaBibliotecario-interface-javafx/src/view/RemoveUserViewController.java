package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.MainApp;
import dao.BancoDAO;
import model.Usuario;

public class RemoveUserViewController {

    @FXML
    private TextField cpfField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleRemoveUser() {
        String cpf = cpfField.getText();

        if (cpf == null || cpf.isEmpty()) {
            showAlert("Erro", "Campo vazio", "Por favor, preencha o CPF.");
            return;
        }

        BancoDAO bancoDAO = BancoDAO.getInstance();
        Usuario usuario = bancoDAO.buscarUsuarios().stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            showAlert("Erro", "Usuário não encontrado", "Nenhum usuário encontrado com o CPF fornecido.");
        } else {
            bancoDAO.removerUsuario(usuario);
            showAlert("Sucesso", "Usuário removido", "O usuário foi removido com sucesso.");
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
