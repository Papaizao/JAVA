/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1410;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author ifsp
 */
public class BuscarProdutos {
 
    public static void main(String[] args) {
        Conexao c = new Conexao();
        Connection conn = c.getConexao();
        
        String sql = "SELECT * FROM produtos";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("nome"));
                System.out.println(rs.getDouble("valor"));
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar produtos: " + e.getMessage());
        }
        
        
        //buscar pelo ID
        String buscarPeloId = "SELECT * FROM produtos WHERE id = 2";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(buscarPeloId);
            rs.first();
            
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("nome"));
            System.out.println(rs.getDouble("valor"));
        } catch (Exception e) {
            
        }
    }
}
