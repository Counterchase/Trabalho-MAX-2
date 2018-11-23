//mysql jdbc - para pesquisar na net e baixar 
package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Banco {

    public static Connection conecta() {
        String prefixo = "jdbc:mysql://";
        String host = "localhost/"; //127.0.0.1
        String banco = "piccianimed";
        String usuario = "root";
        String pass = "root";

        String url = prefixo + host + banco;
        //Connection conexao; // sem esse objeto não há conexao

        try {
            return DriverManager.getConnection(url, usuario, pass);
            //conexao = DriverManager.getConnection(url, usuario, pass);

            /*System.out.println("conectado com sucesso!!!!!!!!!!");
            String sql = "INSERT INTO aluno (nome, data_nascimento) VALUES" + "('GLEICE','2018-06-08')";

            //
            Statement stmt = conexao.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
            conexao.close();*/
        } catch (SQLException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        //somente em caso de erro de conexão
        return null;
    }

    public static void main(String[] args) {
        new Banco();
    }
}
