package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws Exception {

		String odKlijenta;
		int konacanRezultat = 0;

		ServerSocket kontrolniSoket = new ServerSocket(1908);
		ServerSocket dataSoket = new ServerSocket(758);

		while (true) {

			Socket soketZaKomande = kontrolniSoket.accept();
			Socket soketZaPodatke = dataSoket.accept();

			// ulazna komanda
			BufferedReader komanda = new BufferedReader(new InputStreamReader(soketZaKomande.getInputStream()));

			// ulazni podaci
			BufferedReader ulaz = new BufferedReader(new InputStreamReader(soketZaPodatke.getInputStream()));

			// izlazni tok
			DataOutputStream izlaz = new DataOutputStream(soketZaPodatke.getOutputStream());

			if (komanda.readLine().equals("Sabiranje")) {
				String[] b = ulaz.readLine().split("+");
				int temp = 0;
				for (int i = 0; i < b.length; i++) {
					int broj = Integer.parseInt(b[i]);
					temp = temp + broj;
				}
				konacanRezultat = temp;
			}

			if (komanda.readLine().equals("Oduzimanje")) {
				String[] b = ulaz.readLine().split("+");
				int temp = Integer.parseInt(b[0]);

				for (int i = 1; i < b.length; i++) {
					int broj = Integer.parseInt(b[i]);
					temp = temp - broj;
				}
				konacanRezultat = temp;
			}

			if (komanda.readLine().equals("Mnozenje")) {
				String[] b = ulaz.readLine().split("*");
				int temp = 1;

				for (int i = 0; i < b.length; i++) {
					int broj = Integer.parseInt(b[i]);
					temp = temp * broj;
				}
				konacanRezultat = temp;
			}

			if (komanda.readLine().equals("Deljenje")) {
				String[] b = ulaz.readLine().split("/");
				int temp = Integer.parseInt(b[0]);
				for (int i = 1; i < b.length; i++) {
					int broj = Integer.parseInt(b[i]);
					temp = temp / broj;
				}
				konacanRezultat = temp;
			}

			izlaz.writeInt(konacanRezultat);

		}

	}

}
