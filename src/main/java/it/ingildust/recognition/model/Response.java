package it.ingildust.recognition.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
	
	
	public enum EXIT_CODE {OK, KO};
	
	private EXIT_CODE esito;
	private String descr;
	private List<Object> result;
	
	public Response(EXIT_CODE esito, String descr) {
		super();
		this.esito = esito;
		this.descr = descr;
	}

	public Response(List<Object> result) {
		super();
		this.result = result;
		this.esito= EXIT_CODE.OK;
	}
	
	public Response() {
		this.esito= EXIT_CODE.OK;
	}

}
