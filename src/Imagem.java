
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Imagem {

    double jobX;
    double jobY;

    double x;
    double y;

    double originalX;
    double originalY;


    BufferedImage bufferedImage;

    public Imagem( double x, double y, BufferedImage bufferedImage,double jobX, double jobY) {
        this.jobX = jobX;
        this.jobY = jobY;
        this.x = x;
        this.y = y;
        originalX=x;
        originalY=y;
        this.bufferedImage = bufferedImage;

    }

    void brokeInPieces(List<Imagem> imgs){
        double w=bufferedImage.getWidth()/2;
        double h=bufferedImage.getHeight()/2;
        BufferedImage buff=this.bufferedImage;
        //1 topo esquerda
        Imagem topoEsquerda = new Imagem(x,y,buff.getSubimage(0,0,(int) w, (int)h),(int)w,0);

        //2 topoDireita
        Imagem topoDireita = new Imagem(x+w,y,buff.getSubimage((int)w,0,(int)w,(int)h),0,h);

        //3 baixo esquerda

        Imagem baixoEsquerda = new Imagem(x,y+w,buff.getSubimage(0,(int)h,(int)w,(int)h),0,-h);

        //4 baixo direita

        Imagem baixoDireira = new Imagem(x+w,y+h,buff.getSubimage((int)w,(int)h,(int)w,(int)h),-w,0);

        imgs.add(topoEsquerda);
        imgs.add(topoDireita);
        imgs.add(baixoEsquerda);
        imgs.add(baixoDireira);

    }

    public void paint(Graphics g){
        g.drawImage(this.bufferedImage,(int)x,(int)y,null);
    }

   void moveStep(int steps){
        x+=jobX/steps;
        y+=jobY/steps;
    }

    Thread move(int amountX, int amountY, int times){
          return   new Thread(()->{
                double deltaX= amountX/times;
                double deltaY= amountY/times;

                double originalX=x;
                double originalY=y;

                for(int i=0;i<=times;i++){
                    x=(int)(originalX+(i*deltaX));
                    y=(int)(originalY+(i*deltaY));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
           });
    }

   public void fix(){
        x=originalX+jobX;
        y=originalY+jobY;
    }
}
