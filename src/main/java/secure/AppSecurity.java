package secure;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppSecurity {

    private static final String PASSWORD = "$2a$10$RqHrdcuPa0SHczIvlgTQTOf0Ur5P16wSo.7qXJKvMEJ1aZycZn0bG";

    public static boolean check(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, PASSWORD);
    }
//    public static void main(String[] args) {
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        for (int i = 0; i <5 ; i++) {
//            String encode = passwordEncoder.encode("hello");
//            System.out.println(encode);
//
//        }
//
//        String hash = "$2a$10$RqHrdcuPa0SHczIvlgTQTOf0Ur5P16wSo.7qXJKvMEJ1aZycZn0bG";
//        boolean matches = passwordEncoder.matches("hello", hash);
//        System.out.println(matches);

//    }
}
