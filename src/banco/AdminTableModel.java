package banco;

import java.util.ArrayList;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AdminTableModel extends AbstractTableModel {

    private List<String> cabecalho;

    //item - linhas da tabela
    private List<Admin> listaAdmins;

    public void setListaAdmins(List<Admin> listaAdmins) {
        this.listaAdmins = listaAdmins;
    }

    public List<Admin> getListaAdmins() {
        return listaAdmins;
    }

    public AdminTableModel() {
        cabecalho = new ArrayList<>();
        listaAdmins = new ArrayList<>();

        cabecalho.add("ID");
        cabecalho.add("NOME");
        cabecalho.add("LOGIN");
        cabecalho.add("SENHA");
        cabecalho.add("ADM");
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho.get(column);
    }

    @Override
    public int getRowCount() {
        return listaAdmins.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                //retornar o id
                return listaAdmins.get(rowIndex).getIdadmin();
            case 1:
                //retornar o nome
                return listaAdmins.get(rowIndex).getNome();
            case 2:
                return listaAdmins.get(rowIndex).getLogin();
            case 3:
                return listaAdmins.get(rowIndex).getSenha();
            case 4:
                return listaAdmins.get(rowIndex).getAdm();
            default:
                return null;
        }
    }

}
