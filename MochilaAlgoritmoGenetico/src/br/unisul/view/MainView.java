package br.unisul.view;

import java.io.File;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.unisul.controller.KnapsackController;
import br.unisul.model.domain.Item;
import br.unisul.model.domain.Knapsack;
import br.unisul.model.dto.ParametersDTO;
import br.unisul.model.enums.CutPointType;

public class MainView {

	protected Shell shlAlgoritmoGentico;
	private Text txtInitialPopulation;
	private Text txtFirstCutPoint;
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
	private StyledText lblUploadInformation;
	
	private Button btnProcessar;
	private Composite composite; 

	private KnapsackController controller;
	private Table tableItem;
	private Label lblMelhorMochilaEncontrada;
	private Label lblVolume;
	private Label lblPeso;
	private Label lblValor;
	private Text txtBestVolume;
	private Text txtBestWeight;
	private Text txtBestValue;
	private Label lblNewLabel;
	private Label lblProcessando;
	
	private FileDialog fileDialog;
	private String csvPath;
	private Button btnFileUpload;
	
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
	    
	    composite = new Composite(shlAlgoritmoGentico, SWT.NONE);
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
	    			txtFirstCutPoint.setEnabled(true);
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
	    			txtFirstCutPoint.setEnabled(true);
	    			txtSecondCutPoint.setEnabled(true);
	    		}
	    	}
	    });
	    radioDualCutPoint.setSelection(true);
	    radioDualCutPoint.setBounds(70, 60, 54, 16);
	    radioDualCutPoint.setText("Duplo");
	    
	    txtFirstCutPoint = new Text(composite, SWT.BORDER);
	    txtFirstCutPoint.setEnabled(false);
	    txtFirstCutPoint.setBounds(27, 82, 37, 21);
	    
	    txtSecondCutPoint = new Text(composite, SWT.BORDER);
	    txtSecondCutPoint.setEnabled(false);
	    txtSecondCutPoint.setBounds(87, 82, 37, 21);
	    
	    label_1 = new Label(composite, SWT.NONE);
	    label_1.setBounds(10, 85, 11, 15);
	    label_1.setText("1\u00BA");
	    
	    label_2 = new Label(composite, SWT.NONE);
	    label_2.setText("2\u00BA");
	    label_2.setBounds(70, 85, 11, 15);
	    
	    checkRandomCutPoint = new Button(composite, SWT.CHECK);
	    checkRandomCutPoint.setSelection(true);
	    checkRandomCutPoint.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		if(checkRandomCutPoint.getSelection() == false) {
	    			txtFirstCutPoint.setEnabled(true);
	    			txtSecondCutPoint.setEnabled(radioSingleCutPoint.getSelection() == true ? false : true);
	    		} else {
	    			txtFirstCutPoint.setEnabled(false);
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
	    lblQuandoPopulaoChegar.setBounds(10, 200, 177, 15);
	    lblQuandoPopulaoChegar.setText("Quando popula\u00E7\u00E3o for maior que");
	    
	    Label lblRemover = new Label(composite, SWT.NONE);
	    lblRemover.setBounds(249, 200, 44, 15);
	    lblRemover.setText("remover");
	    
	    Label lblIndivduos = new Label(composite, SWT.NONE);
	    lblIndivduos.setBounds(342, 200, 55, 15);
	    lblIndivduos.setText("indiv\u00EDduos");
	    
	    txtMaxPopulationSize = new Text(composite, SWT.BORDER);
	    txtMaxPopulationSize.setText("600");
	    txtMaxPopulationSize.setBounds(192, 197, 51, 21);
	    
	    txtDisposalAmount = new Text(composite, SWT.BORDER);
	    txtDisposalAmount.setText("50");
	    txtDisposalAmount.setBounds(299, 197, 37, 21);
	    
	    Label lblQuandoPopulaoFor = new Label(composite, SWT.NONE);
	    lblQuandoPopulaoFor.setText("Quando popula\u00E7\u00E3o for menor que");
	    lblQuandoPopulaoFor.setBounds(9, 227, 182, 15);
	    
	    txtMinPopulationSize = new Text(composite, SWT.BORDER);
	    txtMinPopulationSize.setText("50");
	    txtMinPopulationSize.setBounds(192, 224, 51, 21);
	    
	    Label lblAdicionar = new Label(composite, SWT.NONE);
	    lblAdicionar.setText("adicionar");
	    lblAdicionar.setBounds(245, 227, 54, 15);
	    
	    txtIncrementAmount = new Text(composite, SWT.BORDER);
	    txtIncrementAmount.setText("50");
	    txtIncrementAmount.setBounds(299, 224, 37, 21);
	    
	    Label label_7 = new Label(composite, SWT.NONE);
	    label_7.setText("indiv\u00EDduos");
	    label_7.setBounds(342, 227, 55, 15);
	    
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
	    lblTotalDeGeraes.setBounds(10, 291, 102, 15);
	    
	    txtTotalGeneration = new Text(composite, SWT.BORDER);
	    txtTotalGeneration.setText("100");
	    txtTotalGeneration.setBounds(115, 288, 54, 21);
	    
	    btnProcessar = new Button(composite, SWT.NONE);
	    btnProcessar.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		ParametersDTO parameters = null;
	    		
	    		try {
		    		boolean randomCutPoint = checkRandomCutPoint.getSelection() ? true : false;
		    		int initialPopulationSize = Integer.parseInt(txtInitialPopulation.getText());
		    		int firstCutPointPosition = randomCutPoint ? 0 : Integer.parseInt(txtFirstCutPoint.getText());
		    		int secondCutPointPosition = randomCutPoint ? 0:  Integer.parseInt(txtSecondCutPoint.getText());
		    		CutPointType cutPointType = radioSingleCutPoint.getSelection() ? CutPointType.SINGLE : CutPointType.DUAL;
		    		double knapsackWeight = Double.parseDouble(txtMaxKnapsackWeight.getText());
		    		double knapsackVolume = Double.parseDouble(txtMaxKnapsackVolume.getText());
		    		int maximumPopulationSize = Integer.parseInt(txtMaxPopulationSize.getText());
		    		int minimumPopulationSize = Integer.parseInt(txtMinPopulationSize.getText());
		    		int disposalAmount = Integer.parseInt(txtDisposalAmount.getText());
		    		int incrementAmount = Integer.parseInt(txtIncrementAmount.getText());
		    		int totalGeneration = Integer.parseInt(txtTotalGeneration.getText());
		    		int recombinationRate = Integer.parseInt(txtRecombinationRate.getText());
	    		
		    		parameters = new ParametersDTO(initialPopulationSize, firstCutPointPosition, secondCutPointPosition, 
		    				randomCutPoint, cutPointType, knapsackWeight, knapsackVolume, maximumPopulationSize, 
		    				minimumPopulationSize, disposalAmount, incrementAmount, totalGeneration, recombinationRate, csvPath);

		    		
		    		lblProcessando.setText("Processando...");
		    		
	    			controller = new KnapsackController(parameters);
		    		controller.process();
		    		
		    		txtFirstCutPoint.setText(String.valueOf(controller.getFirstCutPointPosition()));
		    		txtSecondCutPoint.setText(String.valueOf(controller.getSecondCutPointPosition()));
		    		
		    		Knapsack knapsack = controller.getBestKnapsack();
		    		
		    		setItensOnTable(controller.getMapItens());
		    		
		    		highlightTableItens(knapsack.itemArray);
		    		
		    		txtBestValue.setText(knapsack.getValue() + " / " + controller.getMaxKnapsackValue());
		    		txtBestVolume.setText(knapsack.getVolume() + " / " + txtMaxKnapsackVolume.getText());
		    		txtBestWeight.setText(knapsack.getWeight() + " / " + txtMaxKnapsackWeight.getText());
	    		} catch (Exception e) {
	    			lblNewLabel.setText("Alguma coisa deu errado, revise os parâmetros");
	    			e.printStackTrace();
	    		}
	    		
	    		lblProcessando.setText("");
	    	}

	    });
	    btnProcessar.setBounds(10, 325, 199, 25);
	    btnProcessar.setText("Processar");
	    
	    tableItem = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
	    tableItem.setBounds(403, 44, 371, 507);
	    tableItem.setHeaderVisible(true);
	    tableItem.setLinesVisible(true);
	    
	    TableColumn tblclmnArtigo = new TableColumn(tableItem, SWT.NONE);
	    tblclmnArtigo.setWidth(100);
	    tblclmnArtigo.setText("Artigo");
	    
	    TableColumn tblclmnVolume = new TableColumn(tableItem, SWT.NONE);
	    tblclmnVolume.setWidth(100);
	    tblclmnVolume.setText("Volume");
	    
	    TableColumn tblclmnPeso = new TableColumn(tableItem, SWT.NONE);
	    tblclmnPeso.setWidth(100);
	    tblclmnPeso.setText("Peso");
	    
	    TableColumn tblclmnValor = new TableColumn(tableItem, SWT.NONE);
	    tblclmnValor.setWidth(65);
	    tblclmnValor.setText("Valor");
	    
	    lblMelhorMochilaEncontrada = new Label(composite, SWT.NONE);
	    lblMelhorMochilaEncontrada.setBounds(235, 10, 147, 15);
	    lblMelhorMochilaEncontrada.setText("Melhor Mochila Encontrada");
	    
	    lblVolume = new Label(composite, SWT.NONE);
	    lblVolume.setBounds(403, 10, 44, 15);
	    lblVolume.setText("Volume");
	    
	    lblPeso = new Label(composite, SWT.NONE);
	    lblPeso.setBounds(535, 10, 25, 15);
	    lblPeso.setText("Peso");
	    
	    lblValor = new Label(composite, SWT.NONE);
	    lblValor.setBounds(651, 10, 31, 15);
	    lblValor.setText("Valor");
	    
	    txtBestVolume = new Text(composite, SWT.BORDER);
	    txtBestVolume.setEditable(false);
	    txtBestVolume.setBounds(453, 7, 76, 21);
	    
	    txtBestWeight = new Text(composite, SWT.BORDER);
	    txtBestWeight.setEditable(false);
	    txtBestWeight.setBounds(566, 7, 76, 21);
	    
	    txtBestValue = new Text(composite, SWT.BORDER);
	    txtBestValue.setEditable(false);
	    txtBestValue.setBounds(686, 7, 76, 21);
	    
	    lblNewLabel = new Label(composite, SWT.NONE);
	    lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
	    lblNewLabel.setBounds(9, 397, 371, 39);
	    
	    lblProcessando = new Label(composite, SWT.NONE);
	    lblProcessando.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
	    lblProcessando.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
	    lblProcessando.setBounds(26, 452, 354, 59);
	    
	    btnFileUpload = new Button(composite, SWT.NONE);
	    btnFileUpload.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent event) {
	    		fileDialog = new FileDialog(shlAlgoritmoGentico);
	    		fileDialog.setFilterExtensions(new String[]{"*.csv"});
	    		fileDialog.setText("Abrir arquivo CSV");
	    		csvPath = fileDialog.open();
	    		
	    		if(csvPath != null && !csvPath.isEmpty()) {
	    			File file = new File(csvPath);
	    			if(!file.exists()) {
	    				lblUploadInformation.setText("Este arquivo não existe");
	    			} else {
	    				lblUploadInformation.setText("Arquivo carregado no sistema!");
	    			}
	    		} else {
	    			lblUploadInformation.setText("Nenhum arquivo informado.\nO sistema usará o padrão");
	    		}
	    	}
	    });
	    btnFileUpload.setBounds(231, 295, 144, 21);
	    btnFileUpload.setText("Escolha uma planilha");
	    
	    lblUploadInformation = new StyledText(composite, SWT.BORDER);
	    lblUploadInformation.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
	    lblUploadInformation.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
	    lblUploadInformation.setText("Nenhum arquivo selecionado...\r\nSe voc\u00EA n\u00E3o selecionar nenhum\r\narquivo, o sistema usar\u00E1 o padr\u00E3o\r\nque est\u00E1 em sua raiz.");
	    lblUploadInformation.setEditable(false);
	    lblUploadInformation.setEnabled(false);
	    lblUploadInformation.setBounds(215, 322, 182, 69);
		
		shlAlgoritmoGentico.open();
		shlAlgoritmoGentico.layout();
		while (!shlAlgoritmoGentico.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void highlightTableItens(int[] itemArray) {
		
		TableItem[] itens = tableItem.getItems();
		
		for (int i = 0; i < itemArray.length; i++) {
			
			if(itemArray[i] == 1) {
				TableItem linha = itens[i];
				linha.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
			}
			
		}
		
	}
	
	private void setItensOnTable(Map<Integer, Item> mapItens) {
		
		tableItem.removeAll();
		
		for (Item item : mapItens.values()) {
			
			TableItem linha = new TableItem(tableItem, SWT.NONE);
			linha.setText(0, item.getName());
			linha.setText(1, String.valueOf(item.getVolume()));
			linha.setText(2, String.valueOf(item.getWeight()));
			linha.setText(3, String.valueOf(item.getValue()));
			
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
