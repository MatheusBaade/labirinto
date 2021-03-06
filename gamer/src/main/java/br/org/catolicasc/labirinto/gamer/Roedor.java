package br.org.catolicasc.labirinto.gamer;

import br.org.catolicasc.labirinto.core.cobaia.Cobaia;
import br.org.catolicasc.labirinto.core.regra.Energia;
import br.org.catolicasc.labirinto.view.Labirinto;
import br.org.catolicasc.labirinto.view.elemento.Cenario;
import br.org.catolicasc.labirinto.view.elemento.EnumElementoCenario;
import br.org.catolicasc.labirinto.view.elemento.Posicao;
import br.org.catolicasc.labirinto.view.elemento.Substancia;

/**
 * Classe abstrata de rato que vai implementar uma cobaia
 * @author matheus.baade
 *
 */
public abstract class Roedor implements Cobaia {

	/**
	 * Construtor da classe
	 * @param posicao
	 */
	public Roedor(Posicao posicao) {
		this.posicao = posicao;
		this.energia = new Energia();
		this.elementoCenario = EnumElementoCenario.COBAIA;
	}

	/**
	 * Ação do rato
	 */
	public Posicao make(Labirinto labirinto, int isPossibleContinue) {
		Posicao movimente = this.game(labirinto, isPossibleContinue);
		while (isExtremo(labirinto, movimente)) {
			System.out.println("Loop");
			movimente = this.game(labirinto, isPossibleContinue);
		}
		Cenario elementoCenario = labirinto.getCenario()[movimente.getPosicaoX()][movimente.getPosicaoY()];
		if (elementoCenario instanceof Substancia) {
			Substancia substanciaComestivel = (Substancia) elementoCenario;
			eat(substanciaComestivel);
			executeMutacao();
		}
		return movimente;
	}

	/**
	 * Executa a mutação do rato
	 */
	private void executeMutacao() {
		if (this.energia.getEnergia() >= 30) {
			this.setElementoCenario(EnumElementoCenario.MUTACAO);
		}
	}

	protected Energia energia;
	protected Posicao posicao;

	/**
	 * Retorna a energia
	 * @return
	 */
	public Energia getEnergia() {
		return energia;
	}

	/**
	 * Retorna a posição 
	 */
	public Posicao getPosicao() {
		return this.posicao;
	}

	/**
	 * Seta a posição
	 */
	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;

	}

	protected EnumElementoCenario elementoCenario;

	/**
	 * Seta um elemento no cenario
	 */
	public void setElementoCenario(EnumElementoCenario elementoCenario) {
		this.elementoCenario = elementoCenario;

	}

	/**
	 * Retorna um elemento do cenario
	 */
	public EnumElementoCenario getElementoCenario() {
		// TODO Auto-generated method stub
		return this.elementoCenario;
	}

	/**
	 * Verifica se é um extremo do labirinto a posição atual
	 * @param labirinto
	 * @param posicao
	 * @return
	 */
	private boolean isExtremo(Labirinto labirinto, Posicao posicao) {
		boolean retorno = true;
		if (posicao.getPosicaoX() <= labirinto.getCenario().length) {
			if (posicao.getPosicaoY() <= labirinto.getCenario()[0].length) {
				retorno = false;
			}
		}

		return retorno;
	}

	public abstract void eat(Substancia substancia);

	public abstract Posicao game(Labirinto labirinto, int isPossibleContinue);
}
