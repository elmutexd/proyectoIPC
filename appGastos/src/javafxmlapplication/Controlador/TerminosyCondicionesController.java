/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication.Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author coozy
 */
public class TerminosyCondicionesController implements Initializable {

    @FXML
    private Label termsLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String termsText = "Términos y Condiciones de Uso de MyMoney\n" +
"\n" +
"1. Introducción\n" +
"\n" +
"Bienvenido a MyMoney, una aplicación diseñada para ayudarte a gestionar y controlar tus gastos personales. Al utilizar nuestra aplicación, aceptas cumplir y estar sujeto a los siguientes términos y condiciones. Si no estás de acuerdo con estos términos, no utilices nuestra aplicación.\n" +
"\n" +
"2. Uso de la Aplicación\n" +
"\n" +
"2.1. Registro y Cuenta: Para utilizar MyMoney, debes crear una cuenta proporcionando información precisa y completa. Eres responsable de mantener la confidencialidad de tu cuenta y contraseña, y de todas las actividades que ocurran bajo tu cuenta.\n" +
"\n" +
"2.2. Edad Mínima: Debes tener al menos 18 años de edad para utilizar MyMoney. Si eres menor de 18 años, no debes utilizar la aplicación.\n" +
"\n" +
"3. Privacidad y Seguridad\n" +
"\n" +
"3.1. Datos Personales: Nos comprometemos a proteger tu privacidad. Consulta nuestra [Política de Privacidad](link a la política de privacidad) para obtener más información sobre cómo recopilamos, utilizamos y protegemos tu información personal.\n" +
"\n" +
"3.2. Seguridad: Implementamos medidas de seguridad razonables para proteger tu información, pero no podemos garantizar la seguridad absoluta de tus datos.\n" +
"\n" +
"4. Uso Aceptable\n" +
"\n" +
"4.1. Prohibiciones: No debes utilizar MyMoney para ningún propósito ilegal o no autorizado. Esto incluye, pero no se limita a, la distribución de malware, el acoso, el fraude, y la violación de los derechos de propiedad intelectual de terceros.\n" +
"\n" +
"4.2. Contenido del Usuario: Eres responsable de cualquier contenido que subas, publiques o transmitas a través de MyMoney. No debes publicar contenido que sea ofensivo, difamatorio, o que infrinja los derechos de terceros.\n" +
"\n" +
"5. Propiedad Intelectual\n" +
"\n" +
"5.1. Derechos de Autor: Todos los contenidos de MyMoney, incluidos textos, gráficos, logotipos, y software, son propiedad de MyMoney o sus licenciantes y están protegidos por las leyes de derechos de autor y otras leyes de propiedad intelectual.\n" +
"\n" +
"5.2. Marcas Registradas: MyMoney y su logotipo son marcas registradas de MyMoney. No puedes utilizar estas marcas sin nuestro consentimiento previo por escrito.\n" +
"\n" +
"6. Limitación de Responsabilidad\n" +
"\n" +
"6.1. Sin Garantías: MyMoney se proporciona \"tal cual\" y \"según disponibilidad\". No garantizamos que la aplicación esté libre de errores, segura o ininterrumpida.\n" +
"\n" +
"6.2. Daños y Perjuicios: En la máxima medida permitida por la ley, MyMoney no será responsable de ningún daño indirecto, incidental, especial, consecuente o punitivo, o de cualquier pérdida de ingresos, ganancias, datos o reputación que surja de tu uso de la aplicación.\n" +
"\n" +
"7. Modificaciones de los Términos\n" +
"\n" +
"Nos reservamos el derecho de modificar estos términos y condiciones en cualquier momento. Te notificaremos sobre cualquier cambio publicando los términos actualizados en la aplicación. Tu uso continuado de MyMoney después de la publicación de los cambios constituye tu aceptación de los términos modificados.\n" +
"\n" +
"8. Terminación\n" +
"\n" +
"Podemos suspender o terminar tu acceso a MyMoney en cualquier momento, sin previo aviso, por cualquier motivo, incluyendo, pero no limitado a, tu incumplimiento de estos términos.\n" +
"\n" +
"9. Ley Aplicable y Jurisdicción\n" +
"\n" +
"Estos términos se regirán e interpretarán de acuerdo con las leyes del país en el que resides. Cualquier disputa que surja en relación con estos términos estará sujeta a la jurisdicción exclusiva de los tribunales de ese país.";
        termsLabel.setText(termsText);
    }

    @FXML
    private void handleAccept() {
        // Cierra la ventana de términos y condiciones
        Stage stage = (Stage) termsLabel.getScene().getWindow();
        stage.close();
    }

    
}