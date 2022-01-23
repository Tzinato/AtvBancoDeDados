/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Conexao.conexao;
import Model.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class SiteDAO {
     public void inserirSite (Site s){
        try {
        String SQL = "INSERT INTO thales_zinato.site (url_site, nome_site, tipo_site) VALUES (?,?,?)";
        Connection minhaConexao = conexao.getConexao();
        PreparedStatement comando = minhaConexao.prepareStatement(SQL);
        comando.setString(1, s.getUrl_site());
        comando.setString(2, s.getNome_site());
        comando.setString(3, s.getTipo_site());
        int retorno = comando.executeUpdate();
        if(retorno>0){
            JOptionPane.showMessageDialog(null, "Site " +s.getNome_site()+ " inserido com sucesso." );
        }
        else{
            JOptionPane.showMessageDialog(null, "Erro ao inserir o Site " +s.getNome_site()+ ", verifique os LOGS.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public List<Site> listarSites(){
      try{
        String SQL = "SELECT tipo_site, nome_site, url_site  FROM thales_zinato.site";
        List<Site> listavideos = new ArrayList<Site>();
        Connection c =conexao.getConexao();
        PreparedStatement ps=c.prepareStatement(SQL);
        ResultSet resultado = ps.executeQuery();
        while(resultado.next()){
                Site atual=new Site();
                atual=this.pegaDados2(resultado);
                listavideos.add(atual);
            }
        return listavideos;
      }catch (SQLException ex) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private Site pegaDados2(ResultSet resultado){
        try {
            Site atual=new Site();
            atual.setTipo_site (resultado.getString("tipo_site"));
            atual.setNome_site(resultado.getString("nome_site"));
            atual.setUrl_site(resultado.getString("url_site"));
            return atual;
        } catch (SQLException ex) {
            Logger.getLogger(SiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
}
