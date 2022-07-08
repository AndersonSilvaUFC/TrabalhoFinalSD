package server;

import model.Funcionario;
import model.RHManager;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RHManagerEsqueleto {
    private RHManager rhManager;

    public RHManagerEsqueleto(){
        rhManager= new RHManager();
    }

    public String calculaFolhaDePagamento(String args) throws JSONException{
        JSONArray funcionariosJson = new JSONArray(args);

        List<Funcionario> funcionarios = new ArrayList<>();

        for(int i=0; i<funcionariosJson.length(); i++){

            JSONObject funcionarioJson = funcionariosJson.getJSONObject(i);

            Funcionario funcionario = new Funcionario(
                    funcionarioJson.getString("nome"),
                    funcionarioJson.getInt("mesesTrabalhados"),
                    funcionarioJson.getDouble("salario"),
                    funcionarioJson.getDouble("fgts"),
                    funcionarioJson.getBoolean("bonusDeFeriasPago"),
                    funcionarioJson.getBoolean("ativo"),
                    funcionarioJson.getBoolean("demitidoJustaCausa")
            );

            funcionarios.add(funcionario);
        }


        double resposta = rhManager.calculaFolhaDePagamento(funcionarios);

        JSONObject respostaJson = new JSONObject();
        respostaJson.put("resposta",resposta);

        return respostaJson.toString();
    }

    public String calculaBonusDeFerias(String args) throws JSONException{
        JSONObject funcionarioJson = new JSONObject(args);

        Funcionario funcionario = new Funcionario(
                funcionarioJson.getString("nome"),
                funcionarioJson.getInt("mesesTrabalhados"),
                funcionarioJson.getDouble("salario"),
                funcionarioJson.getDouble("fgts"),
                funcionarioJson.getBoolean("bonusDeFeriasPago"),
                funcionarioJson.getBoolean("ativo"),
                funcionarioJson.getBoolean("demitidoJustaCausa")
        );

        double resposta = rhManager.calculaBonusDeFerias(funcionario);

        JSONObject respostaJson = new JSONObject();
        respostaJson.put("resposta",resposta);

        return respostaJson.toString();
    }
}
