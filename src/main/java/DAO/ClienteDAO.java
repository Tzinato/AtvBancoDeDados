
package DAO;
import Conexao.conexao;
import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClienteDAO {
    public void inserirCliente (Cliente c){
        try {
        String SQL = "INSERT INTO thales_zinato.cliente (login, nome_cliente, email) VALUES (?,?,?)";
        Connection minhaConexao = conexao.getConexao();
        PreparedStatement comando = minhaConexao.prepareStatement(SQL);
        comando.setInt(1, c.getLogincliente());
        comando.setString(2, c.getNomecliente());
        comando.setString(3, c.getEmailcliente());
        int retorno = comando.executeUpdate();
        if(retorno>0){
            JOptionPane.showMessageDialog(null, "Cliente " +c.getNomecliente()+ " inserido com sucesso." );
        }
        else{
            JOptionPane.showMessageDialog(null, "Erro ao inserir o Cliente " +c.getNomecliente()+ ", verifique os LOGS.");
        }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List <Cliente> listaDeLoginsc(){
        try {
            String SQL="SELECT login, nome_cliente  FROM thales_zinato.cliente";
            List <Cliente>listaDeLogins = new ArrayList<Cliente>();
            Connection c =conexao.getConexao();
            PreparedStatement ps=c.prepareStatement(SQL);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Cliente atual=new Cliente();
                atual=this.pegaDados(resultado);
                listaDeLogins.add(atual);
            }

            return listaDeLogins;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private Cliente pegaDados(ResultSet resultado){
        try {
            Cliente atual=new Cliente();
            atual.setLogincliente (resultado.getInt ("login"));
            atual.setNomecliente(resultado.getString("nome_cliente"));
            return atual;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
