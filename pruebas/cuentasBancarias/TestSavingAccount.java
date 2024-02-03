package cuentasBancarias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSavingAccount {

	private SavingsAccount savingsAccount;

	@BeforeEach
	public void setUp() {
		// Configurar el estado inicial antes de cada prueba
		savingsAccount = new SavingsAccount(1000);
	}

	@Test
	public void testConstructor() {
		// Verificar las postcondiciones del constructor
		assertEquals(1000, savingsAccount.getBalance());
		assertEquals('S', savingsAccount.getAccountType());
		assertEquals(0, savingsAccount.getTotalInterest());
	}
	
	@Test
	public void testCreditInterest() {
		// Configurar el estado inicial según sea necesario
		savingsAccount.setBalance(2000); // Saldo inicial de 2000
		float interestRate = 0.05f; // Tasa de interés del 5% (la f significa que es un float)

		// Invocar el método que se va a probar
		savingsAccount.creditInterest(interestRate);

		// Verificar las postcondiciones
		assertEquals(2000 * interestRate, savingsAccount.getTotalInterest()); // Comprobar que totalInterest se																// incrementa correctamente
		assertEquals(2000 + (2000 * interestRate), savingsAccount.getBalance()); // Comprobar que el depósito se realiza
																					// correctamente
	}
	
	@Test
	public void testWithDraw() {
		assertEquals(true, savingsAccount.withdraw(2000));
	}
}
