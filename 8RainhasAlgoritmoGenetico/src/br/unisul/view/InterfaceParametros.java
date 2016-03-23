package br.unisul.view;

import java.util.List;

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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.unisul.controller.Controller;
import br.unisul.model.domain.Geracao;
import br.unisul.model.domain.Individuo;
import br.unisul.model.enums.TipoSelecao;

public class InterfaceParametros {

	protected Shell shell;
	
	Button checkBoxPontoCorteAleatorio;
	Spinner spinnerPontoCorte;
	Label lblTaxaDeMutacao;
	Spinner spinnerNumGeracoes;
	Spinner spinnerTamanhoPopulacao;
	Button radioElitista;
	Button radioRoleta;
	Spinner spinnerDivisorPopulacao;
	private Text txtTaxaMutacao;
	private Text txtTaxaRecombinacao;
	private Table table;
	private Table tableResultados;
	private Label lblProcessando;
	
	private Controller controller;

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
	    lblPontoDeCorte.setBounds(10, 87, 87, 15);
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
	    checkBoxPontoCorteAleatorio.setBounds(101, 73, 75, 16);
	    checkBoxPontoCorteAleatorio.setText("Aleat\u00F3rio");
	    
	    spinnerPontoCorte = new Spinner(shell, SWT.BORDER);
	    spinnerPontoCorte.setBounds(101, 95, 55, 22);
	    
	    Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label.setBounds(10, 123, 166, 7);
	    
	    Label lblNumDeGeracoes = new Label(shell, SWT.NONE);
	    lblNumDeGeracoes.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblNumDeGeracoes.setBounds(10, 136, 90, 15);
	    lblNumDeGeracoes.setText("N\u00BA de Gera\u00E7\u00F5es:");
	    
	    spinnerNumGeracoes = new Spinner(shell, SWT.BORDER);
	    spinnerNumGeracoes.setMaximum(10000);
	    spinnerNumGeracoes.setBounds(101, 133, 75, 22);
	    
	    Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_1.setBounds(10, 157, 166, 7);
	    
	    Label lblTipoDeReproduo = new Label(shell, SWT.NONE);
	    lblTipoDeReproduo.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTipoDeReproduo.setBounds(10, 170, 120, 15);
	    lblTipoDeReproduo.setText("Tipo de Sele\u00E7\u00E3o Pais:");
	    
	    radioElitista = new Button(shell, SWT.RADIO);
	    radioElitista.setSelection(true);
	    radioElitista.setBounds(10, 191, 61, 16);
	    radioElitista.setText("Elitista");
	    
	    radioRoleta = new Button(shell, SWT.RADIO);
	    radioRoleta.setEnabled(false);
	    radioRoleta.setBounds(77, 191, 61, 16);
	    radioRoleta.setText("Roleta");
	    
	    Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_2.setBounds(10, 213, 166, 7);
	    
	    lblTaxaDeMutacao = new Label(shell, SWT.NONE);
	    lblTaxaDeMutacao.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTaxaDeMutacao.setBounds(10, 226, 97, 15);
	    lblTaxaDeMutacao.setText("Taxa de Muta\u00E7\u00E3o:");
	    
	    Label label_3 = new Label(shell, SWT.NONE);
	    label_3.setText("%");
	    label_3.setBounds(140, 226, 16, 15);
	    
	    txtTaxaMutacao = new Text(shell, SWT.BORDER);
	    txtTaxaMutacao.setText("0");
	    txtTaxaMutacao.setBounds(108, 223, 31, 21);
	    
	    Label label_4 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_4.setBounds(10, 247, 166, 7);
	    
	    Label lblTaxaDeRecombinacao = new Label(shell, SWT.NONE);
	    lblTaxaDeRecombinacao.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTaxaDeRecombinacao.setBounds(10, 260, 129, 15);
	    lblTaxaDeRecombinacao.setText("Taxa de Recombina\u00E7\u00E3o:");
	    
	    txtTaxaRecombinacao = new Text(shell, SWT.BORDER);
	    txtTaxaRecombinacao.setText("100");
	    txtTaxaRecombinacao.setBounds(140, 257, 31, 21);
	    
	    Label label_5 = new Label(shell, SWT.NONE);
	    label_5.setBounds(174, 260, 16, 15);
	    label_5.setText("%");
	    
	    Label label_6 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_6.setBounds(10, 281, 166, 7);
	    
	    table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	    table.setBounds(383, 8, 391, 257);
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);
	    
	    Button btnProcessar = new Button(shell, SWT.NONE);
	    btnProcessar.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		controller = new Controller(
	    						   checkBoxPontoCorteAleatorio.getSelection(),
	    						   spinnerPontoCorte.getSelection(),
	    						   Integer.parseInt(txtTaxaMutacao.getText()),
	    						   Integer.parseInt(txtTaxaRecombinacao.getText()),
	    						   spinnerNumGeracoes.getSelection(),
	    						   spinnerTamanhoPopulacao.getSelection(),
	    						   spinnerDivisorPopulacao.getSelection(),
	    						   radioElitista.getSelection() ? TipoSelecao.ELITISTA : TipoSelecao.ROLETA);

	    		lblProcessando.setText("Processando...");
	    		
	    		List<Geracao> geracoes = controller.processar();
	    		
	    		
	    		limparTabelas();
	    		preencherTabela(geracoes);
	    	}
	    });
	    btnProcessar.setBounds(10, 310, 120, 25);
	    btnProcessar.setText("Processar");
	    
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
	    
	    TableColumn tblclmnMutante = new TableColumn(table, SWT.NONE);
	    tblclmnMutante.setWidth(67);
	    tblclmnMutante.setText("Mutante");
	    
	    Label label_7 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_7.setBounds(10, 60, 166, 7);
	    
	    Label lblTamanhoPopulaoInicial = new Label(shell, SWT.NONE);
	    lblTamanhoPopulaoInicial.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTamanhoPopulaoInicial.setBounds(10, 8, 147, 15);
	    lblTamanhoPopulaoInicial.setText("Tamanho Popula\u00E7\u00E3o Inicial:");
	    
	    spinnerTamanhoPopulacao = new Spinner(shell, SWT.BORDER);
	    spinnerTamanhoPopulacao.setMaximum(10000000);
	    spinnerTamanhoPopulacao.setBounds(159, 5, 66, 22);
	    
	    Label lblDivisorDePopulao = new Label(shell, SWT.NONE);
	    lblDivisorDePopulao.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblDivisorDePopulao.setBounds(34, 29, 120, 15);
	    lblDivisorDePopulao.setText("Divisor de popula\u00E7\u00E3o:");
	    
	    spinnerDivisorPopulacao = new Spinner(shell, SWT.BORDER);
	    spinnerDivisorPopulacao.setMaximum(7);
	    spinnerDivisorPopulacao.setMinimum(1);
	    spinnerDivisorPopulacao.setBounds(159, 29, 47, 22);
	    
	    tableResultados = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
	    tableResultados.setLinesVisible(true);
	    tableResultados.setHeaderVisible(true);
	    tableResultados.setBounds(383, 294, 391, 257);
	    
	    TableColumn tableColumn_9 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_9.setWidth(25);
	    tableColumn_9.setText("1");
	    
	    TableColumn tableColumn_10 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_10.setWidth(25);
	    tableColumn_10.setText("2");
	    
	    TableColumn tableColumn_11 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_11.setWidth(25);
	    tableColumn_11.setText("3");
	    
	    TableColumn tableColumn_12 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_12.setWidth(25);
	    tableColumn_12.setText("4");
	    
	    TableColumn tableColumn_13 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_13.setWidth(25);
	    tableColumn_13.setText("5");
	    
	    TableColumn tableColumn_14 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_14.setWidth(25);
	    tableColumn_14.setText("6");
	    
	    TableColumn tableColumn_15 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_15.setWidth(25);
	    tableColumn_15.setText("7");
	    
	    TableColumn tableColumn_16 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_16.setWidth(25);
	    tableColumn_16.setText("8");
	    
	    TableColumn tableColumn_17 = new TableColumn(tableResultados, SWT.NONE);
	    tableColumn_17.setWidth(61);
	    tableColumn_17.setText("Conflitos");
	    
	    TableColumn tblclmnMutante_1 = new TableColumn(tableResultados, SWT.NONE);
	    tblclmnMutante_1.setWidth(67);
	    tblclmnMutante_1.setText("Mutante");
	    
	    lblProcessando = new Label(shell, SWT.NONE);
	    lblProcessando.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
	    lblProcessando.setFont(SWTResourceManager.getFont("Segoe UI", 36, SWT.NORMAL));
	    lblProcessando.setBounds(10, 421, 307, 92);
	    
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void preencherTabela(List<Geracao> geracoes) {
		
		for (Geracao geracao : geracoes) {
			
			for (Individuo individuo : geracao.getPopulacao()) {
				
				TableItem linha = new TableItem(table, SWT.NONE);
				
				linha.setText(0, String.valueOf(geracao.getNumeroGeracao()));
				linha.setText(1, String.valueOf(individuo.array[0]));
				linha.setText(2, String.valueOf(individuo.array[1]));
				linha.setText(3, String.valueOf(individuo.array[2]));
				linha.setText(4, String.valueOf(individuo.array[3]));
				linha.setText(5, String.valueOf(individuo.array[4]));
				linha.setText(6, String.valueOf(individuo.array[5]));
				linha.setText(7, String.valueOf(individuo.array[6]));
				linha.setText(8, String.valueOf(individuo.array[7]));
				
				linha.setText(9, String.valueOf(individuo.qtdColisoes));
				linha.setText(10, individuo.isMutante() ? "SIM" : "NÃO");
				
				if(individuo.qtdColisoes == 0) {
					linha.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
				}
				
			}
			
		}
		
		for (Individuo individuo : controller.getSolucoes()) {
			
			TableItem linhaResultado = new TableItem(tableResultados, SWT.NONE);
			linhaResultado.setText(0, String.valueOf(individuo.array[0]));
			linhaResultado.setText(1, String.valueOf(individuo.array[1]));
			linhaResultado.setText(2, String.valueOf(individuo.array[2]));
			linhaResultado.setText(3, String.valueOf(individuo.array[3]));
			linhaResultado.setText(4, String.valueOf(individuo.array[4]));
			linhaResultado.setText(5, String.valueOf(individuo.array[5]));
			linhaResultado.setText(6, String.valueOf(individuo.array[6]));
			linhaResultado.setText(7, String.valueOf(individuo.array[7]));
			linhaResultado.setText(8, String.valueOf(individuo.qtdColisoes));
			linhaResultado.setText(9, individuo.isMutante() ? "SIM" : "NÃO");
			
			linhaResultado.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		}
		
		lblProcessando.setText("");
		
	}
	
	private void limparTabelas() {
		table.clearAll();
		tableResultados.clearAll();
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
