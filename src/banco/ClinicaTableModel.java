package banco;

import banco.Model.Clinica;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClinicaTableModel extends AbstractTableModel {

    private List<String> cabecalho;

    //item - linhas da tabela
    private List<Clinica> listaclinicas;

    public void setListaClinicas(List<Clinica> listaClinicas) {
        this.listaclinicas = listaClinicas;
    }

    public List<Clinica> getListaclinicas() {
        return listaclinicas;
    }

    public ClinicaTableModel() {
        cabecalho = new ArrayList<>();
        listaclinicas = new ArrayList<>();

        cabecalho.add("ID");
        cabecalho.add("NOME");
        cabecalho.add("CNPJ");
        cabecalho.add("CIDADE CLINICA");
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho.get(column);
    }

    @Override
    public int getRowCount() {
        return listaclinicas.size();
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
                return listaclinicas.get(rowIndex).getIdclinica();
            case 1:
                //retornar o nome
                return listaclinicas.get(rowIndex).getNome();
            case 2:
                return listaclinicas.get(rowIndex).getCnpj();
            case 3:
                return listaclinicas.get(rowIndex).getCidadeclinica();
            default:
                return null;
        }
    }

}
