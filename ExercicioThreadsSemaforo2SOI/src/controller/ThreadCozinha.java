package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread {

	private int id = (int) threadId();
	private Semaphore semaforo;

	public ThreadCozinha(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		cozimentoPratos();
		try {
			semaforo.acquire();
			entregaPratos();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}

	private void cozimentoPratos() {
		float tempoCozimento = 0;
		float tempoAtual = 0;
//		cozimento das sopas de cebola
		if (id % 2 != 0) {
			tempoCozimento = (int) ((Math.random() * 31) + 50);
			tempoCozimento /= 100;
			System.out.println("Sopa de cebola #" + id + " iniciou!");
			while (tempoAtual < tempoCozimento) {
				System.out.println(
						"Sopa de Cebola #" + id + " está " + (int) ((tempoAtual / tempoCozimento) * 100) + "% pronto");
				try {
					sleep(100);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				tempoAtual += 0.1;
			}
			System.out.println("Sopa de Cebola #" + id + " está 100% pronto");
//			System.out.println(id + " " +tempoCozimento);
		}
//		cozimento lasanha a bolonhesa
		else {
			tempoCozimento = (int) ((Math.random() * 61) + 60);
			tempoCozimento /= 100;
			System.out.println("Lasanha a Bolonhesa #" + id + " iniciou!");
			while (tempoAtual < tempoCozimento) {
				System.out.println("Lasanha a Bolonhesa #" + id + " está " + (int) ((tempoAtual / tempoCozimento) * 100)
						+ "% pronto");
				try {
					sleep(100);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				tempoAtual += 0.1;
			}
			System.out.println("Lasanha a Bolonhesa #" + id + " está 100% pronto");
//			System.out.println(id + " " +tempoCozimento);
		}
	}
	private void entregaPratos() {
		System.out.println("Prato #" + id + " foi entregue com sucesso!");
		try {
			sleep(500);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
