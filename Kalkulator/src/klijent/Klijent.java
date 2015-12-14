package klijent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klijent extends CalculatorGui implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Socket soketZaKomunikacijuPodataka = null;
	static Socket soketZaKomunikacijuKomandi = null;
	static PrintStream izlazniTokKaServeruZaKomande = null;
	static PrintStream izlazniTokKaServeruZaPodatke = null;
	static BufferedReader ulazniTokOdServeraZaPotvrdu = null;
	static BufferedReader ulazniTokOdServeraZaRezultat = null;
	// primljena informacija o uspesno dostavljenom rezultatu sa servera
	static boolean kraj = false;

	public static void main(String[] args) {

		int portZaKomande = 1908;
		int portZaPodatke = 758;

		try {
			// povezujemo se na server
			soketZaKomunikacijuKomandi = new Socket("localhost", portZaKomande);
			soketZaKomunikacijuPodataka = new Socket("localhost", portZaPodatke);

			// definisanje izlaznih tokova za sokete za komande i podatke
			izlazniTokKaServeruZaKomande = new PrintStream(soketZaKomunikacijuKomandi.getOutputStream());
			izlazniTokKaServeruZaPodatke = new PrintStream(soketZaKomunikacijuPodataka.getOutputStream());

			// definisanje ulaznih tokova za sokete za komande i podatke
			ulazniTokOdServeraZaPotvrdu = new BufferedReader(
					new InputStreamReader(soketZaKomunikacijuKomandi.getInputStream()));
			ulazniTokOdServeraZaRezultat = new BufferedReader(
					new InputStreamReader(soketZaKomunikacijuPodataka.getInputStream()));

			// nit koja ce da cita rezultat sa servera
			new Thread(new Klijent()).start();

			if (!kraj) { // IF ILI WHILE ???

				izlazniTokKaServeruZaKomande.print(komanda);
				izlazniTokKaServeruZaPodatke.print(tempRezultat);
			}

			soketZaKomunikacijuKomandi.close();
			soketZaKomunikacijuPodataka.close();

		} catch (UnknownHostException e) {
			tempRezultat = "Ne mogu da se povezem na server";
			e.printStackTrace();
		} catch (IOException e) {
			tempRezultat = "Doslo je do greske. Pokusajte ponovo.";
			e.printStackTrace();
		}
	}

	public void run() {

		String rezultatServera;
		try {

			while (((rezultatServera = ulazniTokOdServeraZaRezultat.readLine()) != null)
					&& ulazniTokOdServeraZaPotvrdu.readLine().equals("=")) {

				// textField tempRezultat dobija vrednost koju je poslao Server
				konacanRezultat = "=" + rezultatServera;

				// server je obradio nas zahtev
				kraj = true;

			}
		} catch (IOException e) {
			tempRezultat = "Server ne moze da obradi zahtev.Pokusajte ponovo.";
			e.printStackTrace();
		}

	}

}
