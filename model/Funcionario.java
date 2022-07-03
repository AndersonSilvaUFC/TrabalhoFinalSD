package model;

public class Funcionario{
    private String nome;
    public int mesesTrabalhados;
    private double salario;
    private double fgts;
    private boolean bonusDeFeriasPago;
    private boolean ativo;
    private boolean demitidoJustaCausa;

    public Funcionario(){}

    public Funcionario(String nome, int mesesTrabalhados, double salario, double fgts, boolean bonusDeFeriasPago, boolean ativo, boolean demitidoJustaCausa){
        this.nome = nome;
        this.salario = salario;
        this.mesesTrabalhados = mesesTrabalhados;
        this.fgts = fgts;
        this.bonusDeFeriasPago = bonusDeFeriasPago;
        this.ativo = ativo;
        this.demitidoJustaCausa = demitidoJustaCausa;
    }

    public void demitir(boolean justaCausa){
        this.ativo = false;
        this.demitidoJustaCausa = justaCausa;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getMesesTrabalhados(){
        return this.mesesTrabalhados;
    }

    public void setMesesTrabalhados(int mesesTrabalhados){
        this.mesesTrabalhados = mesesTrabalhados;
    }

    public double getSalario(){
        return this.salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public double getFgts(){
        return this.fgts;
    }

    public void setFgts(double fgts){
        this.fgts = fgts;
    }

    public boolean isBonusDeFeriasPago(){
        return this.bonusDeFeriasPago;
    }

    public void setBonusDeFeriasPago(boolean bonusDeFeriasPago){
        this.bonusDeFeriasPago = bonusDeFeriasPago;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isDemitidoJustaCausa() {
        return demitidoJustaCausa;
    }

    public void setDemitidoJustaCausa(boolean demitidoJustaCausa) {
        this.demitidoJustaCausa = demitidoJustaCausa;
    }
}