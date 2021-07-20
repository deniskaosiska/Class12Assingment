
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Excercise1 {
    private static final String USER_NAME = "sql6426362";
    private static final String DATABASE_NAME = "sql6426362";
    private static final String PASSWORD = "EIL5tsyzPV";
    private static final String PORT = "3306";
    private static final String SERVER = "sql6.freemysqlhosting.net";



    public static void main(String[] args) throws SQLException {
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT, USER_NAME, PASSWORD);

       // createTable(con);
//        insertDog(con, 3, "rox", "Boxed");
//        insertDog(con, 4, "fex", "Akita");
//        insertDog(con, 5, "rex", "Barbet");
        //getTableContent(con);
        //deleteDogsByName(con, "rex");
        //updateAge(con,10, "fex");
        con.close();
    }
    private static void createTable(Connection con) throws SQLException {


        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`dogs`(`age` INT NOT NULL,`name` VARCHAR(40) NOT NULL, `breed` VARCHAR(30) NOT NULL);";


        con.createStatement().execute(statementToExecute);
    }

    private static void insertDog(Connection con, int age, String name, String breed) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".dogs (`age`, `name`, `breed`) VALUES ('" + age + "', '" + name + "', '" + breed + "');";
        con.createStatement().execute(statementToExecute);
    }

    private static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".dogs;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while(rs.next()){

            int age  = rs.getInt("age");
            String name = rs.getString("name");
            String breed = rs.getString("breed");


            System.out.print("Age: " + age);
            System.out.print(", Name: " + name);
            System.out.print(", Breed: " + breed);
        }
        rs.close();
    }
    private static void deleteDogsByName(Connection con, String name) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`dogs` WHERE `name`='"+name+"';";
        con.createStatement().execute(statementToExecute);
    }

    private static void updateAge(Connection con, int age, String name ) throws SQLException {
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`dogs` SET `age`='"+age+"' WHERE `name`='"+name+"';";
        con.createStatement().executeUpdate(statementToExecute);
    }
}
