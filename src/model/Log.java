package model;

import java.util.List;

public abstract interface Log {
    public boolean gerarLog(String data);

    public List<String> pegarLogs();
}
