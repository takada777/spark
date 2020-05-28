package jp.co.scc_kk.kensyu.new_employee_training_framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class EmpDao {
    String database = "jdbc:postgresql://localhost:5432/training";
    String user = "training";
    String pass = "training";
    employeeEntity eEntity = null;



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

    public EmpArrayBean outputEmp() throws SQLException {

        ResultSet rs = null;
        this.open();

        statement = connection.prepareStatement("SELECT * FROM syain order by syainno");


        rs = statement.executeQuery();
        EmpArrayBean EABean = new EmpArrayBean();
        while (rs.next()) { // 「DBを参照する例」④
            eEntity = new employeeEntity();
            eEntity.setSyainno(rs.getString(employeeParameter.syainno));
            eEntity.setName(rs.getString(employeeParameter.name));
            eEntity.setDepartment(rs.getString(employeeParameter.department));
            EABean.addEmpArray(eEntity);

        }
        // ↑↑↑ DBに接続する例 ↑↑↑

        return EABean;



    }



    public void EmpRegister(int syainno, String name, String department) {

        ResultSet rs = null;
        this.open();
        try {
            statement = connection.prepareStatement("insert into syain values(?,?,?)");
            statement.setInt(1, syainno);
            statement.setString(2, name);
            statement.setString(3, department);
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



    public void empdelete(int syainno) {
        ResultSet rs = null;
        this.open();
        try {
            statement = connection.prepareStatement("delete from syain where syainno=?");
            statement.setInt(1, syainno);

            int num = statement.executeUpdate();
            rs = statement.executeQuery();

            if (rs.next()) {



            }



        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }


    }



    public void Update(int syainno, String name, String department, int beforesyainno) {
        ResultSet rs = null;
        this.open();
        try {
            statement = connection.prepareStatement("update syain set syainno=?,name=?,department=? where syainno=?");
            statement.setInt(1, syainno);
            statement.setString(2, name);
            statement.setString(3, department);
            statement.setInt(4, beforesyainno);

            int num = statement.executeUpdate();
            rs = statement.executeQuery();

            if (rs.next()) {



            }



        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }


    }


    public EmpArrayBean SerchEmp(int syainno) throws SQLException {

        ResultSet rs = null;
        this.open();

        statement = connection.prepareStatement("SELECT * FROM syain where syainno=? ");

        statement.setInt(1, syainno);
        rs = statement.executeQuery();
        EmpArrayBean EABean = new EmpArrayBean();

        while (rs.next()) { // 「DBを参照する例」④
            eEntity = new employeeEntity();
            eEntity.setSyainno(rs.getString(employeeParameter.syainno));
            eEntity.setName(rs.getString(employeeParameter.name));
            eEntity.setDepartment(rs.getString(employeeParameter.department));
            EABean.addEmpArray(eEntity);
            System.out.println(syainno);

        }
        // ↑↑↑ DBに接続する例 ↑↑↑

        return EABean;



    }


}


