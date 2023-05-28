package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Log;

public class LogController implements Serializable, Log {
    private static final long serialVersionUID = 6470090944414208999L;

    List<String> listaLogs = new ArrayList<>();

    @Override
    public boolean gerarLog(String data) {
        listaLogs.add(data);
        return false;
    }

    @Override
    public List<String> pegarLogs() {
        return listaLogs;
    }

    @Override
    public String toString() {
        String res = "";
        for (String string : listaLogs) {
            res = res.concat(string+"\n");
        }
        return res;
    }
}
