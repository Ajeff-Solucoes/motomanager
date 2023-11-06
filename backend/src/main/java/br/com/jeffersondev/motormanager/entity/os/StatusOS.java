package br.com.jeffersondev.motormanager.entity.os;

import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;

public enum StatusOS {
	
	TODOS,
	ABERTO,
	FECHADO,
	PENDENTE;

	public static StatusOS getValue(String status) {
		if(status.equalsIgnoreCase("Aberto")) {
			return StatusOS.ABERTO;
		}else if(status.equalsIgnoreCase("Fechado")) {
			return StatusOS.FECHADO;
		}else if(status.equalsIgnoreCase("Pendente")) {
			return StatusOS.PENDENTE;
		}else if(status.equalsIgnoreCase("Todos")) {
			return StatusOS.TODOS;
		}else {
			throw new ValorInformadoInvalidoException("O status informado '" + status + "' não existe");
		}
	}

}
