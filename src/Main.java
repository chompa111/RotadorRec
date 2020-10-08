import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private  final String imagePath ="";
    Frame frame= new Frame();

    void rotate() throws IOException {
        BufferedImage img= ImageIO.read(new File(imagePath));
        Imagem imagem= new Imagem(0,0,img,0,0);
        List<Imagem> currentImgs= new ArrayList<>();
        currentImgs.add(imagem);
        frame.add(imagem);
        int steps=80;
        for (int i=0;i<8;i++){
          List<Imagem> newImages= brokeInPieces(currentImgs);
          add(newImages);
          performMove(newImages,steps);
          remove(currentImgs);
          currentImgs=newImages;
          if(steps>40){
              steps/=2;
          }

        }

    }

    List<Imagem> brokeInPieces(List<Imagem>source){
       List<Imagem> newList= new ArrayList<>();
       for (Imagem i : source){
           i.brokeInPieces(newList);
       }
       return newList;
    }

    void performMove(List<Imagem>source, int steps){
        for(int i=0;i<steps;i++){
            for (Imagem img : source){
               img.moveStep(steps);
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Imagem img : source){
            img.fix();
        }

    }


    void add(List<Imagem> news){
        for (Imagem img : news){
            frame.add(img);
        }
    }

    void remove(List<Imagem> olds){
        for (Imagem img : olds){
            frame.remove(img);
        }
    }


    public static void main(String[] args) throws IOException {

        new Main().rotate();
    }
}
