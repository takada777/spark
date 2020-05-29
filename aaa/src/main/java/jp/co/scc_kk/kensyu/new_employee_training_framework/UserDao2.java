package jp.co.scc_kk.kensyu.new_employee_training_framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao2 {
    String database = "jdbc:postgresql://localhost:5432/training";
    String user = "training";
    String pass = "training";
    UserEntity uEntity = null;



    // ↓↓↓ DBに接続する例 ↓↓↓

    // conn = DriverManager.getConnection(database, user, pass); // 「DBに接続する例」①
    private Connection connection;
    private PreparedStatement statement = null;



    public void open() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(database, user, pass);
            } catch (ClassNotFoundException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
            }
        }
        statement = null;
    }

    public boolean login(String userid, String passwd) {

        ResultSet rs = null;
        this.open();
        try {
            statement = connection.prepareStatement("SELECT * FROM systemuser WHERE userid = ? AND password = ? ");
            statement.setString(1, userid);
            statement.setString(2, passwd);

            rs = statement.executeQuery();

            if (rs.next()) {



                return true;
            } else {
                return false;

            }



        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return false;
    }



    public UserEntity rolecheck(String userid, String passwd) {

        ResultSet rs = null;
        this.open();
        try {
            statement = connection.prepareStatement("SELECT * FROM systemuser WHERE userid = ? AND password = ? ");
            statement.setString(1, userid);
            statement.setString(2, passwd);

            rs = statement.executeQuery();

            if (rs.next()) {
                uEntity = new UserEntity();
                uEntity.setRole(rs.getString("role"));
                uEntity.setUsername(rs.getString("name"));


            } else {


            }



        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return uEntity;
    }



    public UserArrayBean outputUser() throws SQLException {

        ResultSet rs = null;
        this.open();

        statement = connection.prepareStatement("SELECT * FROM systemuser order by userid");


        rs = statement.executeQuery();
        UserArrayBean UABean = new UserArrayBean();
        while (rs.next()) {
            uEntity = new UserEntity();
            uEntity.setUserid(rs.getString("userid"));
            uEntity.setUsername(rs.getString("name"));
            uEntity.setRole(rs.getString("role"));
            uEntity.setPasswd(rs.getString("password"));


            UABean.addEmpArray(uEntity);

        }
        // ↑↑↑ DBに接続する例 ↑↑↑

        return UABean;



    }



    public void userdelete(String userid) {
        ResultSet rs = null;
        this.open();
        try {
            statement = connection.prepareStatement("delete from systemuser where userid=?");
            statement.setString(1, userid);

            int num = statement.executeUpdate();
            rs = statement.executeQuery();

            if (rs.next()) {



            }



        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }


    }



    public void UserRegister(String role, String name, String userid, String password) {

        ResultSet rs = null;
        this.open();
        try {
            statement = connection.prepareStatement("insert into systemuser values(?,?,?,?)");
            statement.setString(1, userid);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, role);
            int num = statement.executeUpdate();
            rs = statement.executeQuery();

            if (rs.next()) {
                // EmpBean eBean=new EmpBean();
                // eBean.setEmpfname(rs.getString(employeeParameter.empfname));



            }



        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }


    }

    public void UpdateUser(String role, String name, String userid, String password, String beforeuserid) {
        ResultSet rs = null;
        this.open();
        try {
            statement = connection
                    .prepareStatement("update systemuser set userid=?,name=?,password=?,role=? where userid=?");
            statement.setString(1, userid);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, role);
            statement.setString(5, beforeuserid);

            int num = statement.executeUpdate();
            rs = statement.executeQuery();

            if (rs.next()) {



            }



        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }


    }


    public UserArrayBean SerchUser(String userid) throws SQLException {

        ResultSet rs = null;
        this.open();

        statement = connection.prepareStatement("SELECT * FROM systemuser where userid=? ");

        statement.setString(1, userid);
        rs = statement.executeQuery();
        UserArrayBean UABean = new UserArrayBean();

        while (rs.next()) { // 「DBを参照する例」④
            uEntity = new UserEntity();
            uEntity.setUserid(rs.getString("userid"));
            uEntity.setUsername(rs.getString("name"));
            uEntity.setRole(rs.getString("role"));
            uEntity.setPasswd(rs.getString("password"));
            UABean.addEmpArray(uEntity);


        }
        // ↑↑↑ DBに接続する例 ↑↑↑

        return UABean;



    }



}


