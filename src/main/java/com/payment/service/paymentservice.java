package com.payment.service;


import com.payment.model.paymentmodel;

import java.sql.*;
import java.util.ArrayList;

public class paymentservice {
    Connection con;


    public paymentservice(){

        try {
            String url ="jdbc:mysql://localhost:3306/electrogride";
            String uname ="root";
            String pwd = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,uname,pwd);

        } catch(Exception e) {
            System.out.println(e +" data connection unsuccess.");
        }
    }


    public paymentmodel insertPayment(paymentmodel payment) {
        String insert = "insert into payment(paymentId,userId,date,monthlyUnit,amount) values(?,?,?,?,?) ";

        try {
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setLong(1, payment.getPaymentId());
            ps.setString(2, payment.getUserId());
            ps.setString(3, payment.getDate());
            ps.setLong(4, payment.getMonthlyUnit());
            ps.setLong(5, payment.getAmount());

            ps.execute();
        }catch(Exception e) {
            System.out.println(e +"data inserting unsuccess.");
        }

        return payment;

    }

    public ArrayList<paymentmodel> getPayment() throws SQLException {

        ArrayList<paymentmodel> data = new ArrayList<paymentmodel>();

        String select = "select * from payment";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            paymentmodel model = new paymentmodel();

            model.setPaymentId(rs.getInt("paymentId"));
            model.setUserId(rs.getString("userId"));
            model.setDate(rs.getString("date"));
            model.setMonthlyUnit(rs.getInt("monthlyUnit")); // column name
            model.setAmount(rs.getInt("amount"));

            data.add(model);

        }

        return data;

    }


    public ArrayList<paymentmodel> getPaymentById(int paymentId) throws SQLException{

        ArrayList<paymentmodel> data = new ArrayList<paymentmodel>();
        String select = "select * from payment where paymentId =?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,paymentId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            paymentmodel model = new paymentmodel();

            model.setPaymentId(rs.getInt("paymentId"));
            model.setUserId(rs.getString("userId"));
            model.setDate(rs.getString("date"));
            model.setMonthlyUnit(rs.getInt("monthlyUnit")); // column name
            model.setAmount(rs.getInt("amount"));


            data.add(model);
        }
        return data;
    }

    public paymentmodel updatePayment(paymentmodel payment) {
        String insert = "update payment set userId=?,date=? , monthlyUnit=? , amount=? where paymentId=?";

        try {
            PreparedStatement ps = con.prepareStatement(insert);


            ps.setString(1, payment.getUserId());
            ps.setString(2, payment.getDate());
            ps.setLong(3, payment.getMonthlyUnit());
            ps.setLong(4, payment.getAmount());
            ps.setLong(5, payment.getPaymentId());

            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println(e +"data update unsuccess.");
        }

        return payment;

    }


    public int deletePayment(int paymentId) {
        String insert = "delete from payment where paymentId =?";

        try {
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1,paymentId);

            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println(e +"data delete unsuccess.");
        }
        return paymentId;
    }
}
