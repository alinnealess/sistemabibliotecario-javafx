package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainApp;
import model.Livro;

public class BookListViewController {

    @FXML
    private TableView<Livro> bookTable;
    @FXML
    private TableColumn<Livro, String> titleColumn;
    @FXML
    private TableColumn<Livro, String> authorColumn;
    @FXML
    private TableColumn<Livro, String> subjectColumn;
    @FXML
    private TableColumn<Livro, Integer> yearColumn;
    @FXML
    private TableColumn<Livro, Integer> stockColumn;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setBookData(ObservableList<Livro> bookData) {
        bookTable.setItems(bookData);
    }

    @FXML
    private void handleReturnToTheMenu() {
        mainApp.showMainView();
    }

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("assunto"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("qtdEstoque"));
    }
}
