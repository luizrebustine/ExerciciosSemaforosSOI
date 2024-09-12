package view;

import controller.ThreadCozinha;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		int numeroPermissoes = 1;
		Semaphore semaforo = new Semaphore(numeroPermissoes);
		for(int i=0;i<5;i++) {
			Thread t = new ThreadCozinha(semaforo);
			t.start();
		}
	}

}
