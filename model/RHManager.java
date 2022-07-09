package model;

import java.util.List;

public class RHManager{

    public double calculaFolhaDePagamento(List<Funcionario> funcionarios){
       /* //Para testar replicação
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        double total = 0.0;
        for(Funcionario f : funcionarios){
            if(f.isAtivo())
                total += f.getSalario();
        }

        return total;
    }

    public double calculaBonusDeFerias(Funcionario funcionario){
        /* //Para testar replicação e tratamento de duplicadas
        try {
            Thread.sleep(197);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        if(!funcionario.isBonusDeFeriasPago())
            return funcionario.getSalario() + funcionario.getSalario()/3.0;
        else
            return 0.0;
    }

    public double calculaFGTS(Funcionario funcionario){
        if(!funcionario.isAtivo()) {
            if(!funcionario.isDemitidoJustaCausa()) {
                double fgtsMensal = funcionario.getSalario() * 0.08;
                return fgtsMensal * funcionario.getMesesTrabalhados();
            }
        }
        return 0.0;
    }
    
}