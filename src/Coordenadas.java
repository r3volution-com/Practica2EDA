//DNI 77842527 GONZALEZ ALVARADO, MARIO
public class Coordenadas {
    private int fila, columna;
    public Coordenadas (int i, int j){
        fila = i;
        columna = j;
    }
    public void setFila(int i){
        fila = i;
    }
    public void setColumna(int j){
        columna = j;
    }
    public int getFila(){
        return fila;
    }
    public int getColumna(){
        return columna;
    }
    public boolean equals(Coordenadas c){
        if (c.getFila() == fila && c.getColumna() == columna) return true;
        else return false;
    }
    public String toString(){
        return "("+fila+","+columna+")";
    }
}
