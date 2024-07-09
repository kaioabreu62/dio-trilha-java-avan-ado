package dio.aula_spring_data_jpa_exception.handler;

public class CampoObrigatorioException extends BusinessException {
    public CampoObrigatorioException(String campo) {
        super("O campo %s é obrigatório!", campo);
    }
}
