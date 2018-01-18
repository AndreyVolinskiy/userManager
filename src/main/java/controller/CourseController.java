package controller;

import config.EmailConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class CourseController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextArea txtAbout;
    @FXML
    private Button btnSave;

    private ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.addAll("Java Start", "Java OOP", "Java Professional");
        comboBox.setItems(list);


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("in new thread: " + Thread.currentThread().getName());
                btnSave.setOnAction(event -> {
                    saveToFile();
                    try {
                        fileToMail();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        }).start();
    }

    private void fileToMail() throws IOException {

        StringJoiner joiner = new StringJoiner("\n");
        String filePath = "src\\main\\resources\\Users.txt";
        final String email = "mamashka236@gmail.com";
        File file = new File(filePath);

        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text;
            while ((text = reader.readLine()) != null) {
                joiner.add(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new CourseController().sendMessage(joiner.toString(), email);
    }

    private void saveToFile() {

        String filePath = "src\\main\\resources\\Users.txt";
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String item = comboBox.getSelectionModel().getSelectedItem();
            writer.write("Course: " + String.valueOf(item) + "\n");
            writer.write("Name: " + String.valueOf(txtName.getText()) + "\n");
            writer.write("Email: " + String.valueOf(txtEmail.getText()) + "\n");
            writer.write("About: " + String.valueOf(txtAbout.getText()) + "\n");
            writer.write("\n");
//            comboBox.setSelectedItem(null);
            comboBox.setValue(null);
            txtName.setText("");
            txtEmail.setText("");
            txtAbout.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage (String text, String to){

        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(to);
        simpleMessage.setText(text);
        JavaMailSenderImpl mailSender = EmailConfig.mailSender();
        mailSender.send(simpleMessage);
    }
}