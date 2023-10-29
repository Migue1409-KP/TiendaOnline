package co.edu.tiendaonline.service.domain.support;

public class BooleanDomain {
	private boolean valor;
	private boolean valorDefecto;
	
	private BooleanDomain(final boolean valor, final boolean valorDefecto) {
		setValor(valor);
		setValorDefecto(valorDefecto);
	}
	
	public static final BooleanDomain crear(final boolean valor, final boolean valorDefecto) {
		return new BooleanDomain(valor, valorDefecto);
	}

	public final boolean isValor() {
		return valor;
	}
	
	public final boolean isValorDefecto() {
		return valorDefecto;
	}
	
	private final void setValor(final boolean valor) {
		this.valor = valor;
	}
	
	private final void setValorDefecto(final boolean valorDefecto) {
		this.valorDefecto = valorDefecto;
	}
}
