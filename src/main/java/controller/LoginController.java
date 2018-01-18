package controller;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import secure.AppSecurity;
import start.AppManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private PasswordField txtPass;
    @FXML
    private Text txtMessage;
    @FXML
    private Button btnOk;

    public static final String truePassword = "123";

    public void initialize(URL location, ResourceBundle resources) {

//        btnOk.setOnAction(event -> printPassword());
        btnOk.setOnAction(event -> {
            try {
                login();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void printPassword() {

//        txtMessage.setText(txtPass.getText());
        System.out.println(txtPass.getText());


//        new Thread(() -> {
//            txtMessage.setText(Thread.currentThread().getName() + " Ha-ha, your password was stolen!");
//            passwordToFile();
//        }).start();

    }

    private void passwordToFile() {

        String filePath = "src\\main\\resources\\view\\passwords.txt";
        File file = new File(filePath);

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
            writer.write(txtPass.getText());
//            txtMessage.setText("Ha-ha, your password was stolen!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void login() throws InterruptedException {
        if (AppSecurity.check(txtPass.getText())) {
            try {
                AppManager.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/course.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            txtMessage.setUnderline(true);
            txtMessage.setText("Wrong password!");
            txtPass.setText("");
        }


    }
}
