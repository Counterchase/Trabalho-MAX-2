package banco;

import banco.Model.Cliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel {

    private List<String> cabecalho;

    //item - linhas da tabela
    private List<Cliente> listaClientes;

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    

    public ClienteTableModel() {
        cabecalho = new ArrayList<>();
        listaClientes = new ArrayList<>();

        cabecalho.add("ID");
        cabecalho.add("NOME");
        cabecalho.add("RG");
        cabecalho.add("CPF");
        cabecalho.add("DATA DE NASCIMENTO");
        cabecalho.add("ID CLINICA");
        cabecalho.add("ID MEDICO");
        cabecalho.add("ID LEITO");
    }

    public List<String> getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(List<String> cabecalho) {
        this.cabecalho = cabecalho;
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho.get(column);
    }

    @Override
    public int getRowCount() {
        return listaClientes.size();
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
                return listaClientes.get(rowIndex).getIdCliente();
            case 1:
                //retornar o nome
                return listaClientes.get(rowIndex).getNome();
            case 2:
                return listaClientes.get(rowIndex).getRg();
            case 3:
                return listaClientes.get(rowIndex).getCpf();
            case 4:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(listaClientes.get(rowIndex).getDatanascimento().getTime());
            case 5:
                return listaClientes.get(rowIndex).getIdclinica();
            case 6:
                return listaClientes.get(rowIndex).getIdmedico();
            case 7:
                return listaClientes.get(rowIndex).getIdleito();
            default:
                return null;
        }
    }

}
