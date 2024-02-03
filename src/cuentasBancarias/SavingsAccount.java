package cuentasBancarias;

public  class SavingsAccount extends BankAccount {
	  private float totalInterest;
	  
		/**
		 * PRECONDICIONES: 
		 * 1) El saldo inicial debe ser un numero real no negativo
		 * 2) La instancia de la clase SavingsAccount debe ser creada correctamente.
		 * 
		 * POSTCONDICIONES: 
		 * 1) La instancia de la clase SavingsAccount debe tener un saldo inicial igual a initialBalance.
		 * 2) La propiedad accountType de la instancia debe ser establecida como 'S'.
		 * 3) La propiedad totalInterest debe ser inicializada a 0.
		 */
	  public SavingsAccount (float initialBalance)   {
		  super(initialBalance);
	      accountType='S';
	      totalInterest=0;
	  }
	  
	  /**
	   * PRECONDICION: 
	   * 1) El atributo "totalInterest" debe estar inicializado
	   * 2) El parametro "interestRate" debe ser real positivo 
	   * 3) El metodo se invoque en una cuenta existente y valida
	   * 4) La instancia de la clase debe tener un metodo llamado deposit() para 
	   * realiza el respectivo deposito
	   * 
	   * POSTCONDICION:
	   * 1) Se incrementa la variable totalInterest en el producto del saldo 
	   * actual (balance) y la tasa de interés (interestRate).
	   * 2) Se realiza un depósito en la cuenta llamando al método 
	   * super.deposit(balance * interestRate). La cantidad depositada es el 
	   * resultado del producto del saldo actual (balance) y la tasa de interés (interestRate).
	   */
	  public void creditInterest (float interestRate)  {
	       totalInterest= totalInterest+ balance* interestRate;
	       super.deposit(balance* interestRate);
	  }

	public float getTotalInterest() {
		return totalInterest;
	}

}
