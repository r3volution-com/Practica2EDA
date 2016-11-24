import java.util.Stack;

public class Exploracion {
    public static boolean isNumeric(String str){
        try {
            double d = Double.parseDouble(str);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static int main(String args[]){
        if (args.length == 3){
            if (isNumeric(args[1]) && isNumeric(args[2])) {
                Plano mapa = new Plano(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
                mapa.leePlano(args[0]);
                Stack<exPath> recorridos = new Stack<>();
                recorridos.add(new exPath());
            }
        }
        return 0;
    }
}
