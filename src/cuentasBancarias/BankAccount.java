package cuentasBancarias;

public class BankAccount {
	// Saldo
	protected float balance;
	// Nro cuenta
	protected int accountNumber;
	// Tpo cuenta
	protected char accountType;

	private static int lastAccountNumber = 0;
	private static float minBalance = 0;

	// class methods

	/**
	 * PRECONDICIÓN: 
	 * 1) La variable lastAccountNumber este inicializada
	 * 2) El metodo se invoque en una cuenta existente y valida
	 *
	 * POSTCONDICIÓN: 
	 * 1) Incrementa el valor de "lastAccountNumber" en 1
	 * 2) No se incremente el numero de cuenta "lastAccountNumber" y 
	 * quede el valor actual
	 */
	static int newAccountNumber() {
		return lastAccountNumber++;
	}

	/**
	 * PRECONDICIÓN: 
	 * 1) La variable ingresada "initialBalance" sea un número positivo
	 * 2) El saldo inicial debe ser mayor o igual al saldo minimo 
	 * 3) El metodo se invoque en una cuenta existente y valida
	 * 4) Los atributos "minBalance", "balance", "accountNumber", "accountType" esten declarados
	 *
	 * POSTCONDICIÓN: 
	 * 1) Se asignan valores a las respectivas variables 
	 * 2) Se muestra un mensaje especificando un error
	 * 3) No se asignan valores a las respectivas variable, debido a que
	 * el saldo inicial es menor al saldo minimo
	 */
	public BankAccount(float initialBalance) {
		if (initialBalance >= minBalance) {
			balance = initialBalance;
			accountNumber = newAccountNumber() + 1;
			accountType = ' ';
		} else
			System.out.println("Not enough initialAmount");
	}

	/**
	 * PRECONDICIÓN: 
	 * 1) La cantidad que se vaya a depositar sea mayor a 0
	 * 2) El metodo se invoque en una cuenta existente y valida
	 * 3) El "atributo" balance este inicializado
	 *
	 * POSTCONDICIÓN: 
	 * 1) Se incremente el saldo
	 * 2) Falle la ejecucion si el valor depositado no es valido 
	 * 3) No se asignen los intereses al credito de la clase "SavingsAccount"
	 * en el metodo creditInterest(param)
	 * 4) No se realice el deposito en la clase "CheckAccount" en el metodo deposit(param) 
	 */
	public void deposit(float depositAmount) {
		balance += depositAmount;
	}

	/**
	 * PRECONDICIÓN: 
	 * 1) El valor a retirar sea valido o mayor a cero
	 * 2) El saldo a retirar sea <= igual al saldo actual de la cuenta - el saldo minimo
	 * 3) El metodo se invoque en una cuenta existente
	 * 4) El atributo "balance" este inicializado
	 * 
	 * POSTCONDICIÓN: 
	 * 1) Si la retirada es exitosa entonces se realiza la respectiva operacion y 
	 * retorna true, con lo cual: 
	 * 			a. El metodo "deductFeeds()" de la clase "CheckAccount" deduce
	 * 			que la tarifa es normal (true).
	 * 			b. El metodo "withdraw()" de la clase "CheckAccount" deduce que es un retiro
	 * 			normal y retorna true igualmente. 
	 * 
	 * 2) Si la retirada falla, muestra el respectivo mensaje al usuario y retorna false, con
	 * lo cual:
	 * 			a. El metodo "deductFeeds()" de la clase "CheckAccount" deduce que la tarifa 
	 * 			NO es normal (false)
	 * 			b. El metodo "withdraw()" de la clase "CheckAccount" deduce que NO es un retiro
	 * 			normal, por lo que realiza comprobaciones adicionales para determinar si se puede 
	 * 			autorizar un sobregiro
	 *
	 */
	public boolean withdraw(float withdrawAmount) {
		if (withdrawAmount > balance - getMinBalance()) {
			if (withdrawAmount > balance)
				System.out.println("Balance not enough to maintain minimal balance");
			else
				System.out.println("Insufficient Funds!!!");
			return false;
		} else {
			balance -= withdrawAmount;
			return true;
		}
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public char getAccountType() {
		return accountType;
	}

	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}

	public static int getLastAccountNumber() {
		return lastAccountNumber;
	}

	public static void setLastAccountNumber(int lastAccountNumber) {
		BankAccount.lastAccountNumber = lastAccountNumber;
	}

	public static float getMinBalance() {
		return minBalance;
	}

	public static void setMinBalance(float minBalance) {
		BankAccount.minBalance = minBalance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
