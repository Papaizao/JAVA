/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ifsp
 */
public class ProdutoDAO {
    private Connection conn;
    
    public ProdutoDAO() {
        Conexao c = new Conexao();
        this.conn = c.getConexao();
    }
    
    public void inserir(Produto p) throws Exception {
        String sql = "INSERT INTO produtos(nome, valor) VALUES (?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getValor());
            stmt.execute();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public List<Produto> getProdutos() throws Exception {
        List<Produto> lista = new ArrayList();
        
        String sql = "SELECT * FROM produtos";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto p = new Produto();
                
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getDouble("valor"));
                
                lista.add(p);
            }
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar produtos: " + e.getMessage());
        }
    }
    
    public Produto getProduto(int id) throws Exception
    {
        String sql = "SELECT * FROM produtos where id = ?";
        
        try 
        {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getDouble("valor"));
            
            return p;
        } 
        catch (Exception e) 
        {
            throw new Exception("Erro ao buscar produto: " + e.getMessage());
        }
    }
    public void atualizar(Produto p) throws Exception
    {
        String sql = "UPDATE produtos SET nome=?, valor=? WHERE id =?";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getValor());
            stmt.setInt(3, p.getId());
            stmt.execute();
        }
        catch (Exception e) 
        {
            throw new Exception(e.getMessage());
        }
    }
    public void excluir(Produto p) throws Exception
    {
         String sql = "DELETE from produtos WHERE id =?";
        
        try 
        {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, p.getId());            
            stmt.execute();   
            
        } 
        catch (Exception e) 
        {
            throw new Exception(e.getMessage());
        }
    }
    public List<Produto> getProdutosByNome(String nome)throws Exception
    {
        List<Produto> lista = new ArrayList();

            String sql = "SELECT * FROM produtos WHERE nome LIKE ?";

            try {
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1,"%" + nome + "%");
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Produto p = new Produto();

                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setValor(rs.getDouble("valor"));

                    lista.add(p);
                }
                return lista;
            } catch (Exception e) {
                throw new Exception("Erro ao buscar produtos: " + e.getMessage());
            }
    }
}
