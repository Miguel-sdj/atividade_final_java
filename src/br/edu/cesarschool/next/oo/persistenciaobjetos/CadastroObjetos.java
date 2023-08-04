// Source code is decompiled from a .class file using FernFlower decompiler.
package br.edu.cesarschool.next.oo.persistenciaobjetos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class CadastroObjetos {
   private static final String FILE_SEP = System.getProperty("file.separator");
   private static final String FILE_EXT = ".dat";
   private static final String CUR_DIR = ".";

   public CadastroObjetos() {
   }

   public CadastroObjetos(Class tipo) {
   }

public void incluir(Serializable objeto, String chave) {
      FileOutputStream fos = null;
      ObjectOutputStream oos = null;

      try {
         File arquivo = this.criarObterArquivo(objeto.getClass(), chave);
         if (arquivo.exists()) {
            throw new RuntimeException("Arquivo " + arquivo.getName() + " j\u00e1 existe!");
         }

         fos = new FileOutputStream(arquivo);
         oos = new ObjectOutputStream(fos);
         oos.writeObject(objeto);
      } catch (Exception var9) {
         throw new RuntimeException(var9.getMessage());
      } finally {
         this.close((OutputStream)oos);
         this.close((OutputStream)fos);
      }

   }

   public void alterar(Serializable objeto, String chave) {
      this.excluir(objeto.getClass(), chave);
      this.incluir(objeto, chave);
   }

   public void excluir(Class<?> tipo, String chave) {
      File arquivo = this.criarObterArquivo(tipo, chave);
      if (!arquivo.exists() || !arquivo.delete()) {
         throw new RuntimeException("Arquivo " + arquivo.getName() + " n\u00e3o existe ou n\u00e3o p\u00f4de ser apagado!");
      }
   }

   public Serializable buscar(Class<?> tipo, String chave) {
      File arquivo = this.criarObterArquivo(tipo, chave);
      return this.ler(arquivo);
   }

   public Serializable[] buscarTodos(Class tipo) {
      String caminhoDiretorio = this.obterCaminhoDiretorio(tipo);
      File diretorio = new File(caminhoDiretorio);
      File[] arquivos = diretorio.listFiles();
      if (arquivos != null && arquivos.length != 0) {
         Serializable[] rets = new Serializable[arquivos.length];

         for(int i = 0; i < arquivos.length; ++i) {
            rets[i] = this.ler(arquivos[i]);
         }

         return rets;
      } else {
         return new Serializable[0];
      }
   }

   private Serializable ler(File arquivo) {
      FileInputStream fis = null;
      ObjectInputStream ois = null;

      try {
         if (arquivo.exists()) {
            fis = new FileInputStream(arquivo);
            ois = new ObjectInputStream(fis);
            Serializable var6 = (Serializable)ois.readObject();
            return var6;
         }
      } catch (Exception var9) {
         throw new RuntimeException(var9.getMessage());
      } finally {
         this.close((InputStream)ois);
         this.close((InputStream)fis);
      }

      return null;
   }

   private void close(InputStream is) {
      try {
         is.close();
      } catch (Exception var3) {
      }

   }

   private void close(OutputStream os) {
      try {
         os.close();
      } catch (Exception var3) {
      }

   }

   private File criarObterArquivo(Class<?> tipo, String chave) {
      String caminhoDiretorio = this.obterCaminhoDiretorio(tipo);
      File diretorio = new File(caminhoDiretorio);
      if (!diretorio.exists() && !diretorio.mkdir()) {
         throw new RuntimeException("N\u00e3o foi poss\u00edvel criar o diret\u00f3rio " + diretorio.getName() + " no sistema de arquivos!");
      } else {
         return new File(caminhoDiretorio + FILE_SEP + chave + ".dat");
      }
   }

   private String obterCaminhoDiretorio(Class<?> tipo) {
      return "." + FILE_SEP + tipo.getSimpleName();
   }
 
}
