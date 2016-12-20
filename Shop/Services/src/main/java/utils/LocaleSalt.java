package utils;


import org.springframework.stereotype.Component;

@Component
public class LocaleSalt {

    static String localSalt;  // bean of Spring

    public static String getLocalSalt() {
        return localSalt;
    }

    public void setLocalSalt(String localSalt) {
        this.localSalt = localSalt;
    }


    private String hashPassword;

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }


    private String randomSalt;

    public String getRandomSalt() {
        return randomSalt;
    }

    public void setRandomSalt(String randomSalt) {
        this.randomSalt = randomSalt;
    }


    public void createHash(String plainPassword) {
        this.randomSalt = BCrypt.gensalt(10);
        this.hashPassword = BCrypt.hashpw(plainPassword + this.randomSalt + getLocalSalt(), this.getRandomSalt());
    }

    public LocaleSalt() {
    }


    public boolean checkPassword(String candidate_password, String randomSalt, String stored_hash) {
        return BCrypt.checkpw(candidate_password + randomSalt + getLocalSalt(), stored_hash);
    }



   /*
    public String getLocaleSalt() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("salt.ini")));

        return properties.getProperty("SOME_STRING_VALUE");

    }
    */
}