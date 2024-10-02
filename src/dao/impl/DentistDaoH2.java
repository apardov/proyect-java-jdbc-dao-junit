package dao.impl;

import dao.BD;
import dao.IDao;
import model.Dentist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistDaoH2 implements IDao<Dentist> {

    private static final String SQL_INSERT = "INSERT INTO DENTIST (REGISTRATION, NAME, LASTNAME) VALUES (?,?,?)";

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM DENTIST WHERE ID = ?";

    private static final String SQL_UPDATE = "UPDATE DENTIST SET REGISTRATION = ?, NAME = ?, LASTNAME = ? WHERE ID = ?";

    private static final String SQL_DELETE = "DELETE FROM DENTIST WHERE ID = ?";

    private static final String SQL_ALL = "SELECT * FROM DENTIST";

    @Override
    public Dentist save(Dentist dentistInsert) {
        Connection conn = null;
        try {
//          DB CONNECTION
            conn = BD.getConnection();

//          INSERT VALUES                                                  RECOVER ID
            PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, dentistInsert.getRegistration());
            psInsert.setString(2, dentistInsert.getName());
            psInsert.setString(3, dentistInsert.getLastname());
            psInsert.execute();

//          SAVE KEY IN DENTIST ID
            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) dentistInsert.setId(rs.getInt(1));

        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.fillInStackTrace();
            }
        }
        return dentistInsert;
    }

    @Override
    public Dentist findById(Integer id) {
        Dentist dentistSelectById = null;
        try (Connection conn = BD.getConnection();
             PreparedStatement psmtSelect = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            psmtSelect.setInt(1, id);
            ResultSet rs = psmtSelect.executeQuery();
            while (rs.next()) {
                dentistSelectById =
                        new Dentist(rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3),
                                rs.getString(4));
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return dentistSelectById;
    }

    @Override
    public void update(Dentist dentistUpdate) {
        try (Connection conn = BD.getConnection();
             PreparedStatement psmtUpdate = conn.prepareStatement(SQL_UPDATE)) {

            psmtUpdate.setInt(1, dentistUpdate.getRegistration());
            psmtUpdate.setString(2, dentistUpdate.getName());
            psmtUpdate.setString(3, dentistUpdate.getLastname());
            psmtUpdate.setInt(4, dentistUpdate.getId());
            psmtUpdate.executeUpdate();

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection conn = BD.getConnection();
             PreparedStatement psmtDelete = conn.prepareStatement(SQL_DELETE)) {
            psmtDelete.setInt(1, id);
            psmtDelete.execute();

        } catch (Exception e) {
            e.fillInStackTrace();
        }

    }

    @Override
    public List<Dentist> findAll() {
        List<Dentist> allDentist = new ArrayList<>();
        try (Connection conn = BD.getConnection();
             PreparedStatement psmtSelectAll = conn.prepareStatement(SQL_ALL)) {
            ResultSet rs = psmtSelectAll.executeQuery();
            while (rs.next()) {
                Dentist dentist = new Dentist(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                allDentist.add(dentist);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return allDentist;
    }
}
