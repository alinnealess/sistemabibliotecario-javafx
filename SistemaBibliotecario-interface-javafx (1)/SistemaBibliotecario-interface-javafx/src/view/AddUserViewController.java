package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.MainApp;
import model.*;
import dao.BancoDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddUserViewController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField matriculaField;
    @FXML
    private TextField dataNascimentoField;
    @FXML
    private TextField tipoUsuarioField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleAddUser() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String matricula = matriculaField.getText();
        String dataNascimento = dataNascimentoField.getText();
        String tipoUsuarioStr = tipoUsuarioField.getText();

        if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty() || matricula == null || matricula.isEmpty() || dataNascimento == null || dataNascimento.isEmpty() || tipoUsuarioStr == null || tipoUsuarioStr.isEmpty()) {
            showAlert("Erro", "Campos vazios", "Por favor, preencha todos os campos.");
            return;
        }

        Date dataNascimentoDate;
        try {
            dataNascimentoDate = new SimpleDateFormat("dd-MM-yyy").parse(dataNascimento);
        } catch (ParseException e) {
            showAlert("Erro", "Data inválida", "Por favor, insira uma data de nascimento válida (formato: dd-MM-yyy).");
            return;
        }

        int tipoUsuario;
        try {
            tipoUsuario = Integer.parseInt(tipoUsuarioStr);
        } catch (NumberFormatException e) {
            showAlert("Erro", "Tipo de usuário inválido", "Por favor, insira um número válido para o tipo de usuário (1, 2 ou 3).");
            return;
        }

        Usuario usuario;
        switch (tipoUsuario) {
            case 1:
                usuario = new Estudante(nome, cpf, matricula, dataNascimento);
                break;
            case 2:
                usuario = new Professor(nome, cpf, matricula, dataNascimento);
                break;
            case 3:
                usuario = new Bibliotecario(nome, cpf, matricula, dataNascimento, "defaultLogin", "defaultPassword");
                break;
            default:
                showAlert("Erro", "Tipo de usuário inválido", "Por favor, insira um número válido para o tipo de usuário (1, 2 ou 3).");
                return;
        }

        BancoDAO.getInstance().adicionarUsuario(usuario);
        showAlert("Sucesso", "Usuário Adicionado", "O usuário foi adicionado com sucesso.");
        mainApp.showMainView();
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
