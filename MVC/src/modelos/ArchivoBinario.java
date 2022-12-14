package modelos;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Luis Correa */
public class ArchivoBinario {

  public ArrayList<String> leerArchivo(File archivo) {
    ArrayList<String> lineas = new ArrayList<>();
    try {
      FileInputStream fis = new FileInputStream(archivo);
      DataInputStream dis = new DataInputStream(fis);
      while (dis.available() > 0) {
        lineas.add(dis.readUTF());
      }
      fis.close();
      dis.close();
    } catch (IOException e) {
      System.out.println("Error = " + e);
      System.exit(-1);
    }
    return lineas;
  }
  
  public void escribirArchivo(ArrayList<String> lineas, File archivo) {
    try {
      FileOutputStream fous = new FileOutputStream(archivo);
      BufferedOutputStream bos = new BufferedOutputStream(fous);
      DataOutputStream dos = new DataOutputStream(bos);
      for (String linea : lineas) {
        dos.writeBytes(linea);
      }
      fous.close();
      bos.close();
      dos.close();
    } catch (IOException e) {
      System.out.println("Error = " + e);
      System.exit(-1);
    }
  }
  
}
