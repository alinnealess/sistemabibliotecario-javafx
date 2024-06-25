package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainApp;
import model.Usuario;

public class UserListViewController {

    @FXML
    private TableView<Usuario> userTable;
    @FXML
    private TableColumn<Usuario, String> nameColumn;
    @FXML
    private TableColumn<Usuario, String> cpfColumn;
    @FXML
    private TableColumn<Usuario, String> matriculaColumn;
    @FXML
    private TableColumn<Usuario, String> tipoUsuarioColumn;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setUserData(ObservableList<Usuario> userData) {
        userTable.setItems(userData);
    }

    @FXML
    private void handleReturnToTheMenu() {
        mainApp.showMainView();
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        matriculaColumn.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tipoUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
    }
}
