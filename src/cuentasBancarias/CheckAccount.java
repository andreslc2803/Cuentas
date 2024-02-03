package cuentasBancarias;

public class CheckAccount extends BankAccount {
	private int checkNumber;
	private int numGirados;
	private int numCobrados;
	private boolean sobregiro;
	private float valorAutorizado;
	private float sobregiroUsado;
	
	/**
	 * PRECONDICIONES: 
	 * 1) El valor de initialBalance debe ser un número real válido que represente el saldo
	 * inicial de la cuenta
	 * 2) La instancia de la clase CheckAccount debe ser creada correctamente.
	 * 
	 * POSTCONDICIONES: 
	 * 1) La cuenta  ha sido creada exitosamente
	 * 3) El saldo inicial de la cuenta es igual al valor proporcionado en el parámetro initialBalance
	 * 4) El número de cheque (checkNumber) ha sido inicializado en 0
	 * 5) El número de cheques girados (numGirados) ha sido inicializado en 0
	 * 6) El número de cheques cobrados (numCobrados) ha sido inicializado en 0
	 * 7) El tipo de cuenta (accountType) ha sido establecido como 'C'
	 * 8) La propiedad de sobregiro (sobregiro) ha sido establecida en false
	 * 9) El valor autorizado (valorAutorizado) ha sido inicializado en 0
	 * 10) El monto de sobregiro usado (sobregiroUsado) ha sido inicializado en 0
	 */
	public CheckAccount(float initialBalance) {
		super(initialBalance);
		checkNumber = 0;
		numGirados = 0;
		numCobrados = 0;
		accountType = 'C';
		sobregiro = false;
		valorAutorizado = 0;
		sobregiroUsado = 0;
	}

	/**
	 * PRECONDICIONES: 
	 * 1) La tarifa "fee" debe ser un numero real no negativo
	 * 2) La clase "BankAccount" debe tener un metodo booleano llamado "withdraw"
	 * 3) El metodo se invoque en una cuenta existente y valida
	 * 
	 * POSTCONDICIONES: 
	 * 1) Si el retiro realizado en el metodo "withdraw" de la clase "BankAccount" 
	 * retorna true, realiza la verificacion exitosamente.
	 * 2) Retorna FALSE si el retiro no se pudo realizar con normalidad
	 */
	public boolean deductFees(float fee) {
		boolean normalFee = super.withdraw(fee);
		return normalFee ? true : false;
	}

	/**
	 * PRECONDICIONES: 
	 * 1) Los atributos "balance", "sobregiro", "valorAutorizado" sean validos
	 * y esten inicializadas
	 * 2) El metodo se invoque en una cuenta existente y valida
	 * 3) El metodo "getMinBalance()" exista
	 * 
	 * POSTCONDICIONES: 
	 * 1) Si se puede realizar el sobregiro, realiza las respectivas operaciones y retorna TRUE en el sobregiro
	 * 2) Cambia el estado de sobregiro a TRUE
	 * 3) Cambia el valor de valorAutorizado segun las operaciones pertinentes
	 * 4) No puede realizas las operaciones y retorna FALSE en el sobregiro
	 * ya que el balance actual < al valor especificado en el balance minimo
	 */
	public boolean autorizarSobregiro() {
		if (balance > super.getMinBalance()) {
			sobregiro = true;
			valorAutorizado = balance - super.getMinBalance();
			return sobregiro;
		} else
			return sobregiro;
	}

	/**
	 * PRECONDICIONES: 
	 * 1) La cantidad a retirar sea un numero real positivo
	 * 2) Las variables "balance", "minBalance", "valorAutorizado", "sobregiroUsado" deben
	 * estar inicializadas correctamente
	 * 3) El metodo se invoque en una cuenta existente y valida
	 * 4) La instancia de la clase padre debe tener un metodo llamado withdraw() para 
	 * realiza el respectivo retiro
	 * 
	 * POSTCONDICIONES: 
	 * 1) Realiza el retiro de manera normal y retorna TRUE, donde se utiliza el metodo "withdraw" de la clase
	 * padre "BankAccount" en el cual solo se resta el valor a retirar de la cantidad de dinero que
	 * tenga la cuenta
	 * 2) Realiza el retiro y retorna TRUE pero con comprobaciones adicionales para determinar si se puede autorizar
	 * un sobregiro. Dependiendo de las condiciones y configuraciones de la cuenta
	 * 3) Si retorna TRUE, se realiza el cobro de cheques y aumentara el atributo "numCobrados" en el metodo
	 * cobrarCheck() (metodo de la misma clase)
	 * 4) No realiza el retiro ya que el sobregiro no esta autorizado o es insuficiente, muestra un mensaje
	 * y retorna FALSE
	 */
	public boolean withdraw(float amount) {
		float minBalance = super.getMinBalance();
		boolean normalWithdraw = super.withdraw(amount);
		if (!normalWithdraw) {
			if (sobregiro && amount > (balance - minBalance + valorAutorizado - sobregiroUsado)) {
				float resto = balance - minBalance;
				sobregiroUsado = sobregiroUsado + amount - resto;
				return true;

			} else {
				if (sobregiro)
					System.out.println("sobregiro autorizado es insuficiente.");
				else
					System.out.println("sobregiro NO autorizado.");
				return false;
			}
		} else
			return true;
	}
	
	/**
	 * PRECONDICIONES: 
	 * 1) La clase que contiene este método debe representar una cuenta bancaria
	 * 2) El parámetro "amount" debe ser un valor real positivo 
	 * 3) Las variables "checkNumber", "numGirados" deben estar inicializadas en la clase
	 * 4) El metodo se invoque en una cuenta existente y valida
	 * 
	 * POSTCONDICIONES:
	 * 1) Se incrementa el número de cheque actual (checkNumber) en 1 después de llamar al método.
	 * 2) Se incrementa el contador total de cheques girados (numGirados) en 1, después de llamar al método.
	 */
	public void girarCheck(float amount) {
		checkNumber = checkNumber + 1;
		numGirados = numGirados + 1;
	}
	
	/**
	 * PRECONDICIONES: 
	 * 1) La instancia de la clase debe tener un metodo llamado withdraw() para 
	 * realiza el respectivo retiro
	 * 2) "amount" y "number" deben ser un valor valido y positivo
	 * 3) El atributo "numCobrados" debe estar inicializado en la clase
	 * 
	 * POSTCONDICIONES: 
	 * 1) Si el retiro del monto especificado (amount) se realiza con éxito, la 
	 * variable numCobrados se incrementa en 1, indicando que el cheque
	 * ha sido cobrado con éxito.
	 * 2) Si el retiro no es exitoso la variable numCobrados no se incrementa y 
	 * el método retorna false.
	 * 3) La ejecución del método no debe tener efectos secundarios inesperados 
	 * en el estado de la instancia de la clase, excepto por el incremento de numCobrados
	 * si el retiro es exitoso.
	 */
	public boolean cobrarCheck(float amount, int number) {
		boolean cobrado = this.withdraw(amount);
		if (cobrado) {
			numCobrados = numCobrados + 1;
			return true;
		} else
			return false;
	}

	/**
	 * PRECONDICIONES:
	 * 1) La instancia de la clase padre debe tener un metodo llamado deposit() para 
	 * realiza el respectivo deposito
	 * 2) Los atributos "sobregiro", "sobregiroUsado" deben estar inicializados en la clase
	 * 3) El metodo deposit() debe ser un valor positivo
	 * 4) El atributo "depositAmount" debe ser un real positivo
	 * 
	 * POSTCONDICIONES:
	 * 1) Si sobregiro es falso, el método deposit debe aumentar el saldo de la cuenta asociada
	 *  por el valor de depositAmount.
	 * 2) Si sobregiro es verdadero y sobregiroUsado es mayor que 0, entonces:
	 * 
	 * a. Si depositAmount es mayor o igual a sobregiroUsado, el método debe aumentar el saldo
	 *  de la cuenta asociada por (depositAmount - sobregiroUsado) y establecer sobregiroUsado a 0.
	 * b. Si depositAmount es menor que sobregiroUsado, el método debe reducir sobregiroUsado por 
	 * el valor de depositAmount.
	 * 
	 * 3) Si sobregiro es verdadero y sobregiroUsado es igual a 0, el método debe comportarse de la
	 *  misma manera que si sobregiro fuera falso.
	 * 4) En todos los casos, después de ejecutar el método, el saldo de la cuenta asociada no puede
	 *  ser negativo.
	 */
	public void deposit(float depositAmount) {
		if (!sobregiro)
			super.deposit(depositAmount);
		else {
			if (sobregiroUsado > 0) {
				if (depositAmount >= sobregiroUsado) {
					super.deposit(depositAmount - sobregiroUsado);
					sobregiroUsado = 0;
				} else {
					sobregiroUsado = sobregiroUsado - depositAmount;
				}
			} else {
				super.deposit(depositAmount);
			}
		}
	}
	

	public float valorAutorizado() {
		return valorAutorizado;
	}

	public float valorUsado() {
		return sobregiroUsado;
	}

	public boolean sobregiroAutorizado() {
		return sobregiro;
	}

}
