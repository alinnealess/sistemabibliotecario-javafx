package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainApp;
import model.Livro;
import dao.BancoDAO;

public class SearchBookByTitleViewController {

    @FXML
    private TextField tituloField;
    @FXML
    private TableView<Livro> bookTable;
    @FXML
    private TableColumn<Livro, String> tituloColumn;
    @FXML
    private TableColumn<Livro, String> autorColumn;
    @FXML
    private TableColumn<Livro, Integer> qtdEstoqueColumn;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        tituloColumn.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        qtdEstoqueColumn.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("qtdEstoque"));

    }

    @FXML
    private void handleSearchBookByTitle() {
        String titulo = tituloField.getText();

        if (titulo == null || titulo.isEmpty()) {
            showAlert("Erro", "Campo de título vazio", "Por favor, insira o título do livro a ser pesquisado.");
        } else {
            ObservableList<Livro> livrosEncontrados = FXCollections.observableArrayList();
            for (Livro livro : BancoDAO.getInstance().buscarLivros()) {
                if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                    livrosEncontrados.add(livro);
                }
            }
            if (livrosEncontrados.isEmpty()) {
                showAlert("Erro", "Livro Não Encontrado", "Nenhum livro com o título fornecido foi encontrado.");
            } else {
                bookTable.setItems(livrosEncontrados);
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
