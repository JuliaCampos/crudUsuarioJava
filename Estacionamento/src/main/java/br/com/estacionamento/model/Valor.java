package br.com.estacionamento.model;

import java.util.Date;

public class Valor {
	private int id;
	private double valorPrimeiraHora;
	private double valorDemaisHoras;
	private Date dataFim;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValorPrimeiraHora() {
		return valorPrimeiraHora;
	}
	public void setValorPrimeiraHora(double valorPrimeiraHora) {
		this.valorPrimeiraHora = valorPrimeiraHora;
	}
	public double getValorDemaisHoras() {
		return valorDemaisHoras;
	}
	public void setValorDemaisHoras(double valorDemaisHoras) {
		this.valorDemaisHoras = valorDemaisHoras;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
	
}
