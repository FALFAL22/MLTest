package com.fl.ml.response;

import com.fl.ml.model.Posicion;

public class MLResponse {
	
	private Posicion posicion;
	private String mensaje;

	public MLResponse(Posicion posicion, String mensaje) {
		super();
		this.posicion = posicion;
		this.mensaje = mensaje;
	}
	public Posicion getPosicion() {
		return posicion;
	}
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
