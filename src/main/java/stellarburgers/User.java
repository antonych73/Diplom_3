package stellarburgers;


import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class User {

    private static final int LENGHT = 10;
    private final static String EMAIL_SUFFIX = "@yandex.ru";

    private final String name;
    private final String password;
    private final String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }


    public static User getCorrectUser() {
        return new User(random(), random(), random() + EMAIL_SUFFIX);
    }

    public static User getWithoutPassword(String password) {
        return new User(random(), password, random() + EMAIL_SUFFIX);
    }

    public static String random() {
        return randomAlphabetic(LENGHT);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

