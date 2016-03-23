package br.unisul.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
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
	private Label lblProcessando;
	private Controller controller;
	
	private StyledText styledText;
	private StyledText styledTextResultados;

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
	    		
	    		
	    		preencherTabela(geracoes);
	    	}
	    });
	    btnProcessar.setBounds(10, 310, 120, 25);
	    btnProcessar.setText("Processar");
	    
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
	    
	    lblProcessando = new Label(shell, SWT.NONE);
	    lblProcessando.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
	    lblProcessando.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
	    lblProcessando.setBounds(10, 421, 260, 92);
	    
	    styledText = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
	    styledText.setDoubleClickEnabled(false);
	    styledText.setEditable(false);
	    styledText.setBounds(273, 8, 485, 260);
	    
	    styledTextResultados = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
	    styledTextResultados.setDoubleClickEnabled(false);
	    styledTextResultados.setEditable(false);
	    styledTextResultados.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
	    styledTextResultados.setBounds(273, 294, 485, 260);
	    
	    Label lblSoluesEncontradas = new Label(shell, SWT.NONE);
	    lblSoluesEncontradas.setBounds(273, 274, 129, 15);
	    lblSoluesEncontradas.setText("Solu\u00E7\u00F5es Encontradas");
	    
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void preencherTabela(List<Geracao> geracoes) {
		
		styledText.setText("");
		styledTextResultados.setText("");
		
		for (Geracao geracao : geracoes) {
			
			styledText.setText(styledText.getText() + gerarTextoInformacaoGeracao(geracao));
			
		}
			
		if(controller.getSolucoes().size() > 0) {
			gerarTextoInformacaoResultado();
		} else {
			styledTextResultados.setText("Nenhuma solução encontrada.");
		}
		
		lblProcessando.setText("");
		
	}
	
	private void gerarTextoInformacaoResultado() {
		
		for (Individuo individuo : controller.getSolucoes()) {
			
			StringBuilder texto = new StringBuilder();

			texto.append("Geração = " + individuo.getNumeroGeracao());
			texto.append(" / Genótipo = " + arrayToString(individuo.array));
			texto.append(" / Colisões = " + individuo.qtdColisoes);
			texto.append(" / Mutante = " + (individuo.isMutante() ? "SIM" : "NÃO"));
			texto.append(System.getProperty("line.separator"));
			
			styledTextResultados.setText(styledTextResultados.getText() + texto.toString());
			
		}

	}
	
	private String gerarTextoInformacaoGeracao(Geracao geracao) {
		
		StringBuilder texto = new StringBuilder();
		
		for (Individuo individuo : geracao.getPopulacao()) {

			texto.append("Geração = " + geracao.getNumeroGeracao());
			texto.append(" / Genótipo = " + arrayToString(individuo.array));
			texto.append(" / Colisões = " + individuo.qtdColisoes);
			texto.append(" / Mutante = " + (individuo.isMutante() ? "SIM" : "NÃO"));
			texto.append(System.getProperty("line.separator"));
			
		}
		
		return texto.toString();
	}
	
	private String arrayToString(int[] array) {
		StringBuilder texto = new StringBuilder();
		
		for(int i = 0; i < array.length; i++) {
			texto.append(array[i]);
			
			if(i != array.length-1)
				texto.append(", ");
		}
		
		return texto.toString();
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
