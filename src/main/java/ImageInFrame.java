import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageInFrame extends JFrame implements ActionListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Dimension d =Toolkit.getDefaultToolkit().getScreenSize();
    String path = "http://puu.sh/";
    String end = ".jpg";
    int pre = (int)Math.round(Math.random()*49)+1;
    URL url = new URL("http://puu.sh/50xEt.jpg");
    BufferedImage image = ImageIO.read(url);
    JLabel label = new JLabel(new ImageIcon(image));
    static String[] allpossible = new String[140608];
    JPanel pan;
    JTextField field;
    public ImageInFrame()throws Exception{
		/*
		 * JFrame.
		 */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            System.out.println("Setting the L&F failed so I cannot reproduce the bug.");
        }


        getContentPane().setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);//Sets if its visible.


		/*

		 * JButton.
		 */
        pan = new JPanel();
        field = new JTextField();
        URL url = new URL(path);
        BufferedImage image = ImageIO.read(url);
        JButton startButton = new JButton("<");//The JButton name.
        JButton start2Button = new JButton(">");//The JButton name.
        add(pan);
        add(startButton);//Add the button to the JFrame.
        add(start2Button);//Add the button to the JFrame.


        startButton.setBounds(d.width/2-140,d.height-150,140,40);
        start2Button.setBounds(d.width/2,d.height-150,140,40);
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void keyPressed(KeyEvent e){
                System.out.println("e");

                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    left();
                }else
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    right();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        pan.setBounds(0,0,d.width,d.height);
        pan.add(label);
        add(field);
        field.setText("..............................");
        field.setBounds(0,0,200,100);

        startButton.addActionListener(this);//Reads the action.
        start2Button.addActionListener(this);
        pack();

        setSize(d.width,
                d.height-50);
    }

    /*
     *The main method.
     */
    static char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    static int counter = 0;
    public static void main(String[] args)throws Exception{

        for (int i = 0; i < ch.length*2; i++)
        {

            for (int b = 0; b < ch.length*2; b++)
            {

                for (int c = 0; c < ch.length*2; c++)
                {
                    if(i >=26){
                        allpossible[counter] = Character.toString(Character.toUpperCase(ch[i % 26]));
                    }   else{
                        allpossible[counter] = Character.toString(ch[i%26]);
                    }

                    if(b >=26){
                        allpossible[counter] += Character.toString(Character.toUpperCase(ch[b % 26]));
                    }   else{
                        allpossible[counter] += Character.toString(ch[b%26]);
                    }


                    if(c>=26){
                        allpossible[counter] += Character.toString(Character.toUpperCase(ch[c % 26]));
                    }   else {
                        allpossible[counter] += Character.toString(ch[c]);}
                    counter++;
                }

            }


        }
        counter = 100;
        new ImageInFrame();//Reads method main()
    }

    /*
     *What the button does.
     */
    public void left(){
        do{
        if(counter >= 0){
        counter--;
            refresh();
        }else{
            counter = 140607;
            pre--;
            refresh();
        }
        }while(refresh() == 1);
    }
    public void right(){
        do{
        if(counter <= 140607){

            counter++;
        }else{
            counter = 0;
            pre++;
        }
        }while(refresh() == 1);
    }
    public int refresh(){

        try{
            field.setText(path+pre + allpossible[counter] + end +" : " + counter);
            pan.repaint();
            pan.remove(label);
            url = new URL(path + pre + allpossible[counter] + end);
            BufferedImage image = ImageIO.read(url);
            label = new JLabel(new ImageIcon(image));
            pan.add(label);

            revalidate();
            validate();
            return 0;
        }catch(Exception e){
            return 1;
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("<")){

            left();
            System.out.println(path+pre + allpossible[counter] + end +" : " + counter);

        }else if(e.getActionCommand().equals(">")){
            right();

            System.out.println(path+pre+allpossible[counter] + " : " + counter);
        }
    }

}