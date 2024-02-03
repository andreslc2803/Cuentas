/**
 * Pruebas para la clase SavingsAccount.
 */
package cuentasBancarias;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSavingAccount {

	private SavingsAccount savingsAccount;

	/**
	 * Configuración inicial antes de cada prueba.
	 */
	@BeforeEach
	public void setUp() {
		savingsAccount = new SavingsAccount(1000);
	}

	/**
	 * Prueba para verificar el constructor de SavingsAccount.
	 */
	@Test
	public void testConstructor() {
		assertEquals(1000, savingsAccount.getBalance());
		assertEquals('S', savingsAccount.getAccountType());
		assertEquals(0, savingsAccount.getTotalInterest());
	}

	/**
	 * Prueba para verificar el cálculo correcto de intereses y el balance después
	 * de acreditar intereses.
	 */
	@Test
	public void testCreditInterest() {
		savingsAccount.setBalance(2000);
		float interestRate = 0.05f;

		savingsAccount.creditInterest(interestRate);

		assertEquals(2000 * interestRate, savingsAccount.getTotalInterest());
		assertEquals(2000 + (2000 * interestRate), savingsAccount.getBalance());
	}

	/**
	 * Prueba para verificar la retirada exitosa de fondos.
	 */
	@Test
	public void testWithDraw() {
		float withdrawAmount = 800f;
		savingsAccount.setBalance(20000);
		assertTrue(savingsAccount.withdraw(withdrawAmount));
	}

	/**
	 * Prueba para verificar el depósito y la actualización correcta del balance.
	 */
	@Test
	void testDeposit() {
		savingsAccount.setBalance(1000);
		savingsAccount.deposit(500);
		assertEquals(1500, savingsAccount.getBalance());
	}

	/**
	 * Prueba para verificar la retirada con saldo suficiente y la actualización
	 * correcta del balance.
	 */
	@Test
	void testWithdrawSufficientBalance() {
		savingsAccount.setBalance(1000);
		assertTrue(savingsAccount.withdraw(500));
		assertEquals(500, savingsAccount.getBalance());
	}

	/**
	 * Prueba para verificar la retirada sin saldo suficiente y la no actualización
	 * del balance.
	 */
	@Test
	void testWithdrawInsufficientBalance() {
		savingsAccount.setBalance(500);
		assertFalse(savingsAccount.withdraw(1000));
		assertEquals(500, savingsAccount.getBalance());
	}
}
