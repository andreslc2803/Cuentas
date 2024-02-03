package cuentasBancarias;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCheckAccount {

	private CheckAccount cuenta1 = new CheckAccount(10000);
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("\n ANTES: ");
		System.out.print(" saldo: ");
		System.out.println(cuenta1.getBalance());
		System.out.print(" Saldo m nimo: ");
		System.out.println(BankAccount.getMinBalance());
		System.out.print(" sobregiro autorizado?");
		System.out.println(cuenta1.sobregiroAutorizado());
		System.out.print(" valorautorizado:");
		System.out.println(cuenta1.valorAutorizado());
		System.out.print(" valor usado:");
		System.out.println(cuenta1.valorUsado());
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("\n DESPUES: ");
		System.out.print(" saldo: ");
		System.out.println(cuenta1.getBalance());
		System.out.print(" sobregiro autorizado?: ");
		System.out.println(cuenta1.sobregiroAutorizado());
		System.out.print(" valorautorizado: ");
		System.out.println(cuenta1.valorAutorizado());
		System.out.print(" valor usado:");
		System.out.print(cuenta1.valorUsado());
	}

	@Test
	void saldoMinimo() {
		BankAccount.setMinBalance(10000);
		double minimo = BankAccount.getMinBalance();
		assertEquals(10000, minimo);
	}

	@Test
	void estadoInicial() {
		assertAll("Estado inicial", () -> assertEquals(false, cuenta1.sobregiroAutorizado()),
				() -> assertEquals(10000, cuenta1.getBalance()), () -> assertEquals(0, cuenta1.valorAutorizado()),
				() -> assertEquals(0, cuenta1.valorUsado()));
	}

	@Test
	void deposito() {
		cuenta1.deposit(8000);
		assertEquals(18000, cuenta1.getBalance());
	}

	@Test
	void autorizarSobregiroOK() {
		cuenta1.withdraw(7000);
		cuenta1.autorizarSobregiro();
		assertEquals(true, cuenta1.sobregiroAutorizado());

	}

	@Test
	void autorizarSobregiroNOK() {
		cuenta1.withdraw(9000);
		cuenta1.autorizarSobregiro();
		assertEquals(false, cuenta1.sobregiroAutorizado());

	}

}
