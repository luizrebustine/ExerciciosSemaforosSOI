package view;

import controller.ControllerThreads;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		int numeroPermissoes = 1;
		Semaphore semaforo = new Semaphore(numeroPermissoes);
		for (int i = 1; i <= 21; i++) {
			Thread t = new ControllerThreads(i, semaforo);
			t.start();
		}
	}

}
