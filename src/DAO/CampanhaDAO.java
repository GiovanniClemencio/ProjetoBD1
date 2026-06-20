/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Classes.Campanha;
import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Luan
 */
public class CampanhaDAO {

    // Inserção
    public void inserir(Campanha campanha) {

        String sql = "INSERT INTO campanha(idCampanha, nome) VALUES (?, ?)";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, campanha.getIdCampanha());
            stmt.setString(2, campanha.getNome());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
