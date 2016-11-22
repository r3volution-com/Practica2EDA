//DNI 77842527 GONZALEZ ALVARADO, MARIO

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Plano {
    private Casilla[][] pl;
    private Coordenadas dimension;
    private boolean comienzo;
    public Plano(int i, int j){
        if (i < 0) i = 3;
        if (j < 0) j = 3;
        dimension = new Coordenadas(i, j);
        pl = new Casilla[i][j];
        comienzo = false;
    }
    public boolean setCasilla(Casilla x){
        if (x == null) return false;
        if (x.getCoordenadas() == null) return false;
        if (x.getCoordenadas().getFila() < 0 || x.getCoordenadas().getFila() >= dimension.getFila()) return false;
        if (x.getCoordenadas().getColumna() < 0 || x.getCoordenadas().getColumna() >= dimension.getColumna()) return false;
        if (pl[x.getCoordenadas().getFila()] == null) return false;
        if (pl[x.getCoordenadas().getFila()][x.getCoordenadas().getColumna()] == null) {
            pl[x.getCoordenadas().getFila()][x.getCoordenadas().getColumna()] = x;
            return true;
        }
        return false;
    }
    public void leePlano(String f){
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            linea = br.readLine();
            if (!linea.equals("<MAPA>")) return;
            int cont_i = 0, cont_j=0;
            linea = br.readLine();
            for (int i = 0; linea != null && (!linea.equals("<DESTINO>") && !linea.equals("<COMIENZO>") && !linea.equals("<PUERTA>")) ; i++) {
                if (i == 0) {
                    String[] elems = linea.split("");
                    cont_j = elems.length;
                }
                cont_i++;
                linea = br.readLine();
            }
            if (cont_i > dimension.getFila() || cont_j > dimension.getColumna()){
                Casilla[][] aux = redimensionaCasillas(cont_i, cont_j);
                if (aux!= null){
                    pl = aux;
                }
            }
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String[] elems = null;
            linea = br.readLine();
            linea = br.readLine();
            boolean continua = true;
            for (int i = 0; i < dimension.getFila() && continua; i++) {
                if (linea != null) elems = linea.split("");
                for (int j = 0; j < dimension.getColumna(); j++){
                    if (elems != null) {
                        if (j < elems.length) {
                            if (pl[i][j] == null && (elems[j].charAt(0) == 'l' || elems[j].charAt(0) == 'o')) pl[i][j] = new Casilla(elems[j].charAt(0));
                        } else pl[i][j] = new Casilla('l');
                    } else {
                        if (pl[i][j] == null) pl[i][j] = new Casilla('l');
                    }
                }
                linea = br.readLine();
                elems = null;
                if (linea != null && (linea.equals("<DESTINO>") || linea.equals("<PUERTA>") || linea.equals("<COMIENZO>"))) continua = false;
            }
            String nombre = null;
            String[] elements = null;
            int x = -1, y = -1;
            while (linea != null) {
                if (linea.equals("<DESTINO>")){
                    linea = br.readLine();
                    if (compruebaetiqueta(linea)) continue;
                    if (linea != null)
                        nombre = linea;
                    linea = br.readLine();
                    if (compruebaetiqueta(linea)) continue;
                    if (linea != null) {
                        elements = linea.split(" ");
                        if (elements.length == 2) {
                            x = Integer.parseInt(elements[0]);
                            y = Integer.parseInt(elements[1]);
                        }
                    }
                    if ((x >= 0 && x < dimension.getFila()) && (y >= 0 && y < dimension.getColumna()) && nombre != null && elements != null) {
                        if (pl[x][y].getTipo() == 'l') {
                            pl[x][y] = new Casilla('d');
                            pl[x][y].setCoordenadas(x, y, this);
                            pl[x][y].setNombre(nombre);
                        }
                    }
                }
                if (linea.equals("<COMIENZO>")){
                    linea = br.readLine();
                    if (compruebaetiqueta(linea)) continue;
                    if (linea != null)
                        nombre = linea;
                    linea = br.readLine();
                    if (compruebaetiqueta(linea)) continue;
                    if (linea != null) {
                        elements = linea.split(" ");
                        if (elements.length == 2) {
                            x = Integer.parseInt(elements[0]);
                            y = Integer.parseInt(elements[1]);
                        }
                    }
                    if ((x >= 0 && x < dimension.getFila()) && (y >= 0 && y < dimension.getColumna()) && nombre != null && elements != null) {
                        if (pl[x][y].getTipo() == 'l' && !comienzo) {
                            pl[x][y] = new Casilla('c');
                            pl[x][y].setCoordenadas(x, y, this);
                            pl[x][y].setNombre(nombre);
                        }
                    }
                }
                else if (linea.equals("<PUERTA>")){
                    int numero = 0;
                    char puerta = 0;
                    linea = br.readLine();
                    if (compruebaetiqueta(linea)) continue;
                    if (linea != null) {
                        if (isNumeric(linea))
                            numero = Integer.parseInt(linea);
                        else continue;
                    }
                    linea = br.readLine();
                    if (compruebaetiqueta(linea)) continue;
                    if (linea != null) {
                        elements = linea.split(" ");
                        if (elements.length == 2) {
                            x = Integer.parseInt(elements[0]);
                            y = Integer.parseInt(elements[1]);
                        }
                    }
                    linea = br.readLine();
                    if (compruebaetiqueta(linea)) continue;
                    if (linea != null)
                        puerta = linea.charAt(0);
                    if ((x >= 0 && x < dimension.getFila()) && (y >= 0 && y < dimension.getColumna()) && numero != 0 && puerta != 0) {
                        if (pl[x][y].getTipo() == 'l' && numero > 0 && puerta != 'F') {
                            pl[x][y] = new Casilla('p');
                            pl[x][y].setCoordenadas(x, y, this);
                            pl[x][y].setPuerta(numero, puerta);
                        }
                    }
                }
                linea = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Casilla[][] redimensionaCasillas(int x, int y){
        if ((x > dimension.getFila()) || (y > dimension.getColumna())) {
            if (x < dimension.getFila()) x = dimension.getFila();
            if (y < dimension.getColumna()) y = dimension.getColumna();
            dimension.setFila(x);
            dimension.setColumna(y);
            Casilla[][] aux = new Casilla[x][y];
            for (int i = 0; i<pl.length; i++){
                if (pl[i] != null) {
                    for (int j = 0; j < pl[i].length; j++) {
                        aux[i][j] = pl[i][j];
                    }
                }
            }
            return aux;
        } else return null;
    }

    public char consultaCasilla(Coordenadas x) {
        if (x!= null && pl != null){
            if (x.getFila() >= 0 && x.getFila() < dimension.getFila()
            && x.getColumna() >= 0 && x.getColumna() < dimension.getColumna()
            && pl[x.getFila()] != null && pl[x.getFila()][x.getColumna()] != null)
                return pl[x.getFila()][x.getColumna()].getTipo();
        }
        return 'F';
    }
    public char[] consultaVecinas(Coordenadas x){
        int i = x.getFila(), j = x.getColumna();
        int cont = 0;
        int size = 0;
        if ((i == 0 && j == 0) || (i==0 && j == dimension.getColumna()-1) || (i == dimension.getFila()-1 && j == 0)
                || (i == dimension.getFila()-1 && j == dimension.getColumna()-1)) size = 3;
        else if (i == 0 || j == 0 || i == dimension.getFila() -1|| j == dimension.getColumna()-1) size = 5;
        else size = 8;
        char[] vecinas = new char[size];
        for (int m=i-1;m<=i+1;m++){
            if (m >= 0 && m <= pl.length - 1) {
                for (int n = j - 1; n <= j + 1; n++) {
                    if (n >= 0 && n <= pl[m].length-1){
                        if (m != i || n != j) {
                            if (pl[m][n] != null){
                                vecinas[cont] = pl[m][n].getTipo();
                            } else vecinas[cont] = 'F';
                            cont++;
                        }
                    }
                }
            }
        }
        char[] vecinos = new char[size];
        if (size == 8){
            vecinos[0] = vecinas[1];
            vecinos[1] = vecinas[2];
            vecinos[2] = vecinas[4];
            vecinos[3] = vecinas[7];
            vecinos[4] = vecinas[6];
            vecinos[5] = vecinas[5];
            vecinos[6] = vecinas[3];
            vecinos[7] = vecinas[0];
            vecinas = vecinos;
        }else if (size == 5){
            vecinos[0] = vecinas[1];
            vecinos[1] = vecinas[2];
            vecinos[2] = vecinas[3];
            vecinos[3] = vecinas[4];
            vecinos[4] = vecinas[0];
            vecinas = vecinos;
        } else if (size == 3){
            vecinos[0] = vecinas[1];
            vecinos[1] = vecinas[2];
            vecinos[2] = vecinas[0];
            vecinas = vecinos;
        }
        return vecinas;
    }
    public void muestraPlano(){
        if (pl == null) return;
        for (int i = 0; i<pl.length; i++){
            if (pl[i] != null) {
                for (int j = 0; j<pl[i].length; j++){
                    if (pl[i][j] != null) System.out.print(pl[i][j].getTipo());
                    else System.out.print("F");
                }
                System.out.println();
            }
        }
    }
    public boolean equals (Plano c){
        boolean iguales = true;
        if (c != null && pl != null
                && c.getSize().getFila() == dimension.getFila()
                && c.getSize().getColumna() == dimension.getColumna()) {
            for (int i = 0; i < dimension.getFila(); i++){
                if (pl[i] != null) {
                    for (int j = 0; j < dimension.getColumna(); j++) {
                        if(!pl[i][j].equals(c.getPlano()[i][j])) iguales = false;
                    }
                }
            }
        } else iguales = false;
        return iguales;
    }
    public boolean compruebaetiqueta(String linea){
        if (linea.equals("<DESTINO>") || linea.equals("<COMIENZO>") || linea.equals("<PUERTA>")) return true;
        else return false;
    }
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    public void setComieno(){
        comienzo = true;
    }
    public Coordenadas getSize(){
        return dimension;
    }
    public Casilla[][] getPlano() { return pl; }
}
