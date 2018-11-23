package banco;

import banco.Medico;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MedicoTableModel extends AbstractTableModel {

    private List<String> cabecalho;

    //item - linhas da tabela
    private List<Medico> listamedicos;

    public void setListamedicos(List<Medico> listamedicos) {
        this.listamedicos = listamedicos;
    }

    public List<Medico> getListamedicos() {
        return listamedicos;
    }
    

    public MedicoTableModel() {
        cabecalho = new ArrayList<>();
        listamedicos = new ArrayList<>();

        cabecalho.add("ID");
        cabecalho.add("NOME");
        cabecalho.add("CRM");
        cabecalho.add("CPF");
        cabecalho.add("DATA DE NASCIMENTO");
        cabecalho.add("ID CLINICA");
        cabecalho.add("ID ADMIN");
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho.get(column);
    }

    @Override
    public int getRowCount() {
        return listamedicos.size();
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
                return listamedicos.get(rowIndex).getIdmedico();
            case 1:
                //retornar o nome
                return listamedicos.get(rowIndex).getNome();
            case 2:
                return listamedicos.get(rowIndex).getCrm();
            case 3:
                return listamedicos.get(rowIndex).getCpf();
            case 4:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(listamedicos.get(rowIndex).getDatanascimento().getTime());
            case 5:
                return listamedicos.get(rowIndex).getIdclinica();
            case 6:
                return listamedicos.get(rowIndex).getIdadmin();
            default:
                return null;
        }
    }

}
