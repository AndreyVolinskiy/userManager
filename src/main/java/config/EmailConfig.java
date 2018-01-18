package config;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailConfig {

    private static final Integer PORT = 587;

    /**
     * @return configured {@link JavaMailSenderImpl}.
     */
    public static JavaMailSenderImpl mailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(PORT);
        javaMailSender.setUsername("summerjava570@gmail.com");
        javaMailSender.setPassword("Cryptography");
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
        return javaMailSender;
    }
}
