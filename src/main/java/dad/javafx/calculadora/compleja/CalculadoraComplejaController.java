package dad.javafx.calculadora.compleja;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraComplejaController extends Application {

	private TextField operandoAText, operandoBText, operandoCText, operandoDText, resultadoRealText,
			resultadoImaginarioText;
	private ComboBox<String> operacionesCombo;
	private final String[] OPERADORES = { "+", "-", "*", "/" };

	private StringProperty operacionCombo = new SimpleStringProperty();
	private Complejo primerNumeroComplejo = new Complejo();
	private Complejo segundoNumeroComplejo = new Complejo();
	private Complejo resultadoNumeroComplejo = new Complejo();

	@Override
	public void start(Stage primaryStage) throws Exception {

		operandoAText = new TextField();
		operandoAText.setMaxWidth(50);

		operandoBText = new TextField();
		operandoBText.setMaxWidth(50);

		operandoCText = new TextField();
		operandoCText.setMaxWidth(50);

		operandoDText = new TextField();
		operandoDText.setMaxWidth(50);

		resultadoRealText = new TextField();
		resultadoRealText.setDisable(true);
		resultadoRealText.setMaxWidth(50);

		resultadoImaginarioText = new TextField();
		resultadoImaginarioText.setDisable(true);
		resultadoImaginarioText.setMaxWidth(50);

		operacionesCombo = new ComboBox<String>();
		operacionesCombo.getItems().addAll(OPERADORES);
		operacionesCombo.setMaxWidth(90);
		operacionesCombo.getSelectionModel().select(OPERADORES[0]);

		Separator separator = new Separator();

		VBox operecionesVBox = new VBox(5, operacionesCombo);
		operecionesVBox.setAlignment(Pos.CENTER);

		HBox operandoAHBox = new HBox(5, operandoAText, new Label("+"), operandoBText, new Label("i"));
		operandoAHBox.setAlignment(Pos.CENTER);

		HBox operandoBHBox = new HBox(5, operandoCText, new Label("+"), operandoDText, new Label("i"));
		operandoBHBox.setAlignment(Pos.CENTER);

		HBox resultadoHBox = new HBox(5, resultadoRealText, new Label("+"), resultadoImaginarioText, new Label("i"));
		resultadoHBox.setAlignment(Pos.CENTER);

		VBox contenedorVBox = new VBox(5, operandoAHBox, operandoBHBox, separator, resultadoHBox);
		contenedorVBox.setAlignment(Pos.CENTER);

		HBox root = new HBox(5, operecionesVBox, contenedorVBox);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();

		operandoAText.textProperty().bindBidirectional(primerNumeroComplejo.realProperty(),
				new NumberStringConverter());
		operandoBText.textProperty().bindBidirectional(primerNumeroComplejo.imaginarioProperty(),
				new NumberStringConverter());
		operandoCText.textProperty().bindBidirectional(segundoNumeroComplejo.realProperty(),
				new NumberStringConverter());
		operandoDText.textProperty().bindBidirectional(segundoNumeroComplejo.imaginarioProperty(),
				new NumberStringConverter());

		resultadoRealText.textProperty().bindBidirectional(resultadoNumeroComplejo.realProperty(),
				new NumberStringConverter());
		resultadoImaginarioText.textProperty().bindBidirectional(resultadoNumeroComplejo.imaginarioProperty(),
				new NumberStringConverter());
		operacionCombo.bind(operacionesCombo.getSelectionModel().selectedItemProperty());

		operacionCombo.addListener((o, ov, nv) -> OnOperacionCambia(nv));

		operacionesCombo.getSelectionModel().selectFirst();
		OnOperacionCambia(operacionesCombo.getSelectionModel().selectedItemProperty().getValue());

	}

	private void OnOperacionCambia(String nv) {
		try {
			switch (nv) {
			case "+":
				resultadoNumeroComplejo.realProperty()
						.bind(primerNumeroComplejo.realProperty().add(segundoNumeroComplejo.realProperty()));
				resultadoNumeroComplejo.imaginarioProperty().bind(
						primerNumeroComplejo.imaginarioProperty().add(segundoNumeroComplejo.imaginarioProperty()));
				break;
			case "-":
				resultadoNumeroComplejo.realProperty()
						.bind(primerNumeroComplejo.realProperty().subtract(segundoNumeroComplejo.realProperty()));
				resultadoNumeroComplejo.imaginarioProperty().bind(
						primerNumeroComplejo.imaginarioProperty().subtract(segundoNumeroComplejo.imaginarioProperty()));
				break;
			case "*":
				resultadoNumeroComplejo.realProperty()
						.bind((primerNumeroComplejo.realProperty().multiply(segundoNumeroComplejo.realProperty())
								.subtract((primerNumeroComplejo.imaginarioProperty()
										.multiply(segundoNumeroComplejo.imaginarioProperty())))));
				resultadoNumeroComplejo.imaginarioProperty()
						.bind((primerNumeroComplejo.realProperty().multiply(segundoNumeroComplejo.imaginarioProperty())
								.add((primerNumeroComplejo.imaginarioProperty()
										.multiply(segundoNumeroComplejo.realProperty())))));
				break;
			case "/":
				resultadoNumeroComplejo.realProperty()
						.bind((primerNumeroComplejo.realProperty().multiply(segundoNumeroComplejo.realProperty()))
								.add((primerNumeroComplejo.imaginarioProperty()
										.multiply(segundoNumeroComplejo.imaginarioProperty())))
								.divide(((segundoNumeroComplejo.realProperty()
										.multiply(segundoNumeroComplejo.realProperty()))
												.add((segundoNumeroComplejo.imaginarioProperty()
														.multiply(segundoNumeroComplejo.imaginarioProperty())))))

						);
				resultadoNumeroComplejo.imaginarioProperty()
						.bind((primerNumeroComplejo.imaginarioProperty().multiply(segundoNumeroComplejo.realProperty()))
								.subtract((primerNumeroComplejo.realProperty()
										.multiply(segundoNumeroComplejo.imaginarioProperty())))
								.divide(((segundoNumeroComplejo.realProperty()
										.multiply(segundoNumeroComplejo.realProperty()))
												.add((segundoNumeroComplejo.imaginarioProperty()
														.multiply(segundoNumeroComplejo.imaginarioProperty())))))

						);
				break;
			}
		} catch (RuntimeException ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Calculadora Compleja");
			alert.setHeaderText("ERROR");
			alert.setContentText("El numero introducido no es valido.");
			alert.showAndWait();
			// para limpiar los campos de resultado despues del error
			resultadoRealText.setText("");
			resultadoImaginarioText.setText("");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
