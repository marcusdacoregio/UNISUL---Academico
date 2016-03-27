package br.unisul.view;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainView {

	protected Shell shlAlgoritmoGentico;
	private Text txtPopulacaoInicial;
	private Text txtPrimeiroPontoCorte;
	private Text txtSegundoPontoDeCorte;
	
	private Label label_1;
	private Label label_2;
	
	private Button radioPontoUnico;
	private Button radioPontoDuplo;
	
	private Button checkPontoAleatorio;
	private Label label_3;
	private Label lblCapacidadeMochila;
	private Text txtCapacidadeMochila;
	private Label label_4;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainView window = new MainView();
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
		
		Monitor primary = display.getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shlAlgoritmoGentico.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shlAlgoritmoGentico.setLocation(x, y);
	    
	    Composite composite = new Composite(shlAlgoritmoGentico, SWT.NONE);
	    composite.setBounds(0, 0, 784, 561);
	    
	    Label lblPopulacaoInicial = new Label(composite, SWT.NONE);
	    lblPopulacaoInicial.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblPopulacaoInicial.setBounds(10, 10, 99, 15);
	    lblPopulacaoInicial.setText("Popula\u00E7\u00E3o Inicial:");
	    
	    txtPopulacaoInicial = new Text(composite, SWT.BORDER);
	    txtPopulacaoInicial.setText("100");
	    txtPopulacaoInicial.setBounds(115, 4, 76, 21);
	    
	    Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label.setBounds(10, 31, 199, 2);
	    
	    Label lblPontoDeCorte = new Label(composite, SWT.NONE);
	    lblPontoDeCorte.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblPontoDeCorte.setBounds(10, 39, 99, 15);
	    lblPontoDeCorte.setText("Ponto de Corte:");
	    
	    radioPontoUnico = new Button(composite, SWT.RADIO);
	    radioPontoUnico.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		if(checkPontoAleatorio.getSelection() == false){
	    			txtPrimeiroPontoCorte.setEnabled(true);
	    			txtSegundoPontoDeCorte.setEnabled(false);
	    		}
	    	}
	    });
	    radioPontoUnico.setBounds(10, 60, 54, 16);
	    radioPontoUnico.setText("\u00DAnico");
	    
	    radioPontoDuplo = new Button(composite, SWT.RADIO);
	    radioPontoDuplo.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		if(checkPontoAleatorio.getSelection() == false){
	    			txtPrimeiroPontoCorte.setEnabled(true);
	    			txtSegundoPontoDeCorte.setEnabled(true);
	    		}
	    	}
	    });
	    radioPontoDuplo.setSelection(true);
	    radioPontoDuplo.setBounds(70, 60, 54, 16);
	    radioPontoDuplo.setText("Duplo");
	    
	    txtPrimeiroPontoCorte = new Text(composite, SWT.BORDER);
	    txtPrimeiroPontoCorte.setBounds(27, 82, 37, 21);
	    
	    txtSegundoPontoDeCorte = new Text(composite, SWT.BORDER);
	    txtSegundoPontoDeCorte.setBounds(87, 82, 37, 21);
	    
	    label_1 = new Label(composite, SWT.NONE);
	    label_1.setBounds(10, 85, 11, 15);
	    label_1.setText("1\u00BA");
	    
	    label_2 = new Label(composite, SWT.NONE);
	    label_2.setText("2\u00BA");
	    label_2.setBounds(70, 85, 11, 15);
	    
	    checkPontoAleatorio = new Button(composite, SWT.CHECK);
	    checkPontoAleatorio.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		if(checkPontoAleatorio.getSelection() == false) {
	    			txtPrimeiroPontoCorte.setEnabled(true);
	    			txtSegundoPontoDeCorte.setEnabled(radioPontoUnico.getSelection() == true ? false : true);
	    		} else {
	    			txtPrimeiroPontoCorte.setEnabled(false);
	    			txtSegundoPontoDeCorte.setEnabled(false);
	    		}
	    	}
	    });
	    checkPontoAleatorio.setBounds(10, 109, 93, 16);
	    checkPontoAleatorio.setText("Aleat\u00F3rio");
	    
	    label_3 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_3.setBounds(10, 131, 199, 2);
	    
	    lblCapacidadeMochila = new Label(composite, SWT.NONE);
	    lblCapacidadeMochila.setText("Capacidade da Mochila:");
	    lblCapacidadeMochila.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblCapacidadeMochila.setBounds(10, 139, 133, 15);
	    
	    txtCapacidadeMochila = new Text(composite, SWT.BORDER);
	    txtCapacidadeMochila.setBounds(144, 136, 65, 21);
	    
	    label_4 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_4.setBounds(10, 160, 199, 2);
		
		shlAlgoritmoGentico.open();
		shlAlgoritmoGentico.layout();
		while (!shlAlgoritmoGentico.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAlgoritmoGentico = new Shell();
		shlAlgoritmoGentico.setSize(800, 600);
		shlAlgoritmoGentico.setText("Algoritmo Gen\u00E9tico");

	}
}
