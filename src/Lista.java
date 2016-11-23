import java.util.ArrayList;

public class Lista {

    private NodoL pr;
    private NodoL ul;
    public Lista(){
        pr = null;
        ul = null;
    }
    public boolean esVacia(){
        if (pr == null) return true;
        else return false;
    }

    public void insertaCabeza(ArrayList<Coordenadas> v){
        if (v != null) {
            NodoL primero;
            if (pr == null) {
                primero = new NodoL(v);
                pr = primero;
                ul = primero;
            }
            else {
                primero = new NodoL(v, pr);
                pr = primero;
            }
        }
    }
    public void insertaCola(ArrayList<Coordenadas> v){
        if (v != null) {
            NodoL ultimo = new NodoL(v);
            if (pr == null) {
                pr = ultimo;
                ul = ultimo;
            } else {
                NodoL aux = pr;
                while (aux.getNext() != null){
                    aux = aux.getNext();
                }
                aux.cambiaNext(ultimo);
                ul = ultimo;
            }
        }
    }
    public void inserta(ArrayList<Coordenadas> v, int i) throws IndexOutOfBoundsException{
        NodoL nuevo = new NodoL(v);
        NodoL aux = pr;
        if ((aux != null && v != null) && i >= 0) {
            if (i == 0){
                insertaCabeza(v);
            } else {
                boolean valido = true;
                int j;
                for (j = 0; j < i - 1 && valido; j++) {
                    if (aux.getNext() != null) aux = aux.getNext();
                    else valido = false;
                }
                if (valido) {
                    if (aux.getNext() == null){
                        //aux.cambiaNext(nuevo);
                        throw new IndexOutOfBoundsException("" + i);
                        //Esto no estoy seguro, tutoria alicia
                    } else {
                        nuevo.cambiaNext(aux.getNext());
                        aux.cambiaNext(nuevo);
                    }
                } else throw new IndexOutOfBoundsException("" + i);
            }
        } else throw new IndexOutOfBoundsException(""+i);
    }
    public boolean borraCabeza(){
        if (pr != null){
            if (pr.getNext() != null){
                pr = pr.getNext();
            } else {
                if (pr == ul) ul = null;
                pr = null;
            }
            return true;
        } else return false;
    }
    public boolean borraCola(){
        /*quita el ultimo objeto (a la cola) de la lista, devolviendo true si la operacion se ha podido realizar.*/
        NodoL aux = pr;
        if (pr == null) return false;
        if (ul == pr) {
            pr = null;
            ul = null;
            return true;
        }
        if (aux == null) return false;
        while (aux.getNext() != null) {
            if (aux.getNext() == ul) {
                aux.cambiaNext(null);
                ul = aux;
                return true;
            }
            else aux = aux.getNext();
        }
        return false;
    }
    public void borra(int i) throws IndexOutOfBoundsException{
        NodoL aux = pr;
        if (aux != null && i >= 0) {
            if (i == 0) borraCabeza();
            else {
                boolean valido = true;
                for (int j = 0; j < i-1 && valido; j++){
                    if (aux.getNext() != null) aux = aux.getNext();
                    else valido = false;
                }
                if (valido){
                    if (aux.getNext() != null) aux.cambiaNext(aux.getNext().getNext());
                    else throw new IndexOutOfBoundsException(""+i);
                } else throw new IndexOutOfBoundsException(""+i);
            }
        } else throw new IndexOutOfBoundsException(""+i);
    }
    public boolean borra(ArrayList<Coordenadas> v){
        NodoL aux = pr;
        if (aux != null && v != null) {
            if (aux.comparaCamino(v)){
                borraCabeza();
                return true;
            } else {
                while (aux.getNext() != null) {
                    if (aux.getNext().comparaCamino(v)){
                        aux.cambiaNext(aux.getNext().getNext());
                        return true;
                    }
                    aux = aux.getNext();
                }
            }
        }
        return false;
    }
    public void escribeLista(){
        NodoL aux = pr;
        int j;
        for (j = 1; aux != null; j++) {
            System.out.print("camino "+j+": ");
            aux.escribeCamino();
            System.out.println();
            aux = aux.getNext();
        }
    }
    public int enLista(ArrayList<Coordenadas> v){
        /*nos dice si v esta en la lista o no. Si esta, devuelve su posicion, en otro caso devuelve -1.*/
        NodoL aux = pr;
        if (aux != null && v != null) {
            for (int j = 0; aux != null && aux.getCamino() != null; j++){
                if (!aux.comparaCamino(v)) {
                    if (aux.getNext() != null) aux = aux.getNext();
                    else return -1;
                } else {
                    return j;
                }
            }
        }
        return -1;
    }
    public ArrayList<Coordenadas> getCamino(int pos) throws IndexOutOfBoundsException{
        NodoL aux = pr;
        if (aux != null && pos >= 0) {
            boolean valido = true;
            int j;
            for (j = 0; j < pos && valido; j++) {
                if (aux.getNext() != null) aux = aux.getNext();
                else valido = false;
            }
            if (valido) {
                return aux.getCamino();
            } else throw new IndexOutOfBoundsException(""+j);
        } else throw new IndexOutOfBoundsException("0");
    }

    private class NodoL {
        private ArrayList<Coordenadas> camino;
        private NodoL next;

        public NodoL( ArrayList<Coordenadas> v){
            camino = v;
            next = null;
        }
        public NodoL( ArrayList<Coordenadas> v, NodoL n){
            camino = v;
            next = n;
        }
        public void cambiaNext(NodoL n){
            next = n;
        }
        public NodoL getNext(){
            return next;
        }
        public ArrayList<Coordenadas> getCamino() {
            return camino;
        }
        public void escribeCamino(){
            for (Coordenadas c:camino) {
                System.out.print("("+c.getFila()+","+c.getColumna()+")");
            }
        }
        public boolean comparaCamino(ArrayList<Coordenadas> v){
            if (v.size() == camino.size()){
                for (int i = 0; i< v.size(); i++){
                    if ((v.get(i).getFila() != camino.get(i).getFila()) || (v.get(i).getColumna() != camino.get(i).getColumna())){
                        return false;
                    }
                }
            } else return false;
            return true;
        }
    }
}
