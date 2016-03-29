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
	private Text txtInitialPopulation;
	private Text txtfirstCutPoint;
	private Text txtSecondCutPoint;
	
	private Label label_1;
	private Label label_2;
	
	private Button radioSingleCutPoint;
	private Button radioDualCutPoint;
	
	private Button checkRandomCutPoint;
	private Label label_3;
	private Label lblPesoMochila;
	private Text txtMaxKnapsackWeight;
	private Label label_4;
	private Label lblVolumeMaximoMochila;
	private Text txtMaxKnapsackVolume;
	private Text txtMaxPopulationSize;
	private Text txtDisposalAmount;
	private Text txtMinPopulationSize;
	private Text txtIncrementAmount;
	private Label lblTaxaDeRecombinao;
	private Text txtRecombinationRate;
	private Label label_6;
	private Label label_8;
	private Label lblTotalDeGeraes;
	private Text txtTotalGeneration;

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
	    
	    txtInitialPopulation = new Text(composite, SWT.BORDER);
	    txtInitialPopulation.setText("100");
	    txtInitialPopulation.setBounds(115, 4, 76, 21);
	    
	    Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label.setBounds(10, 31, 199, 2);
	    
	    Label lblPontoDeCorte = new Label(composite, SWT.NONE);
	    lblPontoDeCorte.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblPontoDeCorte.setBounds(10, 39, 99, 15);
	    lblPontoDeCorte.setText("Ponto de Corte:");
	    
	    radioSingleCutPoint = new Button(composite, SWT.RADIO);
	    radioSingleCutPoint.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		if(checkRandomCutPoint.getSelection() == false){
	    			txtfirstCutPoint.setEnabled(true);
	    			txtSecondCutPoint.setEnabled(false);
	    		}
	    	}
	    });
	    radioSingleCutPoint.setBounds(10, 60, 54, 16);
	    radioSingleCutPoint.setText("\u00DAnico");
	    
	    radioDualCutPoint = new Button(composite, SWT.RADIO);
	    radioDualCutPoint.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		if(checkRandomCutPoint.getSelection() == false){
	    			txtfirstCutPoint.setEnabled(true);
	    			txtSecondCutPoint.setEnabled(true);
	    		}
	    	}
	    });
	    radioDualCutPoint.setSelection(true);
	    radioDualCutPoint.setBounds(70, 60, 54, 16);
	    radioDualCutPoint.setText("Duplo");
	    
	    txtfirstCutPoint = new Text(composite, SWT.BORDER);
	    txtfirstCutPoint.setBounds(27, 82, 37, 21);
	    
	    txtSecondCutPoint = new Text(composite, SWT.BORDER);
	    txtSecondCutPoint.setBounds(87, 82, 37, 21);
	    
	    label_1 = new Label(composite, SWT.NONE);
	    label_1.setBounds(10, 85, 11, 15);
	    label_1.setText("1\u00BA");
	    
	    label_2 = new Label(composite, SWT.NONE);
	    label_2.setText("2\u00BA");
	    label_2.setBounds(70, 85, 11, 15);
	    
	    checkRandomCutPoint = new Button(composite, SWT.CHECK);
	    checkRandomCutPoint.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		if(checkRandomCutPoint.getSelection() == false) {
	    			txtfirstCutPoint.setEnabled(true);
	    			txtSecondCutPoint.setEnabled(radioSingleCutPoint.getSelection() == true ? false : true);
	    		} else {
	    			txtfirstCutPoint.setEnabled(false);
	    			txtSecondCutPoint.setEnabled(false);
	    		}
	    	}
	    });
	    checkRandomCutPoint.setBounds(10, 109, 93, 16);
	    checkRandomCutPoint.setText("Aleat\u00F3rio");
	    
	    label_3 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_3.setBounds(10, 131, 199, 2);
	    
	    lblPesoMochila = new Label(composite, SWT.NONE);
	    lblPesoMochila.setText("Peso M\u00E1ximo da Mochila:");
	    lblPesoMochila.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblPesoMochila.setBounds(10, 139, 144, 15);
	    
	    txtMaxKnapsackWeight = new Text(composite, SWT.BORDER);
	    txtMaxKnapsackWeight.setText("80");
	    txtMaxKnapsackWeight.setBounds(154, 136, 65, 21);
	    
	    label_4 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_4.setBounds(10, 189, 199, 2);
	    
	    lblVolumeMaximoMochila = new Label(composite, SWT.NONE);
	    lblVolumeMaximoMochila.setText("Volume M\u00E1ximo da Mochila:");
	    lblVolumeMaximoMochila.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblVolumeMaximoMochila.setBounds(10, 168, 155, 15);
	    
	    txtMaxKnapsackVolume = new Text(composite, SWT.BORDER);
	    txtMaxKnapsackVolume.setText("80");
	    txtMaxKnapsackVolume.setBounds(174, 163, 65, 21);
	    
	    Label lblQuandoPopulaoChegar = new Label(composite, SWT.NONE);
	    lblQuandoPopulaoChegar.setBounds(9, 197, 177, 15);
	    lblQuandoPopulaoChegar.setText("Quando popula\u00E7\u00E3o for maior que");
	    
	    Label lblRemover = new Label(composite, SWT.NONE);
	    lblRemover.setBounds(235, 197, 44, 15);
	    lblRemover.setText("remover");
	    
	    Label lblIndivduos = new Label(composite, SWT.NONE);
	    lblIndivduos.setBounds(325, 197, 55, 15);
	    lblIndivduos.setText("indiv\u00EDduos");
	    
	    txtMaxPopulationSize = new Text(composite, SWT.BORDER);
	    txtMaxPopulationSize.setText("600");
	    txtMaxPopulationSize.setBounds(192, 197, 37, 21);
	    
	    txtDisposalAmount = new Text(composite, SWT.BORDER);
	    txtDisposalAmount.setText("50");
	    txtDisposalAmount.setBounds(282, 197, 37, 21);
	    
	    Label lblQuandoPopulaoFor = new Label(composite, SWT.NONE);
	    lblQuandoPopulaoFor.setText("Quando popula\u00E7\u00E3o for menor que");
	    lblQuandoPopulaoFor.setBounds(9, 227, 182, 15);
	    
	    txtMinPopulationSize = new Text(composite, SWT.BORDER);
	    txtMinPopulationSize.setText("50");
	    txtMinPopulationSize.setBounds(192, 224, 37, 21);
	    
	    Label lblAdicionar = new Label(composite, SWT.NONE);
	    lblAdicionar.setText("adicionar");
	    lblAdicionar.setBounds(235, 224, 54, 15);
	    
	    txtIncrementAmount = new Text(composite, SWT.BORDER);
	    txtIncrementAmount.setText("50");
	    txtIncrementAmount.setBounds(292, 224, 37, 21);
	    
	    Label label_7 = new Label(composite, SWT.NONE);
	    label_7.setText("indiv\u00EDduos");
	    label_7.setBounds(336, 227, 55, 15);
	    
	    Label label_5 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_5.setBounds(10, 251, 199, 2);
	    
	    lblTaxaDeRecombinao = new Label(composite, SWT.NONE);
	    lblTaxaDeRecombinao.setText("Taxa de Recombina\u00E7\u00E3o:");
	    lblTaxaDeRecombinao.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTaxaDeRecombinao.setBounds(10, 259, 129, 15);
	    
	    txtRecombinationRate = new Text(composite, SWT.BORDER);
	    txtRecombinationRate.setText("90");
	    txtRecombinationRate.setBounds(145, 256, 54, 21);
	    
	    label_6 = new Label(composite, SWT.NONE);
	    label_6.setBounds(205, 259, 16, 15);
	    label_6.setText("%");
	    
	    label_8 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
	    label_8.setBounds(10, 283, 199, 2);
	    
	    lblTotalDeGeraes = new Label(composite, SWT.NONE);
	    lblTotalDeGeraes.setText("Total de Gera\u00E7\u00F5es:");
	    lblTotalDeGeraes.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
	    lblTotalDeGeraes.setBounds(10, 291, 110, 15);
	    
	    txtTotalGeneration = new Text(composite, SWT.BORDER);
	    txtTotalGeneration.setText("100");
	    txtTotalGeneration.setBounds(115, 288, 54, 21);
		
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
