package main;

import dao.BancoDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.*;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema Bibliotecário");
        showLoginView();
    }

    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/LoginView.fxml"));
            AnchorPane loginPage = loader.load();

            Scene scene = new Scene(loginPage);
            primaryStage.setScene(scene);


            LoginViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
    }
    }

    public void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/MainView.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            primaryStage.setScene(scene);


            MainViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddBookView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/AddBookView.fxml"));
            AnchorPane addBookPage = loader.load();

            Scene scene = new Scene(addBookPage);
            primaryStage.setScene(scene);

            AddBookViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRemoveBookView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RemoveBookView.fxml"));
            AnchorPane removeBookPage = loader.load();

            Scene scene = new Scene(removeBookPage);
            primaryStage.setScene(scene);

            RemoveBookViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSearchBookByTitleView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/SearchBookByTitleView.fxml"));
            AnchorPane searchBookByTitleView = (AnchorPane) loader.load();

            Scene scene = new Scene(searchBookByTitleView);
            primaryStage.setScene(scene);

            SearchBookByTitleViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSearchBookByAuthorView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/SearchBookByAuthorView.fxml"));
            AnchorPane searchBookByAuthorView = (AnchorPane) loader.load();

            Scene scene = new Scene(searchBookByAuthorView);
            primaryStage.setScene(scene);

            SearchBookByAuthorViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoanBookView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/LoanBookView.fxml"));
            AnchorPane loanBookView = (AnchorPane) loader.load();

            Scene scene = new Scene(loanBookView);
            primaryStage.setScene(scene);

            LoanBookViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showReturnBookView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/ReturnBookView.fxml"));
            AnchorPane returnBookView = (AnchorPane) loader.load();

            Scene scene = new Scene(returnBookView);
            primaryStage.setScene(scene);

            ReturnBookViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddUserView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/AddUserView.fxml"));
            AnchorPane addUserView = (AnchorPane) loader.load();

            Scene scene = new Scene(addUserView);
            primaryStage.setScene(scene);
            primaryStage.show();

            AddUserViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRemoveUserView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RemoveUserView.fxml"));
            AnchorPane removeUserView = loader.load();

            Scene scene = new Scene(removeUserView);
            primaryStage.setScene(scene);
            primaryStage.show();

            RemoveUserViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCheckUserStatusView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/CheckUserStatusView.fxml"));
            AnchorPane checkUserStatusView = (AnchorPane) loader.load();

            Scene scene = new Scene(checkUserStatusView);
            primaryStage.setScene(scene);
            primaryStage.show();

            CheckUserStatusViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showListUsersView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/UserListView.fxml"));
            AnchorPane listUsersView = loader.load();

            Scene scene = new Scene(listUsersView);
            primaryStage.setScene(scene);
            primaryStage.show();

            UserListViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setUserData(FXCollections.observableArrayList(BancoDAO.getInstance().buscarUsuarios()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showListBooksView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/BookListView.fxml"));
            AnchorPane listBooksView = loader.load();

            Scene scene = new Scene(listBooksView);
            primaryStage.setScene(scene);
            primaryStage.show();

            BookListViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setBookData(FXCollections.observableArrayList(BancoDAO.getInstance().buscarLivros()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
