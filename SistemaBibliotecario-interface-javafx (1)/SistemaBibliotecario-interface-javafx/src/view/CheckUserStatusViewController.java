package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainApp;
import dao.BancoDAO;
import model.Emprestimo;
import model.Usuario;

public class CheckUserStatusViewController {

    @FXML
    private TextField cpfField;

    @FXML
    private TableView<Emprestimo> loanTable;

    @FXML
    private TableColumn<Emprestimo, String> livroColumn;

    @FXML
    private TableColumn<Emprestimo, String> dataDevolucaoColumn;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        livroColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLivro().getTitulo()));
        dataDevolucaoColumn.setCellValueFactory(new PropertyValueFactory<>("dataDevolucaoPrevista"));
    }

//    @FXML
//    private void initialize() {
//        livroColumn.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("livro"));
//        dataDevolucaoColumn.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("dataDevolucaoPrevista"));
//    }

//    @FXML
//    private void handleCheckUserStatus() {
//        String cpf = cpfField.getText();
//
//        if (cpf == null || cpf.isEmpty()) {
//            showAlert("Erro", "Campo vazio", "Por favor, preencha o CPF.");
//            return;
//        }
//
//        BancoDAO bancoDAO = BancoDAO.getInstance();
//        Usuario usuario = bancoDAO.buscarUsuarios().stream()
//                .filter(u -> u.getCpf().equals(cpf))
//                .findFirst()
//                .orElse(null);
//
//        if (usuario == null) {
//            showAlert("Erro", "Usuário não encontrado", "Nenhum usuário encontrado com o CPF fornecido.");
//        } else {
//            ObservableList<Emprestimo> emprestimos = FXCollections.observableArrayList(
//                    bancoDAO.buscarEmprestimosAtivos().stream()
//                            .filter(e -> e.getUsuario().getCpf().equals(cpf))
//                            .toList()
//            );
//            loanTable.setItems(emprestimos);
//        }
//    }

    @FXML
    private void handleCheckUserStatus() {
        String cpf = cpfField.getText();

        if (cpf == null || cpf.isEmpty()) {
            showAlert("Erro", "Campo vazio", "Por favor, preencha o CPF.");
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
            showAlert("Erro", "Usuário não encontrado", "Nenhum usuário encontrado com o CPF fornecido.");
        } else {
            ObservableList<Emprestimo> emprestimos = FXCollections.observableArrayList();
            for (Emprestimo e : bancoDAO.buscarEmprestimosAtivos()) {
                if (e.getUsuario().getCpf().equals(cpf)) {
                    emprestimos.add(e);
                }
            }
            loanTable.setItems(emprestimos);
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
