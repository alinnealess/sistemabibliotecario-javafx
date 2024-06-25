package view;

import javafx.fxml.FXML;
import main.MainApp;

public class MainViewController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleAddBook() {
        mainApp.showAddBookView();
    }

    @FXML
    private void handleRemoveBook() {
        mainApp.showRemoveBookView();
    }

    @FXML
    private void handleSearchBookByTitle() {
        mainApp.showSearchBookByTitleView();
    }

    @FXML
    private void handleSearchBookByAuthor() {
        mainApp.showSearchBookByAuthorView();
    }

    @FXML
    private void handleLoanBook() {
        mainApp.showLoanBookView();
    }

    @FXML
    private void handleReturnBook() {
        mainApp.showReturnBookView();
    }

    @FXML
    private void handleAddUser() {
        mainApp.showAddUserView();
    }

    @FXML
    private void handleRemoveUser() {
        mainApp.showRemoveUserView();
    }

    @FXML
    private void handleCheckUserStatus() {
        mainApp.showCheckUserStatusView();
    }

    @FXML
    private void handleListUsers() {
        mainApp.showListUsersView();
    }

    @FXML
    private void handleListBooks() {
        mainApp.showListBooksView();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
