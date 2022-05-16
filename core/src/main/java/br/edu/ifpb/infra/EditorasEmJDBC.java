package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Editora;
import br.edu.ifpb.domain.Editoras;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/04/2022, 21:45:44
 */
@Stateless
public class EditorasEmJDBC implements Editoras {
//    @Resource(lookup = "java:app/jdbc/pgadmin")
//
//    private Connection connection;

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;
    public EditorasEmJDBC() {



//        try {
//            Class.forName("org.postgresql.Driver");
//            this.connection = DriverManager.getConnection(
//                "jdbc:postgresql://host-banco:5432/livros",
//                "job","123"
//            );
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(EditorasEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
//        }

    }
    
    @Override
    public void nova(Editora editora) {
        try {
            PreparedStatement statement = this.dataSource.getConnection().prepareStatement(
                "INSERT INTO editoras(localDeOrigem, nomeFantasia) VALUES ( ?, ? );"
            );
            statement.setString(1, editora.getLocalDeOrigem());
            statement.setString(2, editora.getNomeFantasia());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivrosEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public boolean nomeDuplicado(String nomeFantasia) {
        try {

            PreparedStatement prepareStatement = this.dataSource.getConnection().prepareStatement(
                    "SELECT COUNT(nomeFantasia) AS contador FROM editoras WHERE nomeFantasia like ?"
            );
            prepareStatement.setString(1, nomeFantasia);
            ResultSet result = prepareStatement.executeQuery();
            if(result.next()) {
                long contador = result.getLong("contador");
                return contador > 0;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public List<Editora> todas() {
        try {
            List<Editora> lista = new ArrayList<>();
            ResultSet result = this.dataSource.getConnection().prepareStatement(
                "SELECT * FROM editoras"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarEditoras(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<Editora> porLocalDeOrigem(String localDeOrigem) {
        try {
            List<Editora> lista = new ArrayList<>();
            PreparedStatement prepareStatement = this.dataSource.getConnection().prepareStatement(
                    "SELECT * FROM editoras WHERE localDeOrigem like ?"
            );
            prepareStatement.setString(1, localDeOrigem);
            ResultSet result = prepareStatement.executeQuery();
            while (result.next()) {
                lista.add(
                        criarEditoras(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    private Editora criarEditoras(ResultSet result) throws SQLException {
        String localDeOrigem = result.getString("localDeOrigem");
        String nomeFantasia = result.getString("nomeFantasia");
        int codigo = result.getInt("codigo");
        
        return new Editora(codigo,localDeOrigem,nomeFantasia);
    }

}
