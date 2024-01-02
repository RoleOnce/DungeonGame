package com.malcolm.Labboration;
import java.sql.*;

public class DBConnection {

    private String URL = "jdbc:mariadb://localhost:3306/DungeonGame";
    private String USER = "root";
    private String password = "RovierDa1";

    Connection connection;

    // TODO  ===>> KLAR <<===
    public void open() {
        try {
            connection = DriverManager.getConnection(URL, USER, password);
            System.out.println("***** Database connected *****");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO  ===>> KLAR <<===
    public void closeConnection() {

        try {
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO  ===>> KLAR <<===
    public void createTablePlayer() {
        open();
        String sql = "CREATE TABLE player (playerID INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(60), " +
                "health INT, " +
                "strength INT, " +
                "intelligence INT," +
                "agility INT, " +
                "experience INT, " +
                "level INT, " +
                "basedamage INT, " + "primary KEY(playerID))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }

    }
    // TODO  ===>> KLAR <<===
    public void createTableMonster() {
        open();
        String sql = "CREATE TABLE monster (monsterID INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(100), " +
                "strength INT, " +
                "health INT, " +
                "agility INT, " +
                "basedamage INT, " +
                "primary KEY(monsterID))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // TODO  ===>> KLAR <<===
    public int createPlayer(Player player) {

        open();
        int incrementID = 0;
        String sql = "INSERT INTO player (name, health, strength, intelligence, agility, experience, level, basedamage) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(2, player.getHealth());
            preparedStatement.setInt(3, player.getStrength());
            preparedStatement.setInt(4, player.getIntelligence());
            preparedStatement.setInt(5, player.getAgility());
            preparedStatement.setInt(6, player.getExperience());
            preparedStatement.setInt(7, player.getLevel());
            preparedStatement.setInt(8, player.getBaseDamage());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                incrementID = generatedKeys.getInt(1);
                player.setPlayerID(incrementID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incrementID;
    }


    public int createMonster(Monster monster) {

        open();
        int incrementID = 0;
        String sql = "INSERT INTO monster (name, health, strength, agility, basedamage) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, monster.getName());
            preparedStatement.setInt(2, monster.getHealth());
            preparedStatement.setInt(3, monster.getStrength());
            preparedStatement.setInt(4, monster.getAgility());
            preparedStatement.setInt(5, monster.getBaseDamage());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                incrementID = generatedKeys.getInt(1);
                monster.setMonsterID(incrementID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }

        return incrementID;
    }

    // TODO  ===>> KLAR <<===
    public int levelUP(Player player) {
        open();
        String sql = "UPDATE player set level = ?, health = ?, strength = ?, basedamage = ?, agility = ?, intelligence = ?, experience = ? WHERE PlayerID = ?";
        int affectedRows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getLevel());
            preparedStatement.setInt(2, player.getHealth());
            preparedStatement.setInt(3, player.getStrength());
            preparedStatement.setInt(4, player.getBaseDamage());
            preparedStatement.setInt(5, player.getAgility());
            preparedStatement.setInt(6,  player.getIntelligence());
            preparedStatement.setInt(7, player.getExperience());
            preparedStatement.setInt(8, player.getPlayerID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return affectedRows;
    }



    // TODO  ===>> KLAR <<===
    public void showSavedPlayers() {
        open();
        String sql = "SELECT playerID, name, level FROM player";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(String.format("| %-10s | %-20s | %-10s |", "playerID", "name", "level"));

            while (resultSet.next()) {
                int playerID = resultSet.getInt("playerID");
                String playerName = resultSet.getString("name");
                int playerLevel = resultSet.getInt("level");

                String formRow = String.format("| %-10d | %-20s | %-10d |", playerID, playerName, playerLevel);
                System.out.println(formRow);
                System.out.println("|------------|----------------------|------------|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("CHOOSE BY PLAYERID");
            closeConnection();
        }
    }

    // TODO - EN METOD FÖR ATT välja SPARADE SPELARE
    public void selectPlayer(Player player, int getID){
        open();
        String sql = "SELECT playerID, name, health, level, strength, intelligence, agility, experience, basedamage FROM player where PlayerID = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, getID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int playerID = resultSet.getInt("playerId");
                String playerName = resultSet.getString("name");
                int playerHealth = resultSet.getInt("health");
                int playerLevel = resultSet.getInt("level");
                int playerStr = resultSet.getInt("strength");
                int playerInt = resultSet.getInt("intelligence");
                int playerAgi = resultSet.getInt("agility");
                int playerExp = resultSet.getInt("experience");
                int playerBas = resultSet.getInt("basedamage");

                player.setPlayerID(playerID);
                player.setName(playerName);
                player.setHealth(playerHealth);
                player.setLevel(playerLevel);
                player.setStrength(playerStr);
                player.setAgility(playerAgi);
                player.setIntelligence(playerInt);
                player.setExperience(playerExp);
                player.setBaseDamage(playerBas);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
