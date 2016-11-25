import java.util.ArrayList;
import java.util.Stack;

public class Exploracion {
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static void main(String args[]){
        if (args.length == 3){
            if (isNumeric(args[1]) && isNumeric(args[2])) {
                int modo = 1;
                char tipo = 0;
                Stack<exPath> recorridos = new Stack<>();
                ArrayList<Coordenadas> lista = new ArrayList<>();
                Plano mapa = new Plano(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
                mapa.leePlano(args[0]);
                Coordenadas comienzo = mapa.getCasillaComienzo().getCoordenadas();
                if (comienzo != null) {
                    lista.add(comienzo);
                    recorridos.add(new exPath(comienzo.getFila(), comienzo.getColumna(), lista));
                    exPath actual = null;
                    Coordenadas coordActual = null;
                    boolean encontrada = false;
                    while (!encontrada) {
                        while (recorridos.size() > 0) {
                            actual = recorridos.pop();
                            if (actual != null) {
                                coordActual = actual.getCoordenadas();
                                tipo = mapa.consultaCasilla(coordActual);
                                if (tipo == 'c' || tipo == 'l') {
                                    mapa.setVisitada(coordActual.getFila(), coordActual.getColumna());
                                    recorridos.addAll(mapa.obtenerVecinas(1, coordActual.getFila(), coordActual.getColumna()));
                                } else if (tipo == 'd') {

                                } else if (tipo == 'p') {

                                    //si se pilla salida, encontrada es true
                                }
                            }
                        }
                        if (!encontrada){
                            //reiniciar visitadas

                        }
                    }
                    //Mostrar lista
                }
            }
        }
    }
}
