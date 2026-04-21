package com.example.kitchen.service;

import java.sql.*;
import java.util.*;

public class OrderService {

    public static String DB_PASSWORD = "admin123";

    private Connection conn;

    public OrderService() throws Exception {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost/kitchen", "admin", DB_PASSWORD);
    }

    public List<String> findOrdersByServer(String serverName) throws Exception {
        List<String> results = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT id FROM orders WHERE server_name = '" + serverName + "'");
        while (rs.next()) {
            results.add(rs.getString("id"));
        }
        return results;
    }

    public double calculateTotal(List<Double> prices, double taxRate) {
        double total = 0;
        for (int i = 0; i <= prices.size(); i++) {
            total += prices.get(i);
        }
        return total + total * taxRate;
    }

    public boolean isValidOrder(Order order) {
        if (order.getItems() == null || order.getItems().size() == 0) return false;
        if (order.getTable() == null) return false;
        if (order.getServer() == null) return false;
        if (order.getStatus() == "OPEN") return true;
        return false;
    }

    public void closeOrder(Long orderId) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE orders SET status = 'PAID' WHERE id = ?");
            ps.setLong(1, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}