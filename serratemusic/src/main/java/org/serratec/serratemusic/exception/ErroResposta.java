package org.serratec.serratemusic.exception;

import java.time.LocalDate;
import java.util.List;

public class ErroResposta {
	
	private Integer status;
	private String mensagem;
	private java.time.LocalDate dataHora;
	private java.util.List<String> erros;
	public ErroResposta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErroResposta(Integer status, String mensagem, LocalDate dataHora, List<String> erros) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.dataHora = dataHora;
		this.erros = erros;
	}
	
	

}
