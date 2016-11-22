//DNI 77842527 GONZALEZ ALVARADO, MARIO
public class Casilla {
    private char tipo;
    private Coordenadas coor;
    private String nombre;
    private int numpuerta;
    private char puerta;
    public Casilla(char a) {
        tipo = a;
        coor = null;
    }
    public boolean setCoordenadas(int g, int n, Plano m){
        if (m == null) return false;
        if (coor != null) return false;
        if (g >= 0 && g < m.getSize().getFila() && n >= 0 && n < m.getSize().getColumna()){
            coor = new Coordenadas(g,n);
            if (tipo == 'c') m.setComieno();
            return true;
        }
        return false;
    }
    public void setNombre(String n){
        if (n == null) return;
        if (tipo == 'd' || tipo == 'c') nombre = n;
    }
    public void setPuerta(int y, char p){
        if (tipo != 'p') return;
        numpuerta=y;
        puerta=p;
    }
    public boolean esComienzo(){
        if (tipo == 'c') return true;
        else return false;
    }
    public boolean esDestino(){
        if (tipo == 'd') return true;
        else return false;
    }
    public boolean esObstaculo(){
        if (tipo == 'o') return true;
        else return false;
    }
    public boolean esLibre(){
        if (tipo == 'l') return true;
        else return false;
    }
    public boolean esPuerta(){
        if (tipo == 'p') return true;
        else return false;
    }
    public Coordenadas getCoordenadas(){
        return coor;
    }
    public String getNombre(){
        if (tipo == 'd' || tipo == 'c') return nombre;
        else return null;
    }
    public int getNumero(){
        if (tipo == 'p') return numpuerta;
        else return -1;
    }
    public char getPropiedad(){
        if (tipo == 'p') return puerta;
        else return 'F';
    }
    public char getTipo(){
        return tipo;
    }
    public void escribeInfo(){
        String cadena = "";
        if (tipo == 'p') cadena += numpuerta+":";
        else if (tipo == 'd' || tipo == 'c') cadena += nombre+":";
        else if (tipo == 'l') cadena += "libre:";
        else if (tipo == 'o') cadena += "obstaculo:";
        cadena += "("+coor.getFila()+","+coor.getColumna()+")";
        if (tipo == 'p') cadena += ":"+puerta;
        System.out.println(cadena);
    }
    public boolean equals(Casilla c){
        if (c!= null && c.getTipo() == this.getTipo()
        && ((c.getCoordenadas() != null && c.getCoordenadas().equals(coor)) || (c.getCoordenadas() == null && this.getCoordenadas() == null))
        && ((c.getNombre() != null && c.getNombre().equals(this.getNombre())) || c.getNombre() == null && this.getNombre() == null)
        && c.getNumero() == this.getNumero() && c.getPropiedad() == this.getPropiedad())
            return true;
        else return false;
    }
}