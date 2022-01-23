/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Conexao.conexao;
import Model.Avaliador;
import Model.Video;
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
public class VideoDAO {
   public void inserirVideo (Video v){
        try {
        String SQL = "INSERT INTO thales_zinato.video (url_video, nome_video, nome_cliente, tipo_video) VALUES (?,?,?,?)";
        Connection minhaConexao = conexao.getConexao();
        PreparedStatement comando = minhaConexao.prepareStatement(SQL);
        comando.setString(1, v.getUrl_video());
        comando.setString(2, v.getNome_video());
        comando.setString(3, v.getNome_dono());
        comando.setString(4, v.getTipo_video());
        int retorno = comando.executeUpdate();
        if(retorno>0){
            JOptionPane.showMessageDialog(null, "Video " +v.getNome_video()+ "De " + v.getNome_dono()+ " inserido com sucesso." );
        }
        else{
            JOptionPane.showMessageDialog(null, "Erro ao inserir o Video " +v.getNome_video()+ "De " + v.getNome_dono()+ ", verifique os LOGS.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
   public List <Video> listaDeVideos(){
        try {
            String SQL="SELECT nome_cliente, nome_video, url_video, id_video  FROM thales_zinato.video";
            List <Video>listaDeVideos = new ArrayList<Video>();
            Connection c =conexao.getConexao();
            PreparedStatement ps=c.prepareStatement(SQL);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Video atual=new Video();
                atual=this.pegaDados(resultado);
                listaDeVideos.add(atual);
            }

            return listaDeVideos;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    private Video pegaDados(ResultSet resultado){
        try {
            Video atual=new Video();
            atual.setNome_video (resultado.getString("nome_video"));
            atual.setNome_dono(resultado.getString("nome_cliente"));
            atual.setUrl_video(resultado.getString("url_video"));
            atual.setId(resultado.getInt("id_video"));
            return atual;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void inserirNota(int n, int id){
       try {
           String SQL = "UPDATE thales_zinato.video SET nota_video = ? WHERE id_video = ?";
           Connection minhaConexao = conexao.getConexao();
           PreparedStatement atualizar = minhaConexao.prepareStatement(SQL);
           atualizar.setInt(1, n);
           atualizar.setInt(2, id);
           atualizar.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public List<Video> listarVideos(){
      try{
        String SQL = "SELECT nome_cliente, nome_video, url_video, id_video, nota_video, tipo_video  FROM thales_zinato.video";
        List<Video> listavideos = new ArrayList<Video>();
        Connection c =conexao.getConexao();
        PreparedStatement ps=c.prepareStatement(SQL);
        ResultSet resultado = ps.executeQuery();
        while(resultado.next()){
                Video atual=new Video();
                atual=this.pegaDados2(resultado);
                listavideos.add(atual);
            }
        return listavideos;
      }catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private Video pegaDados2(ResultSet resultado){
        try {
            Video atual=new Video();
            atual.setNome_video (resultado.getString("nome_video"));
            atual.setNome_dono(resultado.getString("nome_cliente"));
            atual.setUrl_video(resultado.getString("url_video"));
            atual.setId(resultado.getInt("id_video"));
            atual.setNota(resultado.getInt("nota_video"));
            atual.setTipo_video(resultado.getString("tipo_video"));
            return atual;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
