package cl.duocuc.ecomarket.util.encriptacion;

public class EncriptadorChetado implements Encriptador<String> {
    private final EncriptadorVigenere primero = new EncriptadorVigenere("PACHINGA");
    private final EncriptadorBinario segundo = new EncriptadorBinario();


    @Override
    public String encriptar(String valor) {
        valor = primero.encriptar(valor);
        valor = segundo.encriptar(valor);
        return valor;
    }

    @Override
    public String desencriptar(String valor) {
        valor = segundo.desencriptar(valor);
        valor = primero.desencriptar(valor);
        return valor;
    }

    @Override
    public boolean encriptado(String valor) {
        return segundo.encriptado(valor) && primero.encriptado(segundo.desencriptar(valor));
    }

    public static void main (String[] arga){
        String s = "juanitoComeKaKa";
        EncriptadorChetado enc = new EncriptadorChetado();
        String encriptado = enc.encriptar(s);
        String desencriptado = enc.desencriptar(encriptado);
        System.out.println("Encriptado: " +  encriptado ) ;
        System.out.println("Encriptado ? " + enc.encriptado(encriptado));
        System.out.println("Desencriptado: " + desencriptado  );
        System.out.println("Encriptado ? " + enc.encriptado(desencriptado));
    }

}
