package com.financial.entity.enums;

public enum StagePayment {
	
	PENDING(1, "Pendente"),
	SETTLED(2, "Quitado"),
	CANCELED(3, "Cancelado");
	
	private int cod;
	private String description;
	
	private StagePayment(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static StagePayment toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StagePayment x : StagePayment.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
