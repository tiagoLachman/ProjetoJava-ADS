package model;

import java.util.List;

public interface Log {
    public boolean gerarLog(String data);

    public List<String> pegarLogs();
}
