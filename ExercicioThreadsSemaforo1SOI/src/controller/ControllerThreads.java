package controller;

import java.util.concurrent.Semaphore;

public class ControllerThreads extends Thread {

	private int id;
	private Semaphore semaforo;

	public ControllerThreads(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		controleOperações();
	}

	private void controleOperações() {
		if (id % 3 == 1) {
			for (int i = 0; i < 2; i++) {
				calculo(200, 1000);
				try {
					semaforo.acquire();
					transaçãoBD(1000);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				} finally {
					semaforo.release();
				}
			}
		} else if (id % 3 == 2) {
			for (int i = 0; i < 3; i++) {
				calculo(500, 1500);
				try {
					semaforo.acquire();
					transaçãoBD(1500);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				} finally {
					semaforo.release();
				}
			}
		} else if (id % 3 == 0) {
			for (int i = 0; i < 3; i++) {
				calculo(1000, 2000);
				try {
					semaforo.acquire();
					transaçãoBD(1500);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				} finally {
					semaforo.release();
				}
			}
		}
	}

	private void calculo(int tempoMin, int tempoMax) {
		System.out.println("#" + id + " Fazendo Calculo...");
		int tempoCalculo = (int) ((Math.random() * (tempoMax - tempoMin + 1)) + tempoMin);
		try {
			sleep(tempoCalculo);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void transaçãoBD(int tempo) {
		System.out.println("#" + id + " Fazendo Transação...");
		try {
			sleep(tempo);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
