package teht1;

import java.awt.*;

public class SimpleKyltti extends Kyltti {

    public SimpleKyltti(String teksti){
        super(new Dimension(teksti.length()+8, 1), teksti);
    }

    @Override
    public void tulosta(){
        System.out.println("--{ " + super.teksti + " }--");
    }
}
