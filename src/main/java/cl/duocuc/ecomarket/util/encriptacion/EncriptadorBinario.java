package cl.duocuc.ecomarket.util.encriptacion;


public class EncriptadorBinario implements Encriptador<String>{

    @Override
    public String encriptar(String valor) {
        StringBuilder resultado = new StringBuilder();
        for (char c : valor.toCharArray()) {
            String binario = Integer.toBinaryString(c);
            while (binario.length() < 8) {
                binario = "0" + binario;
            }
            resultado.append(binario);
        }
        return resultado.toString();
    }

    @Override
    public String desencriptar(String valor) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < valor.length(); i += 8) {
            String byteStr = valor.substring(i, Math.min(i + 8, valor.length()));
            char c = (char) Integer.parseInt(byteStr, 2);
            resultado.append(c);
        }
        return resultado.toString();
    }

    @Override
    public boolean encriptado(String valor) {
        if (valor == null || valor.isEmpty() || valor.length() % 8 != 0) {
            return false;
        }
        for (char c : valor.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args){
        EncriptadorBinario encriptadorBinario = new EncriptadorBinario();
        String s = "HolaMundo!#-_./|";

        String textoEncriptado = encriptadorBinario.encriptar(s);
        String textoDesencriptado = encriptadorBinario.desencriptar(textoEncriptado);

        System.out.println("Encriptado: "  + textoEncriptado);
        System.out.println("Encriptado ? " + encriptadorBinario.encriptado(textoEncriptado));
        System.out.println("Desencriptado: " + textoDesencriptado);
        System.out.println("Encriptado ? " + encriptadorBinario.encriptado(textoDesencriptado));

    }
}
