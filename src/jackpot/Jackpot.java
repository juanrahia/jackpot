
package jackpot;

import java.text.NumberFormat;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Jackpot {
    //Variables
    double saldo = 0;
    int[] combinacion = new int[3];
    double deposito = 1000;
    
    
    //constantes de moneda
    final int CINCUENTA = 0;
    final int UNO = 1;
    final int DOS = 2;
    
    //Formateador de moneda
    NumberFormat formateador = NumberFormat.getCurrencyInstance();
    
    //Incrementar saldo
    public void incrementarSaldo(int i) {
        switch (i) {
            case 0:
                saldo += 0.50;
                deposito += 0.50;
                break;
            case 1:
                saldo += 1;
                deposito += 1;
                break;
            case 2:
                saldo += 2;
                deposito += 2;
                break;       
        }
        System.out.println(formateador.format(deposito));
    }
    
    //Obtener el saldo
    public double getSaldo(){
        return saldo;
    }

    //Establecer saldo
    public void setSaldo(JLabel jLabelSaldo){
    jLabelSaldo.setText(formateador.format(saldo));
    }

    //Obtener combinacion
    public int[] getCombinacion() {
        Random generador = new Random();
        for(int i=0; i<3 ; i++){
            combinacion[i] = generador.nextInt(5);
        }
        return combinacion;
    }
    //Establecer combinacion
    public void setCombinacion(JLabel jLabel1, JLabel jLabel2, JLabel jLabel3){
        if (saldo >= 0.50) {
            this.getCombinacion();
            this.combinacion = combinacion;
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + combinacion[0] + ".png")));
            jLabel2.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + combinacion[1]+ ".png")));
            jLabel3.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + combinacion[2] + ".png")));
            saldo = saldo - 0.50;
        }
    }
    
    //Comprobar premio
    public void comprobarPremio(JLabel jLabelPremio){
        double premio = 0;
        if(combinacion[0]==0){
            if(combinacion[1]==0){
                saldo += 5;
                premio += 5;
            }else{
                saldo += 1;
                premio += 1;
            }
        }
        if((combinacion[0]==1 && combinacion[1]==1 && combinacion[2]==1) || (combinacion[0]==1 && combinacion[1]==1 && combinacion[2]==4)){
            saldo += 10;
            premio += 10;
        }
        if((combinacion[0]==2 && combinacion[1]==2 && combinacion[2]==2)|| (combinacion[0]==2 && combinacion[1]==2 && combinacion[2]==4)){
            saldo += 15;
            premio += 15;
        }
        if((combinacion[0]==3 && combinacion[1]==3 && combinacion[2]==3)|| (combinacion[0]==3 && combinacion[1]==3 && combinacion[2]==4)){
            saldo += 20;
            premio += 20;
        }
        if ((combinacion[0] == 4 && combinacion[1] == 4 && combinacion[2] == 4) ) {
            saldo +=100;
            premio +=100;
        }
        jLabelPremio.setText(formateador.format(premio));

    }
    
    //Retirar saldo
    public void cobrarPremio(JFrame parent){
        if(deposito-saldo<0){
            JOptionPane.showMessageDialog(parent, "Deposito vacio. Pase por caja", "Atencion", JOptionPane.INFORMATION_MESSAGE );;
        }else{
            deposito = deposito - saldo;
            saldo = 0;
        }
        
    }
    
}
