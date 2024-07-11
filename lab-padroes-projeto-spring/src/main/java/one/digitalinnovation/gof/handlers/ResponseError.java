package one.digitalinnovation.gof.handlers;

import java.util.Date;

/**
 * Data Transfer Object (DTO) - A classe <b>ResponseError</b>
 * pode ser vista como um <b>DTO</b>, que é um objeto usado para transferir dados entre processos.
 * Isso ajuda a separar a lógica de negócios da apresentação e facilita a serialização e
 * deserialização de dados.
 * <br>
 * <br>
 * BuilderPattern - A classe <b>ResponseError</b> também podem ser vistas como uma forma
 * simplificada do padrão Builder, onde você configura um objeto passo a passo antes de usá-lo.
 *
 * @author Kaio Abreu
 */

public class ResponseError {

    private Date timestamp = new Date();
    private String status = "error";
    private int statusCode = 400;
    private String error;

    public ResponseError(Date timestamp, String status, int statusCode, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.statusCode = statusCode;
        this.error = error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
