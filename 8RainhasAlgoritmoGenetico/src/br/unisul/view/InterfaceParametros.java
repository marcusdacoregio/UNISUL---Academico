package br.unisul.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.unisul.controller.Controller;

public class InterfaceParametros {

	protected Shell shell;
	
	Button checkBoxPontoCorteAleatorio;
	Spinner spinnerPontoCorte;
	Label lblTaxaDeMutacao;
	Spinner spinnerNumGeracoes;
	Spinner spinnerTamanhoPopulacao;
	Button radioElitista;
	Button radioRoleta;
	private Text txtTaxaMutacao;
	private Text txtTaxaRecombinacao;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InterfaceParametros window = new InterfaceParametros();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
//		shell.setMaximized(true);
		
		Monitor primary = display.getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shell.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shell.setLocation(x, y);
	    
	    Label lblPontoDeCorte = new Label(shell, SWT.NONE);
	    lblPontoDeCorte.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblPontoDeCorte.setBounds(10, 56, 87, 15);
	    lblPontoDeCorte.setText("Ponto de Corte:");
	    
	    checkBoxPontoCorteAleatorio = new Button(shell, SWT.CHECK);
	    checkBoxPontoCorteAleatorio.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseUp(MouseEvent event) {
	    		if(checkBoxPontoCorteAleatorio.getSelection()) {
	    			spinnerPontoCorte.setEnabled(false);
	    		} else {
	    			spinnerPontoCorte.setEnabled(true);
	    		}
	    	}
	    });
	    checkBoxPontoCorteAleatorio.setBounds(101, 42, 75, 16);
	    checkBoxPontoCorteAleatorio.setText("Aleat\u00F3rio");
	    
	    spinnerPontoCorte = new Spinner(shell, SWT.BORDER);
	    spinnerPontoCorte.setBounds(101, 64, 47, 22);
	    
	    Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label.setBounds(10, 92, 166, 7);
	    
	    Label lblNumDeGeracoes = new Label(shell, SWT.NONE);
	    lblNumDeGeracoes.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblNumDeGeracoes.setBounds(10, 105, 90, 15);
	    lblNumDeGeracoes.setText("N\u00BA de Gera\u00E7\u00F5es:");
	    
	    spinnerNumGeracoes = new Spinner(shell, SWT.BORDER);
	    spinnerNumGeracoes.setBounds(101, 102, 47, 22);
	    
	    Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_1.setBounds(10, 126, 166, 7);
	    
	    Label lblTipoDeReproduo = new Label(shell, SWT.NONE);
	    lblTipoDeReproduo.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTipoDeReproduo.setBounds(10, 139, 120, 15);
	    lblTipoDeReproduo.setText("Tipo de Reprodu\u00E7\u00E3o:");
	    
	    radioElitista = new Button(shell, SWT.RADIO);
	    radioElitista.setBounds(10, 160, 61, 16);
	    radioElitista.setText("Elitista");
	    
	    radioRoleta = new Button(shell, SWT.RADIO);
	    radioRoleta.setBounds(77, 160, 61, 16);
	    radioRoleta.setText("Roleta");
	    
	    Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_2.setBounds(10, 182, 166, 7);
	    
	    lblTaxaDeMutacao = new Label(shell, SWT.NONE);
	    lblTaxaDeMutacao.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTaxaDeMutacao.setBounds(10, 195, 97, 15);
	    lblTaxaDeMutacao.setText("Taxa de Muta\u00E7\u00E3o:");
	    
	    Label label_3 = new Label(shell, SWT.NONE);
	    label_3.setText("%");
	    label_3.setBounds(140, 195, 16, 15);
	    
	    txtTaxaMutacao = new Text(shell, SWT.BORDER);
	    txtTaxaMutacao.setText("0");
	    txtTaxaMutacao.setBounds(108, 192, 31, 21);
	    
	    Label label_4 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_4.setBounds(10, 216, 166, 7);
	    
	    Label lblTaxaDeRecombinacao = new Label(shell, SWT.NONE);
	    lblTaxaDeRecombinacao.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTaxaDeRecombinacao.setBounds(10, 229, 129, 15);
	    lblTaxaDeRecombinacao.setText("Taxa de Recombina\u00E7\u00E3o:");
	    
	    txtTaxaRecombinacao = new Text(shell, SWT.BORDER);
	    txtTaxaRecombinacao.setText("100");
	    txtTaxaRecombinacao.setBounds(140, 226, 31, 21);
	    
	    Label label_5 = new Label(shell, SWT.NONE);
	    label_5.setBounds(174, 229, 16, 15);
	    label_5.setText("%");
	    
	    Label label_6 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_6.setBounds(10, 250, 166, 7);
	    
	    Button btnProcessar = new Button(shell, SWT.NONE);
	    btnProcessar.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		Controller controller = new Controller(
	    						   checkBoxPontoCorteAleatorio.getSelection(),
	    						   spinnerPontoCorte.getSelection(),
	    						   Integer.parseInt(txtTaxaMutacao.getText()),
	    						   Integer.parseInt(txtTaxaRecombinacao.getText()),
	    						   spinnerNumGeracoes.getSelection(),
	    						   spinnerTamanhoPopulacao.getSelection());
	    		
	    		controller.processar();
	    	}
	    });
	    btnProcessar.setBounds(10, 263, 120, 25);
	    btnProcessar.setText("Processar");
	    
	    table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	    table.setBounds(10, 294, 324, 257);
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);
	    
	    TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn.setToolTipText("N\u00FAmero da Gera\u00E7\u00E3o");
	    tblclmnNewColumn.setWidth(57);
	    tblclmnNewColumn.setText("Gera\u00E7\u00E3o");
	    
	    TableColumn tableColumn = new TableColumn(table, SWT.NONE);
	    tableColumn.setWidth(25);
	    tableColumn.setText("1");
	    
	    TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
	    tableColumn_1.setWidth(25);
	    tableColumn_1.setText("2");
	    
	    TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
	    tableColumn_2.setWidth(25);
	    tableColumn_2.setText("3");
	    
	    TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
	    tableColumn_3.setWidth(25);
	    tableColumn_3.setText("4");
	    
	    TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
	    tableColumn_4.setWidth(25);
	    tableColumn_4.setText("5");
	    
	    TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
	    tableColumn_5.setWidth(25);
	    tableColumn_5.setText("6");
	    
	    TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
	    tableColumn_6.setWidth(25);
	    tableColumn_6.setText("7");
	    
	    TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
	    tableColumn_7.setWidth(25);
	    tableColumn_7.setText("8");
	    
	    TableColumn tblclmnConflitos = new TableColumn(table, SWT.NONE);
	    tblclmnConflitos.setWidth(61);
	    tblclmnConflitos.setText("Conflitos");
	    
	    Label label_7 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_7.setBounds(10, 29, 166, 7);
	    
	    Label lblTamanhoPopulaoInicial = new Label(shell, SWT.NONE);
	    lblTamanhoPopulaoInicial.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTamanhoPopulaoInicial.setBounds(10, 8, 147, 15);
	    lblTamanhoPopulaoInicial.setText("Tamanho Popula\u00E7\u00E3o Inicial:");
	    
	    spinnerTamanhoPopulacao = new Spinner(shell, SWT.BORDER);
	    spinnerTamanhoPopulacao.setBounds(159, 5, 47, 22);
	    
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("Interface Parâmetros");
	}
}
