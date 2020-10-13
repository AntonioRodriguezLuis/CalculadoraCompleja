package dad.javafx.calculadora.compleja;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculadoraComplejaController extends Application {
	
		private TextField operandoAText, operandoBText, operandoCText, operandoDText, resultadoRealText,resultadoImaginarioText;
		private ComboBox<String> operacionesCombo;
		private final String[] OPERADORES = { "+", "-", "*", "/" };
		
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
			primaryStage.show();	}

	public static void main(String[] args) {
		launch(args);
	}

}
