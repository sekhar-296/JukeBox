package Connection;

import org.jasypt.util.text.BasicTextEncryptor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class connectionSetup
{
    public  static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //Fetches the system properties
        String path=System.getProperty("user.dir");
//        System.out.println("working directory =" + path);

        //creating fileReader and specified the fileName
        FileReader reader=new FileReader(path + "/Connection/config.properties");

        Properties p=new Properties();

        //Reads a property list from the input byte Stream
        p.load(reader);
        privateDetails dt=new privateDetails();
        BasicTextEncryptor bte=new BasicTextEncryptor();

        // Getting key from details class object and
        // set the password for encryption and decryption
        bte.setPassword(dt.getKey());

        // Encrypt the message
        String encryptedid = bte.encrypt(dt.getUser());

        // Set the system property
        p.setProperty("username", encryptedid);
        BasicTextEncryptor bte1 = new BasicTextEncryptor();

        // Setting a password
        bte1.setPassword(dt.getKey2());

        // Encrypt the password
        String encryptedps = bte1.encrypt(dt.getPwd());
        p.setProperty("password", encryptedps);
        // Writes the property list in the properties table
        // to the output character stream in a format
        // suitable for using load method
        p.store(
                new FileWriter(path + "/Connection/config.properties"),
                " Properties Data");

        // Load the driver class into the memory at the
        // runtime
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establishes the connection and decrypt the
        // encryptedid and encryptedps
        //        System.out.println("Connection successful!!!");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jukebox",
                bte.decrypt(encryptedid),
                bte1.decrypt(encryptedps));
    }

}
