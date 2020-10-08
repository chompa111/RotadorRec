import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {

    BufferedImage buffer;
    Graphics buffGraphics;

    List<Imagem> imagemList= new ArrayList<>();

    public Frame(){
        buffer=new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
        buffGraphics=buffer.getGraphics();

        setSize(512,512);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }).start();
    }

    void add(Imagem img){
        this.imagemList.add(img);
    }

    void remove(Imagem img){
        this.imagemList.remove(img);
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(255,255,255));
        g.fillRect(0,0,512,512);
        for(int i=0;i<imagemList.size();i++){
            imagemList.get(i).paint(g);
        }
    }

    @Override
    public void paint(Graphics g) {

        paintComponent(buffGraphics);
        g.drawImage(buffer,0,0,this);
    }
}
