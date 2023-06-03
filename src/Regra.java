import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Regra extends JFrame{
    // TextField Array Definition
    CustomTextField[] JTextFields = new CustomTextField[4];
    // Resposta
    Label resposta = new Label();

    Regra(){
        setTitle("Regra de três");
        getContentPane().setBackground(Color.black);
        setLocationRelativeTo(null); // Center Program Location In Screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set GUI to Exit on Close

        setLayout(new GridLayout(3, 2));

        // TextFields
        for (int i = 0; i < 4; i++){
            JTextFields[i] = new CustomTextField();
            JTextFields[i].setHorizontalAlignment(JTextField.CENTER);
            int finalI = i;
            JTextFields[i].addChangeListener(JTextFields[i], e -> Teste(JTextFields[finalI].getText(), finalI));
        }

        // Make Resposta readonly
        //resposta.setEditable(false);

        // Stuff
        add(JTextFields[0]);  add(JTextFields[1]);
        add(JTextFields[2]);  add(JTextFields[3]);
        Label Resp = new Label("Resposta:");
        Resp.setForeground(Color.white);
        resposta.setForeground(Color.white);
        add(Resp);    add(resposta);
    }

    public void Teste(String teste, int CustomTextField){
        double beforeX = -99999;
        int beforeXPos = -1;
        DecimalFormat dc = new DecimalFormat("0.00");

        for (int i = 0; i < JTextFields.length; i++){
            if(JTextFields[i].getText().toLowerCase().contains("x")){
                if(JTextFields[i].getText().toLowerCase().toCharArray()[0] == 'x'){
                    beforeX = 1;
                    beforeXPos = i;
                    break;
                }

                try {
                    int count = 0;
                    for (int j = 0; j < JTextFields[i].getText().length(); j++) {
                        if (JTextFields[i].getText().toLowerCase().toCharArray()[j] == 'x') {
                            beforeX = Double.parseDouble(JTextFields[i].getText().substring(0, j));
                            beforeXPos = i;
                            //System.out.println("O valor antes de X é: " + beforeX );
                        }
                    }
                }catch (NumberFormatException e){
                    System.out.println("Ocorreu um erro, verifique se não digitou nenhuma letra nos campos ou se colocou múltiplos X");
                }
            }
        }

        //System.out.println("BeforeXPos: " + beforeXPos);
        //System.out.println("BeforeX: " + beforeX);
        if(beforeXPos == 0 && beforeX != -99999 && checkFields()){
            double result = (Double.parseDouble(JTextFields[2].getText()) * Double.parseDouble(JTextFields[1].getText())) / (beforeX * Double.parseDouble(JTextFields[3].getText()));
            String formattedText = dc.format(result);
            resposta.setText(formattedText);
            //System.out.println(formattedText);
        }
        if(beforeXPos == 1 && beforeX != -99999 && checkFields()){
            double result = (Double.parseDouble(JTextFields[0].getText()) * Double.parseDouble(JTextFields[3].getText())) / (beforeX * Double.parseDouble(JTextFields[2].getText()));
            String formattedText = dc.format(result);
            resposta.setText(formattedText);
        }
        if(beforeXPos == 2 && beforeX != -99999 && checkFields()){
            double result = (Double.parseDouble(JTextFields[0].getText()) * Double.parseDouble(JTextFields[3].getText())) / (beforeX * Double.parseDouble(JTextFields[1].getText()));
            String formattedText = dc.format(result);
            resposta.setText(formattedText);
        }
        if(beforeXPos == 3 && beforeX != -99999 && checkFields()){
            double result = (Double.parseDouble(JTextFields[2].getText()) * Double.parseDouble(JTextFields[1].getText())) / (beforeX * Double.parseDouble(JTextFields[0].getText()));
            String formattedText = dc.format(result);
            resposta.setText(formattedText);
        }

        //System.out.println(teste);
        //System.out.println("Tabela: " + CustomTextField);
    }

    public boolean checkFields(){
        for(int i = 0; i < 4; i++){
            if(JTextFields[i].getText().isBlank()){
                return false;
            }
        }

        return true;
    }

}