import java.sql.*;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static String name, email;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String db_url = "jdbc:mysql://127.0.0.1/android";
        String db_login = "root";
        String db_pass = "";
        try {
            /*System.out.println("Введите имя:");
            String name = scanner.nextLine();*/
            System.out.println("Введите E-mail:");
            String email = scanner.nextLine();
            System.out.println("Введите пароль: ");
            String pass = scanner.nextLine();
            /* UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();*/
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(db_url, db_login, db_pass);
            Statement statement = connection.createStatement();
            //statement.execute("INSERT INTO `users` (`name`, `email`, `pass`, `uuid`) VALUES ('"+name+"','"+email+"','"+pass+"','"+uuidString+"')");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE `email`='"+email+"' AND `pass`='"+pass+"'");
            if (resultSet.next()) {
                System.out.println("Доступ разрешен!");
            } else {
                System.out.println("Доступ запрещен!");
            }
            String emailInDB = resultSet.getString("email");
            String passInDB = resultSet.getString("pass");
            connection.close();
            /*while (true){
                if(email.equals(emailInDB) && pass.equals(passInDB)){
                    System.out.println("Доступ разрешен!");
                    break;
                }else{
                    System.out.println("Доступ запрещен!");
                }
            }*/

            /*while (resultSet.next()){
                int id = resultSet.getInt("id");
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                System.out.println("id: "+id+"\nname: "+name+"\nemail: "+email);
                System.out.println("--------------------------");
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
