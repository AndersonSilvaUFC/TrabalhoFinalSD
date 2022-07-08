package model;

import java.util.List;

public class RHManager{

    public double calculaFolhaDePagamento(List<Funcionario> funcionarios){
        double total = 0.0;
        for(Funcionario f : funcionarios){
            if(f.isAtivo())
                total += f.getSalario();
        }

        return total;
    }

    public double calculaBonusDeFerias(Funcionario funcionario){
        return funcionario.getSalario() + funcionario.getSalario()/3.0;
    }

    public Funcionario pagaFGTSFuncionario(Funcionario funcionario){
        double fgtsMensal = funcionario.getSalario() * 0.08;
        funcionario.setFgts( fgtsMensal * funcionario.getMesesTrabalhados());
        return funcionario;
    }
    
}