package br.com.jeffersondev.motormanager.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Paginacao {
	
	private Integer pgNo = 1;
	private Integer size = 10;
	private String order = "asc";

}
